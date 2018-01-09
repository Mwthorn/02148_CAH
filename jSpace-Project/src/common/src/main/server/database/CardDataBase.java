package common.src.main.server.database;
import common.src.main.server.utilities.BlackCard;
import common.src.main.server.utilities.WhiteCard;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CardDataBase{
	
	 BufferedReader reader = null;
	 FileInputStream inputStream = null;
	 ArrayList<WhiteCard> wDataBase  = new ArrayList<WhiteCard>();
	 ArrayList<BlackCard> bDataBase  = new ArrayList<BlackCard>();
	 
	public CardDataBase() {
	}

	public ArrayList<WhiteCard> getWhiteDeck(){
		String quip = null;
		WhiteCard newWC;
		
		try {
			inputStream = new FileInputStream("TextWC.txt");
			reader = new BufferedReader(new InputStreamReader(inputStream));

			while( (quip = reader.readLine()) != null ){
				
				newWC = new WhiteCard(quip);
				wDataBase.add(newWC);
				quip = "";
			}
		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("Failed to read white cards successfully.");
		}
		close();
		// System.out.println(cardList);
		return wDataBase;
	}


	public ArrayList<BlackCard> getBlackDeck(){
		String numbers, sentence = null;
		int numOfLines, numOfBlanks = 0;
		BlackCard newBC;

		try {
			inputStream = new FileInputStream("TextBC.txt");
			reader = new BufferedReader( new InputStreamReader(inputStream));

			while( (numbers = reader.readLine()) != null){
				
				numOfLines = Integer.parseInt(numbers.split(" ")[0]);
				numOfBlanks = Integer.parseInt(numbers.split(" ")[1]);
				sentence = "";
				
				for(int i = 0; i < numOfLines; i++ ) {
					if (numOfBlanks == 0) {
						sentence = sentence + reader.readLine();
					} else {
						sentence = sentence + reader.readLine() + " ________";
						numOfBlanks--;
					}
				} 
				newBC = new BlackCard(numOfBlanks, sentence);
				bDataBase.add(newBC);
			}
		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("Failed to read black cards.");
		}
		close();
		// System.out.println(cardList);
		return bDataBase;
	}	

	public void close(){
		try {
			reader.close();
			inputStream.close();
		} catch (IOException e) {
			// System.out.println("Failed to close reader/inputStream.");
			e.printStackTrace();
		}
	}
}
