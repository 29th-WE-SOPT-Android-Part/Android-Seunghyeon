package co.kr.soptandroidseminar

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
                    FollowerData(R.mipmap.ic_launcher, "사람1", "사람1입니다.", "사람1 설명 어쩌구저쩌구"),
                    FollowerData(R.mipmap.ic_launcher, "사람2", "사람2입니다.", "사람2 설명 어쩌구저쩌구"),
                    FollowerData(R.mipmap.ic_launcher, "사람3", "사람3입니다.", "사람3 설명 어쩌구저쩌구"),
                    FollowerData(R.mipmap.ic_launcher, "사람4", "사람4입니다.", "사람4 설명 어쩌구저쩌구"),
                    FollowerData(R.mipmap.ic_launcher, "사람5", "사람5입니다.", "사람5 설명 어쩌구저쩌구"),
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
                    adapter.itemList[fromPos] = adapter.itemList[toPos]
                    adapter.itemList[toPos] = temp
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