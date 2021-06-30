import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Create a scanner
		Scanner input = new Scanner(System.in);
		
		// Project variables
		int projectNumber;
		String projectName;
		String buildingType;
		String projectAddress;
		int ERFNumber;
		double totalFee;
		double paidAmount;
		String deadline;
		
		// Person variables 
		String name;
		String personType;
		String telephoneNumber;
		String emailAddress;
		String physicalAddress;
		
		
		// Create a new customer person
		System.out.println("Please enter the customer details.");

	    System.out.println("Full name: ");
		input = new Scanner(System.in);
		name = input.nextLine();
					
		personType = "customer";
				
		System.out.println("Telephone Number: ");
		input = new Scanner(System.in);
		telephoneNumber = input.nextLine();
					
		System.out.println("Email address: ");
		input = new Scanner(System.in);
		emailAddress = input.nextLine();
				
		System.out.println("Physical Address: ");
		input = new Scanner(System.in);
		physicalAddress = input.nextLine();
				
		Person customer = new Person(name, personType, telephoneNumber, emailAddress, physicalAddress);
		System.out.print(customer.toString());
		System.out.println("Successfully regisetered a new customer");
		
		
		
        // Create a new contractor person
		System.out.println("\nPlease enter the contractor details.");

	    System.out.println("Full name: ");
		input = new Scanner(System.in);
		name = input.nextLine();
					
		personType = "contractor";
				
		System.out.println("Telephone Number: ");
		input = new Scanner(System.in);
		telephoneNumber = input.nextLine();
					
		System.out.println("Email address: ");
		input = new Scanner(System.in);
		emailAddress = input.nextLine();
				
		System.out.println("Physical Address: ");
		input = new Scanner(System.in);
		physicalAddress = input.nextLine();
				
		Person contractor = new Person(name, personType, telephoneNumber, emailAddress, physicalAddress);
		System.out.print(contractor.toString());
		System.out.println("Successfully regisetered a new contractor");
		
		
		
		// Create a new architect person
		System.out.println("\nPlease enter the architect details.");

	    System.out.println("Full name: ");
		input = new Scanner(System.in);
		name = input.nextLine();
					
		personType = "architect";
				
		System.out.println("Telephone Number: ");
		input = new Scanner(System.in);
		telephoneNumber = input.nextLine();
					
		System.out.println("Email address: ");
		input = new Scanner(System.in);
		emailAddress = input.nextLine();
				
		System.out.println("Physical Address: ");
		input = new Scanner(System.in);
		physicalAddress = input.nextLine();
				
		Person architect = new Person(name, personType, telephoneNumber, emailAddress, physicalAddress);
		System.out.print(architect.toString());
		System.out.println("Successfully regisetered a new customer");
		
			
		// Create a new project
		System.out.println("\nPlease enter in the project details.");
		
		System.out.println("Project number: ");
		input = new Scanner(System.in);
		projectNumber = input.nextInt();
		
		System.out.println("Project name: ");
		input = new Scanner(System.in);
		projectName = input.nextLine();
			
		System.out.println("Building Type: ");
		input = new Scanner(System.in);
		buildingType = input.nextLine();
		
		System.out.println("Project address: ");
		input = new Scanner(System.in);
		projectAddress = input.nextLine();
		
		System.out.println("ERF number: ");
		input = new Scanner(System.in);
		ERFNumber = input.nextInt();
			
		System.out.println("Total cost of the project: ");
		input = new Scanner(System.in);
		totalFee = input.nextDouble();
		
		System.out.println("Total amount paid to date: ");
		input = new Scanner(System.in);
		paidAmount = input.nextDouble();
			
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
		
		while (true) {
		// Main menu
		System.out.println("\nSelect an option: \n(1) Edit project \n(2) Update contractor details \n(3) Finalise project");
		input = new Scanner(System.in);
		int option = input.nextInt();
		
		// Edit a project
		if (option == 1) {
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
				newProject.setDeadline(newDeadline);
				System.out.println("\nSuccessfully changed deadline");
				newProject.toString();
			}
			
			// Change paid amount 
			else if (secondOption == 2) {
				// Ask the user for the new paid amount and store it in a variable
				System.out.println("Enter in the new paid amount: ");
				input = new Scanner(System.in);
				double newPaidAmount = input.nextDouble();
				
				// Set the projects paid amount to the new paid amount
				newProject.setPaidAmount(newPaidAmount);
				System.out.println("Succesfully changed paid amount");
				}
			
		System.out.print(newProject.toString());  // This print test if it works
		}
		
		// Update contractor details
		else if (option == 2)  {
			// This will only run if the person type is a contractor
			int secondOption;
			
			// We display a new menu and store the users second option in a new variable
			System.out.println("\nSelect what you would like to edit: \n"
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
		
		
		// Finalize a project
		else if (option == 3 && newProject.getOwedAmount() != 0) {
			
			// When we finalize the project we create a new String called invoice and display the user details including the owed amount
			String invoice = "Invoice \n";
			invoice += "to: " + customer.getName() + "\n";
			invoice += "Telephone Number: " + customer.getTelephoneNumber() + "\n";
			invoice += "Email: " + customer.getEmailAddress() + "\n";
			invoice += "\nTotal remaining fee for project " + newProject.getProjectName() + "\n";
			invoice += "R" + newProject.getOwedAmount() + "\n";
			newProject.finalize(); // We run this function to finalize the project which stores the projects completed date and also sets the projects completion to true
			System.out.println(invoice); // Display the invoice
		}
		
		else if (option == 3 && newProject.getOwedAmount() == 0) {
			// If the project owed amount = 0 we do not generate the invoice but we still finalize the project
			newProject.finalize();
			System.out.println("Project has been fully paid for and finalized.");
		}
		}
		
		
		
		
	}
}
