package common.src.main.gui;

import javax.swing.*;

import common.src.main.client.Client;
import common.src.main.client.GamePreview;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReadyUpLobby extends JFrame implements ActionListener{

	private JButton b1, b2, b3, b4;
	private JLabel l1,l2,l3,l4,l5;
	private JList pList;



	/*
	public static ArrayList<GamePreview> putList() throws InterruptedException{
		for (GamePreview gp : Client.getGameList()) {
			gp.getCurrentPlayerSize();
			gp.getGameName();
			gp.getGameStatus();
			gp.getId();
			gp.getMaxPlayerSize();
			gp.hasPassword();
			gp.isPasswordProtected();
		}

		return Client.getGameList();
	}
	 */

	public ReadyUpLobby(){
		getContentPane().setLayout(new BorderLayout()); //Default layout
		getContentPane().setBackground(Color.WHITE);

		//Create buttons
		Dimension btnsize1 = new Dimension(200,100);
		Dimension btnsize2 = new Dimension(150,55);

		// Create game button
		b1 = new JButton("Ready");
		b1.setPreferredSize(btnsize1);
		b1.addActionListener(this);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setBorderPainted(true);
		b1.setFocusPainted(true);
		b1.setEnabled(true);
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.BLACK);

		// Sign out button
		b2 = new JButton("Leave Game");
		b2.setPreferredSize(btnsize1);
		b2.addActionListener(this);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setBorderPainted(true);
		b2.setFocusPainted(true);
		b2.setEnabled(true);
		b2.setForeground(Color.WHITE);
		b2.setBackground(Color.BLACK);
		
		b3 = new JButton("ready?");
		b3.addActionListener(this);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setBorderPainted(true);
		b3.setFocusPainted(true);
		b3.setEnabled(true);
		b3.setForeground(Color.WHITE);
		b3.setBackground(Color.BLACK);


		// Creates label "Cards Against Humanity"
		l1 = new JLabel("Cards Against Humanity ");
		l1.setPreferredSize(new Dimension(480, 50));
		l1.setFont(new Font("Cards Against Humanity",Font.ITALIC,40));
		l1.setForeground(Color.BLACK);
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Implementing pictures for white cards and black cards as JLabel
		l2 = new JLabel();
		l3 = new JLabel();

		l2.setIcon(new ImageIcon(new ImageIcon("BCRLobby.png").getImage().getScaledInstance(190, 295, Image.SCALE_DEFAULT)));
		l3.setIcon(new ImageIcon(new ImageIcon("WCRLobby.png").getImage().getScaledInstance(195, 300, Image.SCALE_DEFAULT)));

		// PANELS ARE BEING CREATED
		//Creates panel for buttons
		JPanel p1 = new JPanel();

		p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
		p1.setBackground(Color.WHITE);
		p1.add(Box.createRigidArea(new Dimension(0,100)));
		p1.add(b2);
		p1.add(Box.createHorizontalGlue());
		p1.add(b3);
		p1.add(Box.createHorizontalGlue());
		p1.add(b1);

		getContentPane().add(p1, BorderLayout.SOUTH);

		//Creates panel for labels
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.setBackground(Color.WHITE);

		JPanel p4 = new JPanel();

		// Panel for Title label
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		p2.setBackground(Color.WHITE);
		p2.add(l1);

		// Panel for Black Card image
		p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
		p3.setBackground(Color.WHITE);
		p3.add(Box.createRigidArea(new Dimension(50, 0)));
		p3.add(l2);
		p3.add(Box.createRigidArea(new Dimension(50, 0)));


		// Panel for White Card image
		p4.setLayout(new BoxLayout(p4, BoxLayout.LINE_AXIS));
		p4.add(Box.createRigidArea(new Dimension(50, 0)));
		p4.setBackground(Color.WHITE);
		p4.add(l3);

		getContentPane().add(p2, BorderLayout.NORTH);
		getContentPane().add(p3, BorderLayout.EAST);
		getContentPane().add(p4, BorderLayout.WEST);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b2){
			try {
				new Lobby();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if(e.getSource()==b3){
			

		}

	}



	public static void main(String[] args) throws InterruptedException {

		ReadyUpLobby readyLobby = new ReadyUpLobby();

		readyLobby.setSize(1080, 720);
		readyLobby.setLocationRelativeTo(null);
		readyLobby.setDefaultCloseOperation(EXIT_ON_CLOSE);
		readyLobby.setTitle("Cards Against Humanity");
		readyLobby.setResizable(true);
		readyLobby.setVisible(true);

	}

}
