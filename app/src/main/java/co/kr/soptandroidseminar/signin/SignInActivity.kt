package co.kr.soptandroidseminar.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.kr.soptandroidseminar.HomeActivity
import co.kr.soptandroidseminar.MainActivity.MainActivity
import co.kr.soptandroidseminar.R
import co.kr.soptandroidseminar.signup.SignUpActivity
import co.kr.soptandroidseminar.databinding.ActivitySignInBinding

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
            Toast.makeText(this, "안녕하세요 ${binding.etSigninId.text}!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "ID/PW를 확인해주세요!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}