package co.kr.soptandroidseminar.view.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.kr.soptandroidseminar.view.main.MainActivity
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.data.local.SharedPreference
import co.kr.soptandroidseminar.data.signin.RequestSignInData
import co.kr.soptandroidseminar.data.signin.ResponseSignInData
import co.kr.soptandroidseminar.view.signup.SignUpActivity
import co.kr.soptandroidseminar.databinding.ActivitySignInBinding
import co.kr.soptandroidseminar.util.simpleToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            val call: Call<ResponseSignInData> = ApiService.seminarService.postSingIn(requestSignInData)

            call.enqueue(object: Callback<ResponseSignInData> {
                override fun onResponse(
                    call: Call<ResponseSignInData>,
                    response: Response<ResponseSignInData>
                ) {
                    if(response.isSuccessful) {
                        val data = response.body()?.data
                        simpleToast("안녕하세요 ${data?.name}!")
                        val intent = Intent(this@SignInActivity, MainActivity::class.java)
                        SharedPreference.setAutoLogin(this@SignInActivity, true, "hansh0101", data?.email.toString())
                        startActivity(intent)
                    } else {
                        Log.d("server connect : SignIn", "error")
                        Log.d("server connect : SignIn", "$response.errorBody()")
                        Log.d("server connect : SignIn", response.message())
                        Log.d("server connect : SignIn", "${response.code()}")
                        simpleToast("로그인 실패")
                        SharedPreference.setAutoLogin(this@SignInActivity, true, "hansh0101", "hansh0101@naver.com")
                        val intent = Intent(this@SignInActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
                    simpleToast("로그인 실패")
                }
            })
        } else {
            simpleToast("ID/PW를 확인해주세요!")
        }
    }

    private fun clickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun isAutoLogin() {
        if(SharedPreference.getAutoLogin(this)) {
            simpleToast("자동 로그인")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}