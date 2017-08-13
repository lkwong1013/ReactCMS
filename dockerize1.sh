#!/bin/bash

PROJECT_NAME="react-cms"
HOST_PORT=8123
DEBUG_PORT=8000
MVN_OPT=

# Compile frontend
#cd script
#npm run build
#cd ..
gradle clean myCopy war
docker build -t $PROJECT_NAME .
docker stop $PROJECT_NAME
docker rm $PROJECT_NAME
docker run  -p$HOST_PORT:8080 \
            -p$DEBUG_PORT:$DEBUG_PORT \
            -e "JAVA_OPTS=\"-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n\"" \
            --name $PROJECT_NAME $PROJECT_NAME \
