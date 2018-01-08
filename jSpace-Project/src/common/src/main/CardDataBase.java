package common.src.main;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import common.src.main.cards.BlackCard;
import common.src.main.cards.WhiteCard;

public class CardDataBase{
	
	 BufferedReader reader = null;
	 FileInputStream inputStream = null;
	 List<WhiteCard> whiteDB  = new ArrayList<WhiteCard>();
	 List<BlackCard> blackDB  = new ArrayList<BlackCard>();
	 
	public CardDataBase() {
	}

	public List<WhiteCard> getWhiteDeck(){
		String quip = null;
		WhiteCard newWC;
		
		try {
			inputStream = new FileInputStream("TextWC.txt");
			reader = new BufferedReader(new InputStreamReader(inputStream));

			while( (quip = reader.readLine()) != null ){
				
				newWC = new WhiteCard(quip);
				whiteDB.add(newWC);
				quip = "";
			}

		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("Failed to read white cards successfully.");
		}

		close();
		// System.out.println(cardList);
		return whiteDB;
	}


	public  List<BlackCard> getBlackDeck(){
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
				blackDB.add(newBC);
			}
		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("Failed to read black cards.");
		}
		close();
		// System.out.println(cardList);
		return blackDB;
	}	

	public void close(){
		try {
			reader.close();
			inputStream.close();
		} catch (IOException e) {
			// System.out.println("Failed to close bis/fis.");
			e.printStackTrace();
		}
	}
}
