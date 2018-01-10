package common.src.main.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {

	// DECLARATIONS
	Login newLogin;
	Lobby newLobby;
	GameGUI newGameGUI;
	CreateGame newCreateGame;
	ReadyUpLobby newReadyUpLobby;
	private boolean start = true;
	private boolean lob2Log, game2Lob, gLob2Lob, cg2Lob, lob2cg, lob2gLob, cg2gLob, gLob2game; // slettes som der oprettes bool i GUI klasserne der kan kaldes


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
	gui.setVisible(true);
	
	}

	// CONSTRUCTOR
	public MainGUI(){
		this.setTitle("Cards Against Humanity");
		this.setSize(1900,1000); // Use pack instead
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		// LOGIN		
		if(lob2Log || start){

			if (lob2Log){
				newLobby.setVisible(false);
			}
			
			start = false;
			newLogin = new Login();
			this.add(newLogin);
			revalidate();
			repaint();

			
		} // LOBBY
		else if(newLogin.signIn || game2Lob || gLob2Lob || cg2Lob){

			if (newLogin.signIn){
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
				this.add(newLobby);
				revalidate();
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} // CREATE GAME
		else if(lob2cg){

			newCreateGame = new CreateGame();
			this.add(newCreateGame);
			revalidate();
			repaint();

		} // GAME LOBBY
		else if(lob2gLob || cg2gLob){

			newReadyUpLobby = new ReadyUpLobby();
			this.add(newReadyUpLobby);
			revalidate();
			repaint();

		} // GAME GUI
		else if(gLob2game){

			newGameGUI = new GameGUI();
			// viser fejl
			// this.add(newGameGUI);
			revalidate();
			repaint();

		} else {
			// Something went terribly wrong! ha. 
		}
	}



}