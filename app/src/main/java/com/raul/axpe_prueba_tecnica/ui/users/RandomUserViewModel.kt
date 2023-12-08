package com.raul.axpe_prueba_tecnica.ui.users

import UserData
import UserDataResponse
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import com.raul.axpe_prueba_tecnica.data.RandomUserRepositoryImpl

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random


enum class LoadingStatus {
    initial, loading, loadingPagination
}

data class RandomUserUiState(
    val error: String? = null,
    val loadingStatus : LoadingStatus
)


@HiltViewModel
class RandomUserViewModel @Inject constructor(private val repository: RandomUserRepositoryImpl) :
    ViewModel() {

    var _uiState = MutableStateFlow(RandomUserUiState(loadingStatus = LoadingStatus.loading))
    val uiState = _uiState.asStateFlow()


    private var _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private var _users = MutableStateFlow<List<UserData>>(mutableListOf())


    private var _currentPage: Int? = null
    val currentPage: Int
        get() = _currentPage ?: 0


    // caché de usuarios por email: utilizado con el único objetivo
    // de filtrar los usuarios sin necesidad de realizar un bucle
    // sobre los que ya hay. Podría mejorarse, pero el tiempo es limitado.
    private val registeredUsers = mutableMapOf<String, UserData>()


    @OptIn(FlowPreview::class)
    val searchedUsers = searchText.debounce(700L).combine(
        _users
    ) { text, users ->

        if (text.isNotEmpty()) {
            filterUsers(text, users)
        } else {
            users
        }

    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.Lazily,
            initialValue = _users.value
        )


    private fun filterUsers(str: String, users: List<UserData>): List<UserData> {

        val strLowercase = str.lowercase()
        val filteredUsers = users.filter {
            it.getFullName().lowercase().contains(strLowercase) ||
                    it.email.lowercase().contains(strLowercase)
        }
        return filteredUsers


    }

    private fun setLoadingState() {
        val loadingStatus =
            if (currentPage == 0) LoadingStatus.loading else LoadingStatus.loadingPagination
        Log.i("ViewModel", "setting loading state ${loadingStatus}")


        _uiState.value = _uiState.value.copy(loadingStatus = loadingStatus)
    }

    private fun unsetLoadingState(error: String?) {
        _uiState.value = _uiState.value.copy(loadingStatus = LoadingStatus.initial, error = error)
    }

    fun getUsers() {
        setLoadingState()
        viewModelScope.launch(Dispatchers.IO) {

            val page = currentPage + 1

            var result : Flow<UserDataResponse>? = null
           try {

                result = repository.getUsers(page)

            } catch (exception: Exception) {
                unsetLoadingState(exception.message)
                return@launch
            }
            result.flowOn(Dispatchers.IO).collect { usersResult ->
                _currentPage = usersResult.info.page

                val filteredUsers = filterNewUsers(usersResult.results)
                _users.update {
                    it + filteredUsers

                }
                unsetLoadingState(null)
            }
        }
    }

    fun getUserByEmail(email: String): UserData? {
        return registeredUsers[email]
    }

    // Filtrado de usuarios utilizando `registeredUsers`
    private fun filterNewUsers(users: List<UserData>): List<UserData> {
        val filteredList = mutableListOf<UserData>()
        for (user in users) {
            if (!registeredUsers.containsKey(user.email)) {
                registeredUsers[user.email] = user
                filteredList.add(user)
            }
        }
        return filteredList
    }


    fun onQueryChanged(str: String) {
        _searchText.update {
            str
        }

    }

}