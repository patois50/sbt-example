# SBT Example

This is an example of a multi module sbt project containing a simple hello-world http server.

The web-server binds on port `8080` and only contains one end-point `/hello`.

Assuming the application is running locally `http://localhost:8080/hello`.

## Building the App - Jar
To build a fat jar containing all dependencies required to run execute...
```
$ sbt assembly
```
You can then execute the jar with the following command...
```
$ java -jar service/target/scala-2.12/hello-world-assembly-0.0.1-SNAPSHOT.jar
```

## Building the App - Docker Image
To build a Docker Image containing the application execute the following...
```
$ sbt docker:publishLocal
```
> Note... The running this sbt command will run `assembly` to build the Fat Far so that it can be included in the Docker Image.

Run the docker image with the following command...
```
$ docker run -d -p 8080:8080 hello-world:0.0.1-SNAPSHOT
```
