package eswar.bankingApp;

import java.util.List;

import eswar.bankingApp.data.StatementData;
import eswar.bankingApp.objects.StatementEntry;

public class Statement {


	StatementData statementData =new StatementData();
	
	void printMiniStatement(String userEmail) {
		List<StatementEntry> statements = statementData.getAllStatementsByUserEmail(userEmail);
		if(statements.size()==0){
			System.out.println("No transactions yet!!");
			return;
		}
		System.out.println("Date \t\t Description \t\t credited \t\t debited \t\t balance");
		for (StatementEntry entry : statements) {
			System.out.print(entry.getDate() + "\t\t" + entry.getDescription() + "\t\t");
			if(entry.getCredited().equals("null")){
				System.out.print("-\t\t");
			}
			if(!entry.getCredited().equals("null")){
				System.out.print(entry.getCredited()+"\t\t");
			}
			if(entry.getDebited().equals("null")){
				System.out.print("-\t\t");
			}
			if(!entry.getDebited().equals("null")){
				System.out.print(entry.getDebited()+"\t\t");
			}
			System.out.print(entry.getAmount());
			
			System.out.println();
			
		}
	}

}
