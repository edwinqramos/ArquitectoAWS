FROM openjdk:8-jdk-alpine
LABEL maintainer="edwinqramos@gmail.com"

ENV DOCKERIZE_VERSION v0.6.1

RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

WORKDIR /workspace
ADD ./target/service*.jar  app.jar

ENV host="mysql_server"
ENV port="3306"
ENV database="encuestabd"
ENV username="usr_app"
ENV password="pwdUsrApp"
ENV awsCognitoPoolId = "us-east-1_RS1UPfitM"
ENV awsCognitoClientId = "7jgae8od6snn1lijrvgl7fnrhh"
ENV awsCognitoRegion = "us-east-1"
ENV AWS_ACCESS_KEY_ID = "AKIAYM5W4Q55WUZHBL4A"
ENV AWS_SECRET_ACCESS_KEY = "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"

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /workspace/app.jar
EXPOSE 8080