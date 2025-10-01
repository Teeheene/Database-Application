#!/bin/bash
# Create the output directory if it doesn't exist
mkdir -p src/classes

# Compile Java files into the classes/ folder
javac -Xmaxerrs 10000 -Xmaxwarns 500 -Xlint -d src/classes src/*.java src/model/*.java src/view/*.java src/controller/*.java

# Run Main from the classes folder
if [ $? -eq 0 ]; then
   java -cp src/classes Main 
fi

