package co.kr.soptandroidseminar.view.main.profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.kr.soptandroidseminar.view.detail.DetailActivity
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.data.main.profile.FollowerData
import co.kr.soptandroidseminar.data.main.profile.ResponseFollowerData
import co.kr.soptandroidseminar.databinding.FragmentFollowerBinding
import co.kr.soptandroidseminar.util.enqueueUtil
import co.kr.soptandroidseminar.view.adapter.FollowerAdapter

class FollowerFragment(private val username: String) : Fragment() {
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
            getFollowerList()
        }
        initRecyclerView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFollowerList() {
        val call = ApiService.githubService.getFollowerList(username)
        call.enqueueUtil(
            onSuccess = {
                if(!it.isNullOrEmpty()) {
                    getFollowerInfo(it)
                }
            },
            onError = null
        )
    }

    private fun getFollowerInfo(list: List<ResponseFollowerData>) {
        list.forEach {
            val call = ApiService.githubService.getUserInfo(it.login)
            call.enqueueUtil(
                onSuccess = {
                    adapter.itemList.add(
                        FollowerData(it.avatar_url, it.login, it.name, it.bio)
                    )
                    adapter.notifyItemInserted(adapter.itemList.size - 1)
                }
            )
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rcvFollower.adapter = adapter
        binding.rcvFollower.layoutManager = LinearLayoutManager(context)
        binding.rcvFollower.addItemDecoration(MyDecoration(1f, 1f, Color.GRAY))
        setRecyclerViewItemTouch()
    }

    private fun setRecyclerViewItemTouch() {
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
                    if (fromPos < toPos) {
                        for (i in fromPos until toPos) {
                            adapter.itemList[i] = adapter.itemList[i + 1]
                        }
                        adapter.itemList[toPos] = temp
                    } else if (fromPos > toPos) {
                        for (i in toPos + 1..fromPos) {
                            adapter.itemList[i] = adapter.itemList[i - 1]
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
    }
}