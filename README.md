# Database implementation for COVID with PL/SQL code blocks
## Introduction
This is a project where a user is created with its respective schema within an Oracle 19c database to store the information provided by a dataset on COVID cases for some years, this database has several procedures, functions and triggers that help to maintain and obtain the most relevant information without the need for complex queries.
## Database design
![database](/Taller_PL_SQL_covid.png)
This database is designed from the information found in the dataset called WHO-COVID-19-global-data.csv where we find information on countries, regions, dates, case counts and deaths. So three entities are obtained, one for the specific cases and two others for the countries and regions.
## Execution
To execute this project correctly, a certain order of PL/SQL execution must be followed. The recommended order is as follows:

1. Create_Database.sql: this program performs the creation of a user along with the tablespaces for the data and for the database indexes (at this point you can change the path where the datafiles will be created and if you want their size). Also the necessary accesses to manipulate data are granted together with the creation of the tables of the schema proposed. 

2. Package_insert_data.sql: what this part of the code does is to insert the data that is in the temp table (which is where the raw data of the dataset was loaded), to the other tables by means of a package created for that purpose that also loads data to a control table that is used to keep track of the actions that are made in the database.

3. Report_table.sql: this code creates a procedure that allows the creation of a report table where two dates are passed as parameters, a start date and an end date, where in that interval the cases and deaths per year and the totals in each country are calculated.

4. Triggers.sql: this code creates an audit table for the changes that are not insert but update and delete on the tables and creates triggers in each table to add the operation that was performed, who performed it and on which table it was performed in the audit table.

5. Cleaning_procedure.sql: this code creates a procedure that allows the cleaning of all tables and also loads the operations performed in the control table.