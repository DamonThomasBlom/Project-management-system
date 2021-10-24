package poisedPMS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Person {
	// Attributes 
	String name;
	String telephoneNumber;
	String emailAddress;
	String physicalAddress;
	
	/**
	 * Person constructor
	 * @param name Name of the person
	 * @param personType Type of the person example "Customer"
	 * @param telephoneNumber Telephone number of the person
	 * @param emailAddress Email address of the person
	 * @param physicalAddress Physical address of the person 
	 */
	public Person (String name, String telephoneNumber, String emailAddress, String physicalAddress) {
		this.name = name;
		
		this.telephoneNumber = telephoneNumber;
		this.emailAddress = emailAddress;
		this.physicalAddress = physicalAddress;
	}
	
	
	/**
	 * This method allows the user to add the details of any user currently assigned to a project
	 * @param rowsAffected
	 * @param statement
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static void addPerson(int rowsAffected, Statement statement) throws SQLException {
		Scanner input;
		String name;
		String telephoneNumber;
		String emailAddress;
		String physicalAddress;
		
		System.out.print("#NOTE: you can only add person details if they are already assigned to a project!\n");
		
		// Create a new customer person
		System.out.println("\nPlease enter in the following details.");
		
		// Name 
	    System.out.println("Full name: ");
		input = new Scanner(System.in);
		name = input.nextLine();
				
		
		// Telephone number 
		System.out.println("Telephone Number: ");
		input = new Scanner(System.in);
		telephoneNumber = input.nextLine();
					
		// Email address
		System.out.println("Email address: ");
		input = new Scanner(System.in);
		emailAddress = input.nextLine();
				
		// Physical address
		System.out.println("Physical Address: ");
		input = new Scanner(System.in);
		physicalAddress = input.nextLine();
				
		
		// Person type
		System.out.println("Select which type of person you would like to be adding\n"
				+ "1) Structural engineer\n"
				+ "2) Project manager\n"
				+ "3) Architect\n"
				+ "4) Customer");
		input = new Scanner(System.in);
		int option = input.nextInt();
		
		switch (option) {
		
		// Structural engineer 
		case 1:
			rowsAffected = statement.executeUpdate("insert into structural_engineers values ('" + name + "', '" + telephoneNumber + "', '" + emailAddress + "', '" + physicalAddress + "');");
			System.out.println("Query complete, " + rowsAffected + " rows added.");
			break;
		
		// Project manager
		case 2:
			rowsAffected = statement.executeUpdate("insert into proj_managers values ('" + name + "', '" + telephoneNumber + "', '" + emailAddress + "', '" + physicalAddress + "');");
			System.out.println("Query complete, " + rowsAffected + " rows added.");
			break;
			
		// Architect
		case 3:
			rowsAffected = statement.executeUpdate("insert into architects values ('" + name + "', '" + telephoneNumber + "', '" + emailAddress + "', '" + physicalAddress + "');");
			System.out.println("Query complete, " + rowsAffected + " rows added.");
			break;
			
		// Customer 
		case 4:
			rowsAffected = statement.executeUpdate("insert into customers values ('" + name + "', '" + telephoneNumber + "', '" + emailAddress + "', '" + physicalAddress + "')");
			System.out.println("Query complete, " + rowsAffected + " rows added.");
			break;
		}
	}
	
	
	/**
	 * This method will return all the details of every user currently stored on the database in an easy to read format 
	 * @param statement
	 * @throws SQLException
	 */
	public static void showPerson(Statement statement) throws SQLException{
		ResultSet results = statement.executeQuery("SELECT * FROM structural_engineers");
		
		// Structural engineers
		System.out.println("\n----- Structural engineers -----");
		while (results.next()) {
			System.out.println(
					"Name: " + results.getString("name") + "\n" +
					"Telephone: " + results.getString("telephone") + "\n" +
					"Email address: " + results.getString("email") + "\n" +
					"Physical address: " + results.getString("phys_address") + "\n"
					);
		}
		
		results = statement.executeQuery("SELECT * FROM proj_managers");
		
		// Project managers
		System.out.println("\n----- Project managers -----");
		while (results.next()) {
			System.out.println(
					"Name: " + results.getString("name") + "\n" +
					"Telephone: " + results.getString("telephone") + "\n" +
					"Email address: " + results.getString("email") + "\n" +
					"Physical address: " + results.getString("phys_address") + "\n"
					);
		}
		
		results = statement.executeQuery("SELECT * FROM architects");
		
		// Architects
		System.out.println("\n----- Architects -----");
		while (results.next()) {
			System.out.println(
					"Name: " + results.getString("name") + "\n" +
					"Telephone: " + results.getString("telephone") + "\n" +
					"Email address: " + results.getString("email") + "\n" +
					"Physical address: " + results.getString("phys_address") + "\n"
					);
		}
		
		results = statement.executeQuery("SELECT * FROM customers");
		
		// Customers
		System.out.println("\n----- Customers -----");
		while (results.next()) {
			System.out.println(
					"Name: " + results.getString("name") + "\n" +
					"Telephone: " + results.getString("telephone") + "\n" +
					"Email address: " + results.getString("email") + "\n" +
					"Physical address: " + results.getString("phys_address") + "\n"
					);
		}
	}
	
	
	/**
	 * This method allows the user to edit the details of any person currently saved on the database 
	 * @param rowsAffected
	 * @param statement
	 * @throws SQLException
	 */
	public static void editPersonDetails(int rowsAffected, Statement statement) throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		String personType = "";
		int personTypeChoice;
		String personName;
		int editChoice;
		
		// Edit options
		String telephone;
		String email;
		String physicalAddress;
		
		// Person type
		System.out.println("Select which type of person you would like to edit\n"
				+ "1) Structural engineer \n"
				+ "2) Project manager \n"
				+ "3) Architect \n"
				+ "4) Customer \n"
				+ "-1) Return to menu");
		personTypeChoice = sc.nextInt();
		
		// Assign the person type choice to the variable so we can use it to update to the right table
		switch(personTypeChoice) {
		
			case 1:
				personType = "structural_engineers";
				break;	
			case 2:
				personType = "proj_managers";
				break;	
			case 3:
				personType = "architects";
				break;	
			case 4:
				personType = "customers";
				break;	
		}
		
		// Person name
		System.out.println("Enter in the persons name: ");
		sc = new Scanner(System.in);
		personName = sc.nextLine();
		
		// Edit choice
		System.out.println("Select what you would like to edit about this person\n"
				+ "1) Telephone number\n"
				+ "2) Email address\n"
				+ "3) Physical address");
		sc = new Scanner(System.in);
		editChoice = sc.nextInt();
		
		
		switch(editChoice) {
		
			// Telephone number 
			case 1:
				System.out.println("Enter in the persons new telephone number");
				sc = new Scanner(System.in);
				telephone = sc.nextLine();
				
				// Update to the correct table choice 
				rowsAffected = statement.executeUpdate("UPDATE " + personType + " set telephone = '"+ telephone + "' where name = '" + personName + "';");
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				break;
				
			// Email address 
			case 2:
				System.out.println("Enter in the persons new email address");
				sc = new Scanner(System.in);
				email = sc.nextLine();
				
				// Update to the correct table choice 
				rowsAffected = statement.executeUpdate("UPDATE " + personType + " set email = '"+ email + "' where name = '" + personName + "';");
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				break;
				
			// Physical address
			case 3:
				System.out.println("Enter in the persons new physical address");
				sc = new Scanner(System.in);
				physicalAddress = sc.nextLine();
			
				// Update to the correct table choice 
				rowsAffected = statement.executeUpdate("UPDATE " + personType + " set phys_address = '"+ physicalAddress + "' where name = '" + personName + "';");
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				break;
		}
	}
}
