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

En esta parte se nos pedia crear una pantalla que diera la opcion de que el usuario introduzca un nombre, que este se guarde y un botón que te permita navegar hasta la pantalla de configuracion, su psudocódigo sería: 

```
CLASE Inicio HEREDA DE ComponentActivity {
    
    SOBREESCRIBIR función onCreate(Bundle?) {

        super.onCreate(savedInstanceState)

        CONFIGURAR Contenido {
            MostrarGreetingPreview()
        }
    }
}

FUNCIÓN lectorNombre(contexto: Context) {

    NOMBRE = recordar { estadoMutable("") }

    PREFERENCIAS_COMPARTIDAS = recordar {
        contexto.obtenerSharedPreferences("BackgroundPrefs", MODE_PRIVATE)
    }

    COLOR_GUARDADO = preferenciasCompartidas.getInt("backgroundColor", Color.BLANCO)

    COLOR_DE_FONDO = recordar { estadoMutable(getComposeColor(COLOR_GUARDADO)) }

    CREAR COLUMNA {
        OCUPAR TodaPantalla
        FONDO = COLOR_DE_FONDO
        ALINEAR HORIZONTALMENTE al centro
        ORGANIZAR VERTICALMENTE al centro

        CampoDeTexto(
            valor = NOMBRE.valor,
            alCambiarValor = { NOMBRE.valor = it },
            etiqueta = { MostrarTexto("Nombre") }
        )

        ESPACIO(altura = 16.dp)

        CrearBotón {
            con(preferenciasCompartidas.editar()) {
                ponerString("nombre", NOMBRE.valor)
                aplicar()
                contexto.iniciarActividad(Inicio::class)
            }
        }
        MostrarTexto("Guardar")

        ESPACIO(altura = 16.dp)

        NOMBRE_GUARDADO = recordar { preferenciasCompartidas.getString("nombre", "") ?: "" }

        CrearAndroidView { ctx ->
            TextView(ctx).aplicar {
                texto = "Bienvenido $NOMBRE_GUARDADO"
            }
        }

        ESPACIO(altura = 16.dp)

        BotonConfiguracion()
    }
}

FUNCIÓN MostrarGreetingPreview() {
    CONTEXTO = LocalContext.current

    lectorNombre(contexto)
}

FUNCIÓN BotonConfiguracion() {

    ESPACIO(altura = 16.dp)

    CONTEXTO = LocalContext.current

    CrearBotón {
        contexto.iniciarActividad(Config::class)
    }
    MostrarTexto("CONFIGURACION")
}

```
En esta activity usamos la principal función para poder mostrar lo programado en las siguientes funciones. 

La funcion de lectorNombre contiene unas variables las cuales son las encargadas de almacenar el color en el caso de que el usuario haya decidido cambiar el fondo de la aplicación y una variable para guardar el nombre en el caso de que este sea modificado. Si continuamos desglosando la funcion nos encontraremos un column para poder organizar los elementos de manera vertical.

Lo siguiente que encontramos es un TextField que mostrará el nombre que el usuario ha introducido, a continuación encontramos un boton que guardará el nombre introducido (para que en el caso de que el usuario se mueva entre pantallas el nombre siga registrado, este boton reinicia el activity para que el nombre se actualice cuando este se guarde. 

Después encontramos una variable que almacena el nombre y haciendo uso de textView se muestra un mensjae de bienvenida personalizado al usuario. En este caso usamos AndroidView para resolver problemas a la hora de utilizar el textView. 

Por último, encontramos un boton que permite al usuario navegar a la pantalla de configuracion. 

## CONFIG

En esta parte se nos pedia crear una pantalla que diera al usuario la opcion de cambair el fondo de la aplicación y volver al inicio de la aplicacion, su psudocódigo sería: 

```


```



