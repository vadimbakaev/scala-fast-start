FROM alpine:edge

WORKDIR /app
COPY . /app

RUN \
  apk update --verbose --progress --purge && \
  apk add --no-cache openjdk8 && \
  apk add --no-cache --repository http://dl-cdn.alpinelinux.org/alpine/edge/testing sbt && \ 
  sbt dist && \
  unzip target/universal/scala-fast-start-0.1.0.zip && \
  chmod a+x scala-fast-start-0.1.0/bin/scala-fast-start

EXPOSE 9000

CMD /app/scala-fast-start-0.1.0/bin/scala-fast-start
