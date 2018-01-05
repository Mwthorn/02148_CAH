package common.src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CardDataBase {

	static BufferedReader reader = null;
	static FileInputStream inputStream = null;

	public static void main(String[] args){

		getWhiteText();

		getBlackText();

	}

	public static List<String> getWhiteText(){

		List<String> whiteList = new ArrayList<String>();
		String quip = null;
		
		try {
			inputStream = new FileInputStream("TextWC.txt");
			reader = new BufferedReader( new InputStreamReader(inputStream));

			while( (quip = reader.readLine()) != null ){
				whiteList.add(quip);
				quip = "";
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read white cards successfully.");
		}

		close();
		System.out.println(whiteList);
		return whiteList;
	}


	public static List<String> getBlackText(){

		String numbers, text = null;
		int numOfLines, numOfBlanks = 0;
		List<String> blackList = new ArrayList<String>();

		try {
			inputStream = new FileInputStream("TextBC.txt");
			reader = new BufferedReader( new InputStreamReader(inputStream));

			while( (numbers = reader.readLine()) != null){

				text = "";
				numOfLines = Integer.parseInt(numbers.split(" ")[0]);
				numOfBlanks = Integer.parseInt(numbers.split(" ")[1]);

				for(int i = 0; i < numOfLines; i++ ) {

					if (numOfBlanks == 0) {
						text = text + reader.readLine();
					} else {
						text = text + reader.readLine() + " ________";
						numOfBlanks--;
					}
				} 
				blackList.add(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read black cards.");
		}

		close();
		System.out.println(blackList);
		return blackList;
	}	

	public static void close(){

		try {
			reader.close();
			inputStream.close();
		} catch (IOException e) {
			System.out.println("Failed to close bis/fis.");
			e.printStackTrace();
		}

	}

}
