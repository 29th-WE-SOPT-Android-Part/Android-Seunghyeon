package co.kr.soptandroidseminar.view.main.profile.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.soptandroidseminar.data.local.AutoLoginData
import co.kr.soptandroidseminar.databinding.ActivitySettingBinding
import co.kr.soptandroidseminar.util.simpleToast

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
            AutoLoginData.removeAutoLogin(this)
            simpleToast("자동로그인 해제")
        }
    }

    private fun deleteLoginCache() {
        binding.tvSettingDeleteCache.setOnClickListener {
            AutoLoginData.clearAutoLogin(this)
            simpleToast("로그인 캐시 삭제")
        }
    }
}