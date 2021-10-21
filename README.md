# Android-Seunghyeon

![github_한승현_ver1-7](https://user-images.githubusercontent.com/70698151/135753583-cb6d8b51-421f-48e2-9284-0bb3b70bb6d7.png)

#### 1차 세미나 과제

<hr>

#### 필수과제

* **GIF**

  <img src="https://user-images.githubusercontent.com/81508084/136231628-7ac1b492-31db-4810-bce0-e6e518165e19.gif" width="30%" height="30%"/>


* **SignInActivity**

  * 로그인 버튼을 눌렀을 때, ID, PW가 모두 입력되어있을 시 HomeActivity로 이동하고 그렇지 않으면 Toast 출력

    ```kotlin
        private fun clickLogin() {
            if(!binding.etSigninId.text.isNullOrBlank() && !binding.etSigninPw.text.isNullOrBlank()) {
                Toast.makeText(this, "안녕하세요 ${binding.etSigninId.text}!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ID/PW를 확인해주세요!", Toast.LENGTH_SHORT).show()
            }
        }
    ```

    * ID, PW 입력 여부는 isNullOrBlank() 메서드를 활용하여 체크하였음
    * 각 조건문 분기마다 Toast 출력하였음

  * 비밀번호 EditText는 입력 내용이 가려져 있어야하고, 모든 EditText는 미리보기가 있어야 함

    ```xml
            <EditText
                android:id="@+id/et_signin_pw"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:hint="@string/signin_hint_pw"
                android:inputType="textPassword"
                android:maxLines="1"
                android:ellipsize="end"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/tv_signin_pw" />
    ```

    * 모든 EditText마다 hint 속성을 활용하여 미리보기를 추가하였고, 비밀번호 EditText의 경우 inputType을 textPassword로 설정하여 입력 내용을 가렸음

  * 회원가입 버튼을 누를 시 SignUpActivity로 이동

    ```kotlin
        private fun clickSignUp() {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    ```

* **SignUpActivity**

  * 회원가입 버튼을 눌렀을 때, 이름, ID, PW가 모두 입력되어있을 시 SignInActivity로 다시 돌아가고 그렇지 않으면 Toast 출력

    ```kotlin
        private fun clickSignUp() {
            if(!binding.etSignupName.text.isNullOrBlank() && !binding.etSignupId.text.isNullOrBlank() && !binding.etSignupPw.text.isNullOrBlank()) {
                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "이름/ID/PW를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    ```

    * 이름, ID, PW 입력 여부는 isNullOrBlank() 메서드를 활용하여 체크하였음
    * 각 조건문 분기마다 Toast 출력하였음
    * finish() 메서드를 활용하여 이전 스택의 Activity로 복귀하였음

  * 비밀번호 EditText는 입력 내용이 가려져 있어야하고, 모든 EditText는 미리보기가 있어야 함

    ```xml
            <EditText
                android:id="@+id/et_signup_pw"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:ellipsize="end"
                android:hint="@string/signin_hint_pw"
                android:inputType="textPassword"
                android:maxLines="1"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/tv_signup_pw" />
    ```

    * 모든 EditText마다 hint 속성을 활용하여 미리보기를 추가하였고, 비밀번호 EditText의 경우 inputType을 textPassword로 설정하여 입력 내용을 가렸음
