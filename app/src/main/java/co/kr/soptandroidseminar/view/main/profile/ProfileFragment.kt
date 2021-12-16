package co.kr.soptandroidseminar.view.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.databinding.FragmentProfileBinding
import co.kr.soptandroidseminar.util.enqueueUtil
import co.kr.soptandroidseminar.view.main.profile.setting.SettingActivity
import com.bumptech.glide.Glide

class ProfileFragment(private val username: String) : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        getServerData()
        startSetting()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getServerData() {
        val call = ApiService.githubService.getUserInfo(username)
        call.enqueueUtil(
            onSuccess = {
                it.avatar_url?.let { initProfilePicture(it) }
                binding.tvProfileName.text = it.name
                binding.tvProfileId.text = it.login
                it.bio?.let { binding.tvProfileIntro.text = it }
                initTransaction()
            }
        )
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

    private fun startSetting() {
        binding.imgProfileSetting.setOnClickListener {
            val intent = Intent(requireContext(), SettingActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val FOLLOWER_FRAGMENT = 0
        const val REPO_FRAGMENT = 1
    }
}