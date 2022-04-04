//Import relevant frameworks and external files
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import depot.Depot;
import depot.User;
import depot.Vehicle;
import depot.WorkSchedule;

public class Sys {
	private static boolean loggedIn = false; //USER SIGNED IN BOOLEAN
	private static User loggedInUser = null; //CURRENT USER
	private static ArrayList<User> users = new ArrayList<User>(); //STORES USERS

	private static boolean menu = true;
	private static Scanner input = new Scanner(System.in);
	public static String currentUser = " ";
	public static String regAvailable = " ";
	public static String timeWorks = null;

	public static void main(String[] args) throws IOException, ParseException {
		boolean running = true;
		// loop determines the users role and shows the relevant menu
		while (running) {
			if (!loggedIn) {
				loginMenu();
			} else {
				if (loggedInUser.getRole().equals("Driver")) {
					driverMenu();

				} else if (loggedInUser.getRole().equals("Manager")) {
					managerMenu();
				} else {
					System.out.println("Invalid job role.");
					loginMenu();
				}
			}
		}

	}
	
// I wanted to change the above block of code to a Switch statement as I believed it would be more efficient 
// couldn't get it working as well as the above block of code
// 	
//	class Main {
//	    private static User _user;
//	    
//	    public static void main(String[] args) {
//	    
//	    _user = new Driver();
//	    
//	    switch(_user.GetRole()){
//	        case "Manager": 
//	            //Do this;
//	            break;
//	        case "Driver":
//	            throw new Exception("Im a Driver");
//	            break;
//	            
//	            default: 
//	            //logout
//	            break;
//	    }
//	    }
//	}
//
//	class User{
//	    private static String Role;
//	    
//	    public static String GetRole(){
//	        return Role;
//	    }
//	    
//	    public static void SetRole(String role){
//	        Role = role;
//	    }
//	}
//
//	class Manager extends User{
//	    
//	    public Manager(){
//	        SetRole("Manager");
//	    }
//	}
//
//	class Driver extends User{
//	    public Driver(){
//	        SetRole("Driver");
//	    }
//	}

	public static void loginMenu() throws FileNotFoundException {

		Scanner su = new Scanner(new File("src/users.csv"));
		su.useDelimiter(",");
		while (su.hasNextLine()) {
			String s = su.nextLine();
			String[] split = s.split(",");
			users.add(new User(split[0], split[1], split[2], split[3], "depot"));

		}
		
		//put this in a helper 

		su.close();

		String username, password;

		System.out.println("DEPOT SYSTEM LOGIN");
		System.out.println("Username:");
		username = input.next();
		System.out.println("Password:");
		password = input.next();

		for (User user : users) {
			if ((user.getUsername().equals(username)) && (user.getPassword().equals(password))) {
				currentUser = username;
				loggedIn = true;
				loggedInUser = user;
				//checks login details correct
				System.out.println("Current User: " + currentUser);
				break;
			}
		}
		if (!loggedIn) {
			System.out.println("LOGIN INVALID");
		}
	}
	
	public static void driverMenu() throws IOException {
		menu = true;
		while (menu) {
			String choice = "";
			System.out.println("-- DEPOT SYSTEM--");
			System.out.println("1. View Schedule");
			System.out.println("Q. Log Out");
			System.out.print("Input: ");
			choice = (input.next().toUpperCase());

			switch (choice) {
			case "1": {
				driverSchedule();
				break;
			}
			case "Q": {
				menu = false;
				loggedIn = false;
				loggedInUser = null;
				System.out.println("Successfully logged out");
				currentUser = " ";
				break;
			}
			default:
				System.out.println("Invalid entry");
				break;
			}
		}
	}
	
	private static void managerMenu() throws IOException, ParseException {
		String choice = "";
		System.out.println("-- DEPOT SYSTEM--");
		System.out.println("1. View All Schedules");
		System.out.println("2. View Driver Schedule");
		System.out.println("3. Create Work Schedule");
		System.out.println("4. Move Vehicle");
		System.out.println("5. View Vehicles");
		System.out.println("6. Create Vehicle");
		System.out.println("7. Create Driver");
		System.out.println("Q. Log Out");
		System.out.print("Input: ");
		choice = (input.next().toUpperCase());
		switch (choice) {
		case "1": {
			WorkSchedule.viewSchedule();
			break;
		}
		case "2": {
			driverSchedule();
			break;
		}
		case "3": {
			Depot.createSchedule();
			break;
		}
		case "4": {
			//moveVehicle();
			break;
		}
		case "5": {
			Depot.displayVehicleMenu();
			break;
		}
		case "6": {
			Vehicle.createVehicle();
			break;
		}
		case "7": {
			User.createDriver();
			break;
		}
		case "Q": {
			loggedIn = false;
			loggedInUser = null;
			System.out.println("Successfully Logged Out");
			currentUser = " ";
			break;
		}
		default:

			System.out.println("Invalid Entry");
			break;
		}
	}
	


	//parseint





	private static void driverSchedule() throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("src/schedule.csv"));
		List<String> lines = new ArrayList<>();
		String line = null;
		int noOutput = 0;
		while ((line = reader.readLine()) != null) {
			lines.add(line + "\n ");
		}
		int size = (lines.size());
		if (size != 0) {
			for (int x = 0; x < size; x++) {
				String output = lines.get(x);
				{
					if (output.contains(currentUser)) {
						String formattedString = output.toString().replace(",", "\t|\t");
						noOutput++;

						System.out.println(formattedString);
					} else {
						noOutput++;
					}

				}
			}

		}
		if (noOutput == 0) {
			System.out.println("No Schedule For Current User ");
			reader.close();
		}
	}
		//ARRANGE SCHEDULE
		//ONCE SCHEDULE IS LOADED IN, SEARCH SCHEDULE FOR VEHICLE REG TO SEE IF VEHICLE IS PRE BOOKED, IF NOT THEN ADD WHATEVER DATE THEY WANT
		//SAVE REG IN VARIABLE
		//SPECIFY START DATE
		//CHECK SCHEDULE FOR THAT DATE TIME AND SEE IF ITS BEEN BOOKED ALREADY, 30 MINS EITHER SIDE
		//IF NOT THEN BOOK AND SAVE
		//IF IT IS SUGGEST OTHER VEHICLE
		

	
}
//public class WorkSchedule
//{
//	
//	private list<string> _schedule = new list<string>()
//	
//	public WorkSchedule(){
//		
//		_schedule = Load();
//		
//	}
//	
//	public string[] Load(){
//	}
//	
//	
//	public void Add(datetime dateAndTime,string reg){
//		CheckAppointmentAvailable(dateAndTime);
//		CheckVehicleIsAvailable(reg);		
//		_schedule.add(reg, dateAndTime);		
//		
//	}
	
