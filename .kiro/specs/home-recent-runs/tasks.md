# Implementation Plan

- [ ] 1. Base: dependencias, textos e íconos compartidos
- [x] 1.1 Agregar la librería de estado de Compose y los textos de la funcionalidad
  - Declarar explícitamente `lifecycle-runtime-compose`, con la misma versión que `lifecycle-runtime-ktx`, en el catálogo y en `app`. Ya llega transitivamente desde Compose UI (BOM) y está en la caché, así que no hay que descargar nada. Se declara porque Inicio la usa directamente (`collectAsStateWithLifecycle`).
  - Agregar a los recursos de texto las etiquetas de navegación ("Inicio", "Mapa", "Historial"), el mensaje "Todavía no hay recorridos", el texto "Iniciar un recorrido" y la descripción accesible "Nuevo recorrido", con las claves que define el diseño.
  - Listo cuando `./gradlew assembleDebug` compila y los seis textos están disponibles como recursos.
  - _Requirements: 2.1, 3.3, 3.5, 4.1_

- [x] 1.2 Importar los íconos de navegación, del FAB y de la opción
  - Importar de Material Symbols (24 dp, weight 400) los vectores de Inicio, Mapa e Historial, cada uno relleno y con trazo, más "add" y "route". Son 8 en total.
  - Si no hay acceso a Material Symbols, usar el plan B del diseño (`material-icons-core`) y dejarlo anotado en Implementation Notes.
  - Listo cuando los 8 drawables compilan con `./gradlew assembleDebug` y se ven correctamente en la vista previa de Android Studio.
  - _Requirements: 3.3, 3.5, 4.1, 4.5_

- [ ] 2. Piezas principales
- [ ] 2.1 (P) Reemplazar el ícono de la app por el logo de Trazo
  - El primer plano del ícono adaptativo pasa a ser la figura del logo (los trazos y la cabeza, sin el cuadrado de fondo), centrada en el lienzo de 108 dp y dentro de la zona segura de 66 dp.
  - El fondo del ícono adaptativo pasa a ser el color sólido del fondo del logo entregado.
  - El ícono monocromo sigue usando el primer plano. No se tocan el manifiesto, los temas ni la Activity.
  - Listo cuando `./gradlew assembleDebug` compila y la vista previa del ícono adaptativo muestra el logo sin recortes con máscara circular y squircle.
  - _Boundary: Ícono de la app_
  - _Requirements: 1.1, 1.2_

- [ ] 2.2 (P) Estado de Inicio y su ViewModel, con tests primero
  - Escribir primero los tests unitarios en la JVM:
    - El estado inicial es vacío, con el FAB habilitado y la opción oculta.
    - Tocar el FAB muestra la opción.
    - Descartar la opción la oculta.
    - Elegir la opción la oculta.
    - Tocar el FAB dos veces la vuelve a ocultar.
  - Implementar el estado inmutable de Inicio y el ViewModel inyectable por Hilt, que lo expone como flujo observable y no hace nada al tocar el FAB si está deshabilitado.
  - Listo cuando los 5 tests de `HomeViewModelTest` pasan con `./gradlew testDebugUnitTest`.
  - _Boundary: HomeUiState, HomeViewModel_
  - _Requirements: 2.1, 2.2, 3.2, 3.3, 3.4_

- [ ] 2.3 (P) Componente de estado vacío
  - Muestra una ilustración decorativa (no la lee el lector de pantalla) y debajo un mensaje legible, centrados, con la tipografía y el color del sistema de diseño.
  - Es genérico: recibe la ilustración y el mensaje, sin referencias a Inicio.
  - Tiene una preview con la ilustración de recorridos vacíos, envuelta en el tema de Trazo.
  - Listo cuando el componente compila, su preview se ve y no depende de nada fuera del tema y los recursos.
  - _Boundary: EmptyState_
  - _Requirements: 2.1, 2.3_

- [ ] 2.4 (P) Componente de botón flotante con la opción "Iniciar un recorrido"
  - El FAB "+" sigue el estilo del sistema de diseño (primary, 56 dp, forma large) y tiene la descripción accesible "Nuevo recorrido". Admite un estado deshabilitado.
  - Cuando la opción está visible, aparece un botón extendido Lima con el ícono de ruta y el texto "Iniciar un recorrido", alineado a la derecha y por encima del FAB, en una capa emergente que cubre toda la ventana.
  - Tocar fuera de la opción avisa que se descartó, y elegirla avisa que se seleccionó. El componente no guarda estado propio.
  - Tiene previews de los estados cerrado, abierto y deshabilitado.
  - Listo cuando compila, las tres previews se ven y todos los eventos llegan por parámetro.
  - _Boundary: StartRunFab_
  - _Requirements: 3.1, 3.2, 3.3, 3.4, 3.5_

- [ ] 2.5 (P) Barra de navegación con el estilo de Trazo
  - Los destinos toman su etiqueta de los recursos de texto y tienen un ícono relleno y uno con trazo. Las rutas y el orden no cambian.
  - El ítem activo usa el indicador primaryContainer, el ícono relleno y la etiqueta en negrita. Los inactivos usan el ícono con trazo y onSurfaceVariant, y se pueden seguir tocando.
  - Los íconos no repiten el nombre para el lector de pantalla, porque ya lo da la etiqueta.
  - Listo cuando `./gradlew assembleDebug` compila, la app muestra la barra con Inicio acentuado (indicador no Lima) y tocar Mapa o Historial sigue navegando.
  - _Boundary: TrazoDestination, BottomNavBar_
  - _Requirements: 4.1, 4.2, 4.3, 4.4, 4.5_

- [ ] 3. Integración de Inicio
- [ ] 3.1 Armar el contenido de Inicio a partir de su estado
  - Mostrar el estado vacío centrado cuando no hay recorridos, y no mostrar cards en ningún caso.
  - Ubicar el FAB abajo a la derecha, con el margen de pantalla, por encima de la barra de navegación.
  - Pasar los eventos del FAB y de la opción como callbacks, sin estado propio.
  - Agregar previews de "vacío con la opción oculta" y "vacío con la opción visible".
  - Listo cuando compila y las dos previews muestran el mensaje, la ilustración y el FAB en su posición.
  - _Depends: 2.2, 2.3, 2.4_
  - _Requirements: 2.1, 2.2, 3.1_

- [ ] 3.2 Conectar Inicio con su ViewModel y con la navegación de la app
  - La pantalla de Inicio observa el estado del ViewModel respetando el ciclo de vida y le pasa los eventos al contenido. Al elegir "Iniciar un recorrido", cierra la opción y avisa hacia afuera.
  - La navegación de la app usa la nueva pantalla de Inicio como destino inicial y le pasa una acción vacía para "Iniciar un recorrido", que define otra historia.
  - Listo cuando la app instalada abre directo en Inicio con la ilustración, "Todavía no hay recorridos", el FAB "+" e Inicio acentuado, y "Atrás" desde Inicio sale de la app.
  - _Depends: 2.5, 3.1_
  - _Requirements: 1.3, 1.4, 2.1, 3.2, 3.3, 3.4, 4.4_

- [ ] 4. Validación
- [ ] 4.1 (P) Tests de UI instrumentados del contenido de Inicio
  - Verificar que con el estado inicial se ve "Todavía no hay recorridos".
  - Verificar que el FAB con descripción "Nuevo recorrido" existe, está habilitado y se puede tocar.
  - Verificar que con la opción visible se ve "Iniciar un recorrido" y que tocarla dispara el evento de selección.
  - Verificar que, con la opción visible, un toque afuera dispara el descarte y no la selección.
  - Listo cuando `./gradlew assembleDebugAndroidTest` compila y, si hay un emulador o dispositivo, `HomeContentTest` pasa con `connectedDebugAndroidTest`.
  - _Boundary: HomeContentTest_
  - _Requirements: 2.1, 2.3, 3.1, 3.2, 3.3, 3.4, 3.5_

- [ ] 4.2 (P) Tests de UI instrumentados de la barra de navegación
  - Verificar que se ven las tres etiquetas.
  - Verificar que con la ruta de Inicio, Inicio está seleccionado y Mapa e Historial no lo están pero siguen habilitados.
  - Verificar que tocar Mapa avisa la navegación hacia Mapa.
  - Listo cuando `assembleDebugAndroidTest` compila y, si hay un dispositivo, `BottomNavBarTest` pasa.
  - _Boundary: BottomNavBarTest_
  - _Requirements: 4.1, 4.2, 4.3, 4.4_

- [ ] 4.3 Chequeo de regresión completo
  - Correr `./gradlew ktlintCheck testDebugUnitTest assembleDebug assembleDebugAndroidTest` y corregir lo que falle dentro del alcance de la funcionalidad.
  - Correr `koverXmlReportRelease`, el comando que usa CI, para confirmar que los tests unitarios también pasan en release.
  - Listo cuando todos los comandos terminan sin errores.
  - _Depends: 4.1, 4.2_
  - _Requirements: 1.1, 1.3, 1.4, 2.1, 3.2, 4.2_

## Implementation Notes
- `lifecycle-runtime-compose` 2.8.7 ya es transitiva de `androidx.compose.ui:ui` 1.8.0; declararla no cambia la versión resuelta.
- Material Symbols "history" no tiene variante rellena: `ic_nav_history_filled` = `ic_nav_history_outlined`. La selección de Historial se distingue por el indicador y la etiqueta en negrita (4.5).
