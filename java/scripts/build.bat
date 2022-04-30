@setlocal

@set PROJECT=%GITPATH%\github.com\rsmaxwell\messaging\java
@set OUTPUT=%PROJECT%\output\main
@set DIST=%OUTPUT%\dist
@set CLASSES=%OUTPUT%\classes
@set SOURCE=%PROJECT%\src
@set RESOURCES=%PROJECT%\resources

@rd /S /Q %OUTPUT%
@mkdir %DIST%

dir /b /s %SOURCE%\*.java > %OUTPUT%\files.txt
javac -g -d %CLASSES% @%OUTPUT%\files.txt

@cd %CLASSES%
jar cfm %DIST%\messaging.jar %PROJECT%\manifest.txt .

@cd %RESOURCES%
jar uf %DIST%\messaging.jar .
