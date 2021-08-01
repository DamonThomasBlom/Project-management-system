import java.util.Date;
import java.text.SimpleDateFormat;

public class Project {
	
	// Attributes
	int projectNumber;
	String projectName;
	String buildingType;
	String projectAddress;
	int ERFNumber;
	double totalFee;
	double paidAmount;
	String deadline;
	boolean completion;
	double owedAmount;
	String completionDate;
	
	// Constructor
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
	
	// Getters
	public String getProjectName() {
		return projectName;
	}
	
	public Double getOwedAmount(){
		owedAmount = totalFee - paidAmount;
			
		return owedAmount;
	}
		
	public String getBuildingType() {
		return buildingType;
	}
		
	// Setters
	public void setDeadline(String newDeadline) {
		deadline = newDeadline;
	}
	
	public void setPaidAmount(double newPaidAmount) {
		paidAmount = newPaidAmount;
	}
	
	// toString
	public String toString() {
		String output = "\nProject Number: " + projectNumber + "\n"
				+ "Project Name: " + projectName + "\n"
				+ "Building type: " + buildingType + "\n"
				+ "Address: " + projectAddress + "\n"
				+ "ERF number: " + ERFNumber + "\n"
				+ "Total fee: R" + totalFee + "\n"
				+ "Paid amount: R" + paidAmount + "\n"
				+ "Deadline: " + deadline + "\n";
				return output;
	}
	
	// finalize
	public void finalize() {
		completion = true;   // Change the completion varaible to true
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();   // We create a new variable with the current date
		completionDate = formatter.format(today);   // And set the completionDate to this variable
	}
	
	
}
