#!/bin/bash

podman stop mpira-db 2>/dev/null || true
podman rm mpira-db 2>/dev/null || true