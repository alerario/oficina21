@echo off
call mvn clean package
call docker build -t edu.utfpr/AppWork .
call docker rm -f AppWork
call docker run -d -p 9080:9080 -p 9443:9443 --name AppWork edu.utfpr/AppWork