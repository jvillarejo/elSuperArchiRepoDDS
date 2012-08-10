OCEANIC_JAR=aerolinea-oceanic-2.0.jar
LANCHITA_JAR=aerolinea-lanchita-2.0.jar
wget -O $OCEANIC_JAR www.ddsutn.com.ar/cursos/martes-manana-tarde/$OCEANIC_JAR
wget -O $LANCHITA_JAR www.ddsutn.com.ar/cursos/martes-manana-tarde/$LANCHITA_JAR
cd aterrizar-3052-04
mvn install:install-file -Dfile=../$LANCHITA_JAR -DgroupId=edu.utn.dds -DartifactId=aerolinea-lanchita -Dversion=2.0 -Dpackaging=jar
mvn install:install-file -Dfile=../$OCEANIC_JAR -DgroupId=edu.utn.dds -DartifactId=aerolinea-oceanic -Dversion=2.0 -Dpackaging=jar
rm -f $OCEANIC_JAR
rm -f $LANCHITA_JAR
