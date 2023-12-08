package com.raul.axpe_prueba_tecnica.ui.users.composables

import UserData
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlin.random.Random


@Composable
fun RandomUsersLazyList(
    users: List<UserData>,
    navController: NavController,
    loadUsers: () -> Unit
) {
    Log.i("COMPOSING", "${Random(7777)}")
    LazyColumn(

    ) {
        items(users.count()) { index ->
            val user = users[index]
            if (users.count() - 3 == index) {
                loadUsers()
            }
            UserTile(navController,user)
        }
    }
}