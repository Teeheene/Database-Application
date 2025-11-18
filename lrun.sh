#!/bin/bash
# Create the output directory if it doesn't exist
mkdir -p src/classes

# Path to connector
MYSQL_JAR="lib/mysql-connector-j-9.4.0.jar"
OPENPDF_CORE_JAR="lib/openpdf-3.0.1-SNAPSHOT.jar"
OPENPDF_SWING_JAR="lib/pdf-swing-3.0.1-SNAPSHOT.jar"

CP="$MYSQL_JAR:$OPENPDF_CORE_JAR:$OPENPDF_SWING_JAR:src/classes"

# Compiling
javac -Xmaxerrs 10000 -Xmaxwarns 500 -Xlint \
	-cp "$CP" \
	-d src/classes \
	src/*.java src/model/*.java src/view/*.java src/controller/*.java src/util/*.java

# Run main if compiling is successful 
if [ $? -eq 0 ]; then
   java -cp "$CP" Main 
fi

