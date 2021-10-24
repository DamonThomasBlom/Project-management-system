package poisedPMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	
	/**
	 * Main method for the application that runs a loop of the menu 
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Scanner input = new Scanner(System.in);
		
		// Set up connection to database
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/poisedpms?useSSL=false",
				"otheruser",
				"swordfish"
				);
		
		// Create a direct line to the database for running our queries
		Statement statement = connection.createStatement();
		int rowsAffected = 0;
		
		
		
		while(true) {
			
			// Main menu
			System.out.println("\n####### Main menu #######\n\n"
							 + "(1) Add person details\n"
							 + "(2) Add projects\n"
							 + "(3) View projects\n"
							 + "(4) View incomplete projects\n"
							 + "(5) View projects past dead line\n"
							 + "(6) Edit/Update project\n"
							 + "(7) View persons\n"
							 + "(8) Edit/Update persons details\n"
							 + "(9) Finalize a project\n"
							 + "(10) Exit"
							 + "\n##########################\n");
			
			// Users selection
			int option;
			try {
				option = input.nextInt();
			
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Sorry you did not enter in a valid input.\nPlease enter in a different option\n");
				option = input.nextInt();
			}
			
			
			// (10) Save and exit
			if (option == 10) {
				System.out.println("Good bye...");
				break;
			}
			
			
			// Switch depending on users selection
			switch(option) {
			
				// (1) Add person 
				case 1:
					Person.addPerson(rowsAffected, statement);
					break;
					

				// (2) Add Project
				case 2:
					Project.createProject(rowsAffected, statement);
					break;
				
				
				// (3) View projects
				case 3:
					Project.showProjects(statement);
					break;
					

				// (4) View incomplete projects
				case 4:
					Project.showIncompleteProjects(statement);
					break;
						
				
				// (5) View projects past dead line
				case 5:
					Project.showProjectsPastDeadline(statement);
					break;
					
		
				// (6) Edit/Update project
				case 6:
					Project.editProject(rowsAffected, statement);
					break;
					
					
				// (7) View Persons
				case 7:
					Person.showPerson(statement);
					break;
			
		
				// (8) Edit/Update person contact details
				case 8:
					Person.editPersonDetails(rowsAffected, statement);
					break;
					
	
				// (9) Finalize project
				case 9:
					Project.finalizeProject(rowsAffected, statement);
					
					break;
			}
		}
	}
}
	

		
		
