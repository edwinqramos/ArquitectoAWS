#!/bin/bash -xe

yum update -y
yum install java-1.8.0 -y
yum remove java-1.7.0-openjdk -y

echo 'export host=rmwl29wmfqmdd3.c99xllpyleig.us-east-1.rds.amazonaws.com' >> ~/.bash_profile
echo 'export port=3306' >> ~/.bash_profile
echo 'export database=encuestabd' >> ~/.bash_profile
echo 'export username=root' >> ~/.bash_profile
echo 'export password=12345678' >> ~/.bash_profile
echo 'export awsCognitoPoolId=us-east-1_RS1UPfitM' >> ~/.bash_profile
echo 'export awsCognitoClientId=7jgae8od6snn1lijrvgl7fnrhh' >> ~/.bash_profile
echo 'export awsCognitoRegion=us-east-1' >> ~/.bash_profile

source ~/.bash_profile

wget https://github.com/edwinqramos/CustomerSatisfactionAppCallenge/raw/master/service-encuesta-0.0.1-SNAPSHOT.jar
nohup java -Djava.security.egd=file:/dev/./urandom -jar service-encuesta-0.0.1-SNAPSHOT.jar | tee output.log & 