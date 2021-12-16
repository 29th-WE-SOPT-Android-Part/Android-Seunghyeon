package co.kr.soptandroidseminar.view.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.soptandroidseminar.data.local.AutoLoginData
import co.kr.soptandroidseminar.databinding.ActivityOnBoardingBinding
import co.kr.soptandroidseminar.view.main.MainActivity

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isAutoLogin()
    }

    private fun isAutoLogin() {
        if(AutoLoginData.getAutoLogin(this)) {
            val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}