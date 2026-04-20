#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
LAB8_DIR="$ROOT_DIR/lab8"
PORT="${LAB8_PORT:-8088}"

if ! command -v php >/dev/null 2>&1; then
  echo "php not found. Install PHP 8+ or add it to PATH."
  exit 1
fi

php -S "127.0.0.1:$PORT" -t "$LAB8_DIR"
