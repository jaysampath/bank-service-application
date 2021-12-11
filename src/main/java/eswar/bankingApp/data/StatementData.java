package eswar.bankingApp.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import eswar.bankingApp.objects.StatementEntry;

public class StatementData {
	
	private final String FILE_NAME = "Statement.txt";
	
	public boolean addStatementEntry(StatementEntry statementEntry) {
		// System.out.println("inside addStatementEntry method");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
			writer.write("userEmail=" + statementEntry.getUserEmail() + "\n");
			writer.write("date=" + statementEntry.getDate() + "\n");
			writer.write("description=" + statementEntry.getDescription() + "\n");
			writer.write("credited=" + statementEntry.getCredited() + "\n");
			writer.write("debited=" + statementEntry.getDebited() + "\n");
			writer.write("balance=" + statementEntry.getAmount() + "\n\n");
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public List<StatementEntry> getAllStatementsByUserEmail(String userEmail) {
		List<StatementEntry> statementEntries = new ArrayList<StatementEntry>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
			String line = reader.readLine();
			String emailLine = "", date = "", description = "", credited = "", debited = "", balance = "";
			StatementEntry tempEntry = new StatementEntry();
			while (line != null) {
				if (line.length() == 0) {
					line = reader.readLine();
					continue;
				}
				String[] tempArr = line.split("=");
				String key = tempArr[0];
				String value = tempArr[1];
				if (key.equals("userEmail") && value.equals(userEmail)) {
					emailLine = value;
				}
				if (key.equals("date") && emailLine.length() > 0) {
					date = value;
				}
				if (key.equals("description") && emailLine.length() > 0) {
					description = value;
				}
				if (key.equals("credited") && emailLine.length() > 0) {
					credited = value;
				}
				if (key.equals("debited") && emailLine.length() > 0) {
					debited = value;
				}
				if (key.equals("balance") && emailLine.length() > 0) {
					balance = value;
				}
				if (emailLine.length() > 0 && date.length() > 0 && description.length() > 0 && credited.length() > 0
						&& debited.length() > 0 && balance.length() > 0) {
					statementEntries.add(new StatementEntry(emailLine, date, description, credited, debited, balance));
					emailLine="";
					date="";
					description="";
					credited="";
					debited="";
					balance="";
					
				}

				line = reader.readLine();
			}

			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return statementEntries;
	}


}
