package ru.oliverhd.usersdb.data

class RepositoryImpl(private val usersDB: UsersDao) : Repository {

    override fun getUsersList(): List<User> {
        return usersDB.getAllUsers()
    }

    override fun getUserById(userId: Int): User? {
        return usersDB.getUserById(userId)
    }

    override fun addUser(user: User) {
        usersDB.retain(user)
    }

    override fun removeUser(userId: Int) {
        usersDB.remove(userId)
    }

    override fun changeUser(user: User) {
        usersDB.updateUser(user)
    }

    override fun getNotPersistedUsersList(): List<User> {
        return getDefaultUsersList()
    }

    override fun getNotPersistedUserById(userId: Int): User {
        return getDefaultUser()
    }

    private fun getDefaultUsersList(): List<User> {
        val usersList = mutableListOf<User>()
        for (i in 1..100) {
            usersList.add(
                User(
                    i,
                    "name$i",
                    "surname$i",
                    "patronymic$i",
                    null,
                    null,
                    "+7777777777$i",
                    null
                )
            )
        }
        return usersList
    }

    private fun getDefaultUser(): User {
        return User(
            1,
            "Иван",
            "Иванов",
            "Иванович",
            "15.07.1985",
            "ivan@gmail.com",
            "+7(777)777-77-77",
            null
        )
    }
}