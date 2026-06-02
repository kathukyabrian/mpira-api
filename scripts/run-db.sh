#!/bin/bash

podman start mpira-db 2>/dev/null || \
podman run \
  --name mpira-db \
  -p 5432:5432 \
  -e POSTGRES_USER=mpira \
  -e POSTGRES_PASSWORD=mpira2026 \
  -e POSTGRES_DB=mpira \
  -d postgres:14.23-trixie