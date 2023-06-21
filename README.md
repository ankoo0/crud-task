# crud-task
An task for Digital Chief Trainee Program

# domain area description
Comments and posts it's an important part of many online communities. This composition can bee seen in many real world applications (for example, Twitter, Reddit, etc).
Post - it's an distinct piece of content that a user shares with others. Most important and frequently used characteristics of post are: author, creation date, content and title.
Commentary - it's an opinion that user leaves under the post. Usally, commentaries described by author, content and creation time.
One post can have many commentaries, so the relation between Post and Comment entites in application should be One-To-Many.

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


