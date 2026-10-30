package com.trazo.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

/** Las 3 pestañas principales de la app y su ruta de navegación. */
enum class TrazoDestination(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    HOME("home", "Inicio", Icons.Filled.Home),
    MAP("map", "Mapa", Icons.Filled.Place),
    HISTORY("history", "Historial", Icons.AutoMirrored.Filled.List),
}
