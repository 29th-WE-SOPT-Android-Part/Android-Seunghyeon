package co.kr.soptandroidseminar.view.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.data.ResponseUserInfoData
import co.kr.soptandroidseminar.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment(private val username: String) : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        getServerData()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getServerData() {
        val call: Call<ResponseUserInfoData> = ApiService.githubService.getUserInfo(username)

        call.enqueue(object : Callback<ResponseUserInfoData> {
            override fun onResponse(
                call: Call<ResponseUserInfoData>,
                response: Response<ResponseUserInfoData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.avatar_url?.let { initProfilePicture(it) }
                    binding.tvProfileName.text = data?.name
                    binding.tvProfileId.text = data?.login
                    data?.bio?.let { binding.tvProfileIntro.text = it }
                    initTransaction()
                } else {
                    Log.d("server connect : Profile Fragment", "error")
                    Log.d("server connect : Profile Fragment", "$response.errorBody()")
                    Log.d("server connect : Profile Fragment", response.message())
                    Log.d("server connect : Profile Fragment", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoData>, t: Throwable) {
                Log.d("server connect : Profile Fragment", "error: ${t.message}")
            }
        })
    }

    private fun initProfilePicture(imgUrl: String) {
        Glide.with(requireContext())
            .load(imgUrl)
            .circleCrop()
            .into(binding.imgProfilePicture)
    }

    private fun initTransaction() {
        val followerFragment = FollowerFragment(username)
        val repoFragment = RepoFragment(username)
        var position = FOLLOWER_FRAGMENT

        childFragmentManager.beginTransaction()
            .add(R.id.fcv_list, followerFragment)
            .commit()
        binding.btnListFollower.isSelected = true
        binding.btnListRepo.isSelected = false

        binding.btnListFollower.setOnClickListener {
            if (position == REPO_FRAGMENT) {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fcv_list, followerFragment)
                    .commit()
                position = FOLLOWER_FRAGMENT

                binding.btnListFollower.isSelected = true
                binding.btnListRepo.isSelected = false
            }
        }

        binding.btnListRepo.setOnClickListener {
            if (position == FOLLOWER_FRAGMENT) {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fcv_list, repoFragment)
                    .commit()
                position = REPO_FRAGMENT

                binding.btnListFollower.isSelected = false
                binding.btnListRepo.isSelected = true
            }
        }
    }

    companion object {
        const val FOLLOWER_FRAGMENT = 0
        const val REPO_FRAGMENT = 1
    }
}