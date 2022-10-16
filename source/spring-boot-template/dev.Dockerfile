FROM ibm-semeru-runtimes:open-17-jdk as builder
RUN apt-get update

WORKDIR /spring-boot-template/service

ARG UNAME=developer
ARG userID=${userID:-1000}
ARG groupID=${groupID:-1000}
RUN groupadd -g $groupID -o $UNAME
RUN useradd -l -d /app/source/spring-boot-template -u $userID -g $groupID -o -s /bin/bash $UNAME
USER $UNAME

EXPOSE 8080