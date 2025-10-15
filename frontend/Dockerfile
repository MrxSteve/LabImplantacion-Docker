# ---------- build stage ----------
FROM node:20-alpine AS build
WORKDIR /app

# Copia deps y cachea instalación
COPY package*.json ./
# Si usas pnpm/yarn, ajusta aquí
RUN npm ci

# Copia el resto y build
COPY . .
# Inyecta la URL de la API al build (opcional). Usa VITE_API_URL en tu código.
ARG VITE_API_URL=http://localhost:8090/api
ENV VITE_API_URL=$VITE_API_URL
RUN npm run build

# ---------- runtime (nginx) ----------
FROM nginx:alpine

# (Investigación) Directorio por defecto de Nginx para estáticos:
# /usr/share/nginx/html
# Copiamos el build a ese directorio
COPY --from=build /app/dist /usr/share/nginx/html

# Copiamos config personalizada (SPA + try_files)
# Sobrescribe el default.conf por nuestra config
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponer puerto 80
EXPOSE 80

# Arrancar nginx en primer plano
CMD ["nginx", "-g", "daemon off;"]
