---
inclusion: fileMatch
fileMatchPattern: ["app/src/main/java/**/ui/**/*.kt", "**/*Screen.kt"]
---

# Design System · Trazo

Memoria de proyecto para todo trabajo de UI (pantallas, componentes, tema, textos). La especificación completa, con medidas de cada componente, patrones de pantalla y ejemplos de Compose, está en la skill `trazo-design-system`, archivo `references/trazo-design-system.md`.

## Tema

- El tema vive en `../../app/src/main/java/com/trazo/app/ui/theme`: `Color.kt`, `Theme.kt`, `Type.kt`, `Shape.kt` y `Dimens.kt`.
- Toda pantalla se envuelve en `TrazoTheme { … }`. `ScaffoldingAndroid3Theme` es solo un alias temporal.
- Los colores se usan **por rol**: `MaterialTheme.colorScheme.*` o `TrazoTheme.extendedColors.*` (success, warning, info, mapa). **Nunca** hex sueltos en composables.
- El color dinámico (Material You) está desactivado. Por ahora solo hay tema claro.
git
## Color

| Rol | Token | Hex | Uso |
|---|---|---|---|
| primary | Primary950 | `#1B2E31` | Botón filled, FAB, barra superior, traza en vivo, controles del recorrido |
| primaryContainer | Primary100 | `#DEEFEE` | Indicador de navegación, chip de meta seleccionado |
| secondaryContainer | Secondary200 (Lima) | `#DCEB9E` | Destacado elegido, opción "Iniciar un recorrido", celebración |
| secondary | Secondary700 | `#556810` | Pin de inicio, ícono de logro |
| Success (extendido) | = Lima 200/700 | `#DCEB9E` / `#556810` | "Meta cumplida", "Recorrido guardado", GPS bueno |
| tertiary / container | Info700 / Info200 | `#1D5FA8` / `#B0D5FF` | Modo demo, marca "Demo" (texto Info900 `#0F3F75`) |
| error / errorContainer | Error600 / Error200 | `#B42318` / `#FECACA` | Campo inválido, "Descartar", botón Finalizar (ícono Error800 `#912018`) |
| Warning (extendido) | Warning200 / 700 | `#FDE3A7` / `#8A5300` | "Incompleto", "Esperando señal GPS" |
| background / surface | Neutral100 | `#F3F7FF` | Fondo de pantallas |
| surfaceContainerLowest | Neutral0 | `#FFFFFF` | Cards, modales, panel del temporizador |
| surfaceContainer | Neutral200 | `#E9EDF3` | Barra de navegación inferior |
| onSurface / onSurfaceVariant | Neutral900 / Neutral700 | `#212529` / `#495057` | Texto principal / secundario |
| outline / outlineVariant | Neutral600 / Neutral300 | `#6C757D` / `#DDE2EA` | Bordes de input y chip / divisores |
| inverseSurface | Primary900 | `#2E474B` | Alerta de frenada, snackbar |

Reglas:
- **Lima es el único verde** de la app (Secondary = Success).
- **El rojo es solo para errores** y para el pin de frenada en el mapa. La alerta de frenada va en Primary900, con el ícono Primary950 sobre un círculo blanco.
- Primary300 y Neutral300 **nunca** se usan para texto. Neutral600 no se usa para texto chico sobre Neutral100.
- Contraste mínimo: texto ≥ 4.5:1, íconos y bordes ≥ 3:1.

## Tipografía

Escala por defecto de M3 (Roboto, sp). Asignación:

- **displayMedium:** temporizador.
- **displaySmall / headlineMedium:** métricas de celebración.
- **headlineSmall:** título de modal.
- **titleLarge:** barra superior.
- **titleMedium:** título de card.
- **bodyMedium:** mensajes.
- **bodySmall:** fecha y contador.
- **labelLarge:** botones.
- **labelMedium:** chips, marcas y navegación.

Reglas de formato:
- Las métricas usan `style.tabular()` (cifras tabulares).
- Valor y unidad van separados: el valor en el estilo grande y la unidad en labelLarge onSurfaceVariant.
- Mayúscula solo inicial. Coma decimal según el Locale ("5,2 km").

## Espaciado y forma

- **Espaciado:** grilla de 4 dp (`Spacing.xs`…`xxxl`). Margen de pantalla de 16 dp (`Spacing.lg`).
- **Tamaños:** objetivo táctil mínimo de 48 dp. Controles Pausar/Reanudar/Finalizar de 56 dp (`Sizes.runControl`). Botón de 48 dp de alto.
- **Formas:**
  - small 8 dp: chips.
  - medium 12 dp: inputs, controles, avisos.
  - large 16 dp: cards, paneles, FAB.
  - extraLarge 28 dp: modales.
  - Píldora: botones y marcas.
- **Elevación:** sobre el fondo, las cards no llevan sombra (borde outlineVariant). Sobre el mapa, los paneles usan level1–2 y el FAB, los modales y la alerta usan level3.

## Componentes

- **Botones:**
  - Filled (primary): **uno por pantalla**.
  - Outlined (fondo blanco, borde Neutral600 de 1 dp): "Cancelar" e "Ir a configuración".
  - Text: acciones menores.
  - Destructivo (error): "Descartar".
  - Tonal: reservado.
- **FAB "+":** primary, 56 dp, deshabilitado durante un recorrido.
- **Chips:**
  - De meta (3/5/10 km): selección única, 40 dp, seleccionado en primaryContainer.
  - De destacado: selección múltiple, 32 dp, seleccionado en secondaryContainer (Lima) con check.
- **Marcas:** "Meta cumplida" (Lima), "Incompleto" (Warning), "Demo" (Info). Forma de píldora, 24 dp.
- **Campo de título:** outlined, 56 dp, obligatorio entre 3 y 50 caracteres, contador tabular, error "Escribí un título".
- **Card de recorrido:** título en una línea con "…", fecha, y una grilla con Distancia · Tiempo · Frenadas · Regularidad.
- **Panel en curso:** temporizador, controles, barra de progreso hacia la meta y métricas.
- **Navegación:** 3 pestañas (Inicio · Mapa · Historial). Indicador primaryContainer e ícono relleno en el ítem activo.
- **Mapa:** traza en vivo en primary de 6 dp con borde blanco. Recorridos guardados en Primary500 al 75 %, 4 dp.

## Redacción

- Voseo rioplatense con verbos de acción: "Escribí un título", "Elegí una meta", "Podés agregar hasta 2 fotos".
- Los botones dicen la acción concreta ("Guardar recorrido", "Comenzar"). Nunca "OK" ni "Aceptar".

## Accesibilidad

- Los botones de solo ícono llevan `contentDescription`.
- El estado nunca se comunica solo con color.
- El temporizador no usa live region. Las alertas sí.
- Probar con fuente al 200 % y con "Quitar animaciones".
