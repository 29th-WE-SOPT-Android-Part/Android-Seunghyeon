package co.kr.soptandroidseminar.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import co.kr.soptandroidseminar.main.MainActivity
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.signup.SignUpActivity
import co.kr.soptandroidseminar.databinding.ActivitySignInBinding
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
    }

    private fun clickLogin() {
        if(!binding.etSigninId.text.isNullOrBlank() && !binding.etSigninPw.text.isNullOrBlank()) {
            val requestSignInData = RequestSignInData(
                binding.etSigninId.text.toString(),
                binding.etSigninPw.text.toString()
            )

            val call: Call<ResponseSignInData> = ApiService.service.postSingIn(requestSignInData)

            call.enqueue(object: Callback<ResponseSignInData> {
                override fun onResponse(
                    call: Call<ResponseSignInData>,
                    response: Response<ResponseSignInData>
                ) {
                    if(response.isSuccessful) {
                        val data = response.body()?.data
                        Toast.makeText(this@SignInActivity, "안녕하세요 ${data?.name}!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignInActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.d("server connect : SignIn", "error")
                        Log.d("server connect : SignIn", "$response.errorBody()")
                        Log.d("server connect : SignIn", response.message())
                        Log.d("server connect : SignIn", "${response.code()}")
                        Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
                    Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "ID/PW를 확인해주세요!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}