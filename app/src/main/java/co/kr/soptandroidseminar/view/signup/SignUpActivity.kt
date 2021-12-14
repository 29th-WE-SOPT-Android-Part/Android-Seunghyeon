package co.kr.soptandroidseminar.view.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.data.signup.RequestSignUpData
import co.kr.soptandroidseminar.databinding.ActivitySignUpBinding
import co.kr.soptandroidseminar.util.enqueueUtil
import co.kr.soptandroidseminar.util.simpleToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignupOk.setOnClickListener {
            clickSignUp()
        }
    }

    private fun clickSignUp() {
        if(!binding.etSignupName.text.isNullOrBlank() && !binding.etSignupId.text.isNullOrBlank() && !binding.etSignupPw.text.isNullOrBlank()) {
            val requestSignUpData = RequestSignUpData(
                binding.etSignupId.text.toString(),
                binding.etSignupName.text.toString(),
                binding.etSignupPw.text.toString()
            )

            val call = ApiService.seminarService.postSignUp(requestSignUpData)
            call.enqueueUtil(
                onSuccess = {
                    simpleToast(it.message)
                    finish()
                },
                onError = {
                    simpleToast("회원가입 실패")
                }
            )
        } else {
            simpleToast("이름/ID/PW를 확인해주세요")
        }
    }
}