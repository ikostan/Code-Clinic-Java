import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.DoubleStream;
//import java.lang.reflect.Array;
//import java.util.ArrayList;

public class Main {

	
	/*
	 You can browse this data by pointing your web browser at lpo.dt.navy.mil. 
	 You'll find several weather summaries, a web cam, and the raw data that they collect every five minutes, 
	 archived as standard text files. For anyone living or working on Lake Pend Oreille, 
	 weather statistics are an important part of every day life. 
	 
	 Average wind speed can be very different from medium wind speed, 
	 especially if you were a small boat in the middle of the lake. In this challenge, each of our authors will use 
	 their favorite language to calculate:
	 
	 1. the mean of wind speed, air temperature, and barometric pressure
	 2. the median of wind speed, air temperature, and barometric pressure 
	 
	 (recorded at the Deep Moor Station for given range of dates) 
	 */
	
	private final static String FILE = "Environmental_Data_Deep_Moor_2015.txt";
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

	
	//Main method
	public static void main(String[] args) throws IOException {
			
		int totalRows = totalLines(FILE) - 1;
		
		wind_speed = new Double[totalRows];
		air_temperature = new Double[totalRows];
		barometric_pressure = new Double[totalRows];
			
		readSource(FILE, wind_speed, air_temperature, barometric_pressure);
		clacMean();

		
		median_wind_speed = calcMedian(wind_speed);
		median_air_temperature = calcMedian(air_temperature);
		median_barometric_pressure = calcMedian(barometric_pressure);
		
		System.out.println(
				String.format(
						"median_wind_speed: %.2f, "
						+ "median_air_temperature: %.2f, "
						+ "median_barometric_pressure: %.2f", 
						median_wind_speed, median_air_temperature, median_barometric_pressure));
		
	}
	
	//Calculate and MEDIAN
	private static double calcMedian(Double[] dataArray){
		
		double median = 0.0;
		int index = 0;
		
		if((dataArray.length % 2) == 0){
			
			median = (dataArray[dataArray.length] + dataArray[dataArray.length + 1]) / 2;		
		}
		else{
			index = dataArray.length / 2;			
			median = dataArray[index];
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
	
	
	//Read source file and extract rellevant data from it
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
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			String error = ex.getMessage();
			System.out.print(error);
		}
		
		fileReader.close();
	}

	//End
}
