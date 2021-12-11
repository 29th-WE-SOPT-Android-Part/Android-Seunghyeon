package co.kr.soptandroidseminar.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.view.camera.CameraFragment
import co.kr.soptandroidseminar.databinding.ActivityMainBinding
import co.kr.soptandroidseminar.view.adapter.MainViewPagerAdapter
import co.kr.soptandroidseminar.view.home.HomeFragment
import co.kr.soptandroidseminar.view.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: MainViewPagerAdapter
    private lateinit var username: String
    private lateinit var email: String
    private val profileFragment: ProfileFragment by lazy { ProfileFragment(username) }
    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val cameraFragment: CameraFragment by lazy { CameraFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra("name")!!
        email = intent.getStringExtra("email")!!

        initViewPagerAdapter()
        initBottomNavigation()
    }

    private fun initViewPagerAdapter() {
        val fragmentList = listOf(profileFragment, homeFragment, cameraFragment)
        viewPagerAdapter = MainViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)
        binding.vpMain.adapter = viewPagerAdapter
    }

    private fun initBottomNavigation() {
        binding.vpMain.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvMain.setOnItemSelectedListener {
            binding.vpMain.currentItem = when(it.itemId) {
                R.id.menu_profile -> PROFILE_FRAGMENT
                R.id.menu_home -> HOME_FRAGMENT
                else -> CAMERA_FRAGMENT
            }
            return@setOnItemSelectedListener true
        }
    }

    private companion object {
        const val PROFILE_FRAGMENT = 0
        const val HOME_FRAGMENT = 1
        const val CAMERA_FRAGMENT = 2
    }
}