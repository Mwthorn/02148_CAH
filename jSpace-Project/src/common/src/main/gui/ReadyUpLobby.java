package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


import common.src.main.client.Client;

/**
 * Created by Mwthorn on 04-01-2018.
 */
public class ReadyUpLobby extends JFrame implements ActionListener{

	private JButton b1, b2, b3, b4;
	private JLabel l1,l2,l3,l4,l5;
	private JList pList;



	/*
	public static List<GamePreview> putList() throws InterruptedException{
		GamePreview[] gameList = new Client().getGameList();
		gpList = new ArrayList<GamePreview>();


		for(int i=0; i < gameList.length; i++){
			gpList.add(gameList[i]);
		}
		System.out.println(gpList);
		return gpList;
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
		b2.setPreferredSize(btnsize2);
		b2.setBackground(Color.BLACK);
		b2.addActionListener(this);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);


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


		//Creates panel for buttons
		/*JPanel p1 = new JPanel();

		p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
		p1.setBackground(Color.WHITE);
		p1.add(b2);
		p1.add(Box.createHorizontalGlue());
		p1.add(b1);

		getContentPane().add(p1, BorderLayout.SOUTH);
	
	*/
		//Creates panel for labels
		JPanel p2 = new JPanel();
		
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.setBackground(Color.WHITE);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4, BoxLayout.PAGE_AXIS));
		p4.setBackground(Color.WHITE);
		
		// Panel for Title label
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		p2.setBackground(Color.WHITE);
		p2.add(l1);

		p3.add(Box.createRigidArea(new Dimension(100,130)));
		p3.add(l2);
		p3.add(Box.createRigidArea(new Dimension(100,100)));

		// Panel for White Card image
		p4.add(Box.createRigidArea(new Dimension(365,130)));
		p4.add(l3);
		p4.add(Box.createRigidArea(new Dimension(365,100)));
		p4.add(b1);
		p4.add(Box.createRigidArea(new Dimension(365,50)));

		getContentPane().add(p2, BorderLayout.NORTH);
		getContentPane().add(p3, BorderLayout.WEST);
		getContentPane().add(p4, BorderLayout.EAST);



	}

	public void actionPerformed(ActionEvent e){



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
