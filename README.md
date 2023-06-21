# crud-task
An task for Digital Chief Trainee Program

# dependencies
Spring MVC
Spring Data JPA (Hibernate ORM Provider)
Postgresql JDBC Driver

# running
1. Clone repo
``` git clone https://github.com/ankoo0/crud-task.git```
2. Open CMD and change directory to project you just cloned
3. Build Postgresql Docker image
```docker build -t my-postgres-db ./```
4. Run Postgresql Docker container
```docker run -d --name my-postgresdb-container -p 8081:5432 my-postgres-db```
5. Run Spring Boot application
6. Open Postman and hit File - Import and drag'n'drop ```task_postman_collection.json``` from project directory to corresponding window
7. Now you can execute commands and see results of application work


