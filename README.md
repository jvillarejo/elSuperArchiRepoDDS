elSuperArchiRepoDDS
===================

Para correr el proyecto: 
	Crear en la carpeta .m2 el archivo settings.xml
	Pegar lo siguiente
	
	<settings xmlns="http://maven.apache.org/POM/4.0.0"  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0  http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <profiles>
                <profile>
                        <id>uqbar-wiki</id>
                        <repositories>
                                <repository>
                                        <id>uqbar-wiki.org-releases</id>
                                        <name>uqbar-wiki.org-releases</name>
                                        <url>http://uqbar-wiki.org/mvn/releases</url>
                                </repository>
                                <repository>
                                    <snapshots/>
                                    <id>uqbar-wiki.org-snapshots</id>
                                    <name>uqbar-wiki.org-snapshots</name>
                                    <url>http://uqbar-wiki.org/mvn/snapshots</url>
                                </repository>
                        </repositories>
                </profile>
    </profiles>

    <activeProfiles>
        <activeProfile>uqbar-wiki</activeProfile>
    </activeProfiles>

</settings>

	Ejecutar en el proyecto de dominio "mvn clean compile install"
	Luego ejecutar "mvn compile" en el proyecto de la UI



IMPORTANTE: Leer si usan la VM de la catedra
--------------------------------------------

Nosotros no tenemos Maven instalado en el sistema, por lo cual tenemos que hacer estos pasos previos:
  - Abrir una terminal y ejecutar *sudo apt-get install maven2*. Esto nos va a instalar el Maven en nuestro Ubuntu
  - Dentro del Eclipse, ir a __Window - Preferences - Maven - Installations__ y donde pide para seleccionar la instalacion, elegir la primera (Embedded)
  - Seguir las instrucciones para instalar aerolinea-lanchita
  
