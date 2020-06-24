FROM openjdk:8

ENV SCALA_VERSION 2.12.11
ENV SBT_VERSION 1.2.8

WORKDIR /app
COPY . /app

RUN \
  curl -L -o sbt-$SBT_VERSION.deb http://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt && \
  sbt sbtVersion

RUN \
  sbt dist && \
  unzip target/universal/scala-fast-start-0.1.0.zip && \
  chmod a+x scala-fast-start-0.1.0/bin/scala-fast-start

EXPOSE 9000

CMD /app/scala-fast-start-0.1.0/bin/scala-fast-start
