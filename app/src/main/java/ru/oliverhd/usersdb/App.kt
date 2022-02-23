package ru.oliverhd.usersdb

import android.app.Application
import androidx.room.Room
import ru.oliverhd.usersdb.data.UsersDao
import ru.oliverhd.usersdb.data.UsersDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var dataBase: UsersDataBase? = null
        private const val DB_NAME = "Users.db"

        fun getUsersDao(): UsersDao {
            if (dataBase == null) {
                synchronized(UsersDataBase::class.java) {
                    if (dataBase == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        dataBase = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            UsersDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return dataBase!!.usersDao()
        }
    }
}