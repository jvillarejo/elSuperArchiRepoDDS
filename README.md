elSuperArchiRepoDDS
===================


Seteado el proyecto aterrizar

Para correrlo se necesita el jar que subieron a la página. Para instalarlo hacemos lo siguiente:
  - Descargar el .jar de http://ddsutn.com.ar/cursos/miercoles-noche/aerolinea-lanchita.jar?attredirects=0
  - Ejecutarl o siguiente en la consola para instalar el jar a nuestro repo de maven donde path es la ruta donde está el jar
      mvn install:install-file -Dfile=/path/aerolinea-lanchita.jar -DgroupId=edu.dds.utn -DartifactId=aerolinea-lanchita -Dversion=1.0 -Dpackaging=jar
  - Ejecutar mvn eclipse:eclipse
  - Importar el proyecto al eclipse
  
