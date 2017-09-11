@echo off
set CONTAINER_ID=iou
echo %CONTAINER_ID%
cd script
call npm run build
cd ..
call gradle clean myCopy build
java -jar build/libs/bootLesson-0.0.1-SNAPSHOT.jar

#gradle bootRun --debug-jvm