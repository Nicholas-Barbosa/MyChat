#!/bin/sh
mvn clean package && docker build -t com.mychat/MyChat .
docker rm -f MyChat || true && docker run -d -p 9080:9080 -p 9443:9443 --name MyChat com.mychat/MyChat