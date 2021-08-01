
public class Person {
	
	// Attributes 
	String name;
	String personType;
	String telephoneNumber;
	String emailAddress;
	String physicalAddress;
	
	// Constructor 
	public Person (String name, String personType, String telephoneNumber, String emailAddress, String physicalAddress) {
		this.name = name;
		this.personType = personType;
		this.telephoneNumber = telephoneNumber;
		this.emailAddress = emailAddress;
		this.physicalAddress = physicalAddress;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public String getPersonType() {
		return personType;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	
	// Setters
	public void setName(String newName) {
		name = newName;
	}
	
	public void setTelephoneNumber(String newTelephoneNumber) {
		telephoneNumber = newTelephoneNumber;
	}

	public void setEmailAddress(String newEmailAddress) {
		emailAddress = newEmailAddress;
	}

	public void setPhysicalAddress(String newPhysicalAddress) {
		physicalAddress = newPhysicalAddress;
	}
	
	// toString
	public String toString() {
		String output = "\nName: " + name + "\n"
				+ "Person type: " + personType + "\n"
				+ "Telephone number: " + telephoneNumber + "\n"
				+ "Email address: " + emailAddress + "\n"
				+ "Physical Address: " + physicalAddress + "\n";
		return output;
	}
}
