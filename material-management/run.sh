#!/bin/bash

# Skrypt do uruchamiania kontenera Docker
echo "Uruchamianie kontenera Docker na porcie 8083..."

docker run -d --name element-container -p 8083:8083 element-app

echo "Aplikacja działa pod adresem: http://localhost:8083"
        