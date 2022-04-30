@setlocal

@set PROJECT=%GITPATH%\github.com\rsmaxwell\messaging\java
@set OUTPUT=%PROJECT%\output\main
@set DIST=%OUTPUT%\dist

java -classpath %DIST%\echo.jar com.rsmaxwell.adder.AdderClient localhost 4444
