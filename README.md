elSuperArchiRepoDDS
===================


Seteado el proyecto aterrizar

Para correrlo se necesita el jar que subieron a la página. Para instalarlo hacemos lo siguiente:
  - Descargar el .jar de http://ddsutn.com.ar/cursos/miercoles-noche/aerolinea-lanchita.jar?attredirects=0
  - Ejecutarl o siguiente en la consola para instalar el jar a nuestro repo de maven donde path es la ruta donde está el jar
      mvn install:install-file -Dfile=/path/aerolinea-lanchita.jar -DgroupId=edu.utn.dds -DartifactId=aerolinea-lanchita -Dversion=1.0 -Dpackaging=jar
  - Ejecutar mvn eclipse:eclipse
  - Importar el proyecto al eclipse
  




IMPORTANTE: Leer si usan la VM de la catedra
--------------------------------------------

Nosotros no tenemos Maven instalado en el sistema, por lo cual tenemos que hacer estos pasos previos:
  - Abrir una terminal y ejecutar *sudo apt-get install maven2*. Esto nos va a instalar el Maven en nuestro Ubuntu
  - Dentro del Eclipse, ir a __Window - Preferences - Maven - Installations__ y donde pide para seleccionar la instalacion, elegir la primera (Embedded)
  - Seguir las instrucciones para instalar aerolinea-lanchita
  