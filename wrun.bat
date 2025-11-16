@echo off

REM Create the output directory if it doesn't exist
if not exist "src\classes" mkdir "src\classes"

REM Path to connector
set JAR_PATH=Lib\mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar

REM Compiling
javac -Xmaxerrs 10000 -Xmaxwarns 500 -Xlint ^
    -cp "%JAR_PATH%;src\classes" ^
    -d src\classes ^
    src\*.java src\model\*.java src\view\*.java src\controller\*.java src\util\*.java

REM ----- NEW SECTION: Copy assets folder -----
REM Copy all files inside assets/ (fonts, images, etc.)
if exist assets (
    echo Copying assets...
    xcopy /E /I /Y assets src\classes\assets >nul
)
REM -------------------------------------------

REM Run main if compiling is successful
if %errorlevel%==0 (
    java -cp "%JAR_PATH%;src\classes" Main
)