package co.kr.soptandroidseminar.data.signup

data class ResponseSignUpData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val id: Int,
        val name: String,
        val password: String,
        val email: String,
    )
}
