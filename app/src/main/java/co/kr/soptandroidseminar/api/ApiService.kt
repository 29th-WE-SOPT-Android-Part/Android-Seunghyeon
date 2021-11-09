package co.kr.soptandroidseminar.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL_SOPT = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private const val BASE_URL_GITHUB = "https://api.github.com"

    private val soptRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SOPT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GITHUB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val seminarService: SeminarService = soptRetrofit.create(SeminarService::class.java)
    val githubService: GithubService = githubRetrofit.create(GithubService::class.java)
}