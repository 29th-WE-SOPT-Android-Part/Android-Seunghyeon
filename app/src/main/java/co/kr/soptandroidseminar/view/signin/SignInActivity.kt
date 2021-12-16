package co.kr.soptandroidseminar.view.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.soptandroidseminar.view.main.MainActivity
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.data.local.AutoLoginData
import co.kr.soptandroidseminar.data.signin.RequestSignInData
import co.kr.soptandroidseminar.view.signup.SignUpActivity
import co.kr.soptandroidseminar.databinding.ActivitySignInBinding
import co.kr.soptandroidseminar.util.enqueueUtil
import co.kr.soptandroidseminar.util.simpleToast

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignin.setBackgroundResource(R.drawable.shape_button_sign)
        binding.btnSignin.setOnClickListener {
            clickLogin()
        }

        binding.tvSigninSignup.setOnClickListener {
            clickSignUp()
        }

        isAutoLogin()
    }

    private fun clickLogin() {
        if(!binding.etSigninId.text.isNullOrBlank() && !binding.etSigninPw.text.isNullOrBlank()) {
            val requestSignInData = RequestSignInData(
                binding.etSigninId.text.toString(),
                binding.etSigninPw.text.toString()
            )

            val call = ApiService.seminarService.postSingIn(requestSignInData)
            call.enqueueUtil(
                onSuccess = {
                    simpleToast("안녕하세요 ${it.data.name}")
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    AutoLoginData.setAutoLogin(this@SignInActivity, true, "hansh0101", it.data.email)
                    startActivity(intent)
                    finish()
                },
                onError = {
                    simpleToast("로그인 실패")
                    AutoLoginData.setAutoLogin(this@SignInActivity, true, "hansh0101", "hansh0101@naver.com")
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            )
        } else {
            simpleToast("ID/PW를 확인해주세요!")
        }
    }

    private fun clickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun isAutoLogin() {
        if(AutoLoginData.getAutoLogin(this)) {
            simpleToast("자동 로그인")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}