## Docker master class

### Step 1: Dockerfile

```
FROM ubuntu
CMD ["echo", "Hello world"]
```

```
docker build -f Dockerfile -t my-sample:latest .
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
#Base stage
FROM node:alpine AS base
RUN corepack enable
#Build stage
FROM base AS build
WORKDIR /app/counter-app
COPY . .
RUN pnpm install
RUN pnpm build
#Dist stage
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

### Step 6: Deployments

- **Github actions**: Automate builds and deployments using GitHub Actions.
- **Github registry**: Store Docker images in GitHub's container registry.
- **render.com**: A platform for deploying web applications, including Docker containers.

```
name: Docker

on:
  push:
    branches: [main]
  pull_request:
    branches: [main]

permissions:
  contents: read
  packages: write

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Set up Docker Buildx
        uses: docker/setup-buildx-action@v3

      - name: Login to GitHub Container Registry
        uses: docker/login-action@v3
        with:
          registry: ghcr.io
          username: ${{ github.actor }}
          password: ${{ secrets.GITHUB_TOKEN }}

      - name: Build and push
        uses: docker/build-push-action@v6
        with:
          context: ./examples/react-app
          file: ./examples/react-app/Dockerfile
          push: true
          tags: ghcr.io/${{ github.repository }}-react-app:latest
```

#### Steps to setup render.com

- Create a new web service
- Choose existing image from GitHub Container Registry
- ghcr.io/udhaya21/d3-workshop-react-app:latest
- Choose "Free" plan
- Deploy
