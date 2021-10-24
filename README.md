# Project-management-system
I was given a task to make a project management application for a company that specializes in building construction. The application stores details about the project and also stores user information, the projects also store the users that are associated with that project.

## How it works
Users can add a new project to the application and store it's details in a database, they are also able to store the different types of user's details eg.Architect, Structural Engineer, Contractor and Customre etc. They are then able to edit and modify project details and also user details. When a project is fully constructed or perhaps fully paid for they can finalize the project which generates an invioice

### Features
(1) Add person details</br>
(2) Add projects</br>
(3) View projects</br>
(4) View incomplete projects</br>
(5) View projects past dead line</br>
(6) Edit/Update project</br>
(7) View persons</br>
(8) Edit/Update persons details</br>
(9) Finalize a project</br>

#### (1) Add person details
Simple and straight forward it prompts the user to enter in the persons details and which type of person they are eg.Structural engineer and it then adds it to the relevant database table

#### (2) Add project
Similar to the add person function but instead of person details it takes in the project details like the building type, due date, cost price etc. It also stores the relevant people associated with this project

#### (3) View projects 
Returns a list of of the projects with all it's details in a nice easy to read format 

#### (4) View incomplete projects 
Each project stores a boolean value stating whether they are complete or not this simply checks the projects table in the database to see if its complete or not, if not it prints out the incomplete project details 

#### (5) View projects past deadline 
This function is abit more complex then the previous one it checks if the project is incomplete if its incomplete then it checks its due date, if its due date is past the current data and it's marked as incomplete then it is considered past deadline 

#### (6) Edit/Update project 
User is prompted with a list of options of different things they can edit or modify on pre existing projects for example they spoke with the stake holder and have discussed a new price for the project the user can edit the project cost price

#### (7) View persons
This will return a list of all the people currently saved in the database in a easy to read format with all the user details

#### (8) Edit persons details 
User is asked to enter in the person name they would like to edit and they are then prompted to a new menu of things they are able to edit eg. the persons address changes 

#### (9) Finalize a project 
Once a project a fully constructed the user can finalize a project which will then calculate the how much is left to be paid by subtracting the paid amount from the total cost. If there is still money to be paid an invoice is then generated for the customer the project is assigned to along with the amount to be paid and the projects details. The project is also marked as completed

### Conclusion 
This project was mainly aimed at developing my skills in java and did teach me alot such as communicating to a database from a java application. It's not a complex application but I do want to expand on it one day by maybe adding so GUI which could really take this app to the next level 

