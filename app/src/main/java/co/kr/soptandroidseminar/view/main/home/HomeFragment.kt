package co.kr.soptandroidseminar.view.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.soptandroidseminar.databinding.FragmentHomeBinding
import co.kr.soptandroidseminar.view.adapter.HomeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPagerAdapter: HomeViewPagerAdapter
    private val homeFollowerFragment: HomeFollowerFragment by lazy { HomeFollowerFragment() }
    private val homeFollowingFragment: HomeFollowingFragment by lazy { HomeFollowingFragment() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initViewPager()
        initTabLayout()

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initViewPager() {
        val fragmentList = listOf(homeFollowingFragment, homeFollowerFragment)
        viewPagerAdapter = HomeViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)
        binding.vpHome.adapter = viewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tabHome, binding.vpHome) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

    companion object {
        const val FOLLOWING = 0
        const val FOLLOWER = 1
    }
}