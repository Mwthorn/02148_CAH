package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class GameGUI extends JFrame implements ActionListener {

	private JTextArea BlackCard;
	private String[] ChosenCards = new String[8];
	private int numberOfCards = 3;
	private JButton[] PlayerCards = new JButton[10];
	private JButton[] Winner = new JButton[ChosenCards.length];
	private JTextArea[] ChosCard1 = new JTextArea[ChosenCards.length];
	private JTextArea[] ChosCard2 = new JTextArea[ChosenCards.length];
	private JTextArea[] ChosCard3 = new JTextArea[ChosenCards.length];
	private JTextArea[] area = new JTextArea[10];
	private JLabel label = new JLabel();
	public 	JTextArea chatBox = new JTextArea();
	private String message, username = null;
	private JTextField messageField;
	private JButton sendButton;





	public static void main(String[] args) {

		GameGUI main = new GameGUI();

		main.setTitle("Cards Against Humanity");
		main.setSize(1900,1000);
		main.setResizable(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		main.setLocationRelativeTo(null);

	}

	public GameGUI(){	

		getContentPane().setLayout(new BorderLayout());	

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
		BlackCard = new JTextArea("SOME TEXT");
		BlackCard.setPreferredSize(SizeOfBlackCards);
		BlackCard.setFont(new Font("calibri",1,FontSizeOfCards));
		BlackCard.setBorder(BorderForCards);
		BlackCard.setBackground(Color.BLACK);
		BlackCard.setForeground(Color.WHITE);
		BlackCard.setAlignmentX(Component.CENTER_ALIGNMENT);
		BlackCard.setEnabled(true);

		// Czars black card of choice used to fill out
//		BlackCard2 = new JTextArea("SOME TEXT");
//		BlackCard2.setMaximumSize(SizeOfBlackCards);
//		BlackCard2.setFont(new Font("calibri",1,FontSizeOfCards));
//		BlackCard2.setBorder(BorderForCards);
//		BlackCard2.setBackground(Color.BLACK);
//		BlackCard2.setForeground(Color.WHITE);
//		BlackCard2.setAlignmentX(Component.CENTER_ALIGNMENT);
//		BlackCard2.setEnabled(true);
//		BlackCard2.setVisible(false);

		label = new JLabel("Current Black Card");
		label.setForeground(Color.BLACK);
		label.setPreferredSize(new Dimension(300, 80));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(new Font("calibri",1,FontSizeOfCards+4));

		// Cards on players hand is being created
		for(int i=0; i<10; i++){
			area[i] = new JTextArea("Larry er et Jesus "+i);
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

		}

		// Creates chosen cards
		if(numberOfCards > 0) {

			for(int i=0; i<ChosenCards.length; i++){
				ChosCard1[i] = new JTextArea("Larry er et Jesus "+i);
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
				ChosCard2[i] = new JTextArea("Larry er et Jesus "+i);
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
				ChosCard3[i] = new JTextArea("Larry er et Jesus "+i);
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


		/////////////////////////////////////////////// PANELS //////////////////////////////////////////////////////////////////

		JPanel PAll = new JPanel();

		PAll.setLayout(new BorderLayout());
		PAll.setSize(1900, 1000);


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
		PLeft.add(Box.createRigidArea(new Dimension(0,50)));
		PAll.add(PLeft, BorderLayout.WEST);

		// Panel for the right side.
		JPanel PRight = new JPanel();

		PRight.setLayout(new BoxLayout(PRight, BoxLayout.PAGE_AXIS));
		PRight.setPreferredSize(new Dimension(300,1000));
		PRight.setBackground(Color.WHITE);
		PRight.add(Box.createRigidArea(new Dimension(0,50)));
		PAll.add(PRight, BorderLayout.EAST);


		// CHAT 

		// Panels
		JPanel chatPanel, sendPanel; // chat panels
		chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		sendPanel = new JPanel();
		sendPanel.setBackground(Color.WHITE);
		sendPanel.setLayout(new GridBagLayout());

		// Message field and send button
		messageField = new JTextField();
		messageField.requestFocusInWindow();

		sendButton = new JButton(" Send ");
		sendButton.addActionListener(this);

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
		chatPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
		sendPanel.add(messageField, left);
		sendPanel.add(sendButton, right);
		chatPanel.add(BorderLayout.SOUTH, sendPanel);
		PRight.add(Box.createRigidArea(new Dimension(0,600)));

		PRight.add(chatPanel);

		chatPanel.setSize(275, 350);
		chatPanel.setVisible(true);
		
		// CHAT END



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


		// First five cards on the hand is being added to the JPanel
		//	PUCard.add(Box.createRigidArea(new Dimension(265,0)));
		//	for(int i=0; i<5; i++) {
		//		PUCard.add(PlayerCards[i]);
		//		PUCard.add(Box.createRigidArea(new Dimension(10,0)));
		//
		//	}
		//	PUCard.add(Box.createRigidArea(new Dimension(20,0)));

		// Last five cards on the hand is being added to the JPanel
		//		PUCard.add(Box.createRigidArea(new Dimension(10,0)));
		//		for(int i=0; i<10; i++) {
		//			PUCard.add(PlayerCards[i]);
		//			PUCard.add(Box.createRigidArea(new Dimension(10,0)));
		//		}
		//	PUCard.add(Box.createRigidArea(new Dimension(20,0)));

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

		getContentPane().add(PMid, BorderLayout.CENTER);
		PMid.add(PUMid,BorderLayout.NORTH);
		//		PMid.add(PUCard,BorderLayout.CENTER);
		//		PUMid.add(Box.createRigidArea(new Dimension(0,50)));
		PMid.add(Box.createRigidArea(new Dimension(1250,50)));
		PMid.add(PUCard,BorderLayout.CENTER);
		PMid.add(PDCard,BorderLayout.SOUTH	);

		PAll.add(PMid, BorderLayout.CENTER);
		PMid.setBackground(Color.WHITE);
		getContentPane().add(PAll);

		SwingUtilities.getRootPane(sendButton).setDefaultButton(sendButton);

	}

//	public void sendChatMessage(String username, String message) {
//		this.username = username;
//		this.message = message;
//		chatBox.append("<" + username + ">:  " + message + "\n");
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == sendButton ) {
			if (messageField.getText().length() < 1) {
				// DO NOTHING
			} else {
				chatBox.append("<" + "Yael" + ">:  " + messageField.getText() + "\n"); // Skal slettes efter chat-test er færdige
				// Nedenstående skal køre, når clienten skal forbindes.
				// Client.sendChatMessage(messageField.getText());
				messageField.setText("");
			}
			messageField.requestFocusInWindow();
		}

	}

}
