import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Calculator {
	
	private static String FILE;
	private final static String FILE_2015 = "Environmental_Data_Deep_Moor_2015.txt";
	private final static String FILE_2014 = "Environmental_Data_Deep_Moor_2014.txt";
	private final static String FILE_2013 = "Environmental_Data_Deep_Moor_2013.txt";
	private final static String FILE_2012 = "Environmental_Data_Deep_Moor_2012.txt";
	
	private static int year;
	private static int month;
	private static int day;
	
	private static int totalRows;
	
	private final static int firstRow = 0;
	
	private static Double[] wind_speed;
	private static Double total_wind_speed;
	
	private static Double[] air_temperature;
	private static Double total_air_temperature;
	
	private static Double[] barometric_pressure;
	private static Double total_barometric_pressure;
	
	//MEAN
	private static double mean_wind_speed;
	private static double mean_air_temperature;
	private static double mean_barometric_pressure;
	
	//MEDIAN
	private static double median_wind_speed;
	private static double median_air_temperature;
	private static double median_barometric_pressure;
	
	//Constructor
	public Calculator(){
		
		//Get year:
		//int year = getInput("Please enter a year (yyyy):");
		//int month = getInput("Please enter a month (1-12):");
		//int day = getInput("Please enter a day (1-31):");		
	}
	
		
	//MEAN getters
	public double getMeanWindSpeed(){		
		return this.mean_wind_speed;
	}
		
	public double getMeanAirTemperature(){		
		return this.mean_air_temperature;
	}	
	
	public double getMeanBarometricPressure(){		
		return this.mean_barometric_pressure;
	}
	
	//MEDIAN getters
	public double getMedianWindSpeed(){
		return this.median_wind_speed;
	}
	
	public double getMedianAirTemperature(){
		return this.median_air_temperature;
	}
	
	public double getMedianBarometricPressure(){
		return this.median_barometric_pressure;
	}
	
	public int getTotalRows(){		
		return this.totalRows;
	}
	
	//Calculate data
	public void calcData(boolean isMean, boolean isMedian){
		
		String targetDate = concatDate(year, month, day);
		System.out.println("targetDate: " + targetDate); //DEBUG ONLY
		
		FILE = setDataFile(year);
		
		totalRows = 0;
				
		try {
			totalRows = totalLines(FILE, targetDate);
			
			System.out.println("totalRows: " + totalRows); //DEBUG ONLY
			
			if(totalRows > 0){
				
				wind_speed = new Double[totalRows];
				air_temperature = new Double[totalRows];
				barometric_pressure = new Double[totalRows];
						
				readSource(FILE, targetDate, wind_speed, air_temperature, barometric_pressure);
			}
					
		} 
		catch (IOException ex) {
					
			// TODO Auto-generated catch block			
			ex.printStackTrace();				
			System.out.println(ex.getMessage());
		}
				
		if(isMean == true && totalRows > 0){
			//clacMean();
			this.mean_wind_speed = total_wind_speed  / wind_speed.length;
			this.mean_air_temperature = total_air_temperature / air_temperature.length;
			this.mean_barometric_pressure = total_barometric_pressure / barometric_pressure.length;
		}
		else{
			this.mean_wind_speed = 0.0;
			this.mean_air_temperature = 0.0;
			this.mean_barometric_pressure = 0.0;
		}
		
		if(isMedian == true && totalRows > 0){
			this.median_wind_speed = calcMedian(wind_speed);
			this.median_air_temperature = calcMedian(air_temperature);
			this.median_barometric_pressure = calcMedian(barometric_pressure);
		}
		else{
			this.median_wind_speed = 0.0;
			this.median_air_temperature = 0.0;
			this.median_barometric_pressure = 0.0;
		}
		
		
		//Print the results - DEBUG ONLY
		System.out.println(
				String.format(
					"median_wind_speed: %.2f, "
					+ "median_air_temperature: %.2f, "
					+ "median_barometric_pressure: %.2f", 
					median_wind_speed, median_air_temperature, median_barometric_pressure));
		
		System.out.println(
				String.format(
						"mean_wind_temperature: %.2f, "
						+ "mean_air_temperature: %.2f, "
						+ "mean_barometric_pressure: %.2f", 
						mean_wind_speed, mean_air_temperature, mean_barometric_pressure));

		
	}
	
	
	public void setYear(int newYear){
		
		this.year = newYear;
	}
	
	public void setMonth(int newMonth){
		this.month = newMonth;
	}
	
	public void setDay(int newDay){
		this.day = newDay;
	}
	
	//Get user input
	private static int getInput(String request){
			
		int input = 0;
		Scanner scanner = new Scanner(System.in);
			
		System.out.println(request);
			input = scanner.nextInt();
			
		return input;
	}
		
	//Set data file
	private static String setDataFile(int year){
			
		String fileName = "";
			
			switch(year){
				
				case 2015:
					fileName = FILE_2015;
					break;
				case 2014:
					fileName = FILE_2014;
					break;
				case 2013:
					fileName = FILE_2013;
					break;
				case 2012:
					fileName = FILE_2012;
					break;
				
			}
			
		return fileName;
	}
			
	//Calculate and MEDIAN
	private static double calcMedian(Double[] dataArray){
			
		double median = 0.0;
		int index = 0;
		
		try{
			if((dataArray.length % 2) == 0){
				
				median = ((dataArray[dataArray.length/2]) + (dataArray[(dataArray.length /2) - 1])) / 2;		
			}
			else{
				index = dataArray.length / 2;			
				median = dataArray[index];
			}
		}
		catch(Exception ex){
			
			System.out.println(String.format("Error: %s\nArray length: %d", ex.toString(), dataArray.length));
		}
		
		return median;
	}
	
	
	//Get total number of lines in the source file
	private static int totalLines(String FILE, String targetDate) throws IOException{
			
		FileReader fileReader = new FileReader(FILE);
		BufferedReader bufReader = new BufferedReader(fileReader);
			
		int i = 0;
		String line = "";
			
		while(line != null){
				
			try {
				line = bufReader.readLine();
				//System.out.println(line);
				if(line != null && line.contains(targetDate)){
					i++;
				}				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
			
		fileReader.close();
		return i;
	}
		
	//Read source file and extract relevant data from it
	private static void readSource(String FILE, String targetDate, Double[] wind_speed, Double[] air_temperature, Double[] barometric_pressure) throws IOException{
			
			total_wind_speed = 0.0;
			total_air_temperature = 0.0;
			total_barometric_pressure = 0.0;
					
			FileReader fileReader = new FileReader(FILE);
			BufferedReader bufReader = new BufferedReader(fileReader);
			
			int row = 0;
			int index = 0;
			String line = "";
			String[] dataLine;
			
			try {
				while(line != null){
					
				line = bufReader.readLine(); //Read next line
					
				if(row != firstRow && line != null && line.contains(targetDate)){
					
					System.out.println(line); //DEBUG ONLY
					
					dataLine = line.split("\t");					
						
					wind_speed[index] = Double.parseDouble(dataLine[7]);
					total_wind_speed = total_wind_speed + wind_speed[index];
						
					air_temperature[index] = Double.parseDouble(dataLine[1]);
					total_air_temperature = total_air_temperature + air_temperature[index];
						
					barometric_pressure[index] = Double.parseDouble(dataLine[2]);
					total_barometric_pressure = total_barometric_pressure + barometric_pressure[index];
					
					index++; //Index for array members
				}
				
				row++; //Increment
			}
		} 
		catch (IOException ex) {
			// TODO Auto-generated catch block
			String error = ex.getMessage();
			System.out.print(error);
		}
			
		fileReader.close();
	}

	
	private String concatDate(int year, int month, int day){
		
		String date = "";
		date = String.format("%04d_%02d_%02d", year, month, day);
		//System.out.println(date); //DEBUG ONLY
		return date;
	}
	
	//End

}
