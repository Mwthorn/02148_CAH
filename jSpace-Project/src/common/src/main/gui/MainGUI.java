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

		this.setTitle("Cards Against Humanity");
		this.setSize(1900,1000);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.getContentPane().add(newLogin);
		this.getContentPane().add(newLobby);
		this.getContentPane().add(newGameGUI);
		this.getContentPane().add(newCreateGame);
		this.getContentPane().add(newReadyUpLobby);

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