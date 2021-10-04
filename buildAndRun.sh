#!/bin/sh
mvn clean package && docker build -t edu.utfpr/AppWork .
docker rm -f AppWork || true && docker run -d -p 9080:9080 -p 9443:9443 --name AppWork edu.utfpr/AppWork