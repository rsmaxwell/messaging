@setlocal

@set PROJECT=%GITPATH%\github.com\rsmaxwell\messaging\java
@set OUTPUT=%PROJECT%\output\main
@set DIST=%OUTPUT%\dist

java -classpath %DIST%\messaging.jar com.rsmaxwell.adder.AdderServer 4444
