FROM mysql:5.7.25
LABEL maintainer="edwinqramos@gmail.com"

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=encuestabd
ENV MYSQL_ROOT_HOST=%

COPY ./scripts/ /docker-entrypoint-initdb.d/
CMD ["--default-authentication-plugin=mysql_native_password"]