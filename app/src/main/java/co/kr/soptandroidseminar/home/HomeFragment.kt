package co.kr.soptandroidseminar.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.databinding.FragmentHomeBinding

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

        return binding.root
    }

    private fun initViewPager() {
        val fragmentList = listOf(homeFollowingFragment, homeFollowerFragment)
        viewPagerAdapter = HomeViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)
        binding.vpHome.adapter = viewPagerAdapter
    }

    companion object {
        const val FOLLOWING = 0
        const val FOLLOWER = 1
    }
}