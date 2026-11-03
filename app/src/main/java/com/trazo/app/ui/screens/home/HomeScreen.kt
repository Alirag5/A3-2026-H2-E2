package com.trazo.app.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Conecta [HomeViewModel] con [HomeContent]. No dibuja nada propio.
 *
 * @param onStartRunClick se invoca al elegir "Iniciar un recorrido", después de cerrar la opción.
 */
@Composable
fun HomeScreen(
    onStartRunClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        state = state,
        onStartRunFabClick = viewModel::onStartRunFabClick,
        onStartRunOptionDismiss = viewModel::onStartRunOptionDismiss,
        onStartRunOptionClick = {
            viewModel.onStartRunOptionClick()
            onStartRunClick()
        },
        modifier = modifier,
    )
}
