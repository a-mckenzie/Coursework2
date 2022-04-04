package depot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {

	private static ArrayList<User> users = new ArrayList<User>(); //STORES USERS
	private String username, password, driverID, role, depot;

	public User(String username,String password,String driverID,String role,String depot) {
	
		this.username = username;
		this.password = password;
		this.driverID = driverID;
		this.role = role;
		this.depot = depot;
		
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getdriverID() {
		return this.driverID;
	}
	public String getRole() {
		return this.role;
	}
	public String getDepot() {
		return this.depot;
	}
	
	public static void createDriver() throws IOException, ParseException {
		Scanner driverScanner = new Scanner(new File("src/users.csv"));
		driverScanner.useDelimiter(",");

		while (driverScanner.hasNextLine()) {
			String s = driverScanner.nextLine();
			String[] split = s.split(",");
			User x = new User(split[0], split[1], split[2], split[3], split[4]);
			users.add(x);
		}

		driverScanner.close();

		FileWriter csvWriter = new FileWriter("src/users.csv", true);
		Scanner in = new Scanner(System.in);

		System.out.println("--DRIVER CREATION--");

		System.out.println("Enter Username:");
		String username = in.next();

		for (User users : users) {
			if (username.equals(users.getUsername())) {
				System.out.println("--USERNAME NOT AVAILABLE--");
				createDriver();
			} else {
				System.out.println("--USERNAME AVAILABLE");

				System.out.println("Enter Password:");
				String password = in.next();

				String driver = "Driver";

				System.out.println("Enter Driver ID:");
				String ID = in.next();

				if (ID.equals(users.getdriverID())) {
					System.out.println("--ID NOT AVAILABLE--");
					createDriver();
				} else {
					System.out.println("--ID AVAILABLE--");

					System.out.println("Enter depot location:");
					String depot = in.next();

					System.out.println("--NEW DRIVER DETAILS--");
					System.out.println("Username: " + username);
					System.out.println("Password: " + password);
					System.out.println("Driver ID: " + ID);
					System.out.println("Depot Location: " + depot);
					System.out.println("Confirm Details Y/N");
					String choice = in.next();

					if (choice.equalsIgnoreCase("Y")) {

						List<List<String>> rows = Arrays.asList(Arrays.asList(username, password, ID, driver, depot));

						for (List<String> rowData : rows) {
							csvWriter.append(String.join(",", rowData));
							csvWriter.append(",");
							csvWriter.append("\n");
						}

						csvWriter.flush();
						csvWriter.close();
					} else if (choice.equalsIgnoreCase("N")) {
						System.out.println("--DRIVER CREATION CANCELLED--");
						break;
					}
				}
			}
		}
	}
}
