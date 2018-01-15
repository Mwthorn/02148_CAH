package common.src.main.server.database;
import common.src.main.server.utilities.BlackCard;
import common.src.main.server.utilities.WhiteCard;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CardDataBase{
	private BufferedReader reader;
	private FileInputStream inputStream;
	private ArrayList<WhiteCard> wDataBase  = new ArrayList<WhiteCard>();
	private ArrayList<BlackCard> bDataBase  = new ArrayList<BlackCard>();
	 
	public CardDataBase() {
		loadWhiteDeck();
		loadBlackDeck();
		close();
	}

	public void loadWhiteDeck(){
		String quip;
		
		try {
			inputStream = new FileInputStream("TextWC.txt");
			reader = new BufferedReader(new InputStreamReader(inputStream));

			while( (quip = reader.readLine()) != null ){
				wDataBase.add(new WhiteCard(quip));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		close();
	}


	public void loadBlackDeck(){
		String numbers, sentence;
		int numOfLines, numOfBlanks, inputBlanks = 0;

		try {
			inputStream = new FileInputStream("TextBC.txt");
			reader = new BufferedReader( new InputStreamReader(inputStream));

			while( (numbers = reader.readLine()) != null){
				
				numOfLines = Integer.parseInt(numbers.split(" ")[0]);
				numOfBlanks = Integer.parseInt(numbers.split(" ")[1]);
				inputBlanks = numOfBlanks;
				
				sentence = "";
				
				for(int i = 0; i < numOfLines; i++ ) {
					if (inputBlanks == 0) {
						sentence = sentence + reader.readLine();
					} else {
						sentence = sentence + reader.readLine() + " ________";
						inputBlanks--;
					}
				}
				bDataBase.add(new BlackCard(numOfBlanks, sentence));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		close();
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
	
	public ArrayList<WhiteCard> getWhiteCards(){
		return this.wDataBase;
	}
	
	public ArrayList<BlackCard> getBlackCards(){
		return this.bDataBase;
	}
}
