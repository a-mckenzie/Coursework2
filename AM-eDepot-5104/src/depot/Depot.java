package depot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Depot {
	private static ArrayList<WorkSchedule> schedules = new ArrayList<WorkSchedule>();
	private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	static Scanner scan;

	public Depot() {

	}

	public void logOn(String u, String p) {
		
	}
	
	public static Vehicle getVehicle(String regNoInput) {
		return null;
		
	}
	
	public void getDriver() {
		
	}
	
 	public static Vehicle displayVehicleMenu() throws NumberFormatException, ParseException {
		System.out.println(vehicles.toString());
		System.out.println("Select Vehicle by RegNo :");
		String regNoInput = scan.nextLine();
		
		Vehicle selectedVehicle = getVehicle(regNoInput);
		System.out.println(selectedVehicle);
		
		if(selectedVehicle.isAvailable()) {
			return selectedVehicle;
		}
		return null;
	}
	
	public ArrayList<WorkSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(ArrayList<WorkSchedule> schedules) {
		Depot.schedules = schedules;
	}
	
	public static void createSchedule() throws IOException, ParseException {
		Scanner scheduleScanner = new Scanner(new File("src/schedule.csv"));

		while (scheduleScanner.hasNextLine()) {
			String s = scheduleScanner.nextLine();
			String[] split = s.split(",");
			WorkSchedule x = new WorkSchedule(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7]);
			schedules.add(x);
		}

		scheduleScanner.close();

		FileWriter csvWriter = new FileWriter("src/schedule.csv", true);
		Scanner in = new Scanner(System.in);

		System.out.println("--SCHEDULE CREATION--");

		System.out.println("Client Name:");
		String client = in.next();

		System.out.println("Vehicle Type:");
		String vehicleType = in.next();

	    System.out.println("Start Date:");
		String startDate = in.next();
		
		System.out.println("End Date:");
		String endDate = in.next();
		
		System.out.println("Start Time:");
		String startTime = in.next();

		System.out.println("End Time:");
		String endTime = in.next();
		
		System.out.println("Vehicle Registration Number:");
		String vehicleReg = in.next();
		
		System.out.println("Driver ID:");
		String driverID = in.next();

		System.out.println("--NEW SCHEDULE DETAILS--");
		System.out.println("Client: " + client);
		System.out.println("Vehicle Type: " + vehicleType);
		System.out.println("Start Date: " + startDate);
		System.out.println("End Date: " + endDate);
		System.out.println("Start Time: " + startTime);
		System.out.println("End Time: " + endTime);
		System.out.println("Vehicle Reg No.: " + vehicleReg);
		System.out.println("Driver ID: " + driverID);
		System.out.println("Confirm Schedule Y/N");
		String choice = in.next();

					if (choice.equalsIgnoreCase("Y")) {

						List<List<String>> rows = Arrays.asList(Arrays.asList(client, vehicleType, startDate, endDate, startTime, endTime, vehicleReg, driverID));

						for (List<String> rowData : rows) {
							csvWriter.append(String.join(",", rowData));
							csvWriter.append(",");
							csvWriter.append("\n");
						}

						csvWriter.flush();
						csvWriter.close();
					} else if (choice.equalsIgnoreCase("N")) {
						System.out.println("--SCHEDULE CREATION CANCELLED--");
						
					}

	}
	
}

