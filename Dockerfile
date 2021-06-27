FROM tomcat:8.5.68-jdk8-adoptopenjdk-openj9

LABEL maintainer="heaty566@gmail.com"

ADD /dist/PRJ301_SE08D_BookingHotel.war /usr/local/tomcat/webapps/


EXPOSE 8080
CMD ["catalina.sh", "run"]