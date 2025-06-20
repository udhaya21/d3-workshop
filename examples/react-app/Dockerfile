FROM node:alpine AS base
RUN corepack enable
FROM base AS build
WORKDIR /app/counter-app
COPY . .
RUN pnpm install
RUN pnpm build
FROM base AS dist
WORKDIR /app/dist
COPY --from=build /app/counter-app/dist .
RUN npm i -g serve
CMD ["serve", "/app/dist"]