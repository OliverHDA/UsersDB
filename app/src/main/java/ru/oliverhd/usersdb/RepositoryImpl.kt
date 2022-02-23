package ru.oliverhd.usersdb

class RepositoryImpl : Repository {
    override fun getUsersList(): List<User> {
        return getDefaultUsersList()
    }

    private fun getDefaultUsersList(): List<User> {
        val usersList = mutableListOf<User>()
        for (i in 1..10) {
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
}