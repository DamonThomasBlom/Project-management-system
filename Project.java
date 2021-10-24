package poisedPMS;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Project {
	int projectNumber;
	String projectName;
	String buildingType;
	String projectAddress;
	int ERFNumber;
	double totalFee;
	double paidAmount;
	String deadline;
	boolean completed;
	double owedAmount;
	String completionDate;
	String structuralEngineer;
	String projectManager;
	String architect;
	String customer;
	
	/**
	 * Project constructor
	 * @param projectNumber Project number
	 * @param projectName Project name
	 * @param buildingType Building type example house
	 * @param projectAddress Project address
	 * @param ERFNumber ERF number
	 * @param totalFee Total cost of the project
	 * @param paidAmount Total amount paid to date 
	 * @param deadline Deadline of the project
	 */
	public Project(int projectNumber, String projectName, String buildingType, String projectAddress, int ERFNumber, double totalFee, double paidAmount, String deadline) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.projectAddress = projectAddress;
		this.ERFNumber = ERFNumber;
		this.totalFee = totalFee;
		this.paidAmount = paidAmount;
		this.deadline = deadline;		
	}
	
	
	/**
	 * Takes in a string of all the peoples names involved in the project
	 * @param structuralEngineer
	 * @param projectManager
	 * @param architect
	 * @param customer
	 */
	public void setPersons(String structuralEngineer, String projectManager, String architect, String customer) {
		this.structuralEngineer = structuralEngineer;
		this.projectManager = projectManager;
		this.architect = architect;
		this.customer = customer;
	}
	
	
	/**
	 * This method prompts the user to enter in the project details and inserts all the data into the corresponding tables 
	 * @param rowsAffected
	 * @param statement
	 * @throws SQLException
	 */
	public static void createProject(int rowsAffected, Statement statement) throws SQLException {
		
		Scanner input;
		int projectNumber;
		String projectName;
		String buildingType;
		String projectAddress;
		int ERFNumber;
		double totalFee;
		double paidAmount;
		String deadline;
		
		// People involved in the project
		String structuralEngineer;
		String projectManager;
		String architect;
		String customer;
		
		
		// Create a new project
		System.out.println("\nPlease enter in the project details.");
		
		// Project name
		System.out.println("Project name: ");
		input = new Scanner(System.in);
		projectName = input.nextLine();
		
		// Building type
		System.out.println("Building Type: ");
		input = new Scanner(System.in);
		buildingType = input.nextLine();
		
		// Project address
		System.out.println("Project address: ");
		input = new Scanner(System.in);
		projectAddress = input.nextLine();
		
		// ERF number
		// More exception handling to avoid getting the wrong input 
		try {
			System.out.println("ERF number: ");
			input = new Scanner(System.in);
			ERFNumber = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Sorry you did not enter a valid input, Please try again.\nERF number: ");
			input = new Scanner(System.in);
			ERFNumber = input.nextInt();
		}
			
		// Total cost
		// We also do exception handling here to make sure that the user enters an integer as an amount so we can do mathematical calculations
		try {
			System.out.println("Total cost of the project: ");
			input = new Scanner(System.in);
			totalFee = input.nextDouble();
			// If the cost is a negative number
			if (totalFee < 0) {
				System.out.println("Sorry the total cost can not be negative\nPlease try again...");
				totalFee = input.nextDouble();
			}
		} catch (InputMismatchException e) {
			System.out.println("Sorry you did not enter a valid input, Please try again.\nTotal cost of the project: ");
			input = new Scanner(System.in);
			totalFee = input.nextDouble();
			}
		
		// Amount paid
		try {
			System.out.println("Total amount paid to date: ");
			input = new Scanner(System.in);
			paidAmount = input.nextDouble();
			if (paidAmount < 0) {
				System.out.println("Sorry the total amount paid can not be negative\nPlease try again...");
				totalFee = input.nextDouble();
			}
		} catch (InputMismatchException e) {
			System.out.println("Sorry you did not enter a valid input, Please try again.\nTotal amount paid to date: ");
			input = new Scanner(System.in);
			paidAmount = input.nextDouble();
		}
		
		// Deadline
		input.nextLine();
		System.out.println("Deadline of the project(eg. 2001-02-21) : ");
		input = new Scanner(System.in);
		deadline = input.nextLine();
		
		// Structural engineer
		System.out.println("Structural engineer name : ");
		input = new Scanner(System.in);
		structuralEngineer = input.nextLine();
		
		// Project manager
		System.out.println("Project manager name : ");
		input = new Scanner(System.in);
		projectManager = input.nextLine();
		
		// Architect
		System.out.println("Architect's name : ");
		input = new Scanner(System.in);
		architect = input.nextLine();
		
		// Customer
		System.out.println("Customer name : ");
		input = new Scanner(System.in);
		customer = input.nextLine();
				
		// If there is no project name we use the persons last name plus the building type and store it as the project name
		if (projectName == "") {
			String lastName = customer.substring(customer.lastIndexOf(" ")+ 1);  // We get the last index of the full name
			projectName = buildingType + " " + lastName;   // Set the project name to the building type and user surname
		}
		
		// Insert into projects table 
		rowsAffected = statement.executeUpdate("insert into projects(proj_name, phys_address, total_fee, struc_engineer, proj_manager, architect, customer) values "
										+   "('"+ projectName +"', '"+ projectAddress +"', "+ totalFee +", '"+ structuralEngineer +"', '"+ projectManager+"', '"+ architect+"', '"+ customer+"');");
		System.out.println("Query complete, " + rowsAffected + " rows added.");
		
		
		// Insert into prices table
		rowsAffected = statement.executeUpdate("insert into prices(total_fee, amount_paid, deadline) values (" + totalFee + ", " + paidAmount + ", '" + deadline + "');");
		
		
		// Insert into structure details table 
		rowsAffected = statement.executeUpdate("insert into structure_details values ('" + projectAddress + "','" + buildingType + "'," + ERFNumber + ");");
	}
	
	
	
	/**
	 * This statement returns all the projects that are currently on the database in a readable format
	 * @param statement
	 * @throws SQLException
	 */
	public static void showProjects(Statement statement) throws SQLException{
	ResultSet results = statement.executeQuery("SELECT * FROM projects");
	
	// Display results
	while (results.next()) {
	System.out.println(
		"\nProject number: " + results.getInt("proj_num") + "\n"
		+ "Project name: "+ results.getString("proj_name") + "\n"
		+ "Physical address: "+ results.getString("phys_address") + "\n"
		+ "Total price: R" + results.getInt("total_fee") + "\n"
		+ "Structural engineer: "+ results.getString("struc_engineer") + "\n"
		+ "Project manager: "+ results.getString("proj_manager") + "\n"
		+ "Architect: "+ results.getString("architect") + "\n"
		+ "Customer: "+ results.getString("customer")
		);
	}
}
	
	
	/**
	 * This method returns a list of all the incomplete projects
	 * @param statement
	 * @throws SQLException
	 */
	public static void showIncompleteProjects(Statement statement) throws SQLException{
		ResultSet results = statement.executeQuery(
				"SELECT * FROM projects pr JOIN prices p on pr.total_fee = p.total_fee where p.completed = false;");
		
		// Display results
		System.out.println("----- Incomplete Projects -----");
		while (results.next()) {
		System.out.println(
			"\nProject number: " + results.getInt("proj_num") + "\n"
			+ "Project name: "+ results.getString("proj_name") + "\n"
			+ "Physical address: "+ results.getString("phys_address") + "\n"
			+ "Total price: R" + results.getInt("total_fee") + "\n"
			+ "Structural engineer: "+ results.getString("struc_engineer") + "\n"
			+ "Project manager: "+ results.getString("proj_manager") + "\n"
			+ "Architect: "+ results.getString("architect") + "\n"
			+ "Customer: "+ results.getString("customer") + "\n"
			+ "Completed: "+ results.getBoolean("completed")
			);
		}
		System.out.println("----------------------------");
	}
	
	
	/**
	 * This method returns a list of projects past the current deadline it take's into account if the project is completed 
	 * and if it's deadline is past the current date
	 * @param statement
	 * @throws SQLException
	 */
	public static void showProjectsPastDeadline(Statement statement) throws SQLException{
		ResultSet results = statement.executeQuery(
				"select * from projects pr join prices p on pr.total_fee = p.total_fee where p.completed = false and p.deadline < CURRENT_DATE();");
		
		// Display results
		System.out.println("----- Projects Past Deadline -----");
		while (results.next()) {
		System.out.println(
			"\nProject number: " + results.getInt("proj_num") + "\n"
			+ "Project name: "+ results.getString("proj_name") + "\n"
			+ "Physical address: "+ results.getString("phys_address") + "\n"
			+ "Total price: R" + results.getInt("total_fee") + "\n"
			+ "Structural engineer: "+ results.getString("struc_engineer") + "\n"
			+ "Project manager: "+ results.getString("proj_manager") + "\n"
			+ "Architect: "+ results.getString("architect") + "\n"
			+ "Customer: "+ results.getString("customer") + "\n"
			+ "Completed: "+ results.getBoolean("completed") + "\n"
			+ "Deadline: "+ results.getDate("deadline")
			);
		}
		System.out.println("----------------------------------");
	}
	
	
	/**
	 * This method allows the user to edit any of the current projects details 
	 * @param rowsAffected
	 * @param statement
	 * @throws SQLException
	 */
	public static void editProject(int rowsAffected, Statement statement) throws SQLException {
		Scanner sc = new Scanner(System.in);
	
		int projectID;
		
		// Values to possibly update
		String projectName;
		String projectDeadline;
		int totalPaidAmount;
		int totalCost;
		
		System.out.println("Enter in the project number of the project you would like to edit:");
		projectID = sc.nextInt();
		
		System.out.println("Select what you would like to edit\n"
			     + "(1) Project name\n"
			     + "(2) Deadline \n"
			     + "(3) Total paid amount\n"
			     + "(4) Total cost\n"
			     + "(-1) Return to menu\n");

		int editOption = 0;
		try {
			editOption = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("You did not enter in a valid input.\nPlease try again...");
			editOption = sc.nextInt();
		}
		
		switch(editOption) {
		
		// Project Name
		case 1:
			
			// Get user input
			System.out.println("Enter in the new project name: ");
			sc = new Scanner(System.in);
			projectName = sc.nextLine();
			
			// Update into the database
			rowsAffected = statement.executeUpdate("UPDATE projects set proj_name = '" + projectName + "' where proj_num = " + projectID + "");
			System.out.println("Query complete, " + rowsAffected + " rows updated.");
			break;
			
		// Deadline
		case 2:
			
			// Get user input
			System.out.println("Enter in the new project deadline eg(2021-12-23): ");
			sc = new Scanner(System.in);
			projectDeadline = sc.nextLine();
			
			// Update into the database
			rowsAffected = statement.executeUpdate("UPDATE projects pr join prices p on pr.total_fee = p.total_fee set p.deadline = '" + projectDeadline + "' where pr.proj_num = " + projectID + "");
			System.out.println("Query complete, " + rowsAffected + " rows updated.");
			break;
			
		// Total paid amount
		case 3:
			
			// Get user input
			System.out.println("Enter in the new project total paid amount: ");
			sc = new Scanner(System.in);
			totalPaidAmount = sc.nextInt();
			
			// Update into the database
			rowsAffected = statement.executeUpdate("UPDATE projects pr join prices p on pr.total_fee = p.total_fee set p.amount_paid = " + totalPaidAmount + " where pr.proj_num = " + projectID + "");
			System.out.println("Query complete, " + rowsAffected + " rows updated.");
			break;
			
		// Total cost
		case 4:
			// ############################################# Fix here ###################################################
			
			// Get user input
			System.out.println("Enter in the new project total fee: ");
			sc = new Scanner(System.in);
			totalCost = sc.nextInt();
			
			// Update into the database			  ("UPDATE projects pr join prices p on pr.total_fee = p.total_fee set pr.total_fee = " + totalCost + " where pr.proj_num = " + projectID + "");
			rowsAffected = statement.executeUpdate("UPDATE projects SET total_fee = " + totalCost + " WHERE proj_num = "+ 1 + ";"
					+ ";");
			System.out.println("Query complete, " + rowsAffected + " rows updated.");
			break;
		}
		
	}
	
	
	/**
	 * This method generates an invoice for the user if their owed amount is above R0 if not then it will simply set the project 
	 * completed to true 
	 * @param rowsAffected
	 * @param statement
	 * @throws SQLException
	 */
	public static void finalizeProject(int rowsAffected, Statement statement) throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		int projectNum;
		
		System.out.println("Enter the project number that you would like to finalize");
		projectNum = sc.nextInt();
		
		// Update project 
		rowsAffected = statement.executeUpdate("UPDATE projects pr "
				+ "join prices p on pr.total_fee = p.total_fee "
				+ "set p.completed = true, p.date_completed = CURRENT_DATE()"
				+ " where proj_num = " + projectNum + ";"
				);
		System.out.println("Query complete, " + rowsAffected + " rows updated.");
		
		
		// Print out the invoice 
		ResultSet results = statement.executeQuery(
				"SELECT * FROM projects pr "
				+ "join customers c on pr.customer = c.name "
				+ "join prices p on p.total_fee = pr.total_fee "
				+ " where proj_num = 1"
				);
		
		results.next();
		String foundType = results.getString(1);
		int paidAmount = results.getInt("amount_paid");
		int totalFee = results.getInt("total_fee");
		int owedAmount = totalFee - paidAmount;
		
		if (owedAmount > 0) {
			String  invoice = "\n##################################\n";
			   invoice += "Invoice \n";
			   invoice += "to: " + results.getString("name") + "\n";
			   invoice += "Telephone Number: " + results.getString("telephone") + "\n";
			   invoice += "Email: " + results.getString("email") + "\n";
			   invoice += "\nTotal remaining fee for project " + results.getString("proj_name") + "\n";
			   invoice += "R" + owedAmount + "\n";
			   invoice += "##################################\n";
			   
			   System.out.println(invoice);
		} else {
			System.out.println("Everything finalized and all paid for.");
		}
		
		
	}
}
