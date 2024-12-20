#!/bin/bash

# Skrypt do budowania obrazu Docker
echo "Budowanie obrazu Docker dla aplikacji Spring Boot..."

docker build -t element-app .

echo "Budowanie zakończone!"
