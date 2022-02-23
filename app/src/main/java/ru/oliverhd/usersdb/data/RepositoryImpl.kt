package ru.oliverhd.usersdb.data

class RepositoryImpl : Repository {
    override fun getUsersList(): List<User> {
        return getDefaultUsersList()
    }

    override fun getUserById(userId: Int): User? {
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