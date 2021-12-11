package eswar.bankingApp.objects;

public class CustomerDetails {

	private String name;

	private String emailId;

	private String dob;

	private String mobile;

	private String accountNumber;

	private String password;

	private long balance;

	private String lastLogin;

	public CustomerDetails() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDetails(String name, String emailId, String dob, String mobile, String accountNumber,
			String password, long balance, String lastLogin) {

		this.name = name;
		this.emailId = emailId;
		this.dob = dob;
		this.mobile = mobile;
		this.accountNumber = accountNumber;
		this.password = password;
		this.balance = balance;
		this.lastLogin = lastLogin;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerDetails [name=" + name + ", emailId=" + emailId + ", dob=" + dob + ", mobile=" + mobile
				+ ", accountNumber=" + accountNumber + ", password=" + password + ", balance=" + balance
				+ ", lastLogin=" + lastLogin + "]";
	}

	

}
