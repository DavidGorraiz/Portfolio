# Vuelta A Colombia REST

## Introduction
This is a REST API created with SpringBoot that implements an oracle version 19c database to store data related to a cycling event that takes place in Colombia.

## Components
This project uses as a base:

- SpringBoot
- JDBC (11)
- Oracle 19c database
- Gradle

## Database design
![database](/Vuelta_Colombia.png)

## Database creation
We have a document called creation_db.sql, which can be executed directly in sql*plus. In this part we made the creation of a user in the database where the database schema is stored, at this point it is important to know if the oracle database is in container mode because if it is in this mode you must uncomment the alter lines at the beginning. This file if executed correctly makes the creation of the tables and their respective constraints.

## Configuration
The configuration of the API is done in two documents, first we must import the components that we are going to use with the document build.gradle. Then we have the application.properties document that is in src/main/resources/application.properties where we configure the name of the application, the port that is used for the application that in this case is 8082, we make the connection with the database “spring.datasource. url=jdbc:oracle:thin:@localhost:1521:xe”, where according to the installation of oracle database the connection port is 1521 generally but this can be checked in sql*plus and the name of the instance to which we are going to connect that in this case is xe (this name can be seen in sql*plus with ‘select instance_name from v$instance;’); then we say to which user and with which password we want to connect to the instance in this case ADMIN and password 1234. Finally the JDBC driver is called.

## Structure
The structure of this project is divided into 4 folders, each representing a part of the creation process:

- Model: In this part the classes that represent the database tables are created, thus using the @Entity and @Table annotation. Here are also defined the relationships between these same classes and their primary keys with the help of the annotations @ManyToOne, @JoinColumn and @Id. There is also a table that has a composite key which to represent it uses a separate class from the one that represents the table that implements the interface Serializabe and contains the attributes that are part of the composite key, and in the class that represents the table is used @IdClass instead of @Id.
- Repository: here the connection is made with the class to which the CRUD operations will be performed through an interface that extends JpaRepository which receives as parameters the class of the connection and the type of primary key that this entity has. 
- Services: this part of the project contains the logic to be developed to perform CRUD operations, since some entities depend on others, it is necessary to develop the appropriate logic. Here you must create the class with the @Service annotation and also inject the interfaces of the repository part required to perform the operations with @Autowired.
- Controller: here the user requests are received through different endpoints, this is done by creating a class with the annotations @RestController and @RequestMapping in the last one you specify how you want to call the endpoint of the class, you must also inject the service class associated to each entity with @Autowired. Finally the methods that return what the user wants according to the HTTP verb used are created with the help of the annotations @GetMapping, @PostMapping, @PutMapping and @DeleteMapping, these methods can be passed parameters through the path, as query params or in the body by including in the parameters the annotations @PathVariable, @RequestParam or @RequestBody.