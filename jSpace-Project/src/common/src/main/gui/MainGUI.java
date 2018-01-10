package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import common.src.main.client.GamePreview;

@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener {

	// Login
	private JButton BQuit, BSignIn;
	private JLabel LTitle, LText, LFigure1, LName, LIP, LFigure2;
	private JTextField txtfld1, txtfld2;
	public boolean signIn = false;

	// Lobby
	private static final String String = null;
	private JButton b1, b2, b3, b4;
	private JLabel l1,l2,l3,l4,l5;
	private JTextField WP;
	private static JList list;



	/////////// MAIN ///////////

	public static void main(String[] args) {

		MainGUI main = new MainGUI();

		main.setTitle("Cards Against Humanity");
		main.setSize(1900,1000);
		main.setResizable(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		main.setLocationRelativeTo(null);

	}

	////////// MAIN ////////////


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

	JPanel mainLogin = new JPanel();
	JPanel mainLobby = new JPanel();

	// Dimensions of buttons
	Dimension btnsize4 = new Dimension(110,40);
	Dimension btnsize2 = new Dimension(120,60);
	Dimension btnsize5 = new Dimension(200,100);

	//Textfield laves
	Dimension txtfldsize = new Dimension(400, 30);

	//Labels
	Dimension lsize = new Dimension(99, 50);

	public MainGUI(){

		getContentPane().setLayout(new BorderLayout());
		getContentPane().setSize(1900,1000);


		runLogin();
		runLobby();

	}


	public void runLogin(){ 

		/////////////////////////////////////////////// LOGIN //////////////////////////////////////////////////////////////////

		// Using BorderLayout
		mainLogin.setLayout(new BorderLayout());

		//		// Window dimensions
		//		setTitle("Cards Against Humanity");
		//		setSize(1900,1000);
		//		setResizable(true);
		//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		setVisible(true);
		//		setLocationRelativeTo(null);


		// Make Quit Button
		BQuit = new JButton("Quit");
		BQuit.setMaximumSize(btnsize2);
		BQuit.addActionListener(this);
		BQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
		BQuit.setFont(new Font("calibri",1,21));
		BQuit.setBorderPainted(false);
		BQuit.setFocusPainted(false);
		BQuit.setEnabled(true);
		BQuit.setForeground(Color.WHITE);
		BQuit.setBackground(Color.black);

		// Make Sign In button
		BSignIn = new JButton("Sign In");
		BSignIn.setMaximumSize(btnsize4);
		BSignIn.addActionListener(this);
		BSignIn.setAlignmentX(Component.CENTER_ALIGNMENT);
		BSignIn.setFont(new Font("calibri",1,21));
		BSignIn.setBorderPainted(false);
		BSignIn.setFocusPainted(false);
		BSignIn.setEnabled(true);
		BSignIn.setForeground(Color.WHITE);
		BSignIn.setBackground(Color.black);	

		//Makes Title
		LTitle = new JLabel("Cards Against Humanity");
		LTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		LTitle.setFont(new Font("AR JULIAN",Font.PLAIN,70));
		LTitle.setForeground(Color.BLACK);

		//Makes lower title
		LText = new JLabel("Welcome to Cards Against Humanity");
		LText.setAlignmentX(Component.CENTER_ALIGNMENT);
		LText.setFont(new Font("calibri",Font.PLAIN,30));
		LText.setForeground(Color.black);

		// Name Label
		LName = new JLabel("Name:");
		LName.setMaximumSize(lsize);
		LName.setFont(new Font("calibri",Font.PLAIN,25));
		LName.setAlignmentX(Component.CENTER_ALIGNMENT);

		// IP Label
		LIP = new JLabel("Server IP:");
		LIP.setMaximumSize(lsize);
		LIP.setFont(new Font("calibri",Font.PLAIN,25));
		LIP.setAlignmentX(Component.CENTER_ALIGNMENT);

		// The two cards in the sides
		LFigure1 = new JLabel();
		LFigure2 = new JLabel();
		LFigure1.setIcon(new ImageIcon(new ImageIcon("BCLogin.png").getImage().getScaledInstance(243, 376, Image.SCALE_DEFAULT)));
		LFigure2.setIcon(new ImageIcon(new ImageIcon("WCLogin.png").getImage().getScaledInstance(245, 376, Image.SCALE_DEFAULT)));
		LFigure1.setAlignmentX(Component.CENTER_ALIGNMENT);
		LFigure2.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name textfield
		txtfld1 = new JTextField(50);
		txtfld1.setMaximumSize(txtfldsize);
		txtfld1.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld1.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name textfield
		txtfld2 = new JTextField(50);
		txtfld2.setMaximumSize(txtfldsize);
		txtfld2.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld2.setAlignmentX(Component.CENTER_ALIGNMENT);


		// Create the 
		JPanel PC = new JPanel();
		PC.setLayout(new BorderLayout());
		PC.setSize(600, 400);

		// Makes middle JPanel
		JPanel PCenter = new JPanel();
		PCenter.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));			
		PCenter.setLayout(new BorderLayout()); //Default layout
		PCenter.setBackground(Color.BLACK);

		JPanel PCCenter = new JPanel();
		PCCenter.setLayout(new BoxLayout(PCCenter, BoxLayout.PAGE_AXIS));
		PCCenter.add(Box.createRigidArea(new Dimension(100, 20)));
		PCCenter.add(LText);
		PCCenter.add(Box.createRigidArea(new Dimension(100, 20)));
		PCCenter.add(txtfld1, BorderLayout.CENTER);
		PCCenter.add(Box.createRigidArea(new Dimension(100, 40)));
		PCCenter.add(txtfld2, BorderLayout.CENTER);

		PCenter.add(PCCenter, BorderLayout.CENTER);

		//Button inside
		JPanel PCRight = new JPanel();
		PCRight.add(Box.createRigidArea(new Dimension(10, 150)));
		PCRight.add(BSignIn);

		//Placering af button
		PCRight.setLayout(new BoxLayout(PCRight, BoxLayout.PAGE_AXIS));
		PCRight.add(Box.createRigidArea(new Dimension(150, 40)));
		PCenter.add(PCRight, BorderLayout.EAST);

		JPanel PCLeft = new JPanel();
		PCLeft.setLayout(new BoxLayout(PCLeft, BoxLayout.PAGE_AXIS));
		PCLeft.add(Box.createRigidArea(new Dimension(150, 68)));
		PCLeft.add(LName);
		PCLeft.add(Box.createRigidArea(new Dimension(150, 20)));
		PCLeft.add(LIP);

		PCRight.setBackground(Color.WHITE);
		PCCenter.setBackground(Color.WHITE);
		PCLeft.setBackground(Color.WHITE);

		PCenter.add(PCLeft, BorderLayout.WEST);

		//Left JPanel
		JPanel PL = new JPanel();
		PL.setLayout(new BoxLayout(PL, BoxLayout.PAGE_AXIS));
		PL.setBackground(Color.white);

		//Right JPanel
		JPanel PR = new JPanel();
		PR.setLayout(new BoxLayout(PR, BoxLayout.PAGE_AXIS));
		PR.setBackground(Color.white);

		//Left JPanel
		JPanel PLeft = new JPanel();
		PLeft.setLayout(new BoxLayout(PLeft, BoxLayout.PAGE_AXIS));
		PLeft.setBackground(Color.white);

		PLeft.add(Box.createRigidArea(new Dimension(365,200)));
		PLeft.add(LFigure1);
		PLeft.add(Box.createRigidArea(new Dimension(365,100)));

		//Right JPanel
		JPanel PRight = new JPanel();
		PRight.setLayout(new BoxLayout(PRight, BoxLayout.PAGE_AXIS));
		PRight.setBackground(Color.white);

		PRight.add(Box.createRigidArea(new Dimension(365,350)));
		PRight.add(LFigure2);
		PRight.add(Box.createRigidArea(new Dimension(365,50)));
		PRight.add(BQuit);
		PRight.add(Box.createRigidArea(new Dimension(365,50)));

		//Siger hvor de forskellige Paneler skal vÊre
		mainLogin.add(PLeft, BorderLayout.WEST);
		mainLogin.add(PRight, BorderLayout.EAST);

		PC.add(PL, BorderLayout.WEST);
		PC.add(PR, BorderLayout.EAST);

		PL.add(Box.createRigidArea(new Dimension(150,100)));
		PR.add(Box.createRigidArea(new Dimension(150,100)));

		PC.setBackground(Color.WHITE);
		PC.add(PCenter);

		//Hovedpanel - Laver Center JPanel med de forskellige labels og tekstfelter
		JPanel PMiddle = new JPanel();
		PMiddle.setLayout(new BoxLayout(PMiddle, BoxLayout.PAGE_AXIS));
		PMiddle.setSize(400, 1000);
		PMiddle.add(LTitle);
		PMiddle.add(Box.createRigidArea(new Dimension(400, 100)));

		PMiddle.add(Box.createRigidArea(new Dimension(400, 150)));
		PMiddle.add(PC);
		PMiddle.add(Box.createRigidArea(new Dimension(400, 800)));				

		//Add PMiddle and set center
		mainLogin.add(PMiddle, BorderLayout.CENTER);

		PMiddle.setBackground(Color.WHITE);
		add(mainLogin);		

		/////////////////////////////////////////////// LOGIN //////////////////////////////////////////////////////////////////


	}


	public void runLobby() {

		/////////////////////////////////////////////// LOBBY //////////////////////////////////////////////////////////////////

		mainLobby.setLayout(new BorderLayout()); //Default layout
		mainLobby.setBackground(Color.WHITE);

		//Create buttons
		//		Dimension btnsize1 = new Dimension(200,80);
		//		Dimension btnsize2 = new Dimension(150,80);

		// Create game button
		b1 = new JButton("Create Game");
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.black);
		b1.addActionListener(this);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setMinimumSize(btnsize5);
		b1.setMaximumSize(btnsize5);
		b1.setFont(new Font("calibri",1,21));
		b1.setBorderPainted(false);
		b1.setFocusPainted(false);
		b1.setEnabled(true);



		// Sign out button
		b2 = new JButton("Sign Out");
		b2.setForeground(Color.WHITE);
		b2.setBackground(Color.black);
		b2.addActionListener(this);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setMinimumSize(btnsize5);
		b2.setMaximumSize(btnsize5);
		b2.setFont(new Font("calibri",1,21));
		b2.setBorderPainted(false);
		b2.setFocusPainted(false);
		b2.setEnabled(true);

		// Join game button
		b3 = new JButton("Join Game");
		b3.setForeground(Color.WHITE);
		b3.setBackground(Color.black);
		b3.addActionListener(this);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setMinimumSize(btnsize5);
		b3.setMaximumSize(btnsize5);
		b3.setFont(new Font("calibri",1,21));
		b3.setBorderPainted(false);
		b3.setFocusPainted(false);
		b3.setEnabled(true);

		// Refresh button
		b4 = new JButton("Refresh");
		b4.setForeground(Color.WHITE);
		b4.setBackground(Color.black);
		b4.addActionListener(this);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		b4.setMinimumSize(btnsize5);
		b4.setMaximumSize(btnsize5);
		b4.setFont(new Font("calibri",1,21));
		b4.setBorderPainted(false);
		b4.setFocusPainted(false);
		b4.setEnabled(true);

		//Makes Title
		l1 = new JLabel("Cards Against Humanity");
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		l1.setFont(new Font("AR JULIAN",Font.PLAIN,70));
		l1.setForeground(Color.BLACK);

		// Implementing pictures for white cards and black cards as JLabel
		l2 = new JLabel();
		l3 = new JLabel();
		l2.setIcon(new ImageIcon(new ImageIcon("BCLobby.png").getImage().getScaledInstance(250, 376, Image.SCALE_DEFAULT)));
		l3.setIcon(new ImageIcon(new ImageIcon("WCLobby.png").getImage().getScaledInstance(250, 376, Image.SCALE_DEFAULT)));
		l2.setAlignmentX(Component.CENTER_ALIGNMENT);
		l3.setAlignmentX(Component.CENTER_ALIGNMENT);


		// Create List

		//list = new JList(/*Client.getGameList().toArray()*/);
		//list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		//Creates panel for buttons
		JPanel p1 = new JPanel();

		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.setBackground(Color.WHITE);
		p1.add(b2);
		p1.add(b4);
		p1.add(b3);
		p1.add(b1);
//		p1.add(Box.createRigidArea(new Dimension(850,200)));


		mainLobby.add(p1, BorderLayout.SOUTH);

		//Creates panel for labels
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.setBackground(Color.WHITE);

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
		JPanel p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4, BoxLayout.LINE_AXIS));
		p4.setBackground(Color.WHITE);
		
		p4.add(Box.createRigidArea(new Dimension(50, 0)));
		p4.add(l3);

		mainLobby.add(p2, BorderLayout.NORTH);
		mainLobby.add(p3, BorderLayout.EAST);
		mainLobby.add(p4, BorderLayout.WEST);

		mainLobby.setVisible(false);
		// Panel for list of games available
		/*
		JPanel p5 = new JPanel();

		p5.setLayout(new BoxLayout(p5, BoxLayout.LINE_AXIS));
		p5.setBackground(Color.WHITE);
		p5.add(l5);

		mainLobby.add(p5, BorderLayout.CENTER);
		 */


		/////////////////////////////////////////////// LOBBY //////////////////////////////////////////////////////////////////

	}




	String name, IP;

	public void actionPerformed(ActionEvent e){

		/////////////////////////////////////////////////////////////////
		// Quit button closes game
		if(e.getSource() == BQuit){

			System.exit(0);

		} else if (e.getSource() == BSignIn){
			this.name = txtfld1.getText();
			this.IP = txtfld2.getText();

			//			try {
			//
			//				Client.loginUser(IP, name);
			//
			//			} catch (Exception e1) {
			//				txtfld2.setText("Invalid IP");
			//			} 

			System.out.println(name);
			System.out.println(IP);
			
			mainLogin.setVisible(false);

			mainLobby.setVisible(true);
			add(mainLobby);

			//			revalidate();
			//			repaint();

		} else if(e.getSource()==b1) {

		} else if(e.getSource()==b2) {
			dispose();
			new Login();
		}




	}







































	/*
	if(e.getSource()==b3 && Client.getGameList() != null){
		JPanel p6 = new JPanel();
		JFrame ePassword = new JFrame("Enter Password");
		ePassword.pack();
		ePassword.setVisible(true);
		ePassword.setResizable(false);
		ePassword.setLocationRelativeTo(null);

		l5 = new JLabel("Enter Password");
		WP = new JTextField(10);
		b4 = new JButton("Enter");
		b4.addActionListener(this);
		p6.add(ePassword);
		p6.add(WP);
		p6.add(b4);

		if(e.getSource()==b4 && WP == Client.getGameList()){
			new ReadyUpLobby();
			ePassword.dispose();
		}
		else{
			l5 = new JLabel("Wrong Password");
			add(l5);
		}
	}*/


	//		public String getName() {
	//			return name;
	//		}
	//		
	//		public String getIP() {
	//			return IP;
	//		}

}


/*
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
 */