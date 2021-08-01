import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		// Create a scanner
		Scanner input = new Scanner(System.in);
		
		// Customer object
		Person customer = createCustomer();
		
		// Contractor object
        Person contractor = createContractor();
		
		// Architect object	
		Person architect = createArchitect();

		
		// Project object	
		Project project = createProject(customer);
		
		// Menu loop
		while (true) {
			
				// Main menu
				System.out.println("\nSelect an option: \n"
								+ "(1) Edit project \n"
								+ "(2) Update contractor details \n"
								+ "(3) Finalise project \n"
								+ "(-1) Exit");
				int option = input.nextInt();
				
				// Edit a project
				if (option == 1) {
					editProject(project);
				}
				
				// Update/Edit contractor details
				else if (option == 2)  {
					editContractor(contractor);
				}
				
				// Finalize a project
				else if (option == 3) {
					
					finalizeProject(customer, project, project.getOwedAmount());
				}
				
				// If user entered the right type of input but selected an option that doesn't exist
				else if (option > 1 || option > 3) {
					System.out.println("Sorry you did not enter a valid option");
				}	
				
				// Exit
				else if (option == -1) {
					break;
				}
	}	
}

	
	private static void editProject(Project project) {
		Scanner input;
		int secondOption;
		
		// We display a new menu to the user and store their second option in a new variable
		System.out.println("\nSelect an option: \n(1) Change deadline \n(2) Change paid amount");
		input = new Scanner(System.in);
		secondOption = input.nextInt();
		
		// Change deadline
		if (secondOption == 1) {
			// Ask user for the new deadline of the project and store it in a variable
			System.out.println("Enter in the new deadline: ");
			input = new Scanner(System.in);  
			String newDeadline = input.nextLine();
			
			// We set the projects deadline to the new deadline
			project.setDeadline(newDeadline);
			System.out.println("\nSuccessfully changed deadline");
			project.toString();
		}
		
		// Change paid amount 
		else if (secondOption == 2) {
			// Ask the user for the new paid amount and store it in a variable
			System.out.println("Enter in the new paid amount: ");
			input = new Scanner(System.in);
			double newPaidAmount = input.nextDouble();
			
			// Set the projects paid amount to the new paid amount
			project.setPaidAmount(newPaidAmount);
			System.out.println("Succesfully changed paid amount");
			}
		
		System.out.print(project.toString());  // This print test if it works
		}

	private static void editContractor(Person contractor) {
		Scanner input;
		// This will only run if the person type is a contractor
		int secondOption;
		
		// We display a new menu and store the users second option in a new variable
		System.out.println(
				"\nSelect what you would like to edit: \n"
			   + "(1) Telephone Number \n"
						+ "(2) Email Address \n"
						+ "(3) Physical Address\n");
		input = new Scanner(System.in);
		secondOption = input.nextInt();
		
		// If user selects to change the number we get the input and set the contractors telephone number to the new number
		if (secondOption == 1) {
			System.out.println("Enter new telephone number: ");
			input = new Scanner(System.in);
			String newTelephoneNumber = input.nextLine();
			contractor.setTelephoneNumber(newTelephoneNumber);
		}
		
		// If user selects to change the email address we get the input and set the contractors email address to the new email address
		else if (secondOption == 2) {
			System.out.println("Enter new email address: ");
			input = new Scanner(System.in);
			String newEmailAddress = input.nextLine();
			contractor.setEmailAddress(newEmailAddress);
		}
		
		// If user selects to change the physical address we get the input and set the contractors address to the new physical address
		else if (secondOption == 3) {
			System.out.println("Enter new physical address: ");
			input = new Scanner(System.in);
			String newPhysicalAddress = input.nextLine();
			contractor.setPhysicalAddress(newPhysicalAddress);
		}
		
		System.out.println(contractor.toString());  // This print is to see if the changes are made
	}

	private static void finalizeProject(Person customer, Project project, double projectsOwedAmount) {
		
		if (projectsOwedAmount == 0) {
			
			// If the project owed amount = 0 we do not generate the invoice but we still finalize the project
			project.finalize();
			System.out.println("Project has been fully paid for and finalized.");
			
		} else {
			
			// This else block means that the customer still has an owed amount to be paid for the project
			// When we finalize the project we create a new String called invoice and display the user details including the owed amount
						
			String invoice = "Invoice \n";
			invoice += "to: " + customer.getName() + "\n";
			invoice += "Telephone Number: " + customer.getTelephoneNumber() + "\n";
			invoice += "Email: " + customer.getEmailAddress() + "\n";
			invoice += "\nTotal remaining fee for project " + project.getProjectName() + "\n";
			invoice += "R" + project.getOwedAmount() + "\n";
			project.finalize();        // We run this function to finalize the project which stores the projects completed date and also sets the projects completion to true
			System.out.println(invoice);  // Display the invoice
		}
	}

	// Our create project method will take in a customer object as a parameter because the project will be specified to that customer
	private static Project createProject(Person customer) {
		
		Scanner input;
		int projectNumber;
		String projectName;
		String buildingType;
		String projectAddress;
		int ERFNumber;
		double totalFee;
		double paidAmount;
		String deadline;
		
		// Create a new project
		System.out.println("\nPlease enter in the project details.");
		
		// Project number
		// This exception handling is to ensure that the user enters a number and not a string
		try {
			System.out.println("Project number: ");
			input = new Scanner(System.in);
			projectNumber = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Sorry you did not enter a valid input, Please try again.\nProject number: ");
			input = new Scanner(System.in);
			projectNumber = input.nextInt();
		}
		
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
		} catch (InputMismatchException e) {
			System.out.println("Sorry you did not enter a valid input, Please try again.\nTotal amount paid to date: ");
			input = new Scanner(System.in);
			paidAmount = input.nextDouble();
		}
		
		// Deadline
		System.out.println("Deadline of the project(eg. 21/02/2001) : ");
		input = new Scanner(System.in);
		deadline = input.nextLine();
			
		// If there is no project name we use the persons last name plus the building type and store it as the project name
		if (projectName == "") {
			String userName = customer.getName(); // get the customer full name
			String lastName = userName.substring(userName.lastIndexOf(" ")+ 1);  // We get the last index of the full name
			projectName = buildingType + " " + lastName;   // Set the project name to the building type and user surname
		} 
			
		// Create the project with the information we have
		Project newProject = new Project(projectNumber, projectName, buildingType, projectAddress, ERFNumber, totalFee, paidAmount, deadline);
		System.out.print(newProject.toString());
			
		System.out.println("\nNew Project succesfully added.\n");
		return newProject;
	}

	private static Person createArchitect() {
		Scanner input;
		String name;
		String personType;
		String telephoneNumber;
		String emailAddress;
		String physicalAddress;
		// Create a new architect person
		System.out.println("\nPlease enter the architect details.");

		// Name
	    System.out.println("Full name: ");
		input = new Scanner(System.in);
		name = input.nextLine();
				
		// Person type
		personType = "architect";
				
		// Telephone number
		System.out.println("Telephone Number: ");
		input = new Scanner(System.in);
		telephoneNumber = input.nextLine();
		
		// Email Address
		System.out.println("Email address: ");
		input = new Scanner(System.in);
		emailAddress = input.nextLine();
				
		// Physical address
		System.out.println("Physical Address: ");
		input = new Scanner(System.in);
		physicalAddress = input.nextLine();
				
		// Create object
		Person architect = new Person(name, personType, telephoneNumber, emailAddress, physicalAddress);
		System.out.print(architect.toString());
		System.out.println("Successfully regisetered a new architect");
		
		return architect;
	}

	private static Person createContractor() {
		Scanner input;
		String name;
		String personType;
		String telephoneNumber;
		String emailAddress;
		String physicalAddress;
		// Create a new contractor person
		System.out.println("\nPlease enter the contractor details.");

		// Name
	    System.out.println("Full name: ");
		input = new Scanner(System.in);
		name = input.nextLine();
				
		// Person type
		personType = "contractor";
				
		// Telephone 
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
				
		// Create object
		Person contractor = new Person(name, personType, telephoneNumber, emailAddress, physicalAddress);
		System.out.print(contractor.toString());
		System.out.println("Successfully regisetered a new contractor");
		return contractor;
	}

	private static Person createCustomer() {
		Scanner input;
		String name;
		String personType;
		String telephoneNumber;
		String emailAddress;
		String physicalAddress;
		// Create a new customer person
		System.out.println("Please enter the customer details.");
		
		// Name 
	    System.out.println("Full name: ");
		input = new Scanner(System.in);
		name = input.nextLine();
				
		// Person type
		personType = "customer";
				
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
				
		// Create object
		Person customer = new Person(name, personType, telephoneNumber, emailAddress, physicalAddress);
		System.out.print(customer.toString());
		System.out.println("Successfully regisetered a new customer");
		return customer;
	}
}
