# Użyj obrazu bazowego NGINX
FROM nginx:latest

# Skopiuj zbudowaną aplikację Angular do katalogu NGINX
COPY dist/developer-frontend/browser /usr/share/nginx/html

# Skopiuj konfigurację NGINX
COPY nginx/default.conf /etc/nginx/conf.d/default.conf

# Otwórz port 80
EXPOSE 80
