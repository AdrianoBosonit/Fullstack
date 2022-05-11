# Ejercicio Final Bosonit

Este es el repositorio del ejercicio final de Bosonit, aqui explicare todo lo que he echo paso a paso, lo que hace cada endpoint etc.

![Imagen](https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/bosonit.png)


Este proyecto es un ejercicio en el que aplicaremos los conceptos aprendidos durante
la formacion de Java para realizar una aplicacion que simule una agencia de viajes de autobus.


## 1. Descripcion

Una agencia de autobuses que se encuentra en Vitoria realiza viajes para los siguientes destinos:
- Valencia
- Madrid
- Barcelona
- Bilbao

Las posibles hora de salida de los autobuses son:
- 8
- 12
- 16
- 20


Existiran principalmente dos tipos de back: BackEmpresa y BackWeb

- Backweb: Será el servidor que recibirá las peticiones del front donde se realizan las reservas. En nuestro caso, usaremos PostMan para realizar las peticiones.
- BackEmpresa: Será el servidor de la aplicación instalada en la empresa. Se comunicará con BackWeb pero tamboén podrá recibir reservas externas. Será el encargado de realizar la confirmacion de la reserva mediante el envío de un correo electronico.


Cada BackWeb tendrá su propia BBDD independiente. Lo mismo ocurrirá con BackEmpresa.


## 2. Tecnologías utilizadas

<img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/springbootLogo.png" width="150"> Implementar toda la lógica de la aplicación.


<img src="https://github.com/josemgarcia999/VIRTUAL-TRAVEL/blob/main/media/kafka.png" width="150"> Realizar comunicación asíncrona entre los diferentes Backs de la aplicación.


<img src="https://github.com/josemgarcia999/VIRTUAL-TRAVEL/blob/main/media/eureka.png" width="150"> Balanceador de carga para distribuir las peticiones entre los diferentes BackWebs


<img src="https://github.com/josemgarcia999/VIRTUAL-TRAVEL/blob/main/media/docker.png" width="150"> Exportar la aplicación y gestionar todos los servicios de cara al exterior.


## 3.Diagrama del proyecto 

<img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/DiagramaBosonit.png" > 


## 4. Aplicaciones

Nos podemos encontrar 4 carpetas referentes a las 4 aplicaciones que tenemos en nuestro proyecto:
1. **BackWeb:** Aplicación que gestiona la base de datos de la web.
2. **BackEmpresa:** Aplicación que gestiona la base de datos de la empresa.
3. **Balanceador:** Aplicación que gestiona el balanceo de carga junto con eureka
4. **Eureka:** Aplicación que gestiona el balanceo de carga.


## 5.Logica

Para entender los endpoints voy a explicar la lógica del proyecto general, un usuario reserva un viaje, este viaje lega al puerto del balanceador de carga. El balanceador de carga asigna una de las BacksWeb disponibles e inserta en su base de datos dicha reserva. Cada aplicación tiene su propia base de datos, esta añade una reserva cuando el usuario hace una reserva, pero esta ultima reserva solo se almacena en el backWeb al que le ha asignado el balanceador, para que las demás aplicaciones estén actualizadas uso la tecnología asíncrona de Kafka.

Con kafka uso varios topicos, para este caso, uso el topico sincronización, todas las aplicaciones están suscritas a ese topico y se envía una clase Mensaje que encapsula la reserva para saber quien manda el mensaje y evitar bucles escuchándose infinitamente una aplicación a si misma. Además, esta implementado para que si se desea insertar desde un backWeb especifico o desde un backEmpresa una reserva, el resto se actualice también. Aunque para el back empresa por seguridad no te lo permitirá, lo comentaremos posteriormente.

Una vez tenemos todas las bases de datos actualizadas, el backEmpresa tiene que dar el visto bueno de si esta disponible, es decir, hacer una actualización de las reservas, toda actualización de las reservas por parte de backEmpresa o incluso, si se elimina la reserva o un Viaje por la razón que sea, se realiza por un segundo topico llamado actualización.

En este segundo topico volvemos a enviar nuestra clase Mensaje pero esta clase, además tiene un enum, que nos dice que tipo de acción vamos a realizar si crear, actualizar o borrar. 

¿Cómo funciona? Pues el backEmpresa cada minuto envía todas las acciones que están pendientes por el topico actualización, los backs Web lo escuchan y actualizan o eliminan las reservas.

La lógica de este proyecto no contempla que desde el backWeb se puedan borrar o actualizar alguna reserva, algo que tiene mucho sentido, ya que un usuario no puede gestionar las reservas, no tiene sentido, aunque esta implementado y doy esa posibilidad en los endpoints, dará error porque el resto de aplicaciones no se actualizan.


##  6. Manual de usuario

En primer lugar, para iniciar la aplicación lo que haremos será descargar el proyecto y ejecutar el archivo docker-compose.yml para generar los contenedores correspondientes y arrancar la aplicación.(requiere tener docker instalado)
Para ello, en la ruta de la raiz del proyecto, pondremos el siguiente comando: 
```sh
docker-compose up -d
```
Tras ejecutar este comando, en la app de docker nos encontraremos esto:
<img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/contendedorDocker.png"> 
Como se puede observar, se crean los contenedores para las base de datos de cada back, kafka para la mensajería asíncrona, eureka para el balanceo de carga junto al balanceador y las instancias de los diferentes back, tanto webs como empresa.

Después de instalar correctamente la aplicación en docker, podemos empezar a realizar pruebas de la misma. Para ello, he adjuntado una colección de postman con los endpoints implementados, que voy a explicar a continuación.

<img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/todosEndPoints.png">

Como podemos observar, principalmente dividimos la colección en dos grandes grupos. BackWeb y BackEmpresa. Como dice en el word del ejercicio todos usan la ruta localhost:{puerto}/api/v0/{endpoint}. Los nombres que pongo a la izquierda es como los llamo yo en el Postman que tengo adjunto, una vez abierto se puede ver la verdadera ruta.


### 6.1 BackWeb y Balanceador:

<img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/backWebPostman.png">

Para todos los backwebs son los mismos endpoints pero en mi caso, voy a explicarlos para "ServidorBackWeb EUREKA" puesto que es el que se corresponde con el balanceador de carga y este se encargará de alternar entre las dos webs que tenemos en la aplicación.

- **addReserva:** La url es:*localhost:8980/api/v0/reserva*. Se encargará de realizar una reserva, requerirá una ReservaInputDto por un body cuyo formato es el siguiente: 

<img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/Json.png">.

- Hay que tener en cuenta las restricciones de hora y de ciudades disponibles ya que si no, no se hará correctamente la reserva. Además, se tiene que añadir la reserva en los demás backs (tanto web como empresa) ya que cuando se cree la reserva se enviará mediante kafka al tópico en el que están escuchando los backs.

- **buscaReservaCiudadFechaHora:** La url es: *localhost:8980/api/v0/disponible/Valencia?fechaInferior=01-05-2022*. Con este endpoint lo que haremos será listar aquellas reservas que han sido aceptadas. Debemos escribir la ciudad y además pasar por parámetros obligatoriamente una fecha de inicio y opcionalmente una fecha de fin, una hora de inicio y una fecha de fin. Tal que así:

- **allReservas:** La url es: *localhost:8980/api/v0/allReserva*. Devuelve todas las reservas, ya sean aceptadas o canceladas porque el autobús esté lleno.

- **allBus:** La url es: *localhost:8980/api/v0/allBus*. Devuelve todos los viajes.

- **DeleteReserva(*):** La url es: *localhost:8980/api/v0/deleteReserva/{id}* Borra la reserva correspondiente al id pasado por la parte variable del path.

- **DeleteBus(*)** La url es: *localhost:8980/api/v0/deleteBus/{id}* Borra el autobús correspondiente a ese id.

**NOTA(*):** Aunque estén implementados, yo nos los he utilizado puesto que la funcionalidad que he implementado consiste en que cuando borre una reserva desde backempresa, se borren en los backwebs en caso de que se encuentre en su base de datos. Están hechos para comprobar que la implementación estaba correcta.


### 6.2 BackEmpresa:

Tras ver BackWeb, procedo a explicar brevemente el funcionamiento de BackEmpresa, aunque el funcionamiento es muy similar al de los backwebs:

<img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/backEmpresaPostman.png">.

Como se puede ver, la carpeta reserva tiene los mismos endpoints que backweb solo que cambia la url, además de unos endpoints para los correos, login y check.
- **login:** En primer lugar, al tener seguridad, lo que deberemos de hacer es loguearnos mediante este endpoint.

<img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/login.png">.

Para probar la aplicación he creado un administrador. Accederemos a realizar el login mediante la siguiente url: *localhost:8080/api/v0/token* y seleccionando el username y password como se ve en la foto. Si el usuario es correcto obtendremos el token para poder realizarle peticiones a la API.

- **check:** Este método se para por parametro el token generado antes sin el Bearer y te responde si existe o no con un **200 ok**. La url es *localhost:8080/api/v0/token/{token}*

- **ReenviarCorreo:** La url es: *localhost:8080/api/v0/correos* . Se encargará de reenviar un correo dado un correoInputDto tal que asi.

- **ConsultaCorreos:** La url es: *localhost:8080/api/v0/correos/?fechaInferior=01-05-2030&fechaSuperior=09-05-2030&horaInferior=0&horaSuperior=8&ciudadDestino=BARCELONA*.  Se encargará de mostrar los correos de confirmación de un destino entre unas fechas y horas concretas. Requerirá unos parametros cuyo formato es el siguiente:
- <img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/ConsultaCorreos.png">.

El funcionamiento del resto de los endpoints tendra que usar el token generado anteriormente en el login de la siguiente forma:

<img src="https://github.com/AdrianoBosonit/Fullstack/blob/main/Spring%20Boot/back/ejercicioFinal/imagenesReadme/authorization.png">.

Por el header pasaremos el token generado de la misma forma que en la imagen anterior, en caso de que no lo pasemos o el usuario no tenga permisos se nos indicará con un error **403 forbidden** en postman.

De esta forma, tenemos una breve explicación del funciomamiento de la aplicación al completo y se ve como de forma sencilla mediante PostMan podemos someterla a diversas pruebas.

