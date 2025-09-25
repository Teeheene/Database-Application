#!/bin/bash
# Create the output directory if it doesn't exist
mkdir -p classes

# Compile Java files into the classes/ folder
javac -Xmaxerrs 10000 -Xmaxwarns 500 -Xlint -d classes *.java model/*.java view/*.java

# Run Main from the classes folder
if [ $? -eq 0 ]; then
   java -cp classes Main 
fi

