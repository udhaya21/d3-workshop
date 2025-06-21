FROM node:alpine
RUN npm i -g serve
WORKDIR /app
COPY index.html /app/index.html
CMD ["serve", "/app"]