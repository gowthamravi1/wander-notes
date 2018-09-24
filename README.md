# wander-notes

# How to run in docker 

1) Install Java and docker

2) ./gradlew docker

3) docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7

4) "docker exec -it mysql bash" and create database "create database wander_notes" 

5) docker run --link mysql:mysql --name wander-notes -p 8080:8080 -e DB_HOST=mysql -e DB_USERNAME=root -e DB_PASSWORD=root -e DB_PORT=3306 -e DB_NAME=wander_notes docker.wander.com/notes
