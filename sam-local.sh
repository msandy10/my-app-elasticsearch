#!/bin/sh
docker build . -t my-app-elasticsearch
mkdir -p build
docker run --rm --entrypoint cat my-app-elasticsearch  /home/application/function.zip > build/function.zip

sam local start-api -t sam.yaml -p 3000

