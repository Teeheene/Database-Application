@echo off
REM Create the output directory if it doesn't exist
if not exist "src\classes" mkdir "src\classes"

REM Path to connector
set JAR_PATH=lib\mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar

REM Compiling
javac -Xmaxerrs 10000 -Xmaxwarns 500 -Xlint ^
    -cp "%JAR_PATH%;src\classes" ^
    -d src\classes ^
    src\*.java src\model\*.java src\view\*.java src\controller\*.java src\util\*.java

REM Run main if compiling is successful
if %errorlevel%==0 (
    java -cp "%JAR_PATH%;src\classes" Main
)