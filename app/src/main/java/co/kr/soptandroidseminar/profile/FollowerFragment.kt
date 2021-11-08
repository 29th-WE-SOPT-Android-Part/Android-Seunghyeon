package co.kr.soptandroidseminar.profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import co.kr.soptandroidseminar.DetailActivity
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        FollowerAdapter() {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("profile", it.image)
            intent.putExtra("name", it.name)
            intent.putExtra("detailInfo", it.detailInfo)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)

        if (adapter.itemList.isEmpty()) {
            adapter.itemList.addAll(
                listOf(
                    FollowerData("https://avatars.githubusercontent.com/u/53166299?v=4", "한진희", "차로 Android Developer", "차로 Android 리드"),
                    FollowerData("https://avatars.githubusercontent.com/u/71322949?v=4", "곽호택", "차로 Android Developer", "차로 마스코택"),
                    FollowerData("https://avatars.githubusercontent.com/u/63635886?v=4", "오예원", "차로 Server Developer", "차로 서버 원맨쇼 주인공"),
                    FollowerData("https://avatars.githubusercontent.com/u/59547116?v=4", "장혜령", "차로 iOS Developer", "차로 iOS 리드"),
                    FollowerData("https://avatars.githubusercontent.com/u/73978827?v=4", "박익범", "차로 iOS Developer", "차로 iOS 공식잼민"),
                    FollowerData("https://avatars.githubusercontent.com/u/70083982?v=4", "이지원", "차로 iOS Developer", "차로 낭만 차기 대권주자"),
                    FollowerData("https://avatars.githubusercontent.com/u/46644241?v=4", "최인정", "차로 iOS Developer", "차로 vs 당근 어디야 ! 하나만 해 !"),
                    FollowerData("https://avatars.githubusercontent.com/u/63224278?v=4", "황지은", "차로 iOS Developer", "차로 서버 -> iOS 이적 성사")
                )
            )
        }
        binding.rcvFollower.adapter = adapter
        binding.rcvFollower.layoutManager = GridLayoutManager(context, 2)
        binding.rcvFollower.addItemDecoration(MyDecoration(5f, 10f, Color.GRAY))
        adapter.notifyDataSetChanged()

        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val fromPos = viewHolder.adapterPosition
                    val toPos = target.adapterPosition
                    val temp = adapter.itemList[fromPos]
                    if(fromPos < toPos) {
                        for(i in fromPos until toPos) {
                            adapter.itemList[i] = adapter.itemList[i+1]
                        }
                        adapter.itemList[toPos] = temp
                    } else if(fromPos > toPos) {
                        for(i in toPos+1..fromPos) {
                            adapter.itemList[i] = adapter.itemList[i-1]
                        }
                        adapter.itemList[toPos] = temp
                    }
                    adapter.notifyItemMoved(fromPos, toPos)

                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val delPos = viewHolder.adapterPosition
                    adapter.itemList.removeAt(delPos)
                    adapter.notifyItemRemoved(delPos)
                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rcvFollower)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}