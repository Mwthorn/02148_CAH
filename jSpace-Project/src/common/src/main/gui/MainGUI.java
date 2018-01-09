package common.src.main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainGUI {

	public static Login newLogin = new Login();

	
	public static void runLogin(){

		newLogin.setTitle("Cards Against Humanity");
		newLogin.setSize(1900,1000);
		newLogin.setResizable(true);
		newLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newLogin.setVisible(true);
		newLogin.setLocationRelativeTo(null);
	
	}
	
	public void runLobby(){
		
		try {
			Lobby newLobby = new Lobby();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newLogin.setVisible(false);
		
		
	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					
					runLogin();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
