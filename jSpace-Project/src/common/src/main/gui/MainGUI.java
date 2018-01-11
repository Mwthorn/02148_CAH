package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import common.src.main.client.Client;
import common.src.main.client.GamePreview;

@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener {

	// Login
	private JButton BQuit, BSignIn, BBack;
	private JLabel LTitle, LText, LFigure1, LName, LIP, LFigure2;
	private JTextField txtfld1, txtfld2;
	public boolean signIn = false;

	// Lobby
	private static final String String = null;
	private JButton b1, b2, b3, b4, b5;
	private JLabel l1,l2,l3,l4,l5;
	private JTextField WP;
	private static JList list;

	// Create Game
	private JButton BCreateGame;
	private JLabel  LRounds, LTime, LPass, LPassword;
	private JTextField txtfld3, txtfld4, txtfld5, txtfld6;



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


	// Rounded Buttons
	// Source: https://stackoverflow.com/questions/423950/rounded-swing-jbutton-using-java
	private static class RoundedBorder implements Border {
		private int radius;

		RoundedBorder(int radius) {
			this.radius = radius;
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width-1, height-1, radius, radius);
		}
	}

	public static ArrayList<GamePreview> putList() throws InterruptedException{
		for (GamePreview gp : Client.getGameList()) {
			gp.getGameName();
			gp.isPasswordProtected();
			gp.getGameStatus();
			gp.getCurrentPlayerSize();
			gp.getMaxPlayerSize();
			gp.getId();
		}

		return Client.getGameList();
	}

	JPanel mainLogin = new JPanel();
	JPanel mainLobby = new JPanel();
	JPanel mainCreate = new JPanel();

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
		runCreate();

	}

	public void hideAll(){

		mainLogin.setVisible(false);
		mainLobby.setVisible(false);
		mainCreate.setVisible(false);

	}


	public void runLogin(){ 

		/////////////////////////////////////////////// LOGIN //////////////////////////////////////////////////////////////////

		// Using BorderLayout
		mainLogin.setLayout(new BorderLayout());

		// Make Quit Button
		BQuit = new JButton("Quit");
		BQuit.setMaximumSize(btnsize2);
		BQuit.addActionListener(this);
		BQuit.setBorder(new RoundedBorder(40));
		BQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
		BQuit.setFont(new Font("calibri",1,21));
		BQuit.setBorderPainted(true);
		BQuit.setFocusPainted(false);
		BQuit.setEnabled(true);
		BQuit.setForeground(Color.BLACK);
		BQuit.setBackground(Color.WHITE);

		// Make Sign In button
		BSignIn = new JButton("Sign In");
		BSignIn.setMaximumSize(btnsize4);
		BSignIn.addActionListener(this);
		BSignIn.setAlignmentX(Component.CENTER_ALIGNMENT);
		BSignIn.setFont(new Font("calibri",1,21));
		BSignIn.setBorderPainted(false);
		BSignIn.setFocusPainted(false);
		BSignIn.setEnabled(true);
		BSignIn.setForeground(Color.white);
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
		Dimension btnsize2 = new Dimension(180,70);

		// Create game button
		b1 = new JButton("Create Game");
		b1.setPreferredSize(btnsize2);
		b1.setBackground(Color.white);
		b1.setForeground(Color.black);
		b1.addActionListener(this);
		b1.setBorder(new RoundedBorder(30));
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setFont(new Font("calibri",1,21));
		b1.setBorderPainted(true);
		b1.setFocusPainted(false);
		b1.setEnabled(true);

		// Sign out button
		b2 = new JButton("Sign Out");
		b2.setPreferredSize(btnsize2);
		b2.setBorder(new RoundedBorder(30));
		b2.setBackground(Color.white);
		b2.setForeground(Color.black);
		b2.addActionListener(this);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setFont(new Font("calibri",1,21));
		b2.setBorderPainted(true);
		b2.setFocusPainted(false);
		b2.setEnabled(true);

		// Join game button
		b3 = new JButton("Join Game");
		b3.setPreferredSize(btnsize2);
		b3.setBorder(new RoundedBorder(30));
		b3.setBackground(Color.white);
		b3.setForeground(Color.black);
		b3.addActionListener(this);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setFont(new Font("calibri",1,21));
		b3.setBorderPainted(true);
		b3.setFocusPainted(false);
		b3.setEnabled(true);

		// Refresh button
		b4 = new JButton("Refresh");
		b4.setPreferredSize(btnsize2);
		b4.setBorder(new RoundedBorder(30));
		b4.setBackground(Color.white);
		b4.setForeground(Color.black);
		b4.addActionListener(this);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		b4.setFont(new Font("calibri",1,21));
		b4.setBorderPainted(true);
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
		l2.setIcon(new ImageIcon(new ImageIcon("BCLobby.png").getImage().getScaledInstance(249, 381, Image.SCALE_DEFAULT)));
		l3.setIcon(new ImageIcon(new ImageIcon("WCLobby.png").getImage().getScaledInstance(306, 556, Image.SCALE_DEFAULT)));
		l2.setAlignmentX(Component.CENTER_ALIGNMENT);
		l3.setAlignmentX(Component.CENTER_ALIGNMENT);


		// Create List
		//MainGUI lob = new MainGUI();
		//ArrayList<GamePreview> info;

		DefaultListModel model = new DefaultListModel();
		list = new JList(model);

		try {
			if(Client.getGameList() == null){

				model.addElement("There are currently no games available");

			}
			else{

				for (int i = 0; i < 5; i++) {
					model.addElement(Client.getGameList().get(i).getGameName());
					model.addElement(Client.getGameList().get(i).isPasswordProtected());
					model.addElement(Client.getGameList().get(i).getGameStatus());
					model.addElement(Client.getGameList().get(i).getCurrentPlayerSize());
					model.addElement(Client.getGameList().get(i).getMaxPlayerSize());
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NullPointerException e){
			e.printStackTrace();
		}

		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);


		//Creates panel for buttons
		JPanel p1 = new JPanel();

		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		p1.setLayout(flow);
		p1.setBackground(Color.WHITE);
		p1.add(b2);
		p1.add(b4);
		flow.setHgap(100);
		p1.add(b3);
		p1.add(b1);
		p1.add(Box.createRigidArea(new Dimension(0,200)));

		mainLobby.add(p1, BorderLayout.SOUTH);

		//Creates panel for labels
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.setBackground(Color.WHITE);

		// Panel for Title label
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		p2.setBackground(Color.WHITE);
		p2.add(Box.createRigidArea(new Dimension(50, 0)));
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

		JPanel p5 = new JPanel();

		p5.setLayout(new BoxLayout(p5, BoxLayout.LINE_AXIS));
		p5.setBackground(Color.WHITE);
		p5.add(list);

		mainLobby.add(p5, BorderLayout.CENTER);


	}

	/////////////////////////////////////////////// LOBBY //////////////////////////////////////////////////////////////////




	public void runCreate(){

		//Der benyttes BorderLayout
		mainCreate.setLayout(new BorderLayout());

		// Dimensions of buttons
		Dimension btnsize2 = new Dimension(120,60);

		//Textfield laves
		Dimension txtfldsize = new Dimension(300, 30);

		//Labels
		Dimension lsize = new Dimension(200,30);

		// Text font size:
		int fontsize = 25;

		// Make Quit Button
		BBack = new JButton("Back");
		BBack.setMaximumSize(btnsize2);
		BBack.addActionListener(this);
		BBack.setBorder(new RoundedBorder(30));
		BBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		BBack.setFont(new Font("calibri",1,21));
		BBack.setBorderPainted(true);
		BBack.setFocusPainted(false);
		BBack.setEnabled(true);
		BBack.setForeground(Color.BLACK);
		BBack.setBackground(Color.WHITE);

		// Make Sign In button
		BCreateGame = new JButton("Create Game");
		BCreateGame.setMinimumSize(new Dimension(250,100));
		BCreateGame.setMaximumSize(new Dimension(250,100));
		BCreateGame.setBorder(new RoundedBorder(30));
		BCreateGame.addActionListener(this);
		BCreateGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		BCreateGame.setFont(new Font("calibri",1,30));
		BCreateGame.setBorderPainted(true);
		BCreateGame.setFocusPainted(false);
		BCreateGame.setEnabled(true);
		BCreateGame.setForeground(Color.BLACK);
		BCreateGame.setBackground(Color.WHITE);


		//Makes Title
		LTitle = new JLabel("Cards Against Humanity");
		LTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		LTitle.setFont(new Font("AR JULIAN",Font.PLAIN,70));
		LTitle.setForeground(Color.BLACK);

		//Makes lower title
		LText = new JLabel("Create a new game!");
		LText.setAlignmentX(Component.CENTER_ALIGNMENT);
		LText.setFont(new Font("calibri",Font.PLAIN,30));
		LText.setForeground(Color.black);

		// Name Label
		LName = new JLabel("Game name:");
		LName.setMaximumSize(lsize);
		LName.setFont(new Font("calibri",Font.PLAIN,fontsize));
		LName.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name Label
		LRounds = new JLabel("Rounds to win:");
		LRounds.setMaximumSize(lsize);
		LRounds.setFont(new Font("calibri",Font.PLAIN,fontsize));
		LRounds.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name Label
		LTime = new JLabel("Time to answer:");
		LTime.setMaximumSize(lsize);
		LTime.setFont(new Font("calibri",Font.PLAIN,fontsize));
		LTime.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name Label
		LPass = new JLabel("Password Protected:");
		LPass.setMaximumSize(lsize);
		LPass.setFont(new Font("calibri",Font.PLAIN,fontsize));
		LPass.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name Label
		LPassword = new JLabel("Password:");
		LPassword.setMaximumSize(lsize);
		LPassword.setFont(new Font("calibri",Font.PLAIN,fontsize));
		LPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

		// The two cards in the sides
		LFigure1 = new JLabel();
		LFigure2 = new JLabel();
		LFigure1.setIcon(new ImageIcon(new ImageIcon("BCCreate.png").getImage().getScaledInstance(248, 376, Image.SCALE_DEFAULT)));
		LFigure2.setIcon(new ImageIcon(new ImageIcon("WCCreate.png").getImage().getScaledInstance(245, 376, Image.SCALE_DEFAULT)));
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

		// Name textfield
		txtfld3 = new JTextField(50);
		txtfld3.setMaximumSize(txtfldsize);
		txtfld3.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld3.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name textfield
		txtfld4 = new JTextField(50);
		txtfld4.setMaximumSize(txtfldsize);
		txtfld4.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld4.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name textfield
		txtfld5 = new JTextField(50);
		txtfld5.setMaximumSize(txtfldsize);
		txtfld5.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld5.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Other center
		JPanel PC = new JPanel();
		JPanel PCLEFT = new JPanel();
		JPanel PCRIGHT = new JPanel();

		PC.setLayout(new BorderLayout());

		PCLEFT.add(Box.createRigidArea(new Dimension(220,50)));
		PCRIGHT.add(Box.createRigidArea(new Dimension(220,50)));


		PCLEFT.setBackground(Color.WHITE);
		PCRIGHT.setBackground(Color.WHITE);

		PC.add(PCLEFT,BorderLayout.LINE_START);
		PC.add(PCRIGHT,BorderLayout.LINE_END);


		//Center Panel
		JPanel PCenter = new JPanel();
		PCenter.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));		
		PCenter.setLayout(new BorderLayout());
		PCenter.setSize(600, 500);



		JPanel PL = new JPanel();
		PL.setLayout(new BoxLayout(PL, BoxLayout.PAGE_AXIS));
		PL.setBackground(Color.white);

		PL.add(Box.createRigidArea(new Dimension(300,50)));
		PL.add(LName);
		PL.add(Box.createRigidArea(new Dimension(300,50)));
		PL.add(LRounds);
		PL.add(Box.createRigidArea(new Dimension(300,50)));
		PL.add(LTime);
		PL.add(Box.createRigidArea(new Dimension(300,50)));
		PL.add(LPassword);
		PL.add(Box.createRigidArea(new Dimension(300,25)));


		JPanel PR = new JPanel();
		PR.setLayout(new BoxLayout(PR, BoxLayout.PAGE_AXIS));
		PR.setBackground(Color.white);

		PR.add(Box.createRigidArea(new Dimension(350,50)));
		PR.add(txtfld1);
		PR.add(Box.createRigidArea(new Dimension(350,50)));
		PR.add(txtfld2);
		PR.add(Box.createRigidArea(new Dimension(350,50)));
		PR.add(txtfld3);
		PR.add(Box.createRigidArea(new Dimension(350,50)));
		PR.add(txtfld5);
		PL.add(Box.createRigidArea(new Dimension(350,25)));



		PCenter.add(PL, BorderLayout.WEST);
		PCenter.add(PR, BorderLayout.CENTER);

		PC.add(PCenter, BorderLayout.CENTER);

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

		PRight.add(Box.createRigidArea(new Dimension(365,250)));
		PRight.add(LFigure2);
		PRight.add(Box.createRigidArea(new Dimension(365,150)));
		PRight.add(BBack);
		PRight.add(Box.createRigidArea(new Dimension(365,50)));

		//Siger hvor de forskellige Paneler skal vÊre
		mainCreate.add(PLeft, BorderLayout.WEST);
		mainCreate.add(PRight, BorderLayout.EAST);

		//Laver Center JPanel med de forskellige labels og tekstfelter
		JPanel PMiddle = new JPanel();
		PMiddle.setLayout(new BoxLayout(PMiddle, BoxLayout.PAGE_AXIS));
		PMiddle.setSize(400, 1000);
		PMiddle.add(LTitle);
		PMiddle.add(Box.createRigidArea(new Dimension(100, 100)));
		PMiddle.add(LText);
		PMiddle.add(Box.createRigidArea(new Dimension(100, 50)));
		PMiddle.add(PC);
		PMiddle.add(Box.createRigidArea(new Dimension(400, 100)));
		PMiddle.add(BCreateGame);
		PMiddle.add(Box.createRigidArea(new Dimension(400, 400)));				

		//Add PMiddle and set center
		mainCreate.add(PMiddle, BorderLayout.CENTER);

		PMiddle.setBackground(Color.WHITE);
		mainCreate.add(PMiddle);


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

			hideAll();
			mainLobby.setVisible(true);
			add(mainLobby);

		} else if(e.getSource()==b1) {

			hideAll();
			mainCreate.setVisible(true);
			add(mainCreate);

		} else if(e.getSource()==b2) {

			hideAll();
			mainLogin.setVisible(true);
			add(mainLogin);

		} else if (e.getSource() == BBack) {

			hideAll();
			mainLobby.setVisible(true);
			add(mainLobby);


		} else if (e.getSource() == b3) {
			// THIS NEEDS TO CHECK IF GAME HAS PASSWORD AS WELL

			JFrame rules = new JFrame("Rules");
			rules.setLayout(new BorderLayout());
			rules.setVisible(true);
			rules.setResizable(false);
			rules.setSize(600,200);
			rules.setLocationRelativeTo(null);


			JButton b9 = new JButton();
			b9 = new JButton("Back");
			b9.setPreferredSize(new Dimension(120, 20));
			b9.setBorder(new RoundedBorder(30));
			b9.setBackground(Color.white);
			b9.setForeground(Color.black);
			b9.addActionListener(this);
			b9.setAlignmentX(Component.CENTER_ALIGNMENT);
			b9.setFont(new Font("calibri",1,21));
			b9.setBorderPainted(true);
			b9.setFocusPainted(false);
			b9.setEnabled(true);

			JButton b10 = new JButton();
			b10 = new JButton("Join");
			b10.setPreferredSize(new Dimension(120, 20));
			b10.setBorder(new RoundedBorder(30));
			b10.setBackground(Color.white);
			b10.setForeground(Color.black);
			b10.addActionListener(this);
			b10.setAlignmentX(Component.CENTER_ALIGNMENT);
			b10.setFont(new Font("calibri",1,21));
			b10.setBorderPainted(true);
			b10.setFocusPainted(false);
			b10.setEnabled(true);

			//Den lukker Rules JFrame hvis man klikker pÂ den button, som er pÂ den. 
			b9.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e){    	
					rules.dispose();
				}
			});

			//Laver Jpanel til det
			JPanel panel = new JPanel();


			JPanel panelcenter = new JPanel();
			FlowLayout flow = new FlowLayout();
			panelcenter.setLayout(flow);
			panelcenter.setBackground(Color.WHITE);

			// Name Label
			l4 = new JLabel("Enter Password:");
			l4.setMaximumSize(lsize);
			l4.setFont(new Font("calibri",Font.PLAIN,30));
			l4.setAlignmentX(Component.CENTER_ALIGNMENT);

			// Name textfield
			txtfld6 = new JTextField(15);
			txtfld6.setMaximumSize(txtfldsize);
			txtfld6.setFont(new Font("calibri",Font.PLAIN,20));
			txtfld6.setAlignmentX(Component.CENTER_ALIGNMENT);

			panelcenter.add(l4);
			panelcenter.add(txtfld6);
			panelcenter.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


			JPanel panelsouth = new JPanel();

			FlowLayout flow1 = new FlowLayout(FlowLayout.CENTER);
			panelsouth.setLayout(flow1);
			panelsouth.setBackground(Color.WHITE);
			panelsouth.add(Box.createRigidArea(new Dimension(0,40)));
			panelsouth.add(b9);
			panelsouth.add(b10);
			panelsouth.add(Box.createRigidArea(new Dimension(0,5)));

			flow1.setHgap(10);

			rules.add(panel);

			//SÊtter tekstfelt og button pÂ JPanel
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(Box.createRigidArea(new Dimension(200,40)));
			panel.add(panelcenter, BorderLayout.CENTER);
			panel.add(Box.createRigidArea(new Dimension(200,10)));
			panel.add(panelsouth, BorderLayout.SOUTH);
			panel.add(Box.createRigidArea(new Dimension(200,10)));
			panel.setBackground(Color.WHITE);


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