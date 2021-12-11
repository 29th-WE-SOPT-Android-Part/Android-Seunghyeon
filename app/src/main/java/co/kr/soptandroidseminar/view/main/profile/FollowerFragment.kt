package co.kr.soptandroidseminar.view.main.profile

import android.content.Intent
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
import co.kr.soptandroidseminar.view.detail.DetailActivity
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.data.main.profile.FollowerData
import co.kr.soptandroidseminar.data.main.profile.ResponseFollowerData
import co.kr.soptandroidseminar.data.main.profile.ResponseUserInfoData
import co.kr.soptandroidseminar.databinding.FragmentFollowerBinding
import co.kr.soptandroidseminar.view.adapter.FollowerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val call: Call<List<ResponseFollowerData>> =
            ApiService.githubService.getFollowerList(username)

        call.enqueue(object : Callback<List<ResponseFollowerData>> {
            override fun onResponse(
                call: Call<List<ResponseFollowerData>>,
                response: Response<List<ResponseFollowerData>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        getFollowerInfo(data)
                    }
                } else {
                    Log.d("server connect : Follower Fragment", "error")
                    Log.d("server connect : Follower Fragment", "$response.errorBody()")
                    Log.d("server connect : Follower Fragment", response.message())
                    Log.d("server connect : Follower Fragment", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ResponseFollowerData>>, t: Throwable) {
                Log.d("server connect : Follower Fragment", "error: ${t.message}")
            }
        })
    }

    private fun getFollowerInfo(list: List<ResponseFollowerData>) {
        list.forEach {
            val call: Call<ResponseUserInfoData> = ApiService.githubService.getUserInfo(it.login)

            call.enqueue(object : Callback<ResponseUserInfoData> {
                override fun onResponse(
                    call: Call<ResponseUserInfoData>,
                    response: Response<ResponseUserInfoData>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        adapter.itemList.add(
                            FollowerData(
                                data?.avatar_url,
                                data?.login,
                                data?.name,
                                data?.bio
                            )
                        )
                        adapter.notifyItemInserted(adapter.itemList.size - 1)
                        Log.d("server connect : Follower Fragment", "success")
                        Log.d("server connect : Follower Fragment", it.login)
                    } else {
                        Log.d("server connect : Follower Fragment", "error")
                        Log.d("server connect : Follower Fragment", "$response.errorBody()")
                        Log.d("server connect : Follower Fragment", response.message())
                        Log.d("server connect : Follower Fragment", "${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ResponseUserInfoData>, t: Throwable) {
                    Log.d("server connect: FollowerFragment", "error: ${t.message}")
                }
            })
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