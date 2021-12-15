# ams
Asset Management System

  Project Requirements:
    1-Download and install docket-desktop: https://www.docker.com/products/docker-desktop

  Running project:
    1-Go to project folder:
      cd ams
    2-Start docker containers by this command. (d for detached):
      docker-compose up -d
    3-Shut down running containers:
      docker-compose down
      Or:
      docker stop ams-backend_container
    4-List running containers (a for all):
      docker ps -a
    5-Restart running containers:
      docker restart ams-backend_container
    6-Print container logs:
      docker logs ams-backend_container
    7-Connect to container os:
      docker exec -ti ams-backend_container bash
    8-Delete shutdown containers:
      docker system prune
