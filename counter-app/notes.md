## Docker master class

### Step 1: Dockerfile

```
FROM ubuntu
CMD ["echo", "Hello world"]
```

```
docker build -f docker/Dockerfile -t my-sample:latest .
docker run my-sample:latest
```

#### Points to remember

- registry
- platform (--platform linux/amd64)
- RUN node -v
- node slim, alpine

### Step 2: Node.js

```
FROM node:alpine
RUN node -v
CMD ["echo", "Hello world"]
```

- npm i -g serve
- serve an html file

### Step 3: Serve an HTML file

```
FROM node:alpine
RUN node -v
RUN npm i -g serve
COPY index.html /app/index.html
CMD ["serve", "/app"]
```

#### Binding ports OS port to container port

docker run counter-app:latest -p 3000:3000

### Step 4: Dockerfile with counter app

```
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
```

#### Dockerfile ignore

```
node_modules
.git
.github
.vscode
.npmrc
```

#### Points to remember

- corepack enable activates Node's built-in package manager tool (enables pnpm, yarn without installing separately)
- multi-stage build

```
docker run --rm -it counter-app:latest sh
```

- `--rm` removes the container after it exits
- `-it` runs the container in interactive mode with a terminal
- `sh` starts a shell in the container

```
export DOCKER_HOST=unix://$HOME/.colima/default/docker.sock
```

- This command sets the Docker host to use the Colima socket, allowing Docker commands to interact with the Colima-managed Docker environment.

### Step 5: Dive into the image

```
dive counter-app:latest
```

- `dive` is a tool to explore Docker images, showing layers, file sizes, and contents.
