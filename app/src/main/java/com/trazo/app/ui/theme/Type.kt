package com.trazo.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle

// Escala por defecto de Material 3 (Roboto). Se usa tal cual.
val TrazoTypography = Typography()

/**
 * Cifras tabulares para métricas: temporizador, distancia, velocidad y porcentajes.
 * Evitan que los números "salten" de ancho mientras cambian.
 *
 * Uso: Text("00:01:20", style = MaterialTheme.typography.displayMedium.tabular())
 */
fun TextStyle.tabular(): TextStyle = copy(fontFeatureSettings = "tnum")

// Asignación de estilos en Trazo (ver tablero 03 · Tipografía):
// displayMedium  -> temporizador del recorrido en curso
// displaySmall   -> métricas en «¡Meta cumplida!» / «Recorrido finalizado»
// headlineLarge  -> estados vacíos
// headlineMedium -> título de celebración
// headlineSmall  -> título de modales y diálogos
// titleLarge     -> barra superior
// titleMedium    -> título de la card de recorrido
// titleSmall     -> encabezado de sección
// bodyLarge      -> inputs, párrafos
// bodyMedium     -> mensajes, permisos
// bodySmall      -> fecha, contador de caracteres
// labelLarge     -> botones
// labelMedium    -> chips, badges, etiquetas de métrica, barra de navegación
