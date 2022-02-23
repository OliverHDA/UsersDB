package ru.oliverhd.usersdb.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "surname")
    val surname: String?,
    @ColumnInfo(name = "patronymic")
    val patronymic: String?,
    @ColumnInfo(name = "birthdate")
    val birthdate: String?,
    @ColumnInfo(name = "email")
    val email: String?,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "photo")
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