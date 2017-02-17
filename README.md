# finagle grpc example

## Dependencies

grpc-finagle is Work in Progress right now and you need to install development version (pre 0.9.0 and after 0.8.6).

```bash
$ # in linkerd project
$ git checkout 0.9.0-rc1
$ # rename version in project/Base.scala to 0.9.0

$ # in linkerd project
$ sbt
> grpc-gen/assembly // and copy the protoc plugin to your path
> finagle-h2/publishLocal
> grpc-runtime/publishLocal
```

## Run

```
$ sh gen_proto.sh # generate scala source from protobuf
$ sbt
> run server
> run client
```

## Enabling zipkin tracking

```
$ docker run -p 9411:9411 -p 9410:9410 openzipkin/zipkin
$ export SCRIBE_HOST=$DOCKER_IP
$ export TRACING_ENABLED=true
```
