#!/bin/bash
docker stop angular-container || true
docker rm angular-container || true

docker run -d --name angular-container -p 8086:80 angular-nginx-app

