package co.kr.soptandroidseminar.view.main.profile.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.kr.soptandroidseminar.data.local.SharedPreference
import co.kr.soptandroidseminar.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noAutoLogin()
        deleteLoginCache()
    }

    private fun noAutoLogin() {
        binding.tvSettingAutoLogin.setOnClickListener {
            SharedPreference.removeAutoLogin(this)
            Toast.makeText(this@SettingActivity, "자동로그인 해제", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteLoginCache() {
        binding.tvSettingDeleteCache.setOnClickListener {
            SharedPreference.clearAutoLogin(this)
            Toast.makeText(this@SettingActivity, "로그인 캐시 삭제", Toast.LENGTH_SHORT).show()
        }
    }
}