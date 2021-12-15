FROM centos
MAINTAINER onurakan@gmail.com

RUN yum update -y && yum install passwd -y

RUN useradd ams_user && \
    echo "1234" | passwd ams_user --stdin

RUN yum install -y \
    java-1.8.0-openjdk \
    java-1.8.0-openjdk-devel

ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk/

CMD java -version && \
    echo "Started our ams-backend_container"