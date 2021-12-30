package co.kr.soptandroidseminar.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun Context.simpleToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <ResponseType> Call<ResponseType>.enqueueUtil(
    onSuccess: (ResponseType) -> Unit,
    onError: ((stateCode: Int) -> Unit)? = null
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                onSuccess.invoke(response.body() ?: return)
            } else {
                onError?.invoke(response.code())
                Log.d("server connect", "error")
                Log.d("server connect", "$response.errorBody()")
                Log.d("server connect", response.message())
                Log.d("server connect", "${response.code()}")
            }
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d("Network", "error:$t")
        }
    })
}