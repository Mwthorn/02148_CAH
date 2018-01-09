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

	// CONSTRUCTOR
	public MainGUI(){

		this.add(newLogin);
		this.add(newLobby);
		this.add(newGameGUI);
		this.add(newCreateGame)
		this.add(newReadyUpLobby);

		this.setTitle("Cards Against Humanity");
		this.setSize(1900,1000);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}


	// METHODS

	public void runLogin(){
		newLogin = new Login();
	}

	public void runLobby(){
		newLogin.setVisible(false);
		try {
			newLobby = new Lobby();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void runCreateGame(){
		//?.setVisible(false);
		newCreateGame = new CreateGame();
	}

	public void runReadyUpLobby(){
		//?.setVisible(false);
		this.add(newReadyUpLobby);
	}

	public void runGameGUI(){
		//?.setVisible(false);
		newGameGUI = new GameGUI();
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
