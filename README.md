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

### Windows PHP Setup (ZIP Method)

If `scripts\run_lab8.cmd` says php is not found, install PHP from ZIP using these steps:

1. Download PHP 8.x for Windows (x64, Thread Safe ZIP).
2. Extract to a fixed location, for example: `C:\tools\php-8.3.x`
3. Add that folder to PATH:

- Open Environment Variables.
- Add `C:\tools\php-8.3.x` to Path.

4. Open a new Command Prompt and verify:

```bat
php -v
```

5. In the PHP folder, copy `php.ini-development` to `php.ini`.
6. Edit `php.ini` and ensure these are enabled:

- `extension_dir = "ext"`
- `extension=pdo_mysql`
- `extension=mysqli`

7. Verify extensions:

```bat
php -m
```

Make sure `PDO`, `pdo_mysql`, and `mysqli` are listed.

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
