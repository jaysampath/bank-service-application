package eswar.bankingApp;

import java.io.FileNotFoundException;
import java.util.Scanner;

import eswar.bankingApp.data.CustomerDetailsData;
import eswar.bankingApp.objects.CustomerBalance;
import eswar.bankingApp.objects.CustomerDetails;
import eswar.bankingApp.objects.LastLogin;

public class BankingService {

	Scanner sc = new Scanner(System.in);
	String loggedInUserEmail = "";
	MoneyTransfer moneyTransfer = new MoneyTransfer();
	Statement statement = new Statement();
	CustomerDetailsData customerDetailsData = new CustomerDetailsData();

	public void CheckBalance(String loggedInUser) {
		CustomerBalance loggedInUserBalance = new CustomerBalance();
		loggedInUserBalance = customerDetailsData.fetchBalance(loggedInUser);
		System.out.println("Your balance: " + loggedInUserBalance.getBalance());
	}

	public boolean login() {
		String email;
		String password;
		System.out.println("Enter the Email");
		email = sc.next();
		System.out.println("Enter the Password");
		password = sc.next();
		if (customerDetailsData.checkEmailAndPasswordLogin(email, password) == -1) {
			System.out.println("User does not exist. Create a new Account");
			System.exit(0);
		}
		if (customerDetailsData.checkEmailAndPasswordLogin(email, password) == 0) {
			System.out.println("email and password combination is incorrect. please try again");
			System.exit(0);
		}
		loggedInUserEmail = email;
		LastLogin userLastLogin = customerDetailsData.getUserLastLogin(loggedInUserEmail);
		System.out.println("Login Success. Your last Login: " + userLastLogin.getLastLoginDate());
		customerDetailsData.updateLastLogin(loggedInUserEmail);
		return true;
	}

	public void userActions() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println(
					"\n1. Check Balance\n2. Withdraw Cash\n3. Deposit\n4. Money Transfer\n5. Mini Statement\n6. exit");
			String user = scanner.next();
			switch (user) {
			case "1":
				CheckBalance(loggedInUserEmail);
				break;

			case "2":
				Withdrawl();
				break;

			case "3":
				Deposit();
				break;

			case "4":
				String response = moneyTransfer.moneyTransFer(loggedInUserEmail);
				System.out.println(response);
				break;

			case "5":
				statement.printMiniStatement(loggedInUserEmail);
				break;

			case "6":
				System.out.println("logging off Thank you for using xactly bank");
				System.exit(0);
			}
		}
	}

	public void Withdrawl() {

		try {

			System.out.println("Enter the amount you want to withdraw");

			int withdrawAmountInput = sc.nextInt();

			String response = customerDetailsData.withdrawAndDeposit(loggedInUserEmail, withdrawAmountInput, -1,
					"self bank withdrawl");

			System.out.println(response);

		} catch (Exception e) {
			System.out.println("An error occured!");
		}
	}

	public void Deposit() {

		try {

			System.out.println("Enter the amount you want to Deposit");

			int depositAmountInput = sc.nextInt();

			String response = customerDetailsData.withdrawAndDeposit(loggedInUserEmail, depositAmountInput, 1,
					"self bank deposit");

			System.out.println(response);

		} catch (Exception e) {
			System.out.println("An error occured!");
		}
	}

	boolean OpenSavings() {

		String ac = "12020";
		String email = "";
		System.out.println("Enter your name:");
		String name = sc.next();
		System.out.println("Enter your email id:");
		email = sc.next();
		int flag = 0;
		while (true) {
			boolean checkExistingEmail = customerDetailsData.checkExistingUser(email);
			if (checkExistingEmail) {
				System.out.println("Email already exists. Please choose another email \n");
				email = sc.next();
			} else {
				break;
			}
			if (flag > 1) {
				System.exit(0);
			}
			flag++;
		}

		System.out.println("Enter your Date of Birth:(DDMMYYYY)");
		String dob = sc.next();
		System.out.println("Enter your phone number:");
		String number = sc.next();

		for (int i = 0; i < 10; i++) {
			int rand = (int) (Math.random() * 9);
			ac += String.valueOf(rand);
		}

		String pswd = "";
		pswd = name.substring(0, 4) + dob.substring(4);

		System.out.println("Account number: " + ac);
		System.out.println("pswd: " + pswd);
		CustomerDetails newCustomer = new CustomerDetails(name, email, dob, number, ac, pswd, 0, "");
		System.out.println("Saving your details");
		boolean signupCheck = customerDetailsData.saveNewUser(newCustomer);
		if (signupCheck) {
			System.out.println("Savings Account Succesfully Created!");
			System.out.println("Thank you for choosing Xactly Banking Corporation!\nYour account is active.");
		} else {
			System.out.println("SOmething went wrong. please try again after somethime");
			System.exit(0);
		}
		CustomerBalance newCustomerBalance = new CustomerBalance(email, 0);
		System.out.println("1. Do you wish to login \n 2.exit");
		String input = sc.next();
		if (input.equals("1")) {
			login();
		} else {
			System.out.println("Thank you for using xactly bank");
			System.exit(0);
		}

		return true;
	}

}
