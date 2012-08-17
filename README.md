elSuperArchiRepoDDS
===================


Seteado el proyecto aterrizar

Para correrlo se necesitan los jar de Lanchita y Oceanic que subieron a la página. Para instalarlo hacemos lo siguiente:
  - Desde una consola, nos paramos en el repo y corremos el script: _$ sh install_jar.sh_. Esto nos baja los jar y los agrega como Maven dependencies
  




IMPORTANTE: Leer si usan la VM de la catedra
--------------------------------------------

Nosotros no tenemos Maven instalado en el sistema, por lo cual tenemos que hacer estos pasos previos:
  - Abrir una terminal y ejecutar *sudo apt-get install maven2*. Esto nos va a instalar el Maven en nuestro Ubuntu
  - Dentro del Eclipse, ir a __Window - Preferences - Maven - Installations__ y donde pide para seleccionar la instalacion, elegir la primera (Embedded)
  - Seguir las instrucciones para instalar aerolinea-lanchita
  