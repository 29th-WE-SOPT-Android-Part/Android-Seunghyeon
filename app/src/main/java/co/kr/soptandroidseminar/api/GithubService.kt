package co.kr.soptandroidseminar.api

import co.kr.soptandroidseminar.data.ResponseFollowerData
import co.kr.soptandroidseminar.data.ResponseRepoData
import co.kr.soptandroidseminar.data.ResponseUserInfoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/users/{username}")
    fun getUserInfo(
        @Path("username") username: String
    ): Call<ResponseUserInfoData>

    @GET("/users/{username}/followers")
    fun getFollowerList(
        @Path("username") username: String
    ): Call<List<ResponseFollowerData>>

    @GET("/users/{username}/repos")
    fun getRepoList(
        @Path("username") username: String
    ): Call<List<ResponseRepoData>>
}