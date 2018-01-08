package common.src.main;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CardDataBase{
	
	 BufferedReader reader = null;
	 FileInputStream inputStream = null;
	 List<String> CardDatabase  = new ArrayList<String>();
	
	public CardDataBase() {
	}

	public List<String> getWhiteDeck(){
		String quip = null;
		
		try {
			inputStream = new FileInputStream("TextWC.txt");
			reader = new BufferedReader(new InputStreamReader(inputStream));

			while( (quip = reader.readLine()) != null ){
				CardDatabase.add(quip);
				quip = "";
			}

		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("Failed to read white cards successfully.");
		}

		close();
		// System.out.println(cardList);
		return CardDatabase;
	}


	public  List<String> getBlackDeck(){
		String numbers, text = null;
		int numOfLines, numOfBlanks = 0;

		try {
			inputStream = new FileInputStream("TextBC.txt");
			reader = new BufferedReader( new InputStreamReader(inputStream));

			while( (numbers = reader.readLine()) != null){
				
				numOfLines = Integer.parseInt(numbers.split(" ")[0]);
				numOfBlanks = Integer.parseInt(numbers.split(" ")[1]);
				text = "";
				
				for(int i = 0; i < numOfLines; i++ ) {
					if (numOfBlanks == 0) {
						text = text + reader.readLine();
					} else {
						text = text + reader.readLine() + " ________";
						numOfBlanks--;
					}
				} CardDatabase.add(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("Failed to read black cards.");
		}
		close();
		// System.out.println(cardList);
		return CardDatabase;
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
