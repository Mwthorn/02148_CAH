package common.src.main.gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainGUI extends JFrame{

	public static void main(String[] args) {

	MainGUI gui = new MainGUI();
	
	}

	// CONSTRUCTOR
	public MainGUI(){

		new Login().setVisible(true);
		
	}
}