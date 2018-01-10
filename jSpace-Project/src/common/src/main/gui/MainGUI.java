package common.src.main.gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainGUI extends JFrame{

	// DECLARATIONS
	Login newLogin;
	Lobby newLobby;
	GameGUI newGameGUI;
	CreateGame newCreateGame;
	ReadyUpLobby newReadyUpLobby;
	boolean start = true;
	private boolean lob2Log, game2Lob, gLob2Lob, cg2Lob, lob2cg, lob2gLob, cg2gLob, gLob2game = false; // slettes som der oprettes bool i GUI klasserne der kan kaldes


	// MAIN
	
//	public static void main(String[] args){
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new MainGUI().setVisible(true);
//			}
//		});
//	}
//	
	public static void main(String[] args) {

	MainGUI gui = new MainGUI();
	
	}

	// CONSTRUCTOR
	public MainGUI(){

		Login newLogin = new Login();
		newLogin.setVisible(true);
		
		/*
		// LOGIN		
		if(start || lob2Log){

			if (lob2Log){
				newLobby.dispose();
				newLobby.setVisible(false);
			}
			
			start = false;
			Login newLogin = new Login();
			newLogin.setVisible(true);
			
		} // LOBBY
		else if(newLogin.signIn || game2Lob || gLob2Lob || cg2Lob){

			if (newLogin.signIn){
				newLogin.dispose();
				newLogin.setVisible(false);
				
			} else if (game2Lob){
				// newGameGUI.setVisible(false);
			} else if (gLob2Lob){
				newReadyUpLobby.setVisible(false);
			} else if (cg2Lob){
				newCreateGame.setVisible(false);
			} else {
				// nothing
			}

			try {
				
				newLobby = new Lobby();
				newLobby.setVisible(true);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} // CREATE GAME
		else if(lob2cg){

			newCreateGame = new CreateGame();
			newCreateGame.setVisible(true);

		} // GAME LOBBY
		else if(lob2gLob || cg2gLob){

			newReadyUpLobby = new ReadyUpLobby();
			newReadyUpLobby.setVisible(true);

		} // GAME GUI
		else if(gLob2game){

			newGameGUI = new GameGUI();
			// newGameGUI.setVisible(true);

		} else {
			// Something went terribly wrong! ha. 
		} */
	}
}