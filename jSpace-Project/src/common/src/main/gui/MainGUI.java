package common.src.main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainGUI extends JFrame {

	// DECLARATIONS
	Login newLogin;
	Lobby newLobby;
	GameGUI newGameGUI;
	CreateGame newCreateGame;
	ReadyUpLobby newReadyUpLobby;
	private boolean lob2Log;
	private boolean log2Lob;
	private boolean game2Lob;
	private boolean gLob2Lob;
	private boolean cg2Lob;
	private boolean lob2cg;
	private boolean lob2gLob;
	private boolean cg2gLob;
	private boolean gLob2game;

	// CONSTRUCTOR
	public MainGUI(){

		this.setTitle("Cards Against Humanity");
		this.setSize(1900,1000);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		runLogin(); // når programmet køres, skal den starte med Login.
		
		if(lob2Log){
			runLogin();
		} else if(log2Lob || game2Lob || gLob2Lob || cg2Lob){
			runLobby();
		} else if(lob2cg){
			runCreateGame();
		} else if(lob2gLob || cg2gLob){
			runReadyUpLobby();
		} else if(gLob2game){
			runGameGUI();
		} else {
			// Something went terribly wrong! ha. 
		}
	}


	// METHODS
	
	public void changeGUI(){
		
		/*
		lob2Log;
		log2Lob;
		game2Lob;
		gLob2Lob;
		cg2Lob;
		lob2cg;
		lob2gLob;
		cg2gLob;
		gLob2game;
		*/
	}

	public void runLogin(){

		newLogin = new Login();

	}

	public void runLobby(){

		newLogin.setVisible(false);
		//newLogin.setVisible(false);
		try {
			newLobby = new Lobby();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void runCreateGame(){

		//previous.setVisible(false);
		newCreateGame = new CreateGame();

		this.setContentPane(newCreateGame);
		revalidate();
		repaint();
	}

	public void runReadyUpLobby(){

		//previous.setVisible(false);
		this.add(newReadyUpLobby);

		this.setContentPane(newReadyUpLobby);
		revalidate();
		repaint();
	}

	public void runGameGUI(){

		//previous.setVisible(false);
		newGameGUI = new GameGUI();

		this.setContentPane(newReadyUpLobby);		
		revalidate();
		repaint();
	}


	// MAIN
	static void main(String[] args) {

		/*
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {

					runLogin();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); */
	}
}