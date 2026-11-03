package com.trazo.app.ui.screens.home

/**
 * Estado inmutable de la pantalla de Inicio.
 *
 * Invariante: si [isStartRunEnabled] es `false`, [isStartRunOptionVisible] también es `false`.
 */
data class HomeUiState(
    val hasRecentRuns: Boolean = false,
    val isStartRunEnabled: Boolean = true,
    val isStartRunOptionVisible: Boolean = false,
)
