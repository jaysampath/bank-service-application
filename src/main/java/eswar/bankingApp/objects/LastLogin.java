package eswar.bankingApp.objects;

public class LastLogin {
	
	private String userEmail;
	
	private String lastLoginDate;
	
	public LastLogin() {
		// TODO Auto-generated constructor stub
	}

	public LastLogin(String userEmail, String lastLoginDate) {
		this.userEmail = userEmail;
		this.lastLoginDate = lastLoginDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	

}
