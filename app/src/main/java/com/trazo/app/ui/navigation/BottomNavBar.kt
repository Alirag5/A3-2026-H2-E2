package com.trazo.app.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavBar(
    currentRoute: String?,
    onDestinationSelected: (TrazoDestination) -> Unit,
) {
    NavigationBar {
        TrazoDestination.entries.forEach { destination ->
            NavigationBarItem(
                selected = currentRoute == destination.route,
                onClick = { onDestinationSelected(destination) },
                icon = { Icon(destination.icon, contentDescription = destination.title) },
                label = { Text(destination.title) },
            )
        }
    }
}
