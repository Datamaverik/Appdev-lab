# AppLab Main

This repository is intended for the JSP and PHP labs only.

## Included

- MySQL JDBC connector jars are already vendored for the JSP labs:
  - [lab6a/src/main/webapp/WEB-INF/lib/mysql-connector-j-8.3.0.jar](lab6a/src/main/webapp/WEB-INF/lib/mysql-connector-j-8.3.0.jar)
  - [lab7a/src/main/webapp/WEB-INF/lib/mysql-connector-j-8.3.0.jar](lab7a/src/main/webapp/WEB-INF/lib/mysql-connector-j-8.3.0.jar)
- Database bootstrap SQL for the shared `AppLab` schema:
  - [offline/applab_init.sql](offline/applab_init.sql)
- Helper scripts for JSP and PHP labs:
  - [scripts](scripts)

## What You Need

- JDK 17+
- Apache Tomcat 9.0.x
- MySQL Server 8.x
- MySQL client (`mysql`)
- PHP 8+

## Windows Prerequisites (Detailed Setup)

Use this checklist on each Windows machine before running scripts.

### 1) Install JDK 17+ and verify `javac`

1. Install JDK 17 or newer.
2. Ensure `JAVA_HOME` points to JDK folder (not JRE).
3. Ensure `%JAVA_HOME%\\bin` is in `Path`.
4. Open a new Command Prompt and verify:

```bat
java -version
javac -version
echo %JAVA_HOME%
```

If `javac` is not recognized, fix `JAVA_HOME` and `Path` first.

### 2) Install MySQL Server + client and verify

1. Install MySQL Server 8.x.
2. Ensure MySQL service is running.
3. Ensure `mysql.exe` is available in `Path`.
4. Verify:

```bat
mysql --version
mysql -u root -p
```

Then run database bootstrap:

```bat
cd AppLab-main
scripts\init_db.cmd
```

### 3) Install PHP 8+ and enable MySQL extensions

If using ZIP package:

1. Extract to a fixed path, for example `C:\\tools\\php-8.3.x`.
2. Add that folder to `Path`.
3. Copy `php.ini-development` to `php.ini`.
4. In `php.ini`, enable:

- `extension_dir = "ext"`
- `extension=pdo_mysql`
- `extension=mysqli`

5. Verify:

```bat
php -v
php -m
```

Confirm modules include `PDO`, `pdo_mysql`, and `mysqli`.

### 4) Install/Extract Tomcat 9 and set `TOMCAT_HOME`

1. Download Tomcat 9 Core ZIP (64-bit Windows zip).
2. Extract it inside this repo at:

- `third_party\\tomcat\\apache-tomcat-9.0.117`

3. In Command Prompt from `AppLab-main`, set:

```bat
set TOMCAT_HOME=%cd%\third_party\tomcat\apache-tomcat-9.0.117
```

4. Verify Tomcat files exist:

```bat
dir "%TOMCAT_HOME%\bin\startup.bat"
dir "%TOMCAT_HOME%\lib\servlet-api.jar"
```

## Tomcat Download Choice

Download **Core -> 64-bit Windows zip** if you want the simplest portable setup on Windows.

Why:

- It is just the Tomcat files in a zip archive.
- You can unzip it anywhere and point `TOMCAT_HOME` to that folder.
- It is better for a repo-based, machine-independent setup than the service installer.

Use the **32-bit/64-bit Windows Service Installer** only if you want Tomcat installed as a Windows service on one machine.

Do not download:

- Full documentation, Deployer, Embedded, or Source Code distributions for normal JSP lab use.

For Linux, use **Core -> tar.gz**.

## Quick Start

Run all commands from the [AppLab-main](.) folder.

### Linux Commands

1. Initialize the database schema and seed data:

```bash
bash scripts/init_db.sh
```

The script prompts for MySQL root password. Leave it blank only if root has no password.

2. Set Tomcat path (for the current repo layout):

```bash
export TOMCAT_HOME="$PWD/third_party/tomcat/apache-tomcat-9.0.117"
```

3. Compile/deploy/run JSP apps (lab6a and lab7a):

```bash
bash scripts/run_web_labs.sh
```

4. Run PHP lab8 server:

```bash
bash scripts/run_lab8.sh
```

### Windows Commands (Command Prompt)

1. Open Command Prompt in [AppLab-main](.)

2. Initialize MySQL schema and seed data:

```bat
scripts\init_db.cmd
```

3. Set Tomcat path:

```bat
set TOMCAT_HOME=%cd%\third_party\tomcat\apache-tomcat-9.0.117
```

4. Compile/deploy/run JSP apps (lab6a and lab7a):

```bat
scripts\run_web_labs.cmd
```

5. Run PHP lab8 server:

```bat
scripts\run_lab8.cmd
```

## Offline Pendrive Plan (No Internet in Lab)

Yes, this can work on offline Windows machines if you carry installers and ZIPs in a pendrive.

### What to copy to pendrive

1. Your full repo clone (or zipped repo folder).
2. JDK installer (17+).
3. MySQL Server installer.
4. PHP ZIP package (x64 Thread Safe).
5. Tomcat 9 Core ZIP (64-bit Windows zip).

Optional:

6. A text file with your exact setup commands for quick copy-paste.

### Offline setup order on lab machine

1. Install JDK.
2. Install MySQL.
3. Extract PHP and add to `Path`.
4. Extract Tomcat into `AppLab-main\\third_party\\tomcat\\apache-tomcat-9.0.117`.
5. Open Command Prompt in `AppLab-main` and run:

```bat
scripts\init_db.cmd
set TOMCAT_HOME=%cd%\third_party\tomcat\apache-tomcat-9.0.117
scripts\run_web_labs.cmd
scripts\run_lab8.cmd
```

This is the closest to one-click with current implementation.
Tool installation is still one-time per machine.

### Windows PHP Setup (ZIP Method)

If `scripts\run_lab8.cmd` says php is not found, install PHP from ZIP using these steps:

1. Download PHP 8.x for Windows (x64, Thread Safe ZIP).
2. Extract to a fixed location, for example: `C:\tools\php-8.5.5`.
3. Open the extracted folder and confirm `php.exe` exists.

4. Optional temporary PATH (current Command Prompt only):

```bat
set PATH=C:\tools\php-8.5.5;%PATH%
php -v
```

5. Permanent PATH (recommended):
1. Press `Win` key and search `Environment Variables`.
1. Open `Edit the system environment variables`.
1. Click `Environment Variables...`.
1. Under `User variables` select `Path` and click `Edit`.
1. Click `New` and add `C:\tools\php-8.5.5`.
1. Click `OK` on all dialogs.
1. Close old Command Prompt windows and open a new one.

1. Verify PHP is now found globally:

```bat
php -v
where php
```

7. In PHP folder, create config file:
1. Copy `php.ini-development` and rename to `php.ini`.
1. Open `php.ini` in a text editor.
1. Ensure these lines are enabled:


    - `extension_dir = "ext"`
    - `extension=pdo_mysql`
    - `extension=mysqli`

8. Verify required modules:

```bat
php -m
```

Confirm output includes `PDO`, `pdo_mysql`, and `mysqli`.

9. Run PHP lab server:

```bat
cd AppLab-main
scripts\run_lab8.cmd
```

### URLs

- lab6a: http://localhost:8080/lab6a/
- lab7a employee: http://localhost:8080/lab7a/e/index.jsp
- lab7a book: http://localhost:8080/lab7a/h/index.jsp
- lab8 PHP: http://127.0.0.1:8088/a.php

For lab8 with the provided scripts, use `/a.php` and `/b.php`.
Do not use `/lab8/a.php` because the script serves the `lab8` folder itself as web root.

### Stop Services

Linux Tomcat stop command:

```bash
"$TOMCAT_HOME/bin/catalina.sh" stop
```

Windows Tomcat stop command:

```bat
"%TOMCAT_HOME%\bin\shutdown.bat"
```

## Notes

- The JSP code expects the `AppLab` database and tables created by [offline/applab_init.sql](offline/applab_init.sql).
- Keep the MySQL username/password consistent across the JSP files.
- If your Tomcat folder version changes, update only `TOMCAT_HOME` command and keep the same script flow.
