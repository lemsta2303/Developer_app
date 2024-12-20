#!/bin/bash

# Skrypt do uruchamiania kontenera Docker
echo "Uruchamianie kontenera Docker na porcie 8081..."

docker run -d --name categories-container -p 8081:8081 categories-app

echo "Aplikacja działa pod adresem: http://localhost:8081"
