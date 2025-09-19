@echo off
REM Create classes folder if it doesn't exist
if not exist classes mkdir classes

REM Compile all .java files into classes\
javac -d classes *.java controller\*.java model\*.java view\*.java
if %errorlevel% neq 0 (
    echo Compilation failed.
    exit /b %errorlevel%
)

REM Run Main from the classes folder
java -cp classes Main

