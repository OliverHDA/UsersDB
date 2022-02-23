package ru.oliverhd.usersdb.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [User::class], exportSchema = false)
abstract class UsersDataBase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}