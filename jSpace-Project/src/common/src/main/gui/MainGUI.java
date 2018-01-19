package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
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
import javax.swing.text.DefaultCaret;

import common.src.main.client.Client;
import common.src.main.client.GamePreview;
import common.src.main.server.GameSlot;

@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener {
	private int maxPlayers = 8;

	private String name, IP, gameName, rounds, time, password;
	private int players;

	// Login
	private JButton BQuit, BSignIn, BBack;
	private JLabel LTitle, LText, LFigure1, LName, LIP, LFigure2;
	private JTextField txtfld1, txtfld2;
	private boolean signIn = false;

	// Lobby
	private JButton LCreateGameBtn, LSignOutBtn, LJoinGameBtn, LRefreshBtn, b5;
	private JLabel l1, l2, l3, l4, l5, l6;
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
	private ArrayList<GamePreview> games;

	// ReadyUpLobby
	private GameSlot[] gameSlot = new GameSlot[maxPlayers];
	private JButton[] readyBtn = new JButton[maxPlayers];
	private JLabel[] player = new JLabel[maxPlayers];
	private JPanel[] lobbyPlayerPanel = new JPanel[maxPlayers];
	private JButton BReady, BLeave;
	private JLabel LHead, LPicWC, LPicBC;
	private static JList playerList;
	private Chat lobbyChat;
	private JTextArea lobbyChatBox;
	private JTextField lobbyMessageField;
	private JButton lobbySendButton;
	private JPanel lobbyChatPanel, lobbySendPanel;

	// Game
	private JTextArea BlackCard;
	private String[] ChosenCards = new String[maxPlayers];
	private int numberOfCards = 3;
	private JButton[] PlayerCards = new JButton[10];
	private JButton[] Winner = new JButton[maxPlayers];
	private JTextArea[] ChosCard1 = new JTextArea[maxPlayers];
	private JTextArea[] ChosCard2 = new JTextArea[maxPlayers];
	private JTextArea[] ChosCard3 = new JTextArea[maxPlayers];
	private JTextArea[] area = new JTextArea[10];
	private JLabel label, czar, phase, number, timerem = new JLabel();
	public boolean isCzar;
	private JLabel[] scores = new JLabel[maxPlayers];
	private JTextArea chatBox;
	private JTextField messageField;
	private JButton gameSendButton;
	private JPanel PRight, chatPanel, sendPanel, PTopRight;
	
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

	// Changes color of the buttons when pressed
	public void changeColor(int x){
		if (gameSlot[x].isReady() == true) {			
			readyBtn[x].setBackground(new Color(76,153,0));
		} else {
			readyBtn[x].setBackground(Color.RED);
		}
	}

	// Create main panels
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

	// Initialize main gui and all the panels
	public MainGUI() throws InterruptedException{

		getContentPane().setLayout(new BorderLayout());
		getContentPane().setSize(1900,1000);

		for (int i = 0; i < 8; i++) {
			gameSlot[i] = new GameSlot(-1, "", false);
		}
		runLogin();
		runLobby();
		runCreate();
		runReadyUpLobby();
		runGame();
		runEnter();
	}

	// So one can press enter in GUI
	public void runEnter(){
		mainLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"signIn");

		mainLogin.getActionMap().put("signIn",new AbstractAction(){
			public void actionPerformed(ActionEvent ae){
				BSignIn.doClick();
			}
		});
		mainReadyUpLobby.getInputMap(JComponent.WHEN_FOCUSED)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"lobSend");

		mainReadyUpLobby.getActionMap().put("lobSend",new AbstractAction(){
			public void actionPerformed(ActionEvent ae){
				lobbySendButton.doClick();
			}
		});
		lobbyMessageField.getInputMap(JComponent.WHEN_FOCUSED)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"lobSend2");
		
		lobbyMessageField.getActionMap().put("lobSend2",new AbstractAction(){
			public void actionPerformed(ActionEvent ae){
				lobbySendButton.doClick();
			}
		});
		
		mainCreate.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"creatGame");

		mainCreate.getActionMap().put("creatGame",new AbstractAction(){
			public void actionPerformed(ActionEvent ae){
				BCreateGame.doClick();
			}
		});

		// Game Chat
		mainGame.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"gameChat");

		mainGame.getActionMap().put("gameChat",new AbstractAction(){
			public void actionPerformed(ActionEvent ae){
				gameSendButton.doClick();
			}
		});
		messageField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"gameChat2");
		messageField.getActionMap().put("gameChat2",new AbstractAction(){
			public void actionPerformed(ActionEvent ae){
				gameSendButton.doClick();
			}
		});
	}

	// Hide every GUI panel
	public void hideAll(){
		mainLogin.setVisible(false);
		mainLobby.setVisible(false);
		mainCreate.setVisible(false);
		mainReadyUpLobby.setVisible(false);
		mainGame.setVisible(false);
	}
	
	// Error popup
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

		l6 = new JLabel("WARNING: ERROR OCCURRED!");
		l6.setMaximumSize(new Dimension(400, 40));
		l6.setFont(new Font("calibri",Font.PLAIN,30));
		l6.setAlignmentX(Component.CENTER_ALIGNMENT);
		l6.setForeground(Color.RED);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);

		panel.add(Box.createRigidArea(new Dimension(400,10)));
		panel.add(l6);
		panel.add(Box.createRigidArea(new Dimension(400,10)));
		panel.add(BOK);
		panel.add(Box.createRigidArea(new Dimension(400,10)));

		Error.add(panel);

		BOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){    	
				Error.dispose();
			}
		});
	}

	/*********************************************************************************************/
	/*************************************** Login Screen ****************************************/
	/*********************************************************************************************/

	// Login GUI
	public void runLogin(){ 
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

		// textfield
		txtfld1 = new JTextField(50);
		txtfld1.setMaximumSize(txtfldsize);
		txtfld1.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld1.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtfld1.setText("");

		// textfield
		txtfld2 = new JTextField(50);
		txtfld2.setMaximumSize(txtfldsize);
		txtfld2.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld2.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtfld2.setText("127.0.0.1");

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
		PRight = new JPanel();
		PRight.setLayout(new BoxLayout(PRight, BoxLayout.PAGE_AXIS));
		PRight.setBackground(Color.white);

		PRight.add(Box.createRigidArea(new Dimension(365,350)));
		PRight.add(LFigure2);
		PRight.add(Box.createRigidArea(new Dimension(365,50)));
		PRight.add(BQuit);
		PRight.add(Box.createRigidArea(new Dimension(365,50)));

		mainLogin.add(PLeft, BorderLayout.WEST);
		mainLogin.add(PRight, BorderLayout.EAST);

		PC.add(PL, BorderLayout.WEST);
		PC.add(PR, BorderLayout.EAST);

		PL.add(Box.createRigidArea(new Dimension(150,100)));
		PR.add(Box.createRigidArea(new Dimension(150,100)));

		PC.setBackground(Color.WHITE);
		PC.add(PCenter);

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
	} // End of login screen GUI

	/*********************************************************************************************/
	/*************************************** Lounge Screen ***************************************/
	/*********************************************************************************************/

	// Create Lobby GUI
	public void runLobby() throws InterruptedException {
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
	} // End of the Lounge screen GUI

	// Update list of games
	public void updateGameList(){
		try {
			games = Client.getGameList();
		} catch (InterruptedException e) { e.printStackTrace(); }
		numberOfGames = games.size();
		
		System.out.println(numberOfGames);
		model.clear();

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
	} // End of the update lounge game list

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
					LJoinGameBtn.doClick(); 
				} else if (me.getClickCount() == 1){
					mainLobby.getRootPane().setDefaultButton(LJoinGameBtn);
				}}});


		availableGames.setBackground(Color.WHITE);
		availableGames.setAlignmentX(CENTER_ALIGNMENT);
		availableGames.setAlignmentY(CENTER_ALIGNMENT);
		availableGames.add(scrollPaneMain, BorderLayout.CENTER);
		mainLobby.add(availableGames, BorderLayout.CENTER);
	} // End of load available games function

	/*********************************************************************************************/
	/************************************* Create Game Screen ************************************/
	/*********************************************************************************************/
	
	// Create game GUI
	public void runCreate(){
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

		// Rounds Label
		LRounds = new JLabel("Rounds to win:");
		LRounds.setMaximumSize(lsize);
		LRounds.setFont(new Font("calibri",Font.PLAIN,fontsize));
		LRounds.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Time Label
		LTime = new JLabel("Time to answer:");
		LTime.setMaximumSize(lsize);
		LTime.setFont(new Font("calibri",Font.PLAIN,fontsize));
		LTime.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Password Label
		LPass = new JLabel("Password Protected:");
		LPass.setMaximumSize(lsize);
		LPass.setFont(new Font("calibri",Font.PLAIN,fontsize));
		LPass.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Password Label
		LPassword = new JLabel("Password:");
		LPassword.setMaximumSize(lsize);
		LPassword.setFont(new Font("calibri",Font.PLAIN,fontsize));
		LPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

		// The two cards in the sides
		LFigure1 = new JLabel();
		LFigure2 = new JLabel();
		LFigure1.setIcon(new ImageIcon(new ImageIcon("BCCreate.png").getImage().getScaledInstance(247, 380, Image.SCALE_DEFAULT)));
		LFigure2.setIcon(new ImageIcon(new ImageIcon("WCCreate.png").getImage().getScaledInstance(244, 381, Image.SCALE_DEFAULT)));
		LFigure1.setAlignmentX(Component.CENTER_ALIGNMENT);
		LFigure2.setAlignmentX(Component.CENTER_ALIGNMENT);

		// textfield
		txtfld8 = new JTextField(50);
		txtfld8.setText("");
		txtfld8.setMaximumSize(txtfldsize);
		txtfld8.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld8.setAlignmentX(Component.CENTER_ALIGNMENT);

		// textfield
		txtfld7 = new JTextField(50);
		txtfld7.setMaximumSize(txtfldsize);
		txtfld7.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld7.setAlignmentX(Component.CENTER_ALIGNMENT);

		// textfield
		txtfld3 = new JTextField(50);
		txtfld3.setMaximumSize(txtfldsize);
		txtfld3.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld3.setAlignmentX(Component.CENTER_ALIGNMENT);

		// textfield
		txtfld4 = new JTextField(50);
		txtfld4.setMaximumSize(txtfldsize);
		txtfld4.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld4.setAlignmentX(Component.CENTER_ALIGNMENT);

		// textfield
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

		// Add labels to panel
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

		// Add textfields to panel
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
		PRight = new JPanel();
		PRight.setLayout(new BoxLayout(PRight, BoxLayout.PAGE_AXIS));
		PRight.setBackground(Color.white);

		PRight.add(Box.createRigidArea(new Dimension(365,250)));
		PRight.add(LFigure2);
		PRight.add(Box.createRigidArea(new Dimension(365,150)));
		PRight.add(BBack);
		PRight.add(Box.createRigidArea(new Dimension(365,50)));

		mainCreate.add(PLeft, BorderLayout.WEST);
		mainCreate.add(PRight, BorderLayout.EAST);

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
	} // End of create game GUI

	/*********************************************************************************************/
	/************************************ Game Lobby Screen **************************************/
	/*********************************************************************************************/

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
		LHead = new JLabel("Cards Against Humanity       ");
		LHead.setAlignmentX(Component.CENTER_ALIGNMENT);
		LHead.setFont(new Font("AR JULIAN",Font.PLAIN,70));
		LHead.setForeground(Color.BLACK);

		// Implementing pictures for white cards and black cards as JLabel
		LPicBC = new JLabel();
		LPicWC = new JLabel();
		LPicBC.setIcon(new ImageIcon(new ImageIcon("BCGLobby.png").getImage().getScaledInstance(244, 376, Image.SCALE_DEFAULT)));
		LPicWC.setIcon(new ImageIcon(new ImageIcon("WCGLobby.png").getImage().getScaledInstance(246, 379, Image.SCALE_DEFAULT)));
		LPicBC.setAlignmentX(Component.TOP_ALIGNMENT);
		LPicWC.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Creates panel for buttons
		JPanel BtnPanel = new JPanel();
		JPanel panel = new JPanel();

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
		BCPanel.setBackground(Color.WHITE);
		BCPanel.add(LPicBC);
		BCPanel.add(Box.createRigidArea(new Dimension(50, 75)));
		BCPanel.add(lobbyChatPanel);
		BCPanel.add(lobbySendPanel);

		// Panel for White Card image
		JPanel WCPanel = new JPanel();
		WCPanel.setLayout(new BoxLayout(WCPanel, BoxLayout.LINE_AXIS));
		WCPanel.setSize(300, 800);
		WCPanel.setBackground(Color.WHITE);
		WCPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		WCPanel.add(LPicWC);

		Dimension maxsize = new Dimension(1000, 75);

		// Middle JPanels
		JPanel middle = new JPanel();
		middle.setPreferredSize(new Dimension(1000, 656));
		middle.setBackground(Color.white);

		for (int i = 0; i < 8; i++) {
			lobbyPlayerPanel[i] = new JPanel();
			lobbyPlayerPanel[i].setLayout(new BorderLayout());
			lobbyPlayerPanel[i].setPreferredSize(maxsize);
			lobbyPlayerPanel[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			lobbyPlayerPanel[i].setBackground(Color.WHITE);
			
			// Name Label
			player[i] = new JLabel("   ");
			player[i].setMaximumSize(lsize);
			player[i].setFont(new Font("calibri",Font.PLAIN,30));
			player[i].setAlignmentX(Component.CENTER_ALIGNMENT);

			//"Button" 
			readyBtn[i] = new JButton();
			readyBtn[i].setPreferredSize(new Dimension(75, 75));
			readyBtn[i].setBackground(Color.WHITE);
			readyBtn[i].setBorderPainted(false);
			readyBtn[i].setEnabled(false);
			
			lobbyPlayerPanel[i].add(Box.createRigidArea(new Dimension(1000,12)));
			lobbyPlayerPanel[i].add(player[i], BorderLayout.WEST);
			lobbyPlayerPanel[i].add(readyBtn[i], BorderLayout.EAST);
			
			middle.add(lobbyPlayerPanel[i]);
		}

		mainReadyUpLobby.add(HeadPanel, BorderLayout.NORTH);
		mainReadyUpLobby.add(BCPanel, BorderLayout.EAST);
		mainReadyUpLobby.add(WCPanel, BorderLayout.WEST);

		mainReadyUpLobby.setVisible(false);

		JPanel playerPanel = new JPanel();

		playerPanel.setBackground(Color.WHITE);
		playerPanel.setAlignmentX(CENTER_ALIGNMENT);
		playerPanel.setAlignmentY(CENTER_ALIGNMENT);
		playerPanel.add(middle);

		mainReadyUpLobby.add(playerPanel, BorderLayout.CENTER);
	} // End of game lobby GUI
	
	public void updatePlayer(GameSlot givenSlot) {
		gameSlot[givenSlot.getSlot()] = givenSlot;
		int index = givenSlot.getSlot();

		if (givenSlot.hasPlayer() == true){
			gameSlot[index] = givenSlot;

			player[index].setText("   "+gameSlot[index].getName());

			if (gameSlot[index].isReady() == true) {			
				readyBtn[index].setBackground(new Color(76,153,0));
			} else {
				readyBtn[index].setBackground(Color.RED);
			}
		} else {
			player[index].setText("");
			readyBtn[index].setBackground(Color.white);
		}
	}
	
	public void lobbyChat() {
		// Panels
		lobbyChatPanel = new JPanel();
		lobbyChatPanel.setLayout(new BorderLayout());
		lobbyChatPanel.setPreferredSize(new Dimension(200, 300));
		lobbySendPanel = new JPanel();
		lobbySendPanel.setBackground(Color.WHITE);
		lobbySendPanel.setLayout(new GridBagLayout());

		// Message field and send button
		lobbyMessageField = new JTextField();
		lobbyMessageField.requestFocusInWindow();
		lobbyMessageField.setPreferredSize(new Dimension(300, 20));

		lobbySendButton = new JButton(" Send ");
		lobbySendButton.addActionListener(this);

		// Chat area
		lobbyChatBox = new JTextArea();
		lobbyChatBox.setEditable(false);
		lobbyChatBox.setFont(new Font("Serif", Font.PLAIN, 15));
		lobbyChatBox.setLineWrap(true);

		// adding elements
		JScrollPane lobbyScrollPane = new JScrollPane(lobbyChatBox);
		DefaultCaret caretLobby = (DefaultCaret) lobbyChatBox.getCaret(); // ←
		caretLobby.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		lobbyChatPanel.add(lobbyScrollPane, BorderLayout.CENTER);
		lobbyScrollPane.setViewportView(lobbyChatBox);
		lobbySendPanel.add(lobbyMessageField);
		lobbySendPanel.add(lobbySendButton);
		lobbyChatPanel.add(BorderLayout.SOUTH, lobbySendPanel);
	} // End of lobby chat setup

	public void chatLobbyMessageReceived(String message) {
		lobbyChatBox.append(message+"\n");
	} // End of lobby chat message received
	
	/*********************************************************************************************/
	/************************************** In-Game Screen ***************************************/
	/*********************************************************************************************/
	
	public void runGame(){
		mainGame.setLayout(new BorderLayout());	

		int FontSizeOfCards = 16;
		Dimension SizeOfPlayerCards = new Dimension(120,500);
		Dimension SizeOfBlackCards = new Dimension(200,300);
		Dimension SizeOfChosenCards = new Dimension(123,170);
		Dimension SizeOfChosenCards1 = new Dimension(123,40);
		Dimension SizeOfPlayerCards1 = new Dimension(120,40);
		Border BorderForCards = BorderFactory.createLineBorder(Color.BLACK, 1);

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

		// buttons for the cards on players hand is being created
		for(int i=0; i<10; i++){
			PlayerCards[i] = new JButton("Choose Card");
			
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
		
		// Button for picking winner
		for(int i=0; i<ChosenCards.length; i++){
			Winner[i] = new JButton("Pick Winner");
			
			Winner[i].setPreferredSize(SizeOfChosenCards1);
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
		
		/** Panels of In-Game Screen **/

		JPanel PAll = new JPanel();

		PAll.setLayout(new BorderLayout());
		PAll.setSize(1900, 1000);

		// Round number
		number = new JLabel("Round Number: ");
		number.setForeground(Color.BLACK);
		number.setPreferredSize(new Dimension(300, 80));
		number.setAlignmentX(Component.CENTER_ALIGNMENT);
		number.setFont(new Font("calibri",1,FontSizeOfCards+10));

		// Who is Czar
		czar = new JLabel("Card Czar is: ");
		czar.setForeground(Color.BLACK);
		czar.setPreferredSize(new Dimension(300, 80));
		czar.setAlignmentX(Component.CENTER_ALIGNMENT);
		czar.setFont(new Font("calibri",1,FontSizeOfCards+10));

		// which phase is it
		phase = new JLabel("Choosing: ");
		phase.setForeground(Color.BLACK);
		phase.setPreferredSize(new Dimension(300, 80));
		phase.setAlignmentX(Component.CENTER_ALIGNMENT);
		phase.setFont(new Font("calibri",1,FontSizeOfCards+10));

		// Time remaining
		timerem = new JLabel("Time remaining: ");
		timerem.setForeground(Color.BLACK);
		timerem.setPreferredSize(new Dimension(300, 80));
		timerem.setAlignmentX(Component.CENTER_ALIGNMENT);
		timerem.setFont(new Font("calibri",1,FontSizeOfCards+10));

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
		PRight = new JPanel();
		PTopRight = new JPanel();
		PTopRight.setLayout(new BoxLayout(PTopRight, BoxLayout.PAGE_AXIS));
		PTopRight.setBackground(Color.WHITE);
		
		PRight.setLayout(new BoxLayout(PRight, BoxLayout.PAGE_AXIS));
		PRight.setPreferredSize(new Dimension(300,1000));
		PRight.setBackground(Color.WHITE);
		PAll.add(PRight, BorderLayout.EAST);
		
		JLabel scoreboard = new JLabel();
		scoreboard.setBackground(Color.white);
		scoreboard.setForeground(Color.BLACK);
		scoreboard.setFont(new Font("calibri",1,25));
		scoreboard.setText("SCOREBOARD");
		PTopRight.add(scoreboard);
		
		PRight.add(Box.createRigidArea(new Dimension(0,50)));
		PRight.add(PTopRight);
		PRight.add(Box.createRigidArea(new Dimension(0,150)));
		gameChat();		
		PRight.add(Box.createRigidArea(new Dimension(0,50)));

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

		mainGame.add(PMid, BorderLayout.CENTER);
		PMid.add(PUMid,BorderLayout.NORTH);
		PMid.add(Box.createRigidArea(new Dimension(1250,50)));
		PMid.add(PUCard,BorderLayout.CENTER);
		PMid.add(PDCard,BorderLayout.SOUTH	);

		PAll.add(PMid, BorderLayout.CENTER);
		PMid.setBackground(Color.WHITE);
		mainGame.add(PAll);
	}
	
	
	public void gameChat(){
		// Panels
		chatPanel = new JPanel();
		sendPanel = new JPanel();

		chatPanel.setLayout(new BorderLayout());
		chatPanel.setPreferredSize(new Dimension(0,100));
		chatPanel.setVisible(true);
		sendPanel.setBackground(Color.WHITE);
		sendPanel.setLayout(new GridBagLayout());

		PRight.add(chatPanel);
		PRight.add(Box.createRigidArea(new Dimension(0,72)));
		PRight.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

		// Message field and send button
		messageField = new JTextField();
		messageField.requestFocusInWindow();

		gameSendButton = new JButton(" Send ");
		gameSendButton.addActionListener(this);

		// Chat area
		chatBox = new JTextArea();
		chatBox.setEditable(false);
		chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
		chatBox.setLineWrap(true);

		// GRIDBAG CONSTRAINTS
		// GBC for send button
		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.LINE_START;
		left.fill = GridBagConstraints.HORIZONTAL;
		left.weightx = 300.0D;
		left.weighty = 1.0D;
		// GBC for message field
		GridBagConstraints right = new GridBagConstraints();
		right.anchor = GridBagConstraints.LINE_END;
		right.fill = GridBagConstraints.NONE;
		right.weightx = 1.0D;
		right.weighty = 1.0D;

		// adding elements
		JScrollPane scrollPane = new JScrollPane(chatBox);
		DefaultCaret caret = (DefaultCaret) chatBox.getCaret(); // ←
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		chatPanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(chatBox);
		sendPanel.add(messageField, left);
		sendPanel.add(gameSendButton, right);
		chatPanel.add(BorderLayout.SOUTH, sendPanel);
	} // End of in-game chat

	public void chatGameMessageReceived(String message) {
		chatBox.append(message+"\n");
	} // End of in-game chat message received

	public void createScoreBoard(){
		int playersInGame = 0;
		for (int i = 0; i < 8; i++) {
			if (gameSlot[i].hasPlayer()){
				gameSlot[i].setInSlot(playersInGame);
				scores[playersInGame] = new JLabel();
				scores[playersInGame].setBackground(Color.white);
				scores[playersInGame].setForeground(Color.BLACK);
				scores[playersInGame].setFont(new Font("calibri",1,16));
				scores[playersInGame].setText(gameSlot[playersInGame].getName()+": "+0);
				PTopRight.add(scores[playersInGame]);	
				
				playersInGame++;
			}
		}	
	} // End of create score board 
	
	/*********************************************************************************************/
	/******************************** In-Game Update Interactions ********************************/
	/*********************************************************************************************/

	public void startGame(int playerNumber){
		players = playerNumber;
		
		createScoreBoard();
		hideAll();
		mainGame.setVisible(true);
		add(mainGame);
		mainGame.requestFocus();
	} // End of start game setup

	public void setWhite(String text, int num){
		area[num].setText(text);
	} // End of Set white card text

	public void setBlack(String text){
		BlackCard.setText(text);
	} // End of set black card text

	public void setSelected(int num, int row, String text){
		if (row == 0) {
			ChosCard1[num].setText(text);
			
			if (text.equals("")) {
				ChosCard1[num].setVisible(false);
			} else {
				ChosCard1[num].setVisible(true);
			}
		}
		if (row == 1) {
			ChosCard2[num].setText(text);
			
			if (text.equals("")) {
				ChosCard2[num].setVisible(false);
			} else {
				ChosCard2[num].setVisible(true);
			}
		}
		if (row == 2) {
			ChosCard3[num].setText(text);
			
			if (text.equals("")) {
				ChosCard3[num].setVisible(false);
			} else {
				ChosCard3[num].setVisible(true);
			}
		}
	} // End of set card selected
	
	public void setScore(int updateSlot, int points){
		String name = gameSlot[updateSlot].getName();
		int index = gameSlot[updateSlot].getInSlot();
		scores[index].setText(name+": "+points);
	} // End of set score

	public void setRound(int rnd){
		number.setText("Round Number: "+rnd);
	} // End of set round

	public void setCzar(boolean cz){
		if (cz) {
			this.isCzar = true;
			czar.setText("You are Card Czar");
			czar.setForeground(Color.RED);
		} else {
			this.isCzar = false;
			czar.setText("You are NOT Card Czar");
			czar.setForeground(Color.BLACK);
		}
	} // End of set czar

	public enum phases {WAIT, PICK, WAITCZAR, CZAR, WINNER}

	public void setPhase(phases phase1) {
		switch (phase1) {
			case WAIT: {
				phase.setText("Waiting on other players...");
				break;
			}
			case PICK: {
				phase.setText("Pick your cards");
				break;
			}
			case WAITCZAR: {
				phase.setText("Waiting for Czar...");
				break;
			}
			case CZAR: {
				phase.setText("Choose a winner");
				break;
			}
			case WINNER: {
				phase.setText("Winner was chosen");
				break;
			} default: {
			phase.setText("null");
			}
		}
	} // End of set phase

	public void setTime(int t) {
		timerem.setText("Time to answer: "+t);
	} // End of set time before timeout


	public void highlightWinner(int i, boolean winner){
		if (winner) {
			ChosCard1[i].setBackground(new Color(255,215,0));
			ChosCard2[i].setBackground(new Color(255,215,0));
			ChosCard3[i].setBackground(new Color(255,215,0));
		} else {
			ChosCard1[i].setBackground(Color.WHITE);
			ChosCard2[i].setBackground(Color.WHITE);
			ChosCard3[i].setBackground(Color.WHITE);
		}
	} // End of highlight winning player

	public void czarButton(boolean show, int i){
		Winner[i].setVisible(show);
	} // End of show czar buttons
	
	public void playerButton(boolean show, int i) {
		PlayerCards[i].setEnabled(show);
	} // End of show player buttons
	
	public void hideRest (int noPlayer, boolean hide){
		ChosCard1[noPlayer].setVisible(hide);
		ChosCard2[noPlayer].setVisible(hide);
		ChosCard3[noPlayer].setVisible(hide);
		Winner[noPlayer].setVisible(hide);
	} // End of hide buttons
	
	/*********************************************************************************************/
	/********************************* Button Listener Actions ***********************************/
	/*********************************************************************************************/

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == BQuit){
			/** Sign Out Button, Login Screen **/
			dispose();
		} else if (e.getSource() == BSignIn){
			/** Sign In Button, Login Screen **/
			this.name = txtfld1.getText();
			this.IP = txtfld2.getText();
			
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
			/** Create Game Button, Lounge Screen **/
			hideAll();
			mainCreate.setVisible(true);
			add(mainCreate);
			mainCreate.requestFocus();
		} else if(e.getSource()==LSignOutBtn) {
			/** Sign Out Button, Lounge Screen **/
			hideAll();
			mainLogin.setVisible(true);
			add(mainLogin);
			mainLogin.requestFocus();
		} else if (e.getSource() == BBack) {
			/** Create Game Button, Lounge Screen **/
			hideAll();
			mainLobby.setVisible(true);
			add(mainLobby);
			mainLobby.requestFocus();
		} else if (e.getSource() == LRefreshBtn) {
			/** Refresh Game List Button, Lounge Screen **/
			updateGameList();
		} else if (e.getSource() == LJoinGameBtn) {
			/** Join Game Button, Lounge Screen **/
			gameSelected = list.getSelectedIndex();
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
		}  else if (e.getSource() == BCreateGame){
			/** Create Game Button, Create Game Screen **/
			this.gameName = txtfld8.getText();
			this.rounds = txtfld7.getText();
			this.time = txtfld4.getText();
			this.password = txtfld5.getText();

			hideAll();
			mainReadyUpLobby.setVisible(true);
			add(mainReadyUpLobby);
			mainReadyUpLobby.requestFocus();

			Client.createNewGame(gameName);
		} else if(e.getSource()==BLeave){
			/** Leave Game Button, Lobby Screen **/
			hideAll();
			mainLobby.setVisible(true);
			add(mainLobby);
			mainLobby.requestFocus();
			Client.sendLeave();
			updateGameList();
		} else if (e.getSource() == BReady) {
			/** Ready Button, Lobby Screen **/
			Client.sendReady();
		} else if ( e.getSource() == lobbySendButton ) {
			/** Chat Send Button, Lobby Screen **/
			if (lobbyMessageField.getText().length() < 1) {
				// DO NOTHING
			} else {
				Client.sendLobbyChatMessage(lobbyMessageField.getText());
				lobbyMessageField.setText("");
			}
			lobbyMessageField.requestFocusInWindow();
		} else if ( e.getSource() == gameSendButton ) {
			/** Chat Send Button, In-Game Screen **/
			if (messageField.getText().length() < 1) {
				// DO NOTHING
			} else {
				Client.sendGameChatMessage(messageField.getText());
				messageField.setText("");
			}
			messageField.requestFocusInWindow();
		}
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == PlayerCards[i]) {
				/** Choose White Card Button, In-Game Screen **/
				Client.pickWhiteCard(i);	
			}
		}
		for (int i = 0; i < players; i++) {
			if (e.getSource() == Winner[i]) {
				/** Choose Winner Button, In-Game Screen **/
				Client.pickWinnerCard(i);				
			}
		}
	}

	public void clearLobby() {
		for (int i = 0; i < maxPlayers; i++) {
			gameSlot[i].setReady(false);
			gameSlot[i].setInSlot(0);
			gameSlot[i].setReady(false);
			gameSlot[i].setInSlot(0);
		}
		
	}
	
	/*
	
	// If password were to be added
	if(e.getSource()==b3){
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
}