FROM tomcat:8.5-jre8

EXPOSE 8080

ADD build/libs/react-cms.war /usr/local/tomcat/webapps/

ENV TZ=Asia/Hong_Kong
#following cmd can be ignored for setting timezone
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD ["catalina.sh", "run"]:q