#!/bin/bash

PROJECT_NAME="react-cms"
HOST_PORT=8123
MVN_OPT=

# Compile frontend
cd script
npm run build
cd ..
gradle clean myCopy war
docker build -t $PROJECT_NAME .
docker stop $PROJECT_NAME
docker rm $PROJECT_NAME
docker run -p$HOST_PORT:8080 --name $PROJECT_NAME $PROJECT_NAME
