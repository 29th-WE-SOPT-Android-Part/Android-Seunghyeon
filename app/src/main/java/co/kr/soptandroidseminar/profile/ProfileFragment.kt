package co.kr.soptandroidseminar.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        initTransaction()
        initProfilePicture()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initProfilePicture() {
        Glide.with(requireContext())
            .load("https://avatars.githubusercontent.com/u/81508084?v=4")
            .circleCrop()
            .into(binding.imgProfilePicture)
    }

    private fun initTransaction() {
        val followerFragment = FollowerFragment()
        val repoFragment = RepoFragment()
        var position = FOLLOWER_FRAGMENT

        childFragmentManager.beginTransaction()
            .add(R.id.fcv_list, followerFragment)
            .commit()
        binding.btnListFollower.isSelected = true
        binding.btnListRepo.isSelected = false

        binding.btnListFollower.setOnClickListener {
            if(position == REPO_FRAGMENT) {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fcv_list, followerFragment)
                    .commit()
                position = FOLLOWER_FRAGMENT

                binding.btnListFollower.isSelected = true
                binding.btnListRepo.isSelected = false
            }
        }

        binding.btnListRepo.setOnClickListener {
            if(position == FOLLOWER_FRAGMENT) {
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