package ru.oliverhd.usersdb.data

interface Repository {
    fun getUsersList(): List<User>
    fun getUserById(userId: Int): User?
}