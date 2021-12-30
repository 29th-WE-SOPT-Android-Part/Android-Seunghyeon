package co.kr.soptandroidseminar.data.signin

data class ResponseSignInData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
)  {
    data class Data(
        val id: Int,
        val name: String,
        val email: String,
    )
}
