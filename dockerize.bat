@echo off
set CONTAINER_ID=react-cms
echo %CONTAINER_ID%
cd script
call npm run build
cd ..
call gradle clean myCopy war
call docker build -t %CONTAINER_ID% .
call docker stop %CONTAINER_ID%
call docker rm %CONTAINER_ID%
call docker run -p 8123:8080 --name %CONTAINER_ID% %CONTAINER_ID%

