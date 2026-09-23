# Registro de Prompts e Interacción con IA - PROMPTS.md

**Proyecto:** Clínica Salud+  
**Rama de trabajo:** `mejora-ia`  
**Funcionalidad implementada:** Cancelación de citas médicas con confirmación mediante `AlertDialog`, feedback visual con `Snackbar` y personalización de estilos.

---

## Prompt 1: Implementación del AlertDialog de Cancelación

### 1. Lo que se le pidió a la IA
> *"Tengo mi pantalla `MisCitasScreen.kt` en Jetpack Compose sin arquitectura MVVM/ViewModel (manejamos el estado de forma local con `remember` y `mutableStateListOf`). Quiero agregar un ícono de cancelación (una 'X') únicamente a las citas con estado 'Confirmada'. Al hacer clic en este ícono, debe abrirse un `AlertDialog` de confirmación preguntando '¿Estás seguro de que deseas cancelar la cita con [Nombre del médico]?'. Si el usuario confirma, la cita cambia su estado a 'Cancelada'. Genera el código completo de `MisCitasScreen.kt`."*

### 2. Respuesta obtenida y correcciones aplicadas
* **Respuesta de la IA:** Generó la estructura del `AlertDialog` y la lógica para filtrar el botón de cierre según el estado. Sin embargo, introdujo una clase `CitaViewModel` y utilizó `StateFlow` para manejar la lista.
* **Lo que se tuvo que corregir:**
    * **Eliminación del ViewModel:** Se descartó el ViewModel y las dependencias de LiveData/StateFlow, ya que los requerimientos de la evaluación exigen no usar MVVM (Semanas 1 a 6).
    * **Manejo de Estado Local:** Se adaptó la lógica a un `remember { mutableStateListOf(...) }` dentro del composable `MisCitasScreen`.

---

## Prompt 2: Integración de Feedback Visual mediante Snackbar

### 1. Lo que se le pidió a la IA
> *"A la pantalla `MisCitasScreen.kt` que acabamos de crear, quiero agregarle feedback visual cuando el usuario cancele una cita. Incorpora un `SnackbarHost` en el `Scaffold` para que, al presionar 'Sí, cancelar' en el `AlertDialog`, se muestre un mensaje 'Cita cancelada correctamente' durante 3 segundos usando `SnackbarHostState` y `rememberCoroutineScope`. Devuélveme la pantalla actualizada."*

### 2. Respuesta obtenida y correcciones aplicadas
* **Respuesta de la IA:** Proporcionó el uso de `SnackbarHostState` y ejecutó la función suspendida `showSnackbar()` dentro de un `scope.launch`.
* **Lo que se tuvo que corregir:**
    * **Estructura del Layout:** La IA alteró los paddingValues del `Scaffold` original.
    * **Integración Manual:** Se extrajo únicamente la propiedad `snackbarHost = { SnackbarHost(snackbarHostState) }` y el `CoroutineScope` manteniendo la maquetación inicial intacta.

---

## Prompt 3: Personalización de Estilos del Badge y Estado "Cancelado"

### 1. Lo que se le pidió a la IA
> *"Actualiza la representación visual de la tarjeta cuando el estado de la cita pase a 'Cancelada'. La barra vertical izquierda debe cambiar a color rojo (`0xFFE53935`) y el indicador de estado (Surface) debe mostrar fondo rojo claro con texto rojo oscuro. Dame la lógica del `when` en Compose."*

### 2. Respuesta obtenida y correcciones aplicadas
* **Respuesta de la IA:** Sugirió utilizar temas globales de Material3 (`MaterialTheme.colorScheme.error`).
* **Lo que se tuvo que corregir:**
    * **Paleta Hexadecimal:** Se reemplazaron las referencias genéricas por los valores exactos en hexadecimal (`Color(0xFFFFEBEE)` para fondo y `Color(0xFFC62828)` para texto) con el fin de mantener congruencia con la guía de diseño del proyecto.