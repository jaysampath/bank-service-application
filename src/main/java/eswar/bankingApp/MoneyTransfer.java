package eswar.bankingApp;

import java.util.Map;
import java.util.Scanner;

import eswar.bankingApp.data.CustomerDetailsData;

public class MoneyTransfer {

	CustomerDetailsData customerDatailsData = new CustomerDetailsData();

	String moneyTransFer(String userEmail) {
		Map<String, String> map = customerDatailsData.getUserEmailsAndAccountNumbers();
		if (!map.containsKey(userEmail)) {
			return "user account number not found";
		}
		String fromUserAccountNumber = map.get(userEmail);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the account number to which you want to transfer money");
		String toUserAccountNumber = sc.next();
		if (fromUserAccountNumber.equals(toUserAccountNumber)) {
			return "invalid account number";
		}
		if (!map.containsValue(toUserAccountNumber)) {
			return "invalid account number";
		}
		System.out.println("Enter the amount");
		int amount = Integer.parseInt(sc.next());

		String toEmail = customerDatailsData.getUserEmailUsingAccountNumber(toUserAccountNumber);
		if (toEmail.equals("null")) {
			return "Something went wrong. please try again after sometime";
		}
		System.out.println("You are trying to transfer to user : "+toEmail);
		String fromAccountWithdraw = customerDatailsData.withdrawAndDeposit(userEmail, amount, -1,
				"debited to accountno:" + (toUserAccountNumber));
		if (!fromAccountWithdraw.contains("Transaction successful. Available balance: ")) {
			return fromAccountWithdraw;
		} else {
			String response = customerDatailsData.withdrawAndDeposit(toEmail, amount, 1,
					"credited from accountno:" + (fromUserAccountNumber));
		}
        System.out.println("Money transefer complete. Transfered amount: "+amount);
		return fromAccountWithdraw;

	}

}