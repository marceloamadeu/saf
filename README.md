# Steps to build

_Prerequisites_:

* Maven 3.6.2 or higher
* OpenJDK 11 or higher

1. Build frontend

`mvn -U clean install -pl :frontend`

2. Build backend

`mvn -U clean package -pl :backend`

3. Build the docker image with:

`docker build -f backend/src/main/docker/Dockerfile.jvm -t quarkus/quarkus-saf-jvm .`

4. Run the container using:

`docker run -i --rm -p 8080:8080 -m 256m --cpus=2 quarkus/quarkus-saf-jvm`

## Remove images except one

`docker rmi $(docker image ls | grep -v "exception" | grep -v "IMAGE" | tr -s ' ' | cut -d ' ' -f3)`