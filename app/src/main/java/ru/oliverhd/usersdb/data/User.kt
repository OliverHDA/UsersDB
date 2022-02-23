package ru.oliverhd.usersdb.data

data class User(
    val id: Int,
    val name: String,
    val surname: String?,
    val patronymic: String?,
    val birthdate: String?,
    val email: String?,
    val phone: String,
    val photo: String?
)

fun getEmptyUser(): User {
    return User(
        0,
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}