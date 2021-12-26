# ams  
Asset Management System Backend

## Running project:  
    $ java -jar target/ams-backend-0.0.1-SNAPSHOT.jar

    Or
    $ mvn spring-boot:run
    
    Or (you should start msql container before running spring boot application)
    $ docker-compose up ams-mysql-db-service
    $ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=mysql"

## Debugging project:
    - Because of the mapstruct maven plugin dependeny, project should ve started as below. Intellij Build button doesnt work, In order to make it working, you should do the following setting:
        $ mvn spring-boot:run -Dspring-boot.run.fork=false
    
        Intellij->IDE SEttings->Build,Execution, Deployment->Compiler->Annotation profile for ams-backend->ams-backend:
            Select this: Obtain processors from project classpath
            Not this: Processor Patn
    - 