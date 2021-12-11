package eswar.bankingApp.objects;

// This is a simple class which is used while storing the transaction or retriving the transaction - Statement.txt
public class StatementEntry {
	
	private String userEmail;
	
	private String date;
	
	private String description;
	
	private String credited;
	
	private String debited;
	
	private String amount;
	
	public StatementEntry(){
		
	}

	public StatementEntry(String userEmail, String date, String description, String credited, String debited,
			String amount) {
		this.userEmail = userEmail;
		this.date = date;
		this.description = description;
		this.credited = credited;
		this.debited = debited;
		this.amount = amount;
	}

	


	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCredited() {
		return credited;
	}

	public void setCredited(String credited) {
		this.credited = credited;
	}

	public String getDebited() {
		return debited;
	}

	public void setDebited(String debited) {
		this.debited = debited;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	

}
