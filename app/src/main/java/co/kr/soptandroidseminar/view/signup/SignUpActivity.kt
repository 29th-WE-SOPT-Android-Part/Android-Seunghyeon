package co.kr.soptandroidseminar.view.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.data.signup.RequestSignUpData
import co.kr.soptandroidseminar.data.signup.ResponseSignUpData
import co.kr.soptandroidseminar.databinding.ActivitySignUpBinding
import co.kr.soptandroidseminar.util.simpleToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            val call: Call<ResponseSignUpData> = ApiService.seminarService.postSignUp(requestSignUpData)

            call.enqueue(object: Callback<ResponseSignUpData> {
                override fun onResponse(
                    call: Call<ResponseSignUpData>,
                    response: Response<ResponseSignUpData>
                ) {
                    if(response.isSuccessful) {
                        val data = response.body()
                        data?.message?.let { simpleToast(it) }
                    } else {
                        Log.d("server connect : SignUp", "error")
                        Log.d("server connect : SignUp", "$response.errorBody()")
                        Log.d("server connect : SignUp", response.message())
                        Log.d("server connect : SignUp", "${response.code()}")
                        simpleToast("회원가입 실패")
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                    simpleToast("회원가입 실패")
                }
            })

            finish()
        } else {
            simpleToast("이름/ID/PW를 확인해주세요")
        }
    }
}