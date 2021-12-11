package co.kr.soptandroidseminar.view.profile

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.data.RepoData
import co.kr.soptandroidseminar.data.ResponseRepoData
import co.kr.soptandroidseminar.databinding.FragmentRepoBinding
import co.kr.soptandroidseminar.view.adapter.RepoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val call: Call<List<ResponseRepoData>> = ApiService.githubService.getRepoList(username)

        call.enqueue(object : Callback<List<ResponseRepoData>> {
            override fun onResponse(
                call: Call<List<ResponseRepoData>>,
                response: Response<List<ResponseRepoData>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.forEach {
                        adapter.itemList.add(
                            RepoData(
                                it.name,
                                it.description
                            )
                        )
                        adapter.notifyItemInserted(adapter.itemList.size - 1)
                    }
                } else {
                    Log.d("server connect : Repo Fragment", "error")
                    Log.d("server connect : Repo Fragment", "$response.errorBody()")
                    Log.d("server connect : Repo Fragment", response.message())
                    Log.d("server connect : Repo Fragment", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ResponseRepoData>>, t: Throwable) {
                Log.d("server connect : Repo Fragment", "error: ${t.message}")
            }
        })
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