package depot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Vehicle {
	protected String make;
	protected String model;
	protected String weight;
	protected String regNo;
	protected String vehicleType;
	protected String depot;
	
	private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>(); //STORES VEHICLES
	
	public Vehicle(String make, String model, String weight, String regNo, String vehicleType, String depot) {
		this.make = make;
		this.model = model;
		this.weight = weight;
		this.regNo = regNo;
		this.vehicleType = vehicleType;
		this.depot = depot;

	}
	public Vehicle(String make2, String model2, int weight2, String regNo2) {
		// TODO Auto-generated constructor stub
	}

	public String getMake() {
		return this.make;
	}
	public String getModel() {
		return this.model;
	}
	public String getWeight() {
		return this.weight;
	}
	public String getregNo() {
		return this.regNo;
	}
	public String getvehicleType() {
		return this.vehicleType;
	}
	public String getDepot() {
		return this.depot;
	}
	
	public static void createVehicle() throws IOException, ParseException {
		Scanner vehicleScanner = new Scanner(new File("src/vehicle.csv"));

		while (vehicleScanner.hasNextLine()) {
			String s = vehicleScanner.nextLine();
			String[] split = s.split(",");
			Vehicle x = new Vehicle(split[0], split[1], split[2], split[3], split[4], split[5]);
			vehicles.add(x);
		}

		vehicleScanner.close();

		FileWriter csvWriter = new FileWriter("src/vehicle.csv", true);
		Scanner in = new Scanner(System.in);

		System.out.println("--VEHICLE CREATION--");

		System.out.println("Enter make:");
		String vehicleMake = in.next();

		System.out.println("Enter model:");
		String vehicleModel = in.next();

	    System.out.println("Enter weight:");
		String vehicleWeight = in.next();
		
		System.out.println("Enter Reg No.:");
		String vehicleReg = in.next();
		
		System.out.println("Enter Vehicle Type:");
		String vehicleType = in.next();

		System.out.println("Enter depot location:");
		String depot = in.next();

		System.out.println("--NEW DRIVER DETAILS--");
		System.out.println("Make: " + vehicleMake);
		System.out.println("Model: " + vehicleModel);
		System.out.println("Weight: " + vehicleWeight);
		System.out.println("Reg No.: " + vehicleReg);
		System.out.println("Type: " + vehicleType);
		System.out.println("Depot Location: " + depot);
		System.out.println("Confirm Details Y/N");
		String choice = in.next();

					if (choice.equalsIgnoreCase("Y")) {

						List<List<String>> rows = Arrays.asList(Arrays.asList(vehicleMake, vehicleModel, vehicleWeight, vehicleReg, vehicleType, depot));

						for (List<String> rowData : rows) {
							csvWriter.append(String.join(",", rowData));
							csvWriter.append(",");
							csvWriter.append("\n");
						}

						csvWriter.flush();
						csvWriter.close();
					} else if (choice.equalsIgnoreCase("N")) {
						System.out.println("--VEHICLE CREATION CANCELLED--");
						
					}

	}
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}
}
