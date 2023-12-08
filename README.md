# Prueba técnica 

# Framework utilizado y arquitectura de la app

Para del desarrollo de la prueba técnica he utilizado Jetpack Compose para construir la interfaz de Usuario.

La aplicación sigue una arquitectura MVVM (model-view-viewModel). Para ello me he ayudado de Dagger-Hilt para
inyectar dependencias en los ViewModel (en este caso, la implementación de los repositorios). A su vez, el
repositorio utiliza el datasource definido para acceder a los datos de la api (RandomUserApi). Todo esto queda
conectado perfectamente de forma automática gracias a Dagger-Hilt. 

Los procesos asíncronos se han manejado mediante Flow y Coroutines.

# Capa de Datos
La capa de datos está definida en la carpeta `data` y allí encontraremos los modelos dentro de la carpeta `models`, en una única clase.
He decido incluir todas las clases de datos para parsear la respuesta de la api en un único archivo por razones de tiempo.

En la raíz de `data` tenemos la interfaz para realizar peticiones HTTP a la API `RandomUserApi.kt`, la interfaz del repositorio `RandomUserRepository.kt`
y la implementación del repositorio `RandomUserRepositoryImpl`, donde la implementación se inyecta por el constructor gracias a que está bindeada en
`RepositoryModule` de la carpeta `di`.

Soy consciente que la carpeta puede estar un poco desordenada, pero si tenemos en cuenta todos los aspectos y 'buenas prácticas' la prueba
llevaría demasiado tiempo.

# Interfaz de usuario

Dentro de MainActivity.kt tendremos el NavigationGraph, un composable donde declaro las rutas de la app. He inyectado directamente el viewModel
`RandomUserViewModel` utilizando Dagger-Hilt.

## RandomUserScreen

Escuchará cambios en el Flow de searchedUsers gracias al método collectAsStateFlowWithLifecycle, el cual mantiene la colección del flujo activa
solamente cuando la app está activa, y no cuando esté en el background => [ver post en Medium](https://medium.com/androiddevelopers/consuming-flows-safely-in-jetpack-compose-cde014d0d5a3)

Para no complicar mucho las cosas, searchedUsers es un Flujo que combina dos Flujos: el flujo de los usuarios que son obtenidos de la API y el flujo del texto
de la barra de búsqueda. De tal manera que nos permite obtener todos los resultados en el caso de que no se esté buscando nada (texto vacío) o los usuarios que
coincidan con el texto.

nota: La búsqueda de usuarios la he realizado filtrando por aquellos usuarios que ya existen en el flujo de usuarios `_users` en el viewModel.

`RandomUserScreen` realiza una primera llamada al método getUsers() del viewModel dentro del composable LaunchedEffect. Esto permite una primera carga de datos.

El método getUsers es el único método utilizado para obtener usuarios, incluso para la paginación. Este método utiliza la última página guardada
dentro del viewModel para avanzar en la paginación, además de coleccionar el flujo del repositorio y actualizar el flujo de  `_users`.

## RandomUserDetail

Es la pantalla de detalles del usuario. Si se observa el NavigationGraph del MainActivity.kt, se puede observar que es una ruta que toma un argumento: `email`. 
Este argumento se utiliza para obtener el usuario del Map `registeredUsers` del viewModel. En los comentarios explico el por qué de este registeredUsers, utilizando en la función
del filtrado de usuarios para no incluir usuarios repetidos. 

Esta carpeta utiliza los datos del usuario para generar la pantalla.

Una cosa interesante que he intentado hacer es incluir MapBox para pintar el mapa en esta pantalla. La implementación está hecha. He incluido los token en el repositorio 
(sé que no debe hacerse, pero esto solo es una prueba). MapBox funciona, pero las coordenadas que devuelve la API apuntan al océano, así que no lo he incluido. El composable
para ver el mapa sería el siguiente:

```kotlin
      MapboxMap(
        Modifier.fillMaxSize(),
        mapViewportState = MapViewportState().apply {
          setCameraOptions {
            zoom(2.0)
            // Coordenadas del usuario
            center(Point.fromLngLat(-98.0, 39.5))
            pitch(0.0)
            bearing(0.0)
          }
        },
      )
```
## Test

He incluido un pequeño test ViewModelTest donde pruebo los flows. Solo lo he hecho como ejemplo.


