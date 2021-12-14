package co.kr.soptandroidseminar.view.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.soptandroidseminar.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}