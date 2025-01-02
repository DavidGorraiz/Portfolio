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
Se tiene un documento llamado creation_db.sql, el cual se puede ejecutar directamente en sql*plus. En esta parte se realizo la creacion de un usuario en la base de datos donde se almecena el esquema de la base de datos en este punto es importante saber si la base de datos oracle esta en modo contenedores puesto que si se encuentra en este modo se debe descomentar las lineas alter al inicio. Este archivo si se ejecuta correctamente hace la creacion de las tablas y sus respectivos constraints.

## Structure
The structure of this project is divided into 4 folders, each representing a part of the creation process:

- Model: In this part the classes that represent the database tables are created, thus using the @Entity and @Table annotation. Here are also defined the relationships between these same classes and their primary keys with the help of the annotations @ManyToOne, @JoinColumn and @Id. There is also a table that has a composite key which to represent it uses a separate class from the one that represents the table that implements the interface Serializabe and contains the attributes that are part of the composite key, and in the class that represents the table is used @IdClass instead of @Id.
- 