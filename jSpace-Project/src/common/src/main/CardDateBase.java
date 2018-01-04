package common.src.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CardDateBase {

	// DECLARATIONS
	static BufferedReader br = null;
	
	
	public static String[] whiteCardText(){
		
		String line = null;
		
		int i = 0;
		
		try {
			br = new BufferedReader(new FileReader("WhiteCardText.txt"));
			
			while(br.readLine() != null){
				line = (String) br.readLine();
				
				System.out.println(line);

			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read white cards successfully.");
		}
	}
	
	
	public static String blackCardText(){
		
		String line = null;
		String[] blackStrings = new String[4];
		
		try {
			br = new BufferedReader(new FileReader("BlackCardText.txt"));
			line = (String) br.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed toread white cards.");
		}
		return line;
	}	
	
}
