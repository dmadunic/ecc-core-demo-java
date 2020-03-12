# ECC Core Poc Demo 

ECC Core Poc demo (Helloworld) Microservice.

## Running in dev mode

To build and run demo application simply run:

    ./gradlew

This will fire up default bootRun task and start the demo application with dev profile active.

To ensure everything is working navigate to [http://localhost:8080/api/hello](http://localhost:8080/api/hello) in your browser.

## Building for production

### Packaging as jar

To build the final jar and optimize the application for production, run:

    ./gradlew -Pprod clean bootJar

To ensure everything worked, run:

    java -jar build/libs/*.jar

## Testing [TODO]

To launch (all) demo application's tests, run:

    ./gradlew test integrationTest jacocoTestReport

### Code quality [TODO]

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the gradle plugin.

Then, run a Sonar analysis:

```
./gradlew -Pprod clean check jacocoTestReport sonarqube
```

## Swagger generated REST Api documentation
If the **swagger** profile is active, swagger generated REST Api documentation is available at the following url:

[http://localhost:8080/swagger-ui.html(http://localhost:8080/swagger-ui.html)

## Building (local) docker image [TODO]

To build a Docker image of the demo application using Jib connecting to the local Docker daemon, type:

```
./gradlew -Pprod bootJar jibDockerBuild
```

To build a Docker image of the demo application without Docker and push it directly into your Docker registry, run:
```
 ./gradlew -Pprod bootJar jib
```

To push docker image to specific docker registry as part of the CI step the following command can be used:

```
./gradlew jib -Pprod -Djib.to.image=$REGISTRY_URL/$IMAGE_TAG -Djib.to.auth.username=unused -Djib.to.auth.password=$DOCKER_REGISTRY_TOKEN --no-daemon
```

Thi assumes that the following environment variables are configured on CI system:
* REGISTRY_URL
* IMAGE_TAG
* DOCKER_REGISTRY_TOKEN

## Using Docker locally

After building a Docker image and pushing it to local Docker daemon, you can start demo microservice by running:

```
docker-compose -f src/main/docker/app.yml up
```
