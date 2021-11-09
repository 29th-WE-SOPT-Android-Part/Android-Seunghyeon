package co.kr.soptandroidseminar.api

import co.kr.soptandroidseminar.profile.ResponseFollowerData
import co.kr.soptandroidseminar.profile.ResponseRepoData
import co.kr.soptandroidseminar.profile.ResponseUserInfoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubService {
    @Headers("authorization:token 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")
    @GET("/users/{username}")
    fun getUserInfo(
        @Path("username") username: String
    ): Call<ResponseUserInfoData>

    @Headers("authorization:token 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")
    @GET("/users/{username}/followers")
    fun getFollowerList(
        @Path("username") username: String
    ): Call<List<ResponseFollowerData>>

    @Headers("authorization:token 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")
    @GET("/users/{username}/repos")
    fun getRepoList(
        @Path("username") username: String
    ): Call<List<ResponseRepoData>>
}