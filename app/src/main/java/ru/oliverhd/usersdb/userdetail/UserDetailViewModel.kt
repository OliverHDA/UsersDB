package ru.oliverhd.usersdb.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.oliverhd.usersdb.App
import ru.oliverhd.usersdb.AppState
import ru.oliverhd.usersdb.Constants
import ru.oliverhd.usersdb.data.Repository
import ru.oliverhd.usersdb.data.RepositoryImpl
import ru.oliverhd.usersdb.data.User

class UserDetailViewModel(private val repository: Repository = RepositoryImpl(App.getUsersDao())): ViewModel() {
    private val _userLiveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
    private val userLiveData: LiveData<AppState> = _userLiveData

    fun getLiveData(): LiveData<AppState> = userLiveData

    fun getUserById(id: Int) {
        if(repository.getUserById(id) == null) {
            _userLiveData.value = AppState.Error(Throwable(Constants.DB_ERROR))
        } else
            _userLiveData.value = AppState.Success<User>(repository.getUserById(id)!!)
    }

    fun changeUser (user: User) {
        repository.changeUser(user)
    }

    fun saveNewUser(user: User) {
        repository.addUser(user)
    }

}