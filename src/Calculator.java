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
	
	//Calculate data
	public void calcData(){
		
		FILE = setDataFile(year);
		
		int totalRows;
				
		try {
			totalRows = totalLines(FILE) - 1;
					
			wind_speed = new Double[totalRows];
			air_temperature = new Double[totalRows];
			barometric_pressure = new Double[totalRows];
					
			readSource(FILE, wind_speed, air_temperature, barometric_pressure);
					
		} 
		catch (IOException ex) {
					
			// TODO Auto-generated catch block			
			ex.printStackTrace();				
			System.out.println(ex.getMessage());
		}
				
		clacMean();

		median_wind_speed = calcMedian(wind_speed);
		median_air_temperature = calcMedian(air_temperature);
		median_barometric_pressure = calcMedian(barometric_pressure);
		
		
		//Print the results - DEBUG ONLY
		System.out.println(
				String.format(
					"median_wind_speed: %.2f, "
					+ "median_air_temperature: %.2f, "
					+ "median_barometric_pressure: %.2f", 
					median_wind_speed, median_air_temperature, median_barometric_pressure));
		
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
		
	//Calculate and display MEAN values
	private static void clacMean(){
			
		mean_wind_speed = total_wind_speed  / wind_speed.length;
		mean_air_temperature = total_air_temperature / air_temperature.length;
		mean_barometric_pressure = total_barometric_pressure / barometric_pressure.length;
			
		System.out.println(String.format("mean_air_temperature: %.2f, mean_air_temperature: %.2f, mean_barometric_pressure: %.2f", mean_wind_speed, mean_air_temperature, mean_barometric_pressure));
	}
		
	//Get total number of lines in the source file
	private static int totalLines(String FILE) throws IOException{
			
		FileReader fileReader = new FileReader(FILE);
		BufferedReader bufReader = new BufferedReader(fileReader);
			
		int i = 0;
		String line = "";
			
		while(line != null){
				
			try {
				line = bufReader.readLine();
				//System.out.println(line);
				if(line != null){
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
	private static void readSource(String FILE, Double[] wind_speed, Double[] air_temperature, Double[] barometric_pressure) throws IOException{
			
			total_wind_speed = 0.0;
			total_air_temperature = 0.0;
			total_barometric_pressure = 0.0;
					
			FileReader fileReader = new FileReader(FILE);
			BufferedReader bufReader = new BufferedReader(fileReader);
			
			int row = 0;
			int index;
			String line = "";
			String[] dataLine;
			
			try {
				while(line != null){
					
				line = bufReader.readLine(); //Read next line
					
				if(row != firstRow && line != null){
								
					dataLine = line.split("\t");					
					index = row - 1; //Index for array members
						
					wind_speed[index] = Double.parseDouble(dataLine[7]);
					total_wind_speed = total_wind_speed + wind_speed[index];
						
					air_temperature[index] = Double.parseDouble(dataLine[1]);
					total_air_temperature = total_air_temperature + air_temperature[index];
						
					barometric_pressure[index] = Double.parseDouble(dataLine[2]);
					total_barometric_pressure = total_barometric_pressure + barometric_pressure[index];
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

	//End

}
