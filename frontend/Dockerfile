FROM node:19.2-alpine as compile-image

WORKDIR /app
COPY package*.json ./
COPY .env ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.23-alpine as runtime-image

EXPOSE 80
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=compile-image /app/build /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]
