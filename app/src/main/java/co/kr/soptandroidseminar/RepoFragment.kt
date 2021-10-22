package co.kr.soptandroidseminar

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.kr.soptandroidseminar.databinding.FragmentRepoBinding

class RepoFragment : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)

        val adapter = RepoAdapter()
        if (adapter.itemList.isEmpty()) {
            adapter.itemList.addAll(
                listOf(
                    RepoData("레포1", "레포1입니다."),
                    RepoData("레포2", "레포2입니다."),
                    RepoData("레포3", "레포3입니다."),
                    RepoData("레포4", "레포4입니다."),
                    RepoData("레포5", "레포5입니다."),
                )
            )
        }
        binding.rcvRepo.adapter = adapter
        binding.rcvRepo.layoutManager = LinearLayoutManager(context)
        binding.rcvRepo.addItemDecoration(MyDecoration(5f, 10f, Color.GRAY))
        adapter.notifyItemRangeInserted(0, 5)

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
        itemTouchHelper.attachToRecyclerView(binding.rcvRepo)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}