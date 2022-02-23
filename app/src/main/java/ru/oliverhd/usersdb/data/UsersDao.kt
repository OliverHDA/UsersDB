package ru.oliverhd.usersdb.data

import androidx.room.*

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE id LIKE :userId LIMIT 1")
    fun getUserById(userId: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(user: User)

    @Query("DELETE FROM users WHERE id = :userId")
    fun remove(userId: Int)

    @Update
    fun updateUser(user: User)
}