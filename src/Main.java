import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	/*High level design:
	 
	 File sources:
	 	Environmental_Data_Deep_Moor_2012.txt
	 	Environmental_Data_Deep_Moor_2013.txt
	 	Environmental_Data_Deep_Moor_2014.txt
	 	Environmental_Data_Deep_Moor_2015.txt
	 
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

	//Main method
	public static void main(String[] args) {
		
		new GUI();	//Start GUI client	
	}
	
	//End
	
}
