package com.raul.axpe_prueba_tecnica.ui.users.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raul.axpe_prueba_tecnica.ui.users.LoadingStatus
import com.raul.axpe_prueba_tecnica.ui.users.RandomUserUiState
import kotlinx.coroutines.flow.StateFlow


// Evita la recomposición de RandomUsersLazyList cuando se emite un nuevo estado de Carga
@Composable
fun LoadingSpinner(uiState: StateFlow<RandomUserUiState>, showOnStatus: LoadingStatus) {
    val loadingStatus = uiState.collectAsStateWithLifecycle().value.loadingStatus
    Log.i("Loading status", "${loadingStatus}")
    if (loadingStatus == showOnStatus) {
        CircularProgressIndicator(color = Color.Black)
    }
}

@Composable
fun InitialLoadingSpinner(uiState: StateFlow<RandomUserUiState>){
    Box(
        modifier = Modifier
            .fillMaxWidth().padding(top = 50.dp),

        contentAlignment = Alignment.Center
    ) {

        LoadingSpinner(uiState = uiState, showOnStatus = LoadingStatus.loading)
    }
}