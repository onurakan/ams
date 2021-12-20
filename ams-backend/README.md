# ams  
Asset Management System Backend

## Running project:  
    $ java -jar target/ams-backend-0.0.1-SNAPSHOT.jar

    Or
    $ mvn spring-boot:run
    
    Or (you should start msql container before running spring boot application)
    $ docker-compose up ams-mysql-db-service
    $ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=mysql"