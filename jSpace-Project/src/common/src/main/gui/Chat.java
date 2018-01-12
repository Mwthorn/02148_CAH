package common.src.main.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Chat extends JFrame {
	
	public static void main(String[] args) {
		
		Chat mainC = new Chat();
		mainC.setTitle("Cards Against Humanity");
		mainC.setSize(400,550);
		mainC.setResizable(true);
		mainC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainC.setVisible(true);
		mainC.setLocationRelativeTo(null);
	}
	public Chat() {


		
		
	}

	
}
