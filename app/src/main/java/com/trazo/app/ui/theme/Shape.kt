package com.trazo.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val TrazoShapes =
    Shapes(
        extraSmall = RoundedCornerShape(4.dp), // badges rectos, tooltips
        small = RoundedCornerShape(8.dp), // chips de meta y destacados
        medium = RoundedCornerShape(12.dp), // inputs, controles Pausar/Finalizar, miniaturas
        large = RoundedCornerShape(16.dp), // cards, panel del temporizador, FAB
        extraLarge = RoundedCornerShape(28.dp), // modales, bottom sheet
    )
