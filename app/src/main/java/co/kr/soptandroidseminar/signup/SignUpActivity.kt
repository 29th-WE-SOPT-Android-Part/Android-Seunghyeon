package co.kr.soptandroidseminar.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import co.kr.soptandroidseminar.api.ApiService
import co.kr.soptandroidseminar.databinding.ActivitySignUpBinding
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

            val call: Call<ResponseSignUpData> = ApiService.service.postSignUp(requestSignUpData)

            call.enqueue(object: Callback<ResponseSignUpData> {
                override fun onResponse(
                    call: Call<ResponseSignUpData>,
                    response: Response<ResponseSignUpData>
                ) {
                    if(response.isSuccessful) {
                        val data = response.body()
                        Toast.makeText(this@SignUpActivity, data?.message, Toast.LENGTH_SHORT).show()
                    } else {
                        Log.d("server connect : SignUp", "error")
                        Log.d("server connect : SignUp", "$response.errorBody()")
                        Log.d("server connect : SignUp", response.message())
                        Log.d("server connect : SignUp", "${response.code()}")
                        Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            })

            finish()
        } else {
            Toast.makeText(this, "이름/ID/PW를 확인해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}