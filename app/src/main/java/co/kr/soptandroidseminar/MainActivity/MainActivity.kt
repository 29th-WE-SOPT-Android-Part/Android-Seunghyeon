package co.kr.soptandroidseminar.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.camera.CameraFragment
import co.kr.soptandroidseminar.databinding.ActivityMainBinding
import co.kr.soptandroidseminar.home.HomeFragment
import co.kr.soptandroidseminar.profile.ProfileFragment
import java.sql.RowIdLifetime

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: MainViewPagerAdapter
    private val profileFragment: ProfileFragment by lazy { ProfileFragment() }
    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val cameraFragment: CameraFragment by lazy { CameraFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            when(it.itemId) {
                R.id.menu_profile -> {
                    binding.vpMain.currentItem = PROFILE_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpMain.currentItem = HOME_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = CAMERA_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val PROFILE_FRAGMENT = 0
        const val HOME_FRAGMENT = 1
        const val CAMERA_FRAGMENT = 2
    }
}