package com.raul.axpe_prueba_tecnica


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raul.axpe_prueba_tecnica.ui.details.RandomUserDetails
import com.raul.axpe_prueba_tecnica.ui.theme.Axpe_prueba_tecnicaTheme
import com.raul.axpe_prueba_tecnica.ui.users.RandomUserScreen
import com.raul.axpe_prueba_tecnica.ui.users.RandomUserViewModel
import com.raul.axpe_prueba_tecnica.ui.users.UserSearchBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Axpe_prueba_tecnicaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.white)
                ) {
                    val navController = rememberNavController()
                    NavigationGraph(navController)
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController){

    val viewModel = hiltViewModel<RandomUserViewModel>()
    NavHost(navController = navController, startDestination = "home") {
        navigation(startDestination = "users", route = "home") {
            composable("users") {

                val users =
                    viewModel.searchedUsers.collectAsStateWithLifecycle().value;

                RandomUserScreen(
                    users = users,
                    navController = navController,
                    searchBar = {
                        val value =
                            viewModel.searchText.collectAsStateWithLifecycle().value
                        val loading =
                            viewModel.uiState.collectAsStateWithLifecycle().value
                        Log.i("ViewModel", "$loading")
                        UserSearchBar(onSearchUsers = {
                            viewModel.onQueryChanged(it)
                        }, value = value)

                    },
                    uiState = viewModel.uiState,
                    loadUsers = {
                        viewModel.getUsers()
                    })
            }

            composable(
                "user_details/{email}",
                arguments = listOf(navArgument("email") {
                    type = NavType.StringType
                })
            ) { navBackStackEntry ->
                val email = navBackStackEntry.arguments?.getString("email") ?: ""
                val user =
                    viewModel.getUserByEmail(email)!!

                RandomUserDetails(user = user, navController = navController)
            }
        }
    }
}
