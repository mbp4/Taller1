# Taller1
 
Enlace al repositorio: 

En el ejercicio propuesto se nos solicitaba realizar una aplicación en el entorno de desarrollo de Android Studio que proporcionase un saludo personalizado, la posibilidad de guardar el nombre del usuario, la posibilidad de cambiar el fondo de la aplicación y botones para navegar entre las pantallas. 

## PANTALLA INICIO

En esta parte se nos pedia crear una pantalla que diera una bienvenida dependiendo de la hora en la que se este utilizando la aplicación y un boton que nos lleve a la actividad prinicipal, su psudocódigo sería: 

```
CLASE MainActivity HEREDA DE ComponentActivity {

    SOBREESCRIBIR función onCreate(Bundle?) {

        habilitarModoPantallaCompleta()

        CONFIGURAR Contenido {

            ESTRUCTURA_EN Scaffold {

                MODIFICADOR ocuparTodaPantalla()

                Greeting(modificador con padding y centrado)
            }
        }
    }
}

FUNCIÓN composable Greeting (modificador) {

    CONTEXTO = obtenerContextoActual()

    PREFERENCIAS = obtenerSharedPreferences("BackgroundPrefs")

    COLOR_GUARDADO = obtenerColorGuardado(preferencias, Color.BLANCO)

    COLOR_DE_FONDO = recordarEstadoConColorGuardado(COLOR_GUARDADO)

    CREAR COLUMNA {
        OCUPAR TodaPantalla
        FONDO = COLOR_DE_FONDO
        ALINEAR HORIZONTALMENTE al centro
        ORGANIZAR VERTICALMENTE al centro

        HORA_ACTUAL = obtenerHoraActual()

        SI HORA_ACTUAL < 13 ENTONCES 
            BIENVENIDA = "Buenos días"
        SI HORA_ACTUAL < 20 ENTONCES 
            BIENVENIDA = "Buenas tardes"
        SI NO
            BIENVENIDA = "Buenas noches"

        MOSTRAR_TEXTO(BIENVENIDA)

        ESPACIO(altura = 16.dp)

        CREAR_BOTON {
            AL_HACER_CLICK iniciarActividad(Inicio::class)
            MOSTRAR_TEXTO("APLICACIÓN")
        }
    }
}

FUNCIÓN obtenerColorParaCompose(color: Entero): ComposeColor {
    DEVOLVER nuevo ComposeColor (
        ROJO_DEL_COLOR / 255,
        VERDE_DEL_COLOR / 255,
        AZUL_DEL_COLOR / 255,
        ALFA_DEL_COLOR / 255
    )
}
```
En esta activity usamos la principal función para poder mostrar lo programado en las siguientes funciones. 

Las funcion de Greeting contiene unas variables las cuales son las encargadas de almacenar el color en el caso de que el usuario haya decidido cambiar el fondo de la aplicación. Si continuamos desglosando la funcion nos encontraremos un column para poder organizar los elementos de manera vertical. 

Dentro del column encontramos una variable que se encarga de coger la hora en ese momento y dependiendo de esta se mostrará un mensaje de bienvenida u otro. 

Por último, encontramos un boton que nos permite navegar a la siguiente pantalla, en este caso la inicial.

## ACTIVIDAD PRINCIPAL


