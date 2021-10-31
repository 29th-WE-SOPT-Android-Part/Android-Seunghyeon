package co.kr.soptandroidseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.soptandroidseminar.databinding.ActivityHomeBinding
import co.kr.soptandroidseminar.profile.FollowerFragment
import co.kr.soptandroidseminar.profile.RepoFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTransaction()
    }

    private fun initTransaction() {
        val followerFragment = FollowerFragment()
        val repoFragment = RepoFragment()

        supportFragmentManager.beginTransaction().add(R.id.frg_home_rcv, followerFragment).commit()

        binding.btnHomeRepo.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frg_home_rcv, repoFragment)
                .commit()
        }
        binding.btnHomeFollower.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frg_home_rcv, followerFragment)
                .commit()
        }
    }
}