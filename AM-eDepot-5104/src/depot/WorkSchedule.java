package depot;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkSchedule {
	private String client;
	private String vehicleType;
	private Date startDate;
	private Date endDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String vehicleReg;
	private String driverID;

	public WorkSchedule(String client, String vehicleType, String startDate, String endDate, String startTime, String endTime,
			String vehicleReg, String driverID) throws ParseException {

		this.client = client;
		this.vehicleType = vehicleType;
		this.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
		this.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
		this.startTime = LocalTime.parse(startTime);
		this.endTime = LocalTime.parse(endTime);
		this.vehicleReg = vehicleReg;
		this.driverID = driverID;

	}

	public String getClient() {
		return this.client;
	}
	
	  public void setClient(String newClient) {
		    this.client = newClient;
	  }
	
	public String getVehicleType() {
		return this.vehicleType;
	}
	
	  public void setVehicleType(String newVehicleType) {
		    this.vehicleType = newVehicleType;
	  }

	public Date getStartDate() {
		return this.startDate;
	}
	
	  public void setStartDate(Date newStartDate) {
		    this.startDate = newStartDate;
	  }

	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(Date newEndDate) {
	    this.endDate = newEndDate;
  }

	public LocalTime getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(LocalTime newStartTime) {
	    this.startTime = newStartTime;
  }

	public LocalTime getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(LocalTime newEndTime) {
	    this.endTime = newEndTime;
  }

	public String getVehicleReg() {
		return this.vehicleReg;
	}
	
	  public void setVehicleReg(String newVehicleReg) {
		    this.vehicleReg = newVehicleReg;
	  }

	public String getDriverID() {
		return this.driverID;
	}
	
	  public void setDriverID(String newDriverID) {
		    this.driverID = newDriverID;
	  }
	  
		public static void viewSchedule() throws IOException {
			BufferedReader reader = new BufferedReader(new FileReader("src/schedule.csv"));
			List<String> lines = new ArrayList<>();
			String line = null;
			int noOutput = 0;
			System.out.println("\n");
			while ((line = reader.readLine()) != null) {
				lines.add(line + "\n ");
			}
			int size = (lines.size());
			if (size != 0) {

				for (int x = 0; x < size; x++) {
					String output = lines.get(x);

					String formattedString = output.toString().replace(",", "\t|\t");

					System.out.println(formattedString);
					noOutput++;

				}
				if (noOutput == 0) {
					System.out.println("No Scheduled Jobs");
					reader.close();
				}

			} else {
				System.out.println("No Scheduled Jobs");
				reader.close();

			}

		}
}

