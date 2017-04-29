import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
	
	//MEAN
	private static double mean_wind_speed;
	private static double mean_air_temperature;
	private static double mean_barometric_pressure;
	
	//MEDIAN
	private static double median_wind_speed;
	private static double median_air_temperature;
	private static double median_barometric_pressure;
	
	//

	
	//Main method
	public static void main(String[] args) throws IOException {
		
		
		Double[] wind_speed;
		Double[] air_temperature;
		Double[] barometric_pressure;
			
		
		int totalRows = totalLines(FILE) - 1;
		
		wind_speed = new Double[totalRows];
		air_temperature = new Double[totalRows];
		barometric_pressure = new Double[totalRows];
						
		readSource(FILE);
		

		
	}
	
	
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
		System.out.println(i);
		return i;
	}
	
	
	private static void readSource(String FILE) throws IOException{
		
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
							
					//dataLine = line.split("");
					System.out.println(line);
					//System.out.println(line.split(" "));
					
					index = row - 1;
					//wind_speed[index] = Double.parseDouble(dataLine[8]);
					//air_temperature[index] = Double.parseDouble(dataLine[2]);
					//barometric_pressure[index] = Double.parseDouble(dataLine[3]);
				}
				System.out.println(row);
				row++;
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
