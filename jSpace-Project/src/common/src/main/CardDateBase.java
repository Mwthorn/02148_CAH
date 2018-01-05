package common.src.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CardDateBase {

	// DECLARATIONS
	static BufferedReader br = null;


	public static void main(String[] args){
		
		getWhiteText();
		
		getBlackText();
	
	}
	
	public static String getWhiteText(){

		String quip = null;

		try {
			br = new BufferedReader(new FileReader("TextWC.txt"));

			while(br.readLine() != null){
				quip = (String) br.readLine();

				System.out.println(quip);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read white cards successfully.");
		}
		return "0";
	}


	public static String getBlackText(){

		String numberOf = null;
		int numberOfLines = 0;
		int numberOfBlanks = 0;
		String[] q = new String[3];

		try {

			br = new BufferedReader(new FileReader("TextBC.txt"));

			while(br.readLine() != null){

				numberOf = br.readLine();
				numberOfLines = Integer.parseInt(numberOf.split(" ")[0]);
				numberOfBlanks = Integer.parseInt(numberOf.split(" ")[1]);

				for(int i = 0; i < numberOfLines; i++ ) {

					q[i] = br.readLine();

					System.out.println(q);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read black cards.");
		}
		return "1";
	}	

}
