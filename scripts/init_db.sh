#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
SQL_FILE="$ROOT_DIR/offline/applab_init.sql"
MYSQL_BIN="${MYSQL_BIN:-mysql}"
MYSQL_ROOT_USER="${MYSQL_ROOT_USER:-root}"
MYSQL_ROOT_PASSWORD="${MYSQL_ROOT_PASSWORD:-}"

if ! command -v "$MYSQL_BIN" >/dev/null 2>&1; then
  echo "mysql client not found. Set MYSQL_BIN or install mysql client."
  exit 1
fi

if [[ ! -f "$SQL_FILE" ]]; then
  echo "SQL file not found: $SQL_FILE"
  exit 1
fi

if [[ -z "$MYSQL_ROOT_PASSWORD" ]]; then
  read -r -s -p "MySQL password for $MYSQL_ROOT_USER (leave blank for none): " MYSQL_ROOT_PASSWORD
  echo
fi

if [[ -n "$MYSQL_ROOT_PASSWORD" ]]; then
  "$MYSQL_BIN" -u"$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" < "$SQL_FILE"
else
  "$MYSQL_BIN" -u"$MYSQL_ROOT_USER" < "$SQL_FILE"
fi

echo "Database initialized using $SQL_FILE"
