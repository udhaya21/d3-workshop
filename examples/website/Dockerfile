FROM node:alpine

RUN npm i -g http-server

COPY index.html /app/index.html
COPY meta.json /app/meta.json

ENV PORT 3000
EXPOSE 3000

CMD ["http-server", "/app"]

