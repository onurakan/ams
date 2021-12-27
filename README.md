# ams  
Asset Management System

# System consists of following container services:
  1- ams-frontend-service
      This is the frontend of the system. It is developed with Vuejs and, running on nginx.
  2- ams-backend-service
      This is the backend of the system. It is developed with spring boot, running on embedded Tomcat server. Service uses H2 as default database.
  3- ams-mysql-db-service (Optional)
      This is the optional database of the system. It is used when docker-compose is used with profile 'profile-mysql'. Check below how to start mysql container individually.
      Otherwise spring boot embedded H2 database is used.

# Ams System Topology 
  Check out the system topology using on https://draw.io/ website.
  You need to import ./ams-system-topology.drawio file to draw.io

## Project Requirements:  
    1-Download and install docket-desktop: https://www.docker.com/products/docker-desktop

## Running project:  
    1-Go to project folder:  
      $ cd ams  
    2-Start docker containers by this command. (d for detached):
      $ docker-compose --profile profile-default up -d 
      Or : In order to use Ams with Mysql database:
      $ docker-compose --profile profile-mysql up
    3-To start only Mysql container, namely ams-mysql-db-service, use this command:
      $ docker-compose up ams-mysql-db-service
    4-Shut down running containers:  
      $ docker-compose down  
      Or:  
      $ docker stop ams-backend_container  
    5-List running containers (a for all):  
      $ docker ps -a  
    6-Restart running containers:  
      $ docker restart ams-backend_container  
    7-Print container logs:  
      $ docker logs ams-backend_container
      $ docker logs ams-backend-mysql_container
    8-Connect to container os:  
      $ docker exec -ti ams-backend_container bash
      $ docker exec -ti ams-mysql-db_container bash
      $ docker exec -ti ams-frontend_container sh
    9-Delete shutdown containers:  
      $ docker system prune
## Throuble shooting:
    1- Due to architecture change, database schema changed. You may encounter following error. Therefore before running containers delete ./ams-mysql-db folder for a fresh database.
    Caused by: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`ams_db`.`#sql-1_2`, CONSTRAINT `FKviwhygdqrp22t52tbtaenkop` FOREIGN KEY (`specification_list_id`) REFERENCES `tspecification` (`id`))
    2- If you cant build project with intellij, enable annotation processor with classpath. Clean project (delete target). Then retry compile.
    https://stackoverflow.com/questions/29980133/intellij-idea-cannot-see-lombok-generated-code
## Sample UI Pages:
  http://localhost/
## Sample Rest Requests:  
- GET http://localhost:8080/api/asset/read  
- GET http://localhost:8080/api/asset/read/1  
- GET http://localhost:8080/api/specification/read/2  
- POST http://localhost:8080/api/asset/read/filter/1/20  
- POST http://localhost:8080/api/asset/read
    {  
          "assetId": 15,  
          "status": 0,  
          "classification": "NEW",  
          "description": "Aciklama",  
          "assetTag": null,  
          "specificationList": [  
              {  
                  "id": 16,  
                  "attribute": "new_attribute",  
                  "attributeDescription": "new_attribute_description",  
                  "dataType": "new_dataType",  
                  "alphnumericValue": "new_aplhanumericValue",  
                  "alphanumericDescription": "new_alphanumeric_description",  
                  "numericValue": "new_numericValue",  
                  "numericDescription": "new_numericDescription",  
                  "unitOfMeasure": "new_unitOfMeasue",  
                  "tableValue": "new_tableValue"  
              }  
          ]  
      }  
- PUT http://localhost:8080/api/asset/read/1  
      {  
        "assetId": 1,  
        "status": 2,  
        "classification": "NEW",  
        "description": "Aciklama1",  
        "assetTag": "assertTag11",  
        "specificationList": [  
            {  
                "id": 2,  
                "attribute": "new_attribute1",  
                "attributeDescription": "new_attribute_description2",  
                "dataType": "new_dataType3",  
                "alphnumericValue": "new_aplhanumericValue4",  
                "alphanumericDescription": "new_alphanumeric_description5",  
                "numericValue": "new_numericValue6",  
                "numericDescription": "new_numericDescription7",  
                "unitOfMeasure": "new_unitOfMeasue8",  
                "tableValue": "new_tableValue9"  
            }  
        ]  
    }  
- DELETE http://localhost:8080/api/asset/delete/3  


### Ams Project References:
- https://dzone.com/articles/spring-session-demonstration
- http://www.java-allandsundry.com/2015/04/spring-session-demonstration-using.html
  - https://github.com/bijukunjummen/shopping-cart-cf-app
  - https://www.baeldung.com/spring-security-extra-login-fields login page, login filter, spring security
- https://mkyong.com/maven/maven-jacoco-code-coverage-example/
- Spring Data Example
  https://dev.to/pavankjadda/search-data-across-multiple-columns-using-spring-data-jpa-8ed
- Boilerplate code removal dependencies:
  - https://mapstruct.org/
  - https://projectlombok.org/
  - http://hamcrest.org/
- https://developpaper.com/question/how-to-use-docker-to-package-vue-project-to-read-docker-environment-variables/
- https://stackoverflow.com/questions/13872273/api-pagination-best-practices