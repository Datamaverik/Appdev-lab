@echo off
setlocal

set ROOT_DIR=%~dp0..
set SQL_FILE=%ROOT_DIR%\offline\applab_init.sql

if "%MYSQL_BIN%"=="" set MYSQL_BIN=mysql
if "%MYSQL_ROOT_USER%"=="" set MYSQL_ROOT_USER=root

where %MYSQL_BIN% >nul 2>&1
if errorlevel 1 (
  echo mysql client not found. Set MYSQL_BIN or install mysql client.
  exit /b 1
)

if not exist "%SQL_FILE%" (
  echo SQL file not found: %SQL_FILE%
  exit /b 1
)

if "%MYSQL_ROOT_PASSWORD%"=="" (
  set /p MYSQL_ROOT_PASSWORD=MySQL password for %MYSQL_ROOT_USER% (leave blank for none): 
  echo.
)

if "%MYSQL_ROOT_PASSWORD%"=="" (
  %MYSQL_BIN% -u%MYSQL_ROOT_USER% ^< "%SQL_FILE%"
) else (
  %MYSQL_BIN% -u%MYSQL_ROOT_USER% -p%MYSQL_ROOT_PASSWORD% ^< "%SQL_FILE%"
)

echo Database initialized using %SQL_FILE%
