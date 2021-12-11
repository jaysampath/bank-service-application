package eswar.bankingApp;

import java.util.Scanner;

import eswar.bankingApp.data.CustomerDetailsData;
import eswar.bankingApp.objects.CustomerDetails;

public class BankApplication {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Xactly Banking Corporation\nAre you an existing customer(yes/no)");
		String ans = sc.next();
		BankingService bankService = new BankingService();
		if (ans.equals("yes")) {

			if (bankService.login()) {
				System.out.println("======Login success========\n");

				bankService.userActions();
			} else {
				System.out.println("Wrong username and password");
			}
		} else if (ans.equals("no")) {
			System.out.println("Creating new account.... Please enter the details: ");
			if (bankService.OpenSavings()) {

				bankService.userActions();
			}
		} else {
			System.out.println("invalid input");
			System.exit(0);
		}
	}
}