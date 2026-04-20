#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
LAB6_DIR="$ROOT_DIR/lab6a"
LAB7_DIR="$ROOT_DIR/lab7a"
TOMCAT_HOME="${TOMCAT_HOME:-$ROOT_DIR/third_party/tomcat}"
SERVLET_API_JAR="$TOMCAT_HOME/lib/servlet-api.jar"
MYSQL_JAR="$LAB6_DIR/src/main/webapp/WEB-INF/lib/mysql-connector-j-8.3.0.jar"

if [[ ! -x "$TOMCAT_HOME/bin/catalina.sh" ]]; then
  echo "Tomcat not found at $TOMCAT_HOME"
  exit 1
fi

if [[ ! -f "$SERVLET_API_JAR" ]]; then
  echo "Missing servlet-api.jar at $SERVLET_API_JAR"
  exit 1
fi

if [[ ! -f "$MYSQL_JAR" ]]; then
  echo "Missing MySQL connector jar at $MYSQL_JAR"
  exit 1
fi

if ! command -v javac >/dev/null 2>&1; then
  echo "javac not found. Install JDK 17+ and retry."
  exit 1
fi

mkdir -p "$LAB6_DIR/src/main/webapp/WEB-INF/classes"
find "$LAB6_DIR/src/main/webapp/WEB-INF/classes" -type f -name '*.class' -delete

mapfile -t LAB6_JAVA_FILES < <(find "$LAB6_DIR/src/main/java" -type f -name '*.java' | sort)
if [[ ${#LAB6_JAVA_FILES[@]} -gt 0 ]]; then
  javac -encoding UTF-8 -cp "$SERVLET_API_JAR:$MYSQL_JAR" -d "$LAB6_DIR/src/main/webapp/WEB-INF/classes" "${LAB6_JAVA_FILES[@]}"
fi

rm -rf "$TOMCAT_HOME/webapps/lab6a" "$TOMCAT_HOME/webapps/lab7a"
cp -R "$LAB6_DIR/src/main/webapp" "$TOMCAT_HOME/webapps/lab6a"
cp -R "$LAB7_DIR/src/main/webapp" "$TOMCAT_HOME/webapps/lab7a"

"$TOMCAT_HOME/bin/catalina.sh" start

echo "Tomcat started."
echo "lab6a: http://localhost:8080/lab6a/"
echo "lab7a employee: http://localhost:8080/lab7a/e/index.jsp"
echo "lab7a book: http://localhost:8080/lab7a/h/index.jsp"
