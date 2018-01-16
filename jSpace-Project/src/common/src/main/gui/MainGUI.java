package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.src.main.client.Client;
import common.src.main.client.GamePreview;
import common.src.main.server.GameSlot;

@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener {
	private int maxPlayers = 8;

	String name, IP, gameName, rounds, time, password;
	int players;

	// Login
	private JButton BQuit, BSignIn, BBack;
	private JLabel LTitle, LText, LFigure1, LName, LIP, LFigure2;
	private JTextField txtfld1, txtfld2;
	public boolean signIn = false;

	// Lobby
	private static final String String = null;
	private JButton LCreateGameBtn, LSignOutBtn, LJoinGameBtn, LRefreshBtn, b5;
	private JLabel l1, l2, l3, l4, l5, l6;
	private JTextField WP;
	private JList list = new JList();
	private JPanel availableGames;
	private JScrollPane scrollPaneMain;
	private DefaultListModel model;

	// Create Game
	private JButton BCreateGame;
	private JLabel  LRounds, LTime, LPass, LPassword;
	private JTextField txtfld3, txtfld4, txtfld5, txtfld6, txtfld7, txtfld8;

	// Games available list
	private int gameSelected;
	private int numberOfGames;
	private int maxGames = 42;
	private ArrayList<GamePreview> games;

	// ReadyUpLobby
	private GameSlot[] gameSlot = new GameSlot[maxPlayers];
	private JButton[] readyBtn = new JButton[maxPlayers];
	private JLabel[] player = new JLabel[maxPlayers];
	private JButton BReady, BLeave;
	private JLabel LHead, LPicWC, LPicBC, Label1, Label2, Label3, Label4, Label5, Label6, Label7, Label8;
	private static JList playerList;
	private Chat lobbyChat;
	private JTextArea lobbyChatBox;
	private JTextField lobbyMessageField;
	private JButton lobbySendButton;
	private JPanel lobbyChatPanel, lobbySendPanel;

	// Game
	private JTextArea BlackCard;
	private String[] ChosenCards = new String[8];
	private int numberOfCards = 3;
	private JButton[] PlayerCards = new JButton[10];
	private JButton[] Winner = new JButton[8];
	private JTextArea[] ChosCard1 = new JTextArea[8];
	private JTextArea[] ChosCard2 = new JTextArea[8];
	private JTextArea[] ChosCard3 = new JTextArea[8];
	private JTextArea[] area = new JTextArea[10];
	private JLabel label, czar, phase, number, timerem = new JLabel();
	private JTextArea pointsPlayer;


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

	public void changeColor(int x){

		if (gameSlot[x].isReady() == true) {			
			readyBtn[x].setBackground(new Color(76,153,0));
			System.out.println("Changed to Green");

		} else {
			readyBtn[x].setBackground(Color.RED);
			System.out.println("Changed to Red");

		}
	}

	JPanel mainLogin = new JPanel();
	JPanel mainLobby = new JPanel();
	JPanel mainCreate = new JPanel();
	JPanel mainReadyUpLobby = new JPanel();
	JPanel mainGame = new JPanel();

	// Dimensions of buttons
	Dimension btnsize4 = new Dimension(110,40);
	Dimension btnsize2 = new Dimension(120,60);
	Dimension btnsize5 = new Dimension(200,100);

	//Textfield laves
	Dimension txtfldsize = new Dimension(400, 30);

	//Labels
	Dimension lsize = new Dimension(99, 50);

	public MainGUI() throws InterruptedException{

		getContentPane().setLayout(new BorderLayout());
		getContentPane().setSize(1900,1000);


		runLogin();
		runLobby();
		runCreate();
		runReadyUpLobby();
		runGame();
		runEnter();
	}


	public void runEnter(){
		mainLogin.getInputMap(JComponent.WHEN_FOCUSED)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"signIn");

		mainLogin.getActionMap().put("signIn",new AbstractAction(){
			public void actionPerformed(ActionEvent ae){
				BSignIn.doClick();
				System.out.println("!!! signedIn");
			}
		});

		mainReadyUpLobby.getInputMap(JComponent.WHEN_FOCUSED)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"ready");

		mainReadyUpLobby.getActionMap().put("ready",new AbstractAction(){
			public void actionPerformed(ActionEvent ae){
				BReady.doClick();
				System.out.println("!!! ready");
			}
		});

		mainCreate.getInputMap(JComponent.WHEN_FOCUSED)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"creatGame");

		mainCreate.getActionMap().put("creatGame",new AbstractAction(){
			public void actionPerformed(ActionEvent ae){
				BCreateGame.doClick();
				System.out.println("!!! creatGame");
			}
		});

		// Game CHAT
//		chatPanel.getInputMap(JComponent.WHEN_FOCUSED)
//		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"chat");
//
//		chatPanel.getActionMap().put("chat",new AbstractAction(){
//			public void actionPerformed(ActionEvent ae){
//				sendButton.doClick();
//				System.out.println("!!! chat");
//			}
//		});
	}

	public void hideAll(){

		mainLogin.setVisible(false);
		mainLobby.setVisible(false);
		mainCreate.setVisible(false);
		mainReadyUpLobby.setVisible(false);
		mainGame.setVisible(false);

	}

	public void ErrorPopup(){

		JFrame Error = new JFrame("Error Occurred");
		Error.setLayout(new BorderLayout());
		Error.setVisible(true);
		Error.setResizable(false);
		Error.setSize(500,200);
		Error.setLocationRelativeTo(null);

		JButton BOK = new JButton();
		BOK = new JButton("OK");
		BOK.setMaximumSize(new Dimension(90, 40));
		BOK.setBorder(new RoundedBorder(30));
		BOK.setBackground(Color.white);
		BOK.setForeground(Color.RED);
		BOK.addActionListener(this);
		BOK.setAlignmentX(Component.CENTER_ALIGNMENT);
		BOK.setFont(new Font("calibri",1,21));
		BOK.setBorderPainted(true);
		BOK.setFocusPainted(false);
		BOK.setEnabled(true);

		// Name Label
		l6 = new JLabel("WARNING: ERROR OCCURRED!");
		l6.setMaximumSize(new Dimension(400, 40));
		l6.setFont(new Font("calibri",Font.PLAIN,30));
		l6.setAlignmentX(Component.CENTER_ALIGNMENT);
		l6.setForeground(Color.RED);

		//		panelcenter.add(l4);
		//
		//		//SÊtter tekstfelt og button pÂ JPanel
		//		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		//		panel.add(Box.createRigidArea(new Dimension(200,40)));
		//		panel.add(panelcenter, BorderLayout.CENTER);
		//		panel.add(Box.createRigidArea(new Dimension(200,10)));
		//		panel.setBackground(Color.WHITE);

		//Laver Jpanel til det
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);


		//		JPanel panelcenter = new JPanel();
		//		FlowLayout flow = new FlowLayout();
		//		panelcenter.setLayout(flow);
		//		panelcenter.setBackground(Color.WHITE);

		panel.add(Box.createRigidArea(new Dimension(400,10)));
		panel.add(l6);
		panel.add(Box.createRigidArea(new Dimension(400,10)));
		panel.add(BOK);
		panel.add(Box.createRigidArea(new Dimension(400,10)));

		Error.add(panel);

		//Den lukker Rules JFrame hvis man klikker pÂ den button, som er pÂ den. 
		BOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){    	
				Error.dispose();
			}
		});

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
		txtfld1.setText("Alex");

		// Name textfield
		txtfld2 = new JTextField(50);
		txtfld2.setMaximumSize(txtfldsize);
		txtfld2.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld2.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtfld2.setText("127.0.0.1");


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
		mainLogin.requestFocus();

		/////////////////////////////////////////////// LOGIN //////////////////////////////////////////////////////////////////


	}


	public void runLobby() throws InterruptedException {

		/////////////////////////////////////////////// LOBBY //////////////////////////////////////////////////////////////////

		mainLobby.setLayout(new BorderLayout()); //Default layout
		mainLobby.setBackground(Color.WHITE);


		//Create buttons
		Dimension btnsize2 = new Dimension(180,70);

		// Create game button
		LCreateGameBtn = new JButton("Create Game");
		LCreateGameBtn.setPreferredSize(btnsize2);
		LCreateGameBtn.setBackground(Color.white);
		LCreateGameBtn.setForeground(Color.black);
		LCreateGameBtn.addActionListener(this);
		LCreateGameBtn.setBorder(new RoundedBorder(30));
		LCreateGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		LCreateGameBtn.setFont(new Font("calibri",1,21));
		LCreateGameBtn.setBorderPainted(true);
		LCreateGameBtn.setFocusPainted(false);
		LCreateGameBtn.setEnabled(true);

		// Sign out button
		LSignOutBtn = new JButton("Sign Out");
		LSignOutBtn.setPreferredSize(btnsize2);
		LSignOutBtn.setBorder(new RoundedBorder(30));
		LSignOutBtn.setBackground(Color.white);
		LSignOutBtn.setForeground(Color.black);
		LSignOutBtn.addActionListener(this);
		LSignOutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		LSignOutBtn.setFont(new Font("calibri",1,21));
		LSignOutBtn.setBorderPainted(true);
		LSignOutBtn.setFocusPainted(false);
		LSignOutBtn.setEnabled(true);

		// Join game button
		LJoinGameBtn = new JButton("Join Game");
		LJoinGameBtn.setPreferredSize(btnsize2);
		LJoinGameBtn.setBorder(new RoundedBorder(30));
		LJoinGameBtn.setBackground(Color.white);
		LJoinGameBtn.setForeground(Color.black);
		LJoinGameBtn.addActionListener(this);
		LJoinGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		LJoinGameBtn.setFont(new Font("calibri",1,21));
		LJoinGameBtn.setBorderPainted(true);
		LJoinGameBtn.setFocusPainted(false);
		LJoinGameBtn.setEnabled(true);

		// Refresh button
		LRefreshBtn = new JButton("Refresh");
		LRefreshBtn.setPreferredSize(btnsize2);
		LRefreshBtn.setBorder(new RoundedBorder(30));
		LRefreshBtn.setBackground(Color.white);
		LRefreshBtn.setForeground(Color.black);
		LRefreshBtn.addActionListener(this);
		LRefreshBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		LRefreshBtn.setFont(new Font("calibri",1,21));
		LRefreshBtn.setBorderPainted(true);
		LRefreshBtn.setFocusPainted(false);
		LRefreshBtn.setEnabled(true);

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

		//Creates panel for buttons
		JPanel p1 = new JPanel();

		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		p1.setLayout(flow);
		p1.setBackground(Color.WHITE);
		p1.add(LSignOutBtn);
		p1.add(LRefreshBtn);
		flow.setHgap(100);
		p1.add(LJoinGameBtn);
		p1.add(LCreateGameBtn);
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


	}

	public void updateGameList(){

		for (int i = 0; i < numberOfGames; i++) {
			model.remove(i);
		}

		try {
			games = Client.getGameList();
		} catch (InterruptedException e) { e.printStackTrace(); }

		numberOfGames = games.size();

		String blank = "          ";
		for (int i = 0; i < numberOfGames; i++) {
			if (games.get(i) != null){
				String name = games.get(i).getGameName();
				String status = games.get(i).getGameStatus();
				boolean lock = games.get(i).isPasswordProtected();
				int current = games.get(i).getCurrentPlayerSize();
				int max = games.get(i).getMaxPlayerSize();
				String islocked;

				if (lock == true) {
					islocked = "LOCKED";
				} else {
					islocked = "      ";
				}

				model.addElement(name+blank+status+blank+islocked+blank+Integer.toString(current)+"/"+Integer.toString(max));
			}
		}
	}

	public void loadAvailableGames(){
		// Panel for list of games available
		availableGames = new JPanel();
		model = new DefaultListModel();
		list = new JList(model);	

		scrollPaneMain = new JScrollPane(list);
		scrollPaneMain.setPreferredSize(new Dimension(1000, 650));

		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.VERTICAL);
		list.ensureIndexIsVisible(list.getSelectedIndex());
		list.setFont(new Font("calibri",Font.PLAIN,25));
		list.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));			

		try {
			games = Client.getGameList();
		} catch (InterruptedException e) { e.printStackTrace(); }

		numberOfGames = games.size();

		String blank = "          ";
		for (int i = 0; i < numberOfGames; i++) {
			if (games.get(i) != null){
				String name = games.get(i).getGameName();
				String status = games.get(i).getGameStatus();
				boolean lock = games.get(i).isPasswordProtected();
				int current = games.get(i).getCurrentPlayerSize();
				int max = games.get(i).getMaxPlayerSize();
				String islocked;

				if (lock == true) {
					islocked = "LOCKED";
				} else {
					islocked = "      ";
				}

				model.addElement(name+blank+status+blank+islocked+blank+Integer.toString(current)+"/"+Integer.toString(max));
			}
		}

		// catch double-click events
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					gameSelected = list.getSelectedIndex();
					System.out.println("Index Selected: "+ gameSelected);
					if (gameSelected < 0){ return; }
					GamePreview preID = games.get(gameSelected);
					int gameID = preID.getId();

					try {
						Client.joinGame(gameID);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

					hideAll();
					mainReadyUpLobby.setVisible(true);
					add(mainReadyUpLobby);
					mainReadyUpLobby.requestFocus();
				} else if (me.getClickCount() == 1){
					LJoinGameBtn.doClick(); 
				}}});


		//		p5.setLayout(new BoxLayout(p5, BoxLayout.LINE_AXIS));
		availableGames.setBackground(Color.WHITE);
		availableGames.setAlignmentX(CENTER_ALIGNMENT);
		availableGames.setAlignmentY(CENTER_ALIGNMENT);
		//				p5.add(list);
		availableGames.add(scrollPaneMain, BorderLayout.CENTER);
		mainLobby.add(availableGames, BorderLayout.CENTER);
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
		txtfld8 = new JTextField(50);
		txtfld8.setText("Alex' game");
		txtfld8.setMaximumSize(txtfldsize);
		txtfld8.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld8.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name textfield
		txtfld7 = new JTextField(50);
		txtfld7.setMaximumSize(txtfldsize);
		txtfld7.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld7.setAlignmentX(Component.CENTER_ALIGNMENT);

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
		PR.add(txtfld8);
		PR.add(Box.createRigidArea(new Dimension(350,50)));
		PR.add(txtfld7);
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
	
	public void lobbyChat() {

		// Panels
		lobbyChatPanel = new JPanel();
		lobbyChatPanel.setLayout(new BorderLayout());
		lobbyChatPanel.setPreferredSize(new Dimension(200, 400));
		lobbySendPanel = new JPanel();
		lobbySendPanel.setBackground(Color.WHITE);
		lobbySendPanel.setLayout(new GridBagLayout());

		// Message field and send button
		lobbyMessageField = new JTextField();
		lobbyMessageField.requestFocusInWindow();
		lobbyMessageField.setPreferredSize(new Dimension(400, 20));

		lobbySendButton = new JButton(" Send ");
		lobbySendButton.addActionListener(this);
		
		// Chat area
		lobbyChatBox = new JTextArea();
		lobbyChatBox.setEditable(false);
		lobbyChatBox.setFont(new Font("Serif", Font.PLAIN, 15));
		lobbyChatBox.setLineWrap(true);
		
		// adding elements
		lobbyChatPanel.add(new JScrollPane(lobbyChatBox), BorderLayout.CENTER);
		lobbySendPanel.add(lobbyMessageField);
		lobbySendPanel.add(lobbySendButton);
		lobbyChatPanel.add(BorderLayout.SOUTH, lobbySendPanel);
		
		//SwingUtilities.getRootPane(sendButton).setDefaultButton(sendButton);
	}


	public void chatMessageRecived(String message) {
		lobbyChatBox.append(message+"\n");
	}

	/////////////////////////////////////////////// READYUPLOBBY //////////////////////////////////////////////////////////////////

	public void runReadyUpLobby(){
		mainReadyUpLobby.setLayout(new BorderLayout()); //Default layout
		mainReadyUpLobby.setBackground(Color.WHITE);

		//Create buttons
		Dimension btnsize2 = new Dimension(180,70);

		// Create game button
		BReady = new JButton("Ready");
		BReady.setPreferredSize(btnsize2);
		BReady.setBackground(Color.white);
		BReady.setForeground(Color.black);
		BReady.addActionListener(this);
		BReady.setBorder(new RoundedBorder(30));
		BReady.setAlignmentX(Component.CENTER_ALIGNMENT);
		BReady.setFont(new Font("calibri",1,21));
		BReady.setBorderPainted(true);
		BReady.setFocusPainted(false);
		BReady.setEnabled(true);

		// Sign out button
		BLeave = new JButton("Leave Game");
		BLeave.setPreferredSize(btnsize2);
		BLeave.setBorder(new RoundedBorder(30));
		BLeave.setBackground(Color.white);
		BLeave.setForeground(Color.black);
		BLeave.addActionListener(this);
		BLeave.setAlignmentX(Component.CENTER_ALIGNMENT);
		BLeave.setFont(new Font("calibri",1,21));
		BLeave.setBorderPainted(true);
		BLeave.setFocusPainted(false);
		BLeave.setEnabled(true);

		//Makes Title
		LHead = new JLabel("Cards Against Humanity");
		LHead.setAlignmentX(Component.CENTER_ALIGNMENT);
		LHead.setFont(new Font("AR JULIAN",Font.PLAIN,70));
		LHead.setForeground(Color.BLACK);


		// Implementing pictures for white cards and black cards as JLabel
		LPicBC = new JLabel();
		LPicWC = new JLabel();
		LPicBC.setIcon(new ImageIcon(new ImageIcon("BCLobby.png").getImage().getScaledInstance(249, 381, Image.SCALE_DEFAULT)));
		LPicWC.setIcon(new ImageIcon(new ImageIcon("WCLobby.png").getImage().getScaledInstance(306, 556, Image.SCALE_DEFAULT)));
		LPicBC.setAlignmentX(Component.TOP_ALIGNMENT);
		LPicWC.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Creates panel for buttons
		JPanel BtnPanel = new JPanel();

		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		BtnPanel.setLayout(flow);
		BtnPanel.setBackground(Color.WHITE);
		BtnPanel.add(BLeave);
		flow.setHgap(100);
		BtnPanel.add(BReady);
		BtnPanel.add(Box.createRigidArea(new Dimension(0,200)));

		mainReadyUpLobby.add(BtnPanel, BorderLayout.SOUTH);

		// Chat
		lobbyChat();

		// Panel for Title label
		JPanel HeadPanel = new JPanel();
		HeadPanel.setLayout(new BoxLayout(HeadPanel, BoxLayout.PAGE_AXIS));
		HeadPanel.setBackground(Color.WHITE);
		HeadPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		HeadPanel.add(LHead);

		// Panel for Black Card image
		JPanel BCPanel = new JPanel();
		BCPanel.setLayout(new BoxLayout(BCPanel, BoxLayout.Y_AXIS));
		BCPanel.setBackground(Color.GRAY);
		BCPanel.add(LPicBC);
		BCPanel.add(lobbyChatPanel);
		BCPanel.add(lobbySendPanel);
		BCPanel.add(Box.createRigidArea(new Dimension(50, 0)));


		// Panel for White Card image
		JPanel WCPanel = new JPanel();
		WCPanel.setLayout(new BoxLayout(WCPanel, BoxLayout.LINE_AXIS));
		WCPanel.setBackground(Color.WHITE);
		WCPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		WCPanel.add(LPicWC);

		Dimension maxsize = new Dimension(1000, 75);

		// Middle JPanels
		JPanel middle = new JPanel();
		middle.setPreferredSize(new Dimension(1000, 656));
		middle.setBackground(Color.white);

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setPreferredSize(maxsize);
		p1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p1.setBackground(Color.WHITE);

		// Name Label
		player[0] = new JLabel("   ");
		player[0].setMaximumSize(lsize);
		player[0].setFont(new Font("calibri",Font.PLAIN,30));
		player[0].setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		readyBtn[0] = new JButton();
		readyBtn[0].setPreferredSize(new Dimension(75, 75));
		readyBtn[0].setBackground(Color.WHITE);
		readyBtn[0].setBorderPainted(false);
		readyBtn[0].setEnabled(false);

		p1.add(Box.createRigidArea(new Dimension(1000,12)));
		p1.add(player[0], BorderLayout.WEST);
		p1.add(readyBtn[0], BorderLayout.EAST);


		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.setPreferredSize(maxsize);
		p2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p2.setBackground(Color.WHITE);

		// Name Label
		player[1] = new JLabel("   ");
		player[1].setMaximumSize(lsize);
		player[1].setFont(new Font("calibri",Font.PLAIN,30));
		player[1].setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		readyBtn[1] = new JButton();
		readyBtn[1].setPreferredSize(new Dimension(75, 75));
		readyBtn[1].setBackground(Color.WHITE);
		readyBtn[1].setBorderPainted(false);
		readyBtn[1].setEnabled(false);

		p2.add(Box.createRigidArea(new Dimension(1000,12)));
		p2.add(player[1], BorderLayout.WEST);
		p2.add(readyBtn[1], BorderLayout.EAST);


		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.setPreferredSize(maxsize);
		p3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p3.setBackground(Color.WHITE);

		// Name Label
		player[2] = new JLabel("   ");
		player[2].setMaximumSize(lsize);
		player[2].setFont(new Font("calibri",Font.PLAIN,30));
		player[2].setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		readyBtn[2] = new JButton();
		readyBtn[2].setPreferredSize(new Dimension(75, 75));
		readyBtn[2].setBackground(Color.WHITE);
		readyBtn[2].setBorderPainted(false);
		readyBtn[2].setEnabled(false);

		p3.add(Box.createRigidArea(new Dimension(1000,12)));
		p3.add(player[2], BorderLayout.WEST);
		p3.add(readyBtn[2], BorderLayout.EAST);


		JPanel p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		p4.setPreferredSize(maxsize);
		p4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p4.setBackground(Color.WHITE);

		// Name Label
		player[3] = new JLabel("   ");
		player[3].setMaximumSize(lsize);
		player[3].setFont(new Font("calibri",Font.PLAIN,30));
		player[3].setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		readyBtn[3] = new JButton();
		readyBtn[3].setPreferredSize(new Dimension(75, 75));
		readyBtn[3].setBackground(Color.WHITE);
		readyBtn[3].setBorderPainted(false);
		readyBtn[3].setEnabled(false);

		p4.add(Box.createRigidArea(new Dimension(1000,12)));
		p4.add(player[3], BorderLayout.WEST);
		p4.add(readyBtn[3], BorderLayout.EAST);


		JPanel p5 = new JPanel();
		p5.setLayout(new BorderLayout());
		p5.setPreferredSize(maxsize);
		p5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p5.setBackground(Color.WHITE);

		// Name Label
		player[4] = new JLabel("   ");
		player[4].setMaximumSize(lsize);
		player[4].setFont(new Font("calibri",Font.PLAIN,30));
		player[4].setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		readyBtn[4] = new JButton();
		readyBtn[4].setPreferredSize(new Dimension(75, 75));
		readyBtn[4].setBackground(Color.WHITE);
		readyBtn[4].setBorderPainted(false);
		readyBtn[4].setEnabled(false);

		p5.add(Box.createRigidArea(new Dimension(1000,12)));
		p5.add(player[4], BorderLayout.WEST);
		p5.add(readyBtn[4], BorderLayout.EAST);


		JPanel p6 = new JPanel();
		p6.setLayout(new BorderLayout());
		p6.setPreferredSize(maxsize);
		p6.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p6.setBackground(Color.WHITE);

		// Name Label
		player[5] = new JLabel("   ");
		player[5].setMaximumSize(lsize);
		player[5].setFont(new Font("calibri",Font.PLAIN,30));
		player[5].setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		readyBtn[5] = new JButton();
		readyBtn[5].setPreferredSize(new Dimension(75, 75));
		readyBtn[5].setBackground(Color.WHITE);
		readyBtn[5].setBorderPainted(false);
		readyBtn[5].setEnabled(false);

		p6.add(Box.createRigidArea(new Dimension(1000,12)));
		p6.add(player[5], BorderLayout.WEST);
		p6.add(readyBtn[5], BorderLayout.EAST);


		JPanel p7 = new JPanel();
		p7.setLayout(new BorderLayout());
		p7.setPreferredSize(maxsize);
		p7.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p7.setBackground(Color.WHITE);

		// Name Label
		player[6] = new JLabel("   ");
		player[6].setMaximumSize(lsize);
		player[6].setFont(new Font("calibri",Font.PLAIN,30));
		player[6].setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		readyBtn[6] = new JButton();
		readyBtn[6].setPreferredSize(new Dimension(75, 75));
		readyBtn[6].setBackground(Color.WHITE);
		readyBtn[6].setBorderPainted(false);
		readyBtn[6].setEnabled(false);

		p7.add(Box.createRigidArea(new Dimension(1000,12)));
		p7.add(player[6], BorderLayout.WEST);
		p7.add(readyBtn[6], BorderLayout.EAST);


		JPanel p8 = new JPanel();
		p8.setLayout(new BorderLayout());
		p8.setPreferredSize(maxsize);
		p8.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p8.setBackground(Color.WHITE);

		// Name Label
		player[7] = new JLabel("   ");
		player[7].setMaximumSize(lsize);
		player[7].setFont(new Font("calibri",Font.PLAIN,30));
		player[7].setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		readyBtn[7] = new JButton();
		readyBtn[7].setPreferredSize(new Dimension(75, 75));
		readyBtn[7].setBackground(Color.WHITE);
		readyBtn[7].setBorderPainted(false);
		readyBtn[7].setEnabled(false);

		p8.add(Box.createRigidArea(new Dimension(1000,12)));
		p8.add(player[7], BorderLayout.WEST);
		p8.add(readyBtn[7], BorderLayout.EAST);


		middle.add(p1);
		middle.add(p2);
		middle.add(p3);
		middle.add(p4);
		middle.add(p5);
		middle.add(p6);
		middle.add(p7);
		middle.add(p8);


		mainReadyUpLobby.add(HeadPanel, BorderLayout.NORTH);
		mainReadyUpLobby.add(BCPanel, BorderLayout.EAST);
		mainReadyUpLobby.add(WCPanel, BorderLayout.WEST);

		mainReadyUpLobby.setVisible(false);

		JPanel playerPanel = new JPanel();

		playerPanel.setBackground(Color.WHITE);
		playerPanel.setAlignmentX(CENTER_ALIGNMENT);
		playerPanel.setAlignmentY(CENTER_ALIGNMENT);
		playerPanel.add(middle);
		//		playerPanel.add(scrollPane, BorderLayout.CENTER);

		mainReadyUpLobby.add(playerPanel, BorderLayout.CENTER);
	}
	/////////////////////////////////////////////// READYUPLOBBY //////////////////////////////////////////////////////////////////

	public void runSelected(){

	}


	public void runGame(){

		mainGame.setLayout(new BorderLayout());	

		int FontSizeOfCards = 16;
		int FontSizeOfChosenCards = 14;
		Dimension SizeOfPlayerCards = new Dimension(120,500);
		Dimension SizeOfBlackCards = new Dimension(200,300);
		Dimension SizeOfChosenCards = new Dimension(123,170);
		Dimension SizeOfChosenCards1 = new Dimension(123,40);
		Dimension SizeOfPlayerCards1 = new Dimension(120,40);
		Border BorderForCards = BorderFactory.createLineBorder(Color.BLACK, 1);
		Dimension size = new Dimension(126,180);
		Dimension size2 = new Dimension(126,60);

		Dimension size3 = new Dimension(126,250);

		// Czars black card of choice
		//		BlackCard = new JTextArea();
		//		BlackCard.setPreferredSize(SizeOfBlackCards);
		//		BlackCard.setFont(new Font("calibri",1,FontSizeOfCards));
		//		BlackCard.setBorder(BorderForCards);
		//		BlackCard.setBackground(Color.BLACK);
		//		BlackCard.setForeground(Color.WHITE);
		//		BlackCard.setAlignmentX(Component.CENTER_ALIGNMENT);
		//		BlackCard.setEnabled(true);

		// Czars black card of choice used to fill out
		BlackCard = new JTextArea();
		BlackCard.setPreferredSize(SizeOfBlackCards);
		BlackCard.setBorder(BorderForCards);
		BlackCard.setBackground(Color.BLACK);
		BlackCard.setForeground(Color.WHITE);
		BlackCard.setFont(new Font("calibri",1,FontSizeOfCards+5));
		BlackCard.setEditable(false);
		BlackCard.setLineWrap(true);
		BlackCard.setWrapStyleWord(true);

		label = new JLabel("Current Black Card");
		label.setForeground(Color.BLACK);
		label.setPreferredSize(new Dimension(300, 80));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(new Font("calibri",1,FontSizeOfCards+4));

		// Cards on players hand is being created
		for(int i=0; i<10; i++){
			area[i] = new JTextArea();
			area[i].setMaximumSize(SizeOfPlayerCards);
			area[i].setMinimumSize(SizeOfPlayerCards);
			area[i].setBorder(BorderForCards);
			area[i].setBackground(Color.white);
			area[i].setForeground(Color.BLACK);
			area[i].setFont(new Font("calibri",1,FontSizeOfCards));
			area[i].setEditable(false);
			area[i].setLineWrap(true);
			area[i].setWrapStyleWord(true);
		}

		// Cards on players hand is being created
		for(int i=0; i<10; i++){
			PlayerCards[i] = new JButton("Choose Card");

			//			PlayerCards[i].setMaximumSize(SizeOfPlayerCards);
			//				PlayerCards[i].setMaximumSize(SizeOfPlayerCards);
			//			PlayerCards[i].setMinimumSize(SizeofPlayerCards1);
			PlayerCards[i].setPreferredSize(SizeOfPlayerCards1);
			PlayerCards[i].setBackground(Color.BLACK);
			PlayerCards[i].setForeground(Color.WHITE);
			PlayerCards[i].addActionListener(this);
			PlayerCards[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			PlayerCards[i].setFont(new Font("calibri",1,FontSizeOfCards));
			PlayerCards[i].setBorderPainted(true);
			PlayerCards[i].setFocusPainted(false);
			PlayerCards[i].setEnabled(true);	

		}

		for(int i=0; i<ChosenCards.length; i++){
			Winner[i] = new JButton("Pick Winner");


			Winner[i].setPreferredSize(SizeOfChosenCards1);
			//			Winner[i].setMaximumSize(SizeOfChosenCards);
			//				PlayerCards[i].setMaximumSize(SizeOfPlayerCards);
			//			Winner[i].setMinimumSize(SizeOfChosenCards);
			Winner[i].setBackground(Color.BLACK);
			Winner[i].setForeground(Color.WHITE);
			Winner[i].addActionListener(this);
			Winner[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			Winner[i].setFont(new Font("calibri",1,FontSizeOfCards));
			Winner[i].setBorderPainted(true);
			Winner[i].setFocusPainted(false);
			Winner[i].setEnabled(true);	
			Winner[i].setVisible(false);


		}

		// Creates chosen cards
		if(numberOfCards > 0) {

			for(int i=0; i<ChosenCards.length; i++){
				ChosCard1[i] = new JTextArea();
				//				ChosCard1[i].setMaximumSize(SizeOfChosenCards);
				//				ChosCard1[i].setMinimumSize(SizeOfChosenCards);
				ChosCard1[i].setPreferredSize(SizeOfChosenCards);
				ChosCard1[i].setBorder(BorderForCards);
				ChosCard1[i].setBackground(Color.white);
				ChosCard1[i].setForeground(Color.BLACK);
				ChosCard1[i].setFont(new Font("calibri",1,FontSizeOfCards));
				ChosCard1[i].setEditable(false);
				ChosCard1[i].setLineWrap(true);
				ChosCard1[i].setWrapStyleWord(true);
			}
		}

		if(numberOfCards > 1) {

			for(int i=0; i<ChosenCards.length; i++){
				ChosCard2[i] = new JTextArea();
				//				ChosCard2[i].setMaximumSize(SizeOfChosenCards);
				//				ChosCard2[i].setMinimumSize(SizeOfChosenCards);
				ChosCard2[i].setPreferredSize(SizeOfChosenCards);
				ChosCard2[i].setBorder(BorderForCards);
				ChosCard2[i].setBackground(Color.white);
				ChosCard2[i].setForeground(Color.BLACK);
				ChosCard2[i].setFont(new Font("calibri",1,FontSizeOfCards));
				ChosCard2[i].setEditable(false);
				ChosCard2[i].setLineWrap(true);
				ChosCard2[i].setWrapStyleWord(true);

			}
		}

		if(numberOfCards > 2) {

			for(int i=0; i<ChosenCards.length; i++){
				ChosCard3[i] = new JTextArea();
				//				ChosCard3[i].setMaximumSize(SizeOfChosenCards);
				//				ChosCard3[i].setMinimumSize(SizeOfChosenCards);
				ChosCard3[i].setPreferredSize(SizeOfChosenCards);
				ChosCard3[i].setBorder(BorderForCards);
				ChosCard3[i].setBackground(Color.white);
				ChosCard3[i].setForeground(Color.BLACK);
				ChosCard3[i].setFont(new Font("calibri",1,FontSizeOfCards));
				ChosCard3[i].setEditable(false);
				ChosCard3[i].setLineWrap(true);
				ChosCard3[i].setWrapStyleWord(true);
			}
		}


		pointsPlayer = new JTextArea();
		pointsPlayer.setPreferredSize(SizeOfChosenCards);
		pointsPlayer.setBorder(BorderForCards);
		pointsPlayer.setBackground(Color.white);
		pointsPlayer.setForeground(Color.BLACK);
		pointsPlayer.setFont(new Font("calibri",1,FontSizeOfCards));
		pointsPlayer.setEditable(false);
		pointsPlayer.setLineWrap(true);
		pointsPlayer.setWrapStyleWord(true);


		/////////////////////////////////////////////// PANELS //////////////////////////////////////////////////////////////////

		JPanel PAll = new JPanel();

		PAll.setLayout(new BorderLayout());
		PAll.setSize(1900, 1000);

		number = new JLabel("Round Number: ");
		number.setForeground(Color.BLACK);
		number.setPreferredSize(new Dimension(300, 80));
		number.setAlignmentX(Component.CENTER_ALIGNMENT);
		number.setFont(new Font("calibri",1,FontSizeOfCards));

		czar = new JLabel("Card Czar is: ");
		czar.setForeground(Color.BLACK);
		czar.setPreferredSize(new Dimension(300, 80));
		czar.setAlignmentX(Component.CENTER_ALIGNMENT);
		czar.setFont(new Font("calibri",1,FontSizeOfCards));

		phase = new JLabel("Choosing: ");
		phase.setForeground(Color.BLACK);
		phase.setPreferredSize(new Dimension(300, 80));
		phase.setAlignmentX(Component.CENTER_ALIGNMENT);
		phase.setFont(new Font("calibri",1,FontSizeOfCards));

		timerem = new JLabel("Time remaining: ");
		timerem.setForeground(Color.BLACK);
		timerem.setPreferredSize(new Dimension(300, 80));
		timerem.setAlignmentX(Component.CENTER_ALIGNMENT);
		timerem.setFont(new Font("calibri",1,FontSizeOfCards));

		// Panel for the left side.
		JPanel PLeft = new JPanel();
		JPanel card1 = new JPanel();
		card1.setMaximumSize(new Dimension(210,310));
		card1.setBackground(Color.BLACK);
		card1.add(BlackCard);

		PLeft.setLayout(new BoxLayout(PLeft, BoxLayout.PAGE_AXIS));
		PLeft.setPreferredSize(new Dimension(300,1000));
		PLeft.setBackground(Color.WHITE);
		PLeft.add(Box.createRigidArea(new Dimension(0,200)));
		PLeft.add(label);
		PLeft.add(Box.createRigidArea(new Dimension(0,10)));		
		PLeft.add(card1);
		PLeft.add(Box.createRigidArea(new Dimension(0,10)));
		PLeft.add(number);
		PLeft.add(Box.createRigidArea(new Dimension(0,10)));
		PLeft.add(czar);
		PLeft.add(Box.createRigidArea(new Dimension(0,10)));
		PLeft.add(phase);
		PLeft.add(Box.createRigidArea(new Dimension(0,10)));
		PLeft.add(timerem);
		PLeft.add(Box.createRigidArea(new Dimension(0,50)));
		PAll.add(PLeft, BorderLayout.WEST);

		// Panel for the right side.
		JPanel PRight = new JPanel();

		PRight.setLayout(new BoxLayout(PRight, BoxLayout.PAGE_AXIS));
		PRight.setPreferredSize(new Dimension(300,1000));
		PRight.setBackground(Color.WHITE);
		PRight.add(Box.createRigidArea(new Dimension(0,20)));
		PRight.add(pointsPlayer);
		PRight.add(Box.createRigidArea(new Dimension(0,500)));


		PAll.add(PRight, BorderLayout.EAST);


		// Panels for the center
		JPanel PMid = new JPanel();
		JPanel PUMid = new JPanel();
		JPanel topmiddle = new JPanel();
		JPanel toptop = new JPanel();
		JPanel top = new JPanel();
		JPanel mid = new JPanel();
		JPanel low = new JPanel();
		JPanel PUCard = new JPanel();
		JPanel PDCard = new JPanel();

		// Settings for panels
		PUMid.setLayout(new BorderLayout());
		PUCard.setLayout(new BoxLayout(PUCard, BoxLayout.LINE_AXIS));
		PDCard.setLayout(new BoxLayout(PDCard, BoxLayout.LINE_AXIS));
		topmiddle.setLayout(new BorderLayout());
		PMid.setPreferredSize(new Dimension(1250,1000));
		PUMid.setPreferredSize(new Dimension(1250,590));
		PUCard.setPreferredSize(new Dimension(1250,190));
		PDCard.setPreferredSize(new Dimension(1250,40));


		toptop.setPreferredSize(new Dimension(1250,50));
		top.setPreferredSize(new Dimension(1250,180));

		topmiddle.setPreferredSize(new Dimension(1250,230));
		mid.setPreferredSize(new Dimension(1250,130));
		low.setPreferredSize(new Dimension(1250,180));

		PUCard.setBackground(Color.WHITE);
		PDCard.setBackground(Color.WHITE);


		//Chosen card being added to panel
		//		PUMid.add(Box.createRigidArea(new Dimension(90,0)));
		for(int i=0; i<ChosenCards.length; i++) {
			toptop.add(Winner[i]);
			toptop.add(Box.createRigidArea(new Dimension(15,0)));

		}

		if(numberOfCards > 0){
			for(int i=0; i<ChosenCards.length; i++) {
				top.add(ChosCard1[i]);
				top.add(Box.createRigidArea(new Dimension(15,0)));

			}
		}

		if(numberOfCards > 1){
			for(int i=0; i<ChosenCards.length; i++) {
				mid.add(ChosCard2[i]);
				mid.add(Box.createRigidArea(new Dimension(15,0)));

			}
		}

		if(numberOfCards > 2){
			for(int i=0; i<ChosenCards.length; i++) {
				low.add(ChosCard3[i]);
				low.add(Box.createRigidArea(new Dimension(15,0)));

			}
		}

		PUCard.add(Box.createRigidArea(new Dimension(10,0)));
		for(int i=0; i<10; i++) {
			PUCard.add(area[i]);
			PUCard.add(Box.createRigidArea(new Dimension(5,0)));
		}

		PDCard.add(Box.createRigidArea(new Dimension(10,0)));
		for(int i=0; i<10; i++) {
			PDCard.add(PlayerCards[i]);
			PDCard.add(Box.createRigidArea(new Dimension(5,0)));
		}


		toptop.setBackground(Color.WHITE);
		top.setBackground(Color.WHITE);
		mid.setBackground(Color.WHITE);
		low.setBackground(Color.WHITE);

		topmiddle.add(toptop, BorderLayout.NORTH);
		topmiddle.add(top, BorderLayout.CENTER);

		PUMid.add(topmiddle, BorderLayout.NORTH);
		PUMid.add(mid, BorderLayout.CENTER);
		PUMid.add(low, BorderLayout.SOUTH);

		PDCard.setBackground(Color.WHITE);
		PUCard.setBackground(Color.WHITE);

		//		PMid.add(comp, constraints);

		mainGame.add(PMid, BorderLayout.CENTER);
		PMid.add(PUMid,BorderLayout.NORTH);
		//		PMid.add(PUCard,BorderLayout.CENTER);
		//		PUMid.add(Box.createRigidArea(new Dimension(0,50)));
		PMid.add(Box.createRigidArea(new Dimension(1250,50)));
		PMid.add(PUCard,BorderLayout.CENTER);
		PMid.add(PDCard,BorderLayout.SOUTH	);

		PAll.add(PMid, BorderLayout.CENTER);
		PMid.setBackground(Color.WHITE);
		mainGame.add(PAll);

	}










	public void actionPerformed(ActionEvent e){

		/////////////////////////////////////////////////////////////////
		// Quit button closes game
		if(e.getSource() == BQuit){

			//System.exit(0);
			dispose();

		} else if (e.getSource() == BSignIn){
			this.name = txtfld1.getText();
			this.IP = txtfld2.getText();

			System.out.println(name);
			System.out.println(IP);

			try {

				Client.loginUser(IP, name);

			} catch (Exception e1) {
				txtfld2.setText("Invalid IP");
			}

			hideAll();
			loadAvailableGames();
			mainLobby.setVisible(true);
			add(mainLobby);
			mainLobby.requestFocus();

		} else if(e.getSource()==LCreateGameBtn) {
			hideAll();
			mainCreate.setVisible(true);
			add(mainCreate);
			mainCreate.requestFocus();

		} else if(e.getSource()==LSignOutBtn) {
			hideAll();
			mainLogin.setVisible(true);
			add(mainLogin);
			mainLogin.requestFocus();
			
		} else if (e.getSource() == BBack) {
			hideAll();
			mainLobby.setVisible(true);
			add(mainLobby);
			mainLobby.requestFocus();

		} else if (e.getSource() == BCreateGame){
			this.gameName = txtfld8.getText();
			this.rounds = txtfld7.getText();
			this.time = txtfld4.getText();
			this.password = txtfld5.getText();

			hideAll();
			mainReadyUpLobby.setVisible(true);
			add(mainReadyUpLobby);
			mainReadyUpLobby.requestFocus();

			Client.createNewGame(gameName);



		} else if (e.getSource() == LRefreshBtn) {
			updateGameList();

		} else if (e.getSource() == LJoinGameBtn) {

			gameSelected = list.getSelectedIndex();
			System.out.println("Index Selected: "+ gameSelected);
			if (gameSelected < 0){ return; }
			GamePreview preID = games.get(gameSelected);
			int gameID = preID.getId();

			try {
				Client.joinGame(gameID);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			hideAll();
			mainReadyUpLobby.setVisible(true);
			add(mainReadyUpLobby);
			mainReadyUpLobby.requestFocus();

		} else if(e.getSource()==BLeave){
			hideAll();
			mainLobby.setVisible(true);
			add(mainLobby);
			mainLobby.requestFocus();
			Client.sendLeave();
			updateGameList();
		}
		else if (e.getSource() == BReady) {
			Client.sendReady();
		} else if ( e.getSource() == lobbySendButton ) {
			if (lobbyMessageField.getText().length() < 1) {
				// DO NOTHING
			} else {
				Client.sendLobbyChatMessage(lobbyMessageField.getText());
				lobbyMessageField.setText("");
			}
			lobbyMessageField.requestFocusInWindow();
		}

		for (int i = 0; i < 10; i++) {

			if (e.getSource() == PlayerCards[i]) {
				Client.pickWhiteCard(i);	
			}
		}

		for (int i = 0; i < players; i++) {
			System.out.println("Number of Players: "+players);

			if (e.getSource() == Winner[i]) {
				Client.pickWinnerCard(i);				

			}
		}
	}


	public void updatePlayer(GameSlot givenSlot) {
		int index = givenSlot.getSlot();

		if (givenSlot.hasPlayer() == true){
			gameSlot[index] = givenSlot;

			player[index].setText("   "+gameSlot[index].getName());

			if (gameSlot[index].isReady() == true) {			
				readyBtn[index].setBackground(new Color(76,153,0));
				System.out.println("Changed to Green");

			} else {
				readyBtn[index].setBackground(Color.RED);
				System.out.println("Changed to Red");

			}
		} else {
			player[index].setText("");
			readyBtn[index].setBackground(Color.white);
			System.out.println("Changed to White");
		}


	}

	public void startGame(int playerNumber){

		players = playerNumber;
		System.out.println("Number of players: "+players);

		//		runGame();

		ChosCard1[playerNumber].setVisible(true);
		ChosCard2[playerNumber].setVisible(true);
		ChosCard3[playerNumber].setVisible(true);

		hideAll();
		mainGame.setVisible(true);
		add(mainGame);
		mainGame.requestFocus();

	}

	public void setWhite(String text, int num){

		System.out.println("Text1: "+text);
		area[num].setText(text);

		//		runGame();

	}

	public void setBlack(String text){

		System.out.println("Text2: "+text);
		BlackCard.setText(text);

		//		runGame();

	}

	public void setSelected(int num, String text){

		System.out.println("String: "+text+" and num: "+num);

		if (num < 8) {
			ChosCard1[num].setText(text);
		} else {
			System.out.println("Got: "+num);
		}
	}

	public void setScore(String[] playerName, int[] points){

		for(int i=0; i<playerName.length; i++){
			System.out.println(playerName[i]+" has "+points[i]+".\n");
			pointsPlayer.append(playerName[i]+" has "+points[i]+".\n");
		}
	}


	public void setRound(int rnd){
		number.setText("Round Number: "+rnd);
	}

	public void setCzar(boolean cz){
		if (cz) {
			czar.setText("You are Card Czar");
		} else {	
			czar.setText("You are NOT Card Czar");
		}
	}

	public enum phases {WAIT, PICK, WAITCZAR, CZAR, WINNER}

	public void setPhase(phases phase1) {
		switch (phase1) {
		case WAIT: {
			phase.setText("Waiting on other players...");

			for (int i = 0; i < 10; i++) {
				PlayerCards[i].setVisible(false);
			}

			break;
		}
		case PICK: {
			phase.setText("Pick your cards");

			for (int i = 0; i < 10; i++) {
				PlayerCards[i].setVisible(true);
			}

			break;
		}
		case WAITCZAR: {
			phase.setText("Waiting for Czar...");
			break;
		}
		case CZAR: {
			phase.setText("Choose a winner");

			for (int i = 0; i < 8; i++) {
				Winner[i].setVisible(true);
			}

			break;
		}
		case WINNER: {
			phase.setText("Winner was chosen");

			for (int i = 0; i < 8; i++) {
				Winner[i].setVisible(false);
			}

			break;
		}
		default: {
			phase.setText("null");
		}
		}
	}

	public void setTime(int t) {

		timerem.setText("Time to answer: "+t);

	}


	public void highlightWinner(int i){

		ChosCard1[i].setBackground(new Color(255,215,0));
		ChosCard2[i].setBackground(new Color(255,215,0));
		ChosCard3[i].setBackground(new Color(255,215,0));



	}



	//	} else if (e.getSource() == b3) {
	//			// THIS NEEDS TO CHECK IF GAME HAS PASSWORD AS WELL
	//
	//			JFrame rules = new JFrame("Rules");
	//			rules.setLayout(new BorderLayout());
	//			rules.setVisible(true);
	//			rules.setResizable(false);
	//			rules.setSize(600,200);
	//			rules.setLocationRelativeTo(null);
	//
	//
	//			JButton b9 = new JButton();
	//			b9 = new JButton("Back");
	//			b9.setPreferredSize(new Dimension(120, 20));
	//			b9.setBorder(new RoundedBorder(30));
	//			b9.setBackground(Color.white);
	//			b9.setForeground(Color.black);
	//			b9.addActionListener(this);
	//			b9.setAlignmentX(Component.CENTER_ALIGNMENT);
	//			b9.setFont(new Font("calibri",1,21));
	//			b9.setBorderPainted(true);
	//			b9.setFocusPainted(false);
	//			b9.setEnabled(true);
	//
	//			JButton b10 = new JButton();
	//			b10 = new JButton("Join");
	//			b10.setPreferredSize(new Dimension(120, 20));
	//			b10.setBorder(new RoundedBorder(30));
	//			b10.setBackground(Color.white);
	//			b10.setForeground(Color.black);
	//			b10.addActionListener(this);
	//			b10.setAlignmentX(Component.CENTER_ALIGNMENT);
	//			b10.setFont(new Font("calibri",1,21));
	//			b10.setBorderPainted(true);
	//			b10.setFocusPainted(false);
	//			b10.setEnabled(true);
	//
	//			//Den lukker Rules JFrame hvis man klikker pÂ den button, som er pÂ den. 
	//			b9.addActionListener(new ActionListener() {
	//
	//				public void actionPerformed(ActionEvent e){    	
	//					rules.dispose();
	//				}
	//			});
	//
	//			//Laver Jpanel til det
	//			JPanel panel = new JPanel();
	//
	//
	//			JPanel panelcenter = new JPanel();
	//			FlowLayout flow = new FlowLayout();
	//			panelcenter.setLayout(flow);
	//			panelcenter.setBackground(Color.WHITE);
	//
	//			// Name Label
	//			l4 = new JLabel("Enter Password:");
	//			l4.setMaximumSize(lsize);
	//			l4.setFont(new Font("calibri",Font.PLAIN,30));
	//			l4.setAlignmentX(Component.CENTER_ALIGNMENT);
	//
	//			// Name textfield
	//			txtfld6 = new JTextField(15);
	//			txtfld6.setMaximumSize(txtfldsize);
	//			txtfld6.setFont(new Font("calibri",Font.PLAIN,20));
	//			txtfld6.setAlignmentX(Component.CENTER_ALIGNMENT);
	//
	//			panelcenter.add(l4);
	//			panelcenter.add(txtfld6);
	//			panelcenter.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	//
	//
	//			JPanel panelsouth = new JPanel();
	//
	//			FlowLayout flow1 = new FlowLayout(FlowLayout.CENTER);
	//			panelsouth.setLayout(flow1);
	//			panelsouth.setBackground(Color.WHITE);
	//			panelsouth.add(Box.createRigidArea(new Dimension(0,40)));
	//			panelsouth.add(b9);
	//			panelsouth.add(b10);
	//			panelsouth.add(Box.createRigidArea(new Dimension(0,5)));
	//
	//			flow1.setHgap(10);
	//
	//			rules.add(panel);
	//
	//			//SÊtter tekstfelt og button pÂ JPanel
	//			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	//			panel.add(Box.createRigidArea(new Dimension(200,40)));
	//			panel.add(panelcenter, BorderLayout.CENTER);
	//			panel.add(Box.createRigidArea(new Dimension(200,10)));
	//			panel.add(panelsouth, BorderLayout.SOUTH);
	//			panel.add(Box.createRigidArea(new Dimension(200,10)));
	//			panel.setBackground(Color.WHITE);

























	//	// Create List
	//			DefaultListModel model = new DefaultListModel();
	//			playerList = new JList(model);
	//
	//			JScrollPane scrollPane = new JScrollPane(playerList);
	//
	//			scrollPane.setPreferredSize(new Dimension(1000, 656));
	//
	//			playerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	//			playerList.setVisibleRowCount(-1);
	//			playerList.setLayoutOrientation(JList.VERTICAL);
	//			//		playerList.ensureIndexIsVisible(list.getSelectedIndex());
	//			playerList.setFont(new Font("calibri",Font.PLAIN,25));
	//			playerList.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
	//
	//			String name = "Jonas";
	//			String status = "WAITING";
	//			Boolean lock = true;
	//			int current = 7;
	//			int max = 8;
	//			int id = 4738920;
	//			String islocked = "";
	//
	//			String blank = "          ";
	//
	//			for (int i = 0; i < 15; i++) {
	//
	//				if (lock == true) {
	//					islocked = "LOCKED";
	//				} else {
	//					islocked = "      ";
	//				}
	//
	//				model.addElement(name+blank+status+blank+islocked+blank+Integer.toString(current)+"/"+Integer.toString(max));
	//
	//			}









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