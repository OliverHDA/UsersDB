package ru.oliverhd.usersdb.data

interface Repository {
    fun getUsersList(): List<User>
    fun getUserById(userId: Int): User?
    fun addUser(user: User)
    fun removeUser(userId: Int)
    fun changeUser(user: User)

    fun getNotPersistedUsersList(): List<User>
    fun getNotPersistedUserById(userId: Int): User
}