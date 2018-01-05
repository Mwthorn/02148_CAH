package common.src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardDateBase {

	// DECLARATIONS
	static BufferedReader reader = null;

	public static void main(String[] args){

		// getWhiteText();

		getBlackText();

		close();
	}

	public static String getWhiteText(){

		String quip = null;

		try {
			reader = new BufferedReader( new InputStreamReader(new FileInputStream("TextWC.txt")));

			while( (quip = reader.readLine()) != null ){

				System.out.println(quip);

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read white cards successfully.");
		}
		return "0";
	}


	public static String getBlackText(){

		String numbers = null;
		int numOfLines = 0;
		int numOfBlanks = 0;
		String[] q = new String[3];

		try {

			reader = new BufferedReader( new InputStreamReader(new FileInputStream("TextBC.txt")));

			while( (numbers = reader.readLine()) != null){

				numOfLines = Integer.parseInt(numbers.split(" ")[0]);
				numOfBlanks = Integer.parseInt(numbers.split(" ")[1]);

				for(int i = 0; i < numOfLines; i++ ) {

					numbers = reader.readLine();
					q[i] = numbers;

					System.out.println(q[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read black cards.");
		}
		return "1";
	}	

	public static void close(){

		try {
			reader.close();
			//fis.close();
		} catch (IOException e) {
			System.out.println("Failed to close bis/fis.");
			e.printStackTrace();
		}
		
	}

}
