#!/bin/bash

# Skrypt do budowania obrazu Docker
echo "Budowanie obrazu Docker dla aplikacji Spring Boot..."

docker build -t categories-app .

echo "Budowanie zakończone!"
