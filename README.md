# ams  
Asset Management System

System consists of following container services:
  1- ams-frontend-service
      This is the frontend of the system. It is developed with Vuejs and, running on nginx.
  2- ams-backend-service
      This is the backend of the system. It is developed with spring boot, running on embedded Tomcat server. Service uses H2 as default database.
  3- ams-mysql-db-service (Optional)
      This is the optional database of the system. It is used when docker-compose is used with profile 'profile-mysql'. Check below how to start mysql container individually.
      Otherwise spring boot embedded H2 database is used.

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
    8-Connect to container os:  
      $ docker exec -ti ams-backend_container bash
      $ docker exec -ti ams-mysql-db_container bash
    9-Delete shutdown containers:  
      $ docker system prune  
## Sample UI Pages:
  http://localhost/
## Sample Rest Requests:  
- GET http://localhost:8080/api/assets/  
- GET http://localhost:8080/api/assets/1  
- GET http://localhost:8080/api/specifications/2  
- POST http://localhost:8080/api/assets  
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
- PUT http://localhost:8080/api/assets/1  
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
- DELETE http://localhost:8080/api/assets/3  
