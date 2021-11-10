package co.kr.soptandroidseminar.api

import co.kr.soptandroidseminar.data.RequestSignInData
import co.kr.soptandroidseminar.data.ResponseSignInData
import co.kr.soptandroidseminar.data.RequestSignUpData
import co.kr.soptandroidseminar.data.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SeminarService {
    @POST("user/signup")
    fun postSignUp(
        @Body body: RequestSignUpData
    ) : Call<ResponseSignUpData>

    @POST("user/login")
    fun postSingIn(
        @Body body: RequestSignInData
    ) : Call<ResponseSignInData>
}