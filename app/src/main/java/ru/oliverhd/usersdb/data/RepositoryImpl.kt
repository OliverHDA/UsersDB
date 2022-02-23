package ru.oliverhd.usersdb.data

class RepositoryImpl : Repository {
    override fun getUsersList(): List<User> {
        return getDefaultUsersList()
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
}