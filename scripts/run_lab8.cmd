@echo off
setlocal

set ROOT_DIR=%~dp0..
set LAB8_DIR=%ROOT_DIR%\lab8
if "%LAB8_PORT%"=="" set LAB8_PORT=8088

where php >nul 2>&1
if errorlevel 1 (
  echo php not found. Install PHP 8+ or add it to PATH.
  exit /b 1
)

php -S 127.0.0.1:%LAB8_PORT% -t "%LAB8_DIR%"
