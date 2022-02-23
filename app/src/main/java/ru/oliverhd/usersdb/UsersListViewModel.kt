package ru.oliverhd.usersdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.oliverhd.usersdb.data.Repository
import ru.oliverhd.usersdb.data.RepositoryImpl
import ru.oliverhd.usersdb.data.User

class UsersListViewModel(private val repository: Repository = RepositoryImpl()) : ViewModel() {

    private val _usersLiveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
    private val usersLiveData: LiveData<AppState> = _usersLiveData

    fun getLiveData(): LiveData<AppState> {
        _usersLiveData.value = AppState.Loading
        if (!repository.getUsersList().isNullOrEmpty()) {
            _usersLiveData.value = AppState.Success<List<User>>(repository.getUsersList())
        } else {
            _usersLiveData.value = AppState.Error(Throwable(DB_ERROR))
        }
        return usersLiveData
    }

    companion object {
        private const val DB_ERROR = "Ошибка базы данных"
    }
}