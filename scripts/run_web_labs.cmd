@echo off
setlocal enabledelayedexpansion

set ROOT_DIR=%~dp0..
set LAB6_DIR=%ROOT_DIR%\lab6a
set LAB7_DIR=%ROOT_DIR%\lab7a

if "%TOMCAT_HOME%"=="" set TOMCAT_HOME=%ROOT_DIR%\third_party\tomcat

if not exist "%TOMCAT_HOME%\bin\catalina.bat" (
  echo Tomcat not found at %TOMCAT_HOME%
  exit /b 1
)

set SERVLET_API_JAR=%TOMCAT_HOME%\lib\servlet-api.jar
set MYSQL_JAR=%LAB6_DIR%\src\main\webapp\WEB-INF\lib\mysql-connector-j-8.3.0.jar

if not exist "%SERVLET_API_JAR%" (
  echo Missing servlet-api.jar at %SERVLET_API_JAR%
  exit /b 1
)
if not exist "%MYSQL_JAR%" (
  echo Missing MySQL connector jar at %MYSQL_JAR%
  exit /b 1
)

where javac >nul 2>&1
if errorlevel 1 (
  echo javac not found. Install JDK 17+ and retry.
  exit /b 1
)

if not exist "%LAB6_DIR%\src\main\webapp\WEB-INF\classes" mkdir "%LAB6_DIR%\src\main\webapp\WEB-INF\classes"
del /s /q "%LAB6_DIR%\src\main\webapp\WEB-INF\classes\*.class" >nul 2>&1

set JAVA_FILES=
for /r "%LAB6_DIR%\src\main\java" %%F in (*.java) do (
  set JAVA_FILES=!JAVA_FILES! "%%F"
)

if not "!JAVA_FILES!"=="" (
  javac -encoding UTF-8 -cp "%SERVLET_API_JAR%;%MYSQL_JAR%" -d "%LAB6_DIR%\src\main\webapp\WEB-INF\classes" !JAVA_FILES!
  if errorlevel 1 exit /b 1
)

if exist "%TOMCAT_HOME%\webapps\lab6a" rmdir /s /q "%TOMCAT_HOME%\webapps\lab6a"
if exist "%TOMCAT_HOME%\webapps\lab7a" rmdir /s /q "%TOMCAT_HOME%\webapps\lab7a"
xcopy "%LAB6_DIR%\src\main\webapp" "%TOMCAT_HOME%\webapps\lab6a\" /e /i /q /y >nul
xcopy "%LAB7_DIR%\src\main\webapp" "%TOMCAT_HOME%\webapps\lab7a\" /e /i /q /y >nul

call "%TOMCAT_HOME%\bin\startup.bat"

echo Tomcat started.
echo lab6a: http://localhost:8080/lab6a/
echo lab7a employee: http://localhost:8080/lab7a/e/index.jsp
echo lab7a book: http://localhost:8080/lab7a/h/index.jsp
