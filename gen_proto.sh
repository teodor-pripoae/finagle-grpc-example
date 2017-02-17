#!/bin/bash

protoc -I ./src/main/protobuf src/main/protobuf/echo.proto --io.buoyant.grpc_out=plugins=grpc:src/main/scala
