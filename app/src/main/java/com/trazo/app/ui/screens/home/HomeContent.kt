package com.trazo.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.trazo.app.R
import com.trazo.app.ui.screens.home.components.EmptyState
import com.trazo.app.ui.screens.home.components.StartRunFab
import com.trazo.app.ui.theme.Spacing
import com.trazo.app.ui.theme.TrazoTheme

/**
 * Contenido de Inicio. Se dibuja solo a partir de [state]; no guarda estado propio.
 * Sin recorridos muestra el estado vacío; con recorridos, en esta versión no dibuja cards.
 */
@Composable
fun HomeContent(
    state: HomeUiState,
    onStartRunFabClick: () -> Unit,
    onStartRunOptionDismiss: () -> Unit,
    onStartRunOptionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
    ) {
        if (!state.hasRecentRuns) {
            // EmptyState ya aplica el margen lateral de pantalla.
            EmptyState(
                illustrationRes = R.drawable.img_empty_runs,
                message = stringResource(R.string.home_empty_runs),
                modifier = Modifier.align(Alignment.Center),
            )
        }

        StartRunFab(
            enabled = state.isStartRunEnabled,
            isOptionVisible = state.isStartRunOptionVisible,
            onFabClick = onStartRunFabClick,
            onOptionDismiss = onStartRunOptionDismiss,
            onOptionClick = onStartRunOptionClick,
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Spacing.lg),
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun HomeContentEmptyPreview() {
    TrazoTheme {
        HomeContent(
            state = HomeUiState(),
            onStartRunFabClick = {},
            onStartRunOptionDismiss = {},
            onStartRunOptionClick = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun HomeContentOptionVisiblePreview() {
    TrazoTheme {
        HomeContent(
            state = HomeUiState(isStartRunOptionVisible = true),
            onStartRunFabClick = {},
            onStartRunOptionDismiss = {},
            onStartRunOptionClick = {},
        )
    }
}
