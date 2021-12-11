package co.kr.soptandroidseminar.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object ApiService {
    private const val BASE_URL_SOPT = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private const val BASE_URL_GITHUB = "https://api.github.com"

    private val soptRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SOPT)
        .client(provideSoptOkHttpClient(SoptInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GITHUB)
        .client(provideGithubOkHttpClient(GithubInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun provideSoptOkHttpClient(
        interceptor: SoptInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .run {
                addInterceptor(interceptor)
                build()
            }

    private fun provideGithubOkHttpClient(
        interceptor: GithubInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .run {
                addInterceptor(interceptor)
                build()
            }

    class SoptInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest =
                request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()

            proceed(newRequest)
        }
    }

    class GithubInterceptor: Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest =
                request().newBuilder()
                    .addHeader("Authorization", "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")
                    .build()

            proceed(newRequest)
        }
    }

    val seminarService: SeminarService = soptRetrofit.create(SeminarService::class.java)
    val githubService: GithubService = githubRetrofit.create(GithubService::class.java)
}