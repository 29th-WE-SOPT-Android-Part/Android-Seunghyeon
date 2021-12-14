package co.kr.soptandroidseminar.view.main.profile

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.data.main.profile.RepoData
import co.kr.soptandroidseminar.databinding.FragmentRepoBinding
import co.kr.soptandroidseminar.util.enqueueUtil
import co.kr.soptandroidseminar.view.adapter.RepoAdapter

class RepoFragment(private val username: String) : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { RepoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        if(adapter.itemList.isEmpty()) {
            getRepoList()
        }
        initRecyclerView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRepoList() {
        val call = ApiService.githubService.getRepoList(username)
        call.enqueueUtil(
            onSuccess = {
                it.forEach {
                    adapter.itemList.add(
                        RepoData(
                            it.name, it.description
                        )
                    )
                    adapter.notifyItemInserted(adapter.itemList.size - 1)
                }
            },
            onError = null
        )
    }

    private fun initRecyclerView() {
        binding.rcvRepo.adapter = adapter
        binding.rcvRepo.layoutManager = LinearLayoutManager(context)
        binding.rcvRepo.addItemDecoration(MyDecoration(5f, 10f, Color.GRAY))
        setRecyclerViewItemTouch()
    }

    private fun setRecyclerViewItemTouch() {
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
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
        itemTouchHelper.attachToRecyclerView(binding.rcvRepo)
    }
}