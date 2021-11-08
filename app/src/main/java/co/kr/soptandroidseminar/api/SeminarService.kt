package co.kr.soptandroidseminar.api

import co.kr.soptandroidseminar.signin.RequestSignInData
import co.kr.soptandroidseminar.signin.ResponseSignInData
import co.kr.soptandroidseminar.signup.RequestSignUpData
import co.kr.soptandroidseminar.signup.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SeminarService {
    @Headers("Content-Type:application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body body: RequestSignUpData
    ) : Call<ResponseSignUpData>

    @Headers("Content-Type:application/json")
    @POST("user/login")
    fun postSingIn(
        @Body body: RequestSignInData
    ) : Call<ResponseSignInData>
}