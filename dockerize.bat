@echo off
set CONTAINER_ID=react-cms
set DEBUG_PORT=8000
set HOST_PORT=8123
echo %CONTAINER_ID%
cd script
call npm run build
cd ..
call gradle clean myCopy war
call docker build -t %CONTAINER_ID% .
call docker stop %CONTAINER_ID%
call docker rm %CONTAINER_ID%
call docker run -p%HOST_PORT%:8080 ^
                        -p%DEBUG_PORT%:%DEBUG_PORT% ^
                        -e "JAVA_OPTS=\"-agentlib:jdwp=transport=dt_socket,address=%DEBUG_PORT%,server=y,suspend=n\"" ^
                        --name %CONTAINER_ID% %CONTAINER_ID%


