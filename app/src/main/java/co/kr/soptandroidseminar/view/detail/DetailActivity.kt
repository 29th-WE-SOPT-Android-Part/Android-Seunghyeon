package co.kr.soptandroidseminar.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.soptandroidseminar.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profile = intent.getIntExtra("profile", 0)
        val name = intent.getStringExtra("name")
        val detailInfo = intent.getStringExtra("detailInfo")

        binding.imgDetailProfile.setImageResource(profile)
        binding.tvDetailName.text = name
        binding.tvDetailInfo.text = detailInfo
    }
}