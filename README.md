# finagle grpc example

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
