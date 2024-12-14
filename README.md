# PERSONAL BUDGET
#### Video Demo:  https://youtu.be/6z51qFcS6UY
#### Execution:
##### 1. Create virtual environment
python -m venv venv
##### 2. Activate virtual environment
venv\Scripts\activate
##### 3. Install Libraries
pip install -r requirements.txt
##### 4. Verify libraries
pip list
##### 5. Running flask
flask run
#### Description:
##### Introduction
The objective of this project is to have as a product a web page that allows us to make financial budgets to plan
our financial life for certain periods of time and also we can compare the budget of each period with the real values that we upload to the
page. For this I divided the budget into three parts which are: revenues, outgoings and savings, this to give more structure and organization on our
budget, this project also includes a database that will store all our data for the design of this database was used draw.io and also was
taken as a basis what was seen during the course.

To start this project we took as a template what we had done in the finance web page design exercise, so we
copied the files we had there into a
new folder for our project using the linux cp command.As I progress I will delete and modify all the files
through commands such as rm to delete.

##### Database
As mentioned in the introduction for the design of the database we used an online tool called draw.io there we
made the entity-relationship
model that helps us to identify the tables that we will use for the different objects that will be handled on the
web and how they relate to
each other.

The entities that were identified for this project are first a user entity that will contain the user's name and
hash (password) and will also
have a foreign key that relates this entity to the country entity that will contain information about the user's
country name and currency.

We also have the outgoins entity that allows us to store the category to which the outgoing belongs, the
description that tells us where the
outgoing comes from in more detail, the amount budgeted for that outgoing (budget), the actual amount of that
outgoing (actual), the date to
which that actual amount belongs (date_outgoing) and it will have three foreign keys that belong to the user id
and the other two are a
composite primary key that relates to an entity called periods that will contain the initial date and the final
date of the periods in which the
budgets will be made.Similarly we will have a revenue table that will contain that has the category, the
description, the budgeted for that
revenue (budget), the actual value of that revenue (actual), the date of the actual revenue (date_revenue) and
likewise will have three foreign
keys which are the user and the two dates of the period.Then we have the savings table which contains the
budgeted amount to be saved (budget),
the actual amount to be saved (actual), the date of saving (date_saving) and the same foreign keys for the user
and the dates of the period.
Finally we have the periods table that stores the start and end dates for each period to be budgeted and these
periods will be compared with the dates of the other tables to check that the budget is within the established
period.

##### Project structure

In this project several folders are used to store elements that help to the execution of the project in a correct
way as they are the folder flask sessions that stores the sessions of the users with flask, then we have the
static folder that stores the static elements of the web page as it is the css and the javascript along with some
images that were incorporated for the style of the page. Finally we have the Templates folder that stores the
html templates for the different parts of the web page where jinja is used.

Also in the main folder we have the main code app.py where we have the logic of what is done with the elements
once they are received from the front, here we also have the database file called finance.db, we have a helpers.
py code inherited from the exercise that was done in the course which helps you handle the 404 pages and the
format in which the money is presented on the page. py code inherited from the exercise that was done in the
course which helps to manage the 404 pages and the format in which the money is presented on the page, here we
also store this file README.md and finally the requiermnents.txt that contains all the library extensions needed
to run the project.

##### Functionality

The functionality of the app starts with a home page where users can register or directly log in. After logging
in there is a tab that shows several tables that summarize all the budgets that have been made along with a brief
explanation of the app, also have in the top bar the options to load budgets and actual values within a certain
period in these tabs you can add as many revenues and outgoings as you want, it should be clarified that you can
not load the same budget exactly or jump error, from there you can also log out or change the user's password.
