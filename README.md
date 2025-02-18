# Kubernetes Native Quarkus

This application demonstrates how to create a Quarkus application that retrieves messages from a Kafka topic ("power") and inserts the events into a database. 

Watch a live demo from the Jfokus conference here: https://youtu.be/haulxnEtjUc?si=D_gpALKS75UVR65V

It features:

- a **DevicePower** class that represents the database entity
- a **KafkaResource** class that shows how to retrieve messages from a Kafka topic
- a **PowerResource** class that returns the values from the database table

You can run this demo on your local machine with Quarkus Dev Mode (see below) which will spin up a Kafka and PostgreSQL testcontainer.
The application.properties file shows how to set limits, requests, secrets and configmaps for your kubernetes environment.

## Deploying to Kubernetes

If you need a Kubernetes cloud environment to test with, you can get a free Openshift Developer sandbox at https://developers.redhat.com/sandbox.  

You will have to create the configmap and secret before deploying the application. Examples can be found in the src/main/kube folder.
You will also need to create a postgresql database (db 'quarkus') and a Kafka cluster. You can use the kafka.yml and postgresql.yml in the same src/main/kube folder if you want simple ephemeral test instances.

You can apply these files with `kubectl apply -f src/main/kube`.

Before building & deploying the container image, make sure to update the application.properties to point to your registry:

```ini
quarkus.container-image.registry=quay.io
quarkus.container-image.group=kevindubois
```

Then you can package your app, build it into a container and push to a registry with `quarkus image push --also-build`
And deploy it with `quarkus deploy`
  
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```bash
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```bash
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```bash
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```bash
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```bash
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/kubenative-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.
