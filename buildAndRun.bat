@echo off
call mvn clean package
call docker build -t com.mychat/MyChat .
call docker rm -f MyChat
call docker run -d -p 9080:9080 -p 9443:9443 --name MyChat com.mychat/MyChat