package ru.oliverhd.usersdb

interface Repository {
    fun getUsersList(): List<User>
}