@echo off
set CONTAINER_ID=iou
echo %CONTAINER_ID%
call gradle clean war
docker build -t %CONTAINER_ID% .
docker stop %CONTAINER_ID%
docker rm %CONTAINER_ID%
docker run -p 8123:8080 --name %CONTAINER_ID% %CONTAINER_ID%

