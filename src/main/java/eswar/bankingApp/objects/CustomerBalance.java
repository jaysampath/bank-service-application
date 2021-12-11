package eswar.bankingApp.objects;

public class CustomerBalance {
	
	private String emailID;
	
	private long balance;
	
	public CustomerBalance() {
		// TODO Auto-generated constructor stub
	}

	public CustomerBalance(String emailID, long balance) {
		this.emailID = emailID;
		this.balance = balance;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	
	

}
