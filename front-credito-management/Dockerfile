# Stage 1: build the Angular app
FROM node:22-alpine AS build

WORKDIR /app

# copy package files first to leverage docker cache
COPY package*.json ./
RUN npm ci

# copy sources and build
COPY . .
RUN npm run build -- --configuration production

# Stage 2: serve with nginx
FROM nginx:stable-alpine

# Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

# Copy custom nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

# copy build output to nginx html folder
# Copy the entire browser directory to /usr/share/nginx/html/browser
COPY --from=build /app/dist/front-credito-management/browser /usr/share/nginx/html/browser

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
