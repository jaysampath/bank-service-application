package eswar.bankingApp.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eswar.bankingApp.Statement;
import eswar.bankingApp.objects.CustomerBalance;
import eswar.bankingApp.objects.CustomerDetails;
import eswar.bankingApp.objects.LastLogin;
import eswar.bankingApp.objects.StatementEntry;

public class CustomerDetailsData {

	private String filename = "Customer_details.txt";

	private static final String INSUFFICIENT_BALANCE = "Transaction failed. Reason: Insufficient balance in your account";

	private StatementData statementData = new StatementData();

	SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

	Statement statement = new Statement();

	public boolean saveNewUser(CustomerDetails newCustomer) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
			String content = "name: " + newCustomer.getName() + "\n" + "email id:" + newCustomer.getEmailId() + "\n"
					+ "Date of Birth: " + newCustomer.getDob() + "\n" + "phone number: " + newCustomer.getMobile()
					+ "\n" + "Account number: " + newCustomer.getAccountNumber() + "\n" + "Account type: Savings\n"
					+ "Auto generated password: " + newCustomer.getPassword() + "\n" + "balance: "
					+ newCustomer.getBalance() + "\n" + "LastLogin: " + newCustomer.getBalance() + "\n \n";
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occured!");
			return false;
		}
		return true;
	}

	public Map<String, String> getUserEmailsAndAccountNumbers() {
		List<CustomerDetails> customers = getCustomerDetails();
		Map<String, String> map = new HashMap<String, String>();
		for (CustomerDetails customer : customers) {
			map.put(customer.getEmailId(), customer.getAccountNumber());
		}
		return map;
	}

	public int checkEmailAndPasswordLogin(String email, String password) {
		List<CustomerDetails> customers = getCustomerDetails();
		Map<String, String> map = getEmailAndPasswords(customers);
		if (!map.containsKey(email)) {
			return -1;
		}
		if (map.get(email).equals(password)) {
			return 1;
		} else {
			return 0;
		}
	}

	public Map<String, String> getEmailAndPasswords(List<CustomerDetails> customers) {
		Map<String, String> map = new HashMap<String, String>();
		for (CustomerDetails customer : customers) {
			map.put(customer.getEmailId(), customer.getPassword());
		}
		return map;
	}

	public boolean checkExistingUser(String email) {
		try {
			List<CustomerDetails> customers = getCustomerDetails();
			Map<String, String> emailsAndPasswords = getEmailAndPasswords(customers);
			if (emailsAndPasswords.containsKey(email)) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String getUserEmailUsingAccountNumber(String accountNumber) {
		Map<String, String> map = getUserEmailsAndAccountNumbers();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String value = entry.getValue();
			String key = entry.getKey();
			if (value.equals(accountNumber)) {
				return key;
			}
		}
		return "null";
	}

	public List<CustomerDetails> getCustomerDetails() {
		String line;
		String email = "", password = "", dob = "", accNo = "", name = "", mobile = "", lastLogin = "";
		long balance = -1;
		List<CustomerDetails> CustomersList = new ArrayList<CustomerDetails>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);

				if (line.contains("name")) {
					String[] sArr1 = line.split(":");
					name = sArr1[1];
				}
				if (line.contains("email id")) {
					String[] sArr2 = line.split(":");
					email = sArr2[1];
				}
				if (line.contains("Date of Birth")) {
					String[] sArr2 = line.split(":");
					dob = sArr2[1];
				}
				if (line.contains("phone number")) {
					String[] sArr2 = line.split(":");
					mobile = sArr2[1];
				}
				if (line.contains("Account number")) {
					String[] sArr2 = line.split(":");
					accNo = sArr2[1];
				}
				if (line.contains("Auto generated password")) {
					String[] sArr2 = line.split(":");
					password = sArr2[1];
				}
				if (line.contains("balance")) {
					String[] sArr2 = line.split(":");
					balance = Long.parseLong(sArr2[1].trim());
				}
				if (line.contains("LastLogin")) {
					String[] sArr2 = line.split(":");
					lastLogin = sArr2[1];
				}
				if (email.length() > 0 && password.length() > 0 && name.length() > 0 && dob.length() > 0
						&& accNo.length() > 0 && mobile.length() > 0 && balance != -1 && lastLogin.length() > 0) {
					email = email.trim();
					password = password.trim();
					mobile = mobile.trim();
					accNo = accNo.trim();
					dob = dob.trim();
					name = name.trim();
					lastLogin = lastLogin.trim();
					CustomersList
							.add(new CustomerDetails(name, email, dob, mobile, accNo, password, balance, lastLogin));
					email = "";
					password = "";
					mobile = "";
					dob = "";
					name = "";
					accNo = "";
					balance = -1;
					lastLogin = "";
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(CustomersList);
		return CustomersList;
	}

	public CustomerBalance fetchBalance(String userEmail) {
		List<CustomerDetails> customers = getCustomerDetails();
		CustomerBalance customerBalance = new CustomerBalance();
		for (CustomerDetails customer : customers) {
			if (customer.getEmailId().equals(userEmail)) {
				customerBalance.setBalance(customer.getBalance());
				customerBalance.setEmailID(userEmail);
				break;
			}
		}
		return customerBalance;
	}

	boolean updateCustomerDetailsTextFile(List<CustomerDetails> customers) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			for (CustomerDetails customer : customers) {
				writer.write("name:" + customer.getName()+"\n");
				writer.write("email id:" + customer.getEmailId()+"\n");
				writer.write("Date of Birth:" + customer.getDob()+"\n");
				writer.write("phone number:" + customer.getAccountNumber()+"\n");
				writer.write("Account number:" + customer.getAccountNumber()+"\n");
				writer.write("Auto generated password:" + customer.getPassword()+"\n");
				writer.write("balance:" + customer.getBalance()+"\n");
				writer.write("LastLogin:" + customer.getLastLogin()+"\n");
				writer.newLine();
			}
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public String withdrawAndDeposit(String userEmail, int amount, int action, String description) {
		// action = 1 , deposit happens
		// action = -1, withdraw happens
		boolean userDetected = false;
		List<CustomerDetails> customersData = getCustomerDetails();
		long newBalance = 0;
		for (CustomerDetails customer : customersData) {
			if (customer.getEmailId().equals(userEmail)) {
				userDetected = true;
				if (action == 1) {
					newBalance = customer.getBalance() + amount;
					customer.setBalance(newBalance);
					StatementEntry newEntry = new StatementEntry(customer.getEmailId(),
							String.valueOf(sdf.format(System.currentTimeMillis())), description, String.valueOf(amount),
							"null", String.valueOf(newBalance));
					statementData.addStatementEntry(newEntry);
				} else if (action == -1) {
					if (customer.getBalance() - amount < 0) {
						return INSUFFICIENT_BALANCE;
					}
					newBalance = customer.getBalance() - amount;
					customer.setBalance(newBalance);
					StatementEntry newEntry = new StatementEntry(customer.getEmailId(),
							String.valueOf(sdf.format(System.currentTimeMillis())), description, "null",
							String.valueOf(amount), String.valueOf(newBalance));
					statementData.addStatementEntry(newEntry);

				} else {
					return "Invalid Action";
				}
			}
		}
		boolean update = updateCustomerDetailsTextFile(customersData);
		if (!userDetected) {
			return "Transaction failed. Reason : invalid user email";
		}
		return ("Transaction successful. Available balance: " + newBalance);
	}

	public boolean updateLastLogin(String userEmail) {
		boolean userDetected = false;
		List<CustomerDetails> customersData = getCustomerDetails();
		for (CustomerDetails customer : customersData) {
			if (customer.getEmailId().equals(userEmail)) {
				userDetected = true;
				customer.setLastLogin(String.valueOf(sdf.format(System.currentTimeMillis())));
			}
		}
		boolean update = updateCustomerDetailsTextFile(customersData);
		if (!userDetected) {
			return false;
		}
		return true;
	}

	public LastLogin getUserLastLogin(String userEmail) {
		List<CustomerDetails> customers = getCustomerDetails();
		for (CustomerDetails customer : customers) {
			if (customer.getEmailId().equals(userEmail)) {
				return new LastLogin(customer.getEmailId(), customer.getLastLogin());
			}
		}
		return null;
	}
}
