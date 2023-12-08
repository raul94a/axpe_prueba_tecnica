package com.raul.axpe_prueba_tecnica.ui.users

import UserData
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.raul.axpe_prueba_tecnica.ui.users.composables.InitialLoadingSpinner
import com.raul.axpe_prueba_tecnica.ui.users.composables.LoadingSpinner
import com.raul.axpe_prueba_tecnica.ui.users.composables.RandomUsersLazyList
import kotlinx.coroutines.flow.StateFlow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomUserScreen(
    users: List<UserData>,
    navController: NavController,
    loadUsers: () -> Unit,
    uiState: StateFlow<RandomUserUiState>,
    searchBar: @Composable() () -> Unit
) {
    var launched by rememberSaveable() {
       mutableStateOf(false)
    }
    LaunchedEffect(key1 = null) {
        if(launched){
            return@LaunchedEffect
        }
        Log.i("Launched Effect", "traigger",)
        launched = true
        loadUsers()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            HomeTopBar {
                searchBar()
            }
        }
    ) { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .background(Color.White)
        ) {


            Box(contentAlignment = Alignment.Center)
            {
                InitialLoadingSpinner(uiState = uiState)
                RandomUsersLazyList(
                    users = users,
                    navController = navController,
                    loadUsers = loadUsers
                )
                LoadingSpinner(uiState = uiState, showOnStatus = LoadingStatus.loadingPagination)

            }


        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(SearchBar: @Composable() () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White
        ),
        title = {

            SearchBar()
        },
        actions = {
            Icon(
                modifier = Modifier.padding(end = 20.dp),
                imageVector = Icons.Outlined.MoreVert, contentDescription = "more_toolbar")
        },
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSearchBar(

    onSearchUsers: (str: String) -> Unit,
    value: String

) {
    TextField(
        modifier = Modifier.padding(end = 20.dp).fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
        placeholder = { Text(text = "Buscar contactos...") },
        value = value, onValueChange = onSearchUsers, trailingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "search users")
        })
}