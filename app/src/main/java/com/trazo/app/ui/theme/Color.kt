package com.trazo.app.ui.theme

import androidx.compose.ui.graphics.Color

// Paleta Trazo v0.1
// "Actual" = colores definidos por el equipo · "Nuevo" = tonos propuestos por el sistema de diseño

// Primary (marca)
val Primary50 = Color(0xFFEFF7F6) // Nuevo
val Primary100 = Color(0xFFDEEFEE) // Actual
val Primary200 = Color(0xFFBADDDC) // Nuevo
val Primary300 = Color(0xFF95CBCA) // Actual
val Primary400 = Color(0xFF7FAFAF) // Nuevo
val Primary500 = Color(0xFF699395) // Nuevo
val Primary600 = Color(0xFF55797B) // Nuevo
val Primary700 = Color(0xFF415F63) // Nuevo
val Primary800 = Color(0xFF385357) // Nuevo
val Primary900 = Color(0xFF2E474B) // Actual: tono de apoyo (presionado, fondos oscuros secundarios)
val Primary950 = Color(0xFF1B2E31) // Actual: COLOR PRINCIPAL de acción (antes "Primary.950": el punto no es válido en Kotlin)

// Secondary (Lima: logro y selección del corredor)
val Secondary100 = Color(0xFFF2F7DC) // Lima
val Secondary200 = Color(0xFFDCEB9E) // Lima: secondaryContainer (reemplaza al menta #B8FAB8)
val Secondary500 = Color(0xFF9DB82F) // Lima: solo decorativo (2.3:1 sobre blanco)
val Secondary700 = Color(0xFF556810) // Lima: secondary (6.2:1 sobre blanco)

// Neutral
val Neutral0 = Color(0xFFFFFFFF) // Nuevo
val Neutral50 = Color(0xFFFAFBFD) // Nuevo
val Neutral100 = Color(0xFFF3F7FF) // Actual
val Neutral200 = Color(0xFFE9EDF3) // Nuevo
val Neutral300 = Color(0xFFDDE2EA) // Actual
val Neutral500 = Color(0xFF8A939C) // Nuevo
val Neutral600 = Color(0xFF6C757D) // Actual: íconos y bordes (4.37:1 sobre Neutral100, no usar para texto chico)
val Neutral700 = Color(0xFF495057) // Nuevo: texto secundario (7.6:1 sobre Neutral100)
val Neutral900 = Color(0xFF212529) // Actual

// Estados
val Error200 = Color(0xFFFECACA) // Actual
val Error600 = Color(0xFFB42318) // Nuevo
val Error800 = Color(0xFF912018) // Nuevo
val Success200 = Color(0xFFDCEB9E) // = Secondary200 (Lima): un solo verde en la app
val Success700 = Color(0xFF556810) // = Secondary700 (Lima), 4.9:1 sobre Success200
val Info200 = Color(0xFFB0D5FF) // Actual
val Info700 = Color(0xFF1D5FA8) // Nuevo
val Info900 = Color(0xFF0F3F75) // Nuevo
val Warning200 = Color(0xFFFDE3A7) // Nuevo
val Warning700 = Color(0xFF8A5300) // Nuevo
