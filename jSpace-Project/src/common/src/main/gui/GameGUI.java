package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class GameGUI extends JFrame implements ActionListener {

	private JTextArea BlackCard, BlackCard2;
	private String[] ChosenCards = new String[8];
	private int numberOfPlayers = 8;
	private int numberOfCards = 3;
	private JButton[] PlayerCards = new JButton[10];
	private JButton[] Winner = new JButton[ChosenCards.length];
	private JButton[] ChosCard1 = new JButton[ChosenCards.length];
	private JButton[] ChosCard2 = new JButton[ChosenCards.length];
	private JButton[] ChosCard3 = new JButton[ChosenCards.length];
	private JTextArea[] area = new JTextArea[10];




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
		BlackCard2 = new JTextArea("SOME TEXT");
		BlackCard2.setMaximumSize(SizeOfBlackCards);
		BlackCard2.setFont(new Font("calibri",1,FontSizeOfCards));
		BlackCard2.setBorder(BorderForCards);
		BlackCard2.setBackground(Color.BLACK);
		BlackCard2.setForeground(Color.WHITE);
		BlackCard2.setAlignmentX(Component.CENTER_ALIGNMENT);
		BlackCard2.setEnabled(true);

		//		StyleContext context = new StyleContext();
		//	    StyledDocument document = new DefaultStyledDocument(context);
		//
		//	    Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
		//
		//	    StyleConstants.setLeftIndent(style, 16);
		//	    
		//	    document.insertString(document.getLength(), "java2s.com", style);

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
			PlayerCards[i].setBackground(Color.white);
			PlayerCards[i].setForeground(Color.BLACK);
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
			Winner[i].setBackground(Color.white);
			Winner[i].setForeground(Color.BLACK);
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
				ChosCard1[i] = new JButton("Some Text"+i);
				ChosCard1[i].setPreferredSize(SizeOfChosenCards);
				ChosCard1[i].setFont(new Font("calibri",1,FontSizeOfChosenCards));
				ChosCard1[i].setBorder(BorderForCards);
				ChosCard1[i].setBackground(Color.WHITE);
				ChosCard1[i].setForeground(Color.BLACK);
				ChosCard1[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				ChosCard1[i].setBorderPainted(true);
				ChosCard1[i].setFocusPainted(false);
				ChosCard1[i].setEnabled(true);

			}
		}

		if(numberOfCards > 1) {

			for(int i=0; i<ChosenCards.length; i++){
				ChosCard2[i] = new JButton("Some Text"+i);
				ChosCard2[i].setPreferredSize(SizeOfChosenCards);
				ChosCard2[i].setFont(new Font("calibri",1,FontSizeOfChosenCards));
				ChosCard2[i].setBorder(BorderForCards);
				ChosCard2[i].setBackground(Color.WHITE);
				ChosCard2[i].setForeground(Color.BLACK);
				ChosCard2[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				ChosCard2[i].setBorderPainted(true);
				ChosCard2[i].setFocusPainted(false);
				ChosCard2[i].setEnabled(true);

			}
		}

		if(numberOfCards > 2) {

			for(int i=0; i<ChosenCards.length; i++){
				ChosCard3[i] = new JButton("Some Text"+i);
				ChosCard3[i].setPreferredSize(SizeOfChosenCards);
				ChosCard3[i].setFont(new Font("calibri",1,FontSizeOfChosenCards));
				ChosCard3[i].setBorder(BorderForCards);
				ChosCard3[i].setBackground(Color.WHITE);
				ChosCard3[i].setForeground(Color.BLACK);
				ChosCard3[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				ChosCard3[i].setBorderPainted(true);
				ChosCard3[i].setFocusPainted(false);
				ChosCard3[i].setEnabled(true);

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
		PLeft.setBackground(Color.GRAY);
		PLeft.add(Box.createRigidArea(new Dimension(0,50)));
		PLeft.add(card1);
		PLeft.add(Box.createRigidArea(new Dimension(0,50)));
		PAll.add(PLeft, BorderLayout.WEST);

		// Panel for the right side.
		JPanel PRight = new JPanel();

		PRight.setLayout(new BoxLayout(PRight, BoxLayout.PAGE_AXIS));
		PRight.setPreferredSize(new Dimension(300,1000));
		PRight.setBackground(Color.GRAY);
		PRight.add(Box.createRigidArea(new Dimension(0,50)));
		PRight.add(BlackCard2);
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
		PUMid.setPreferredSize(new Dimension(1250,620));
		PUCard.setPreferredSize(new Dimension(1250,190));
		PDCard.setPreferredSize(new Dimension(1250,40));


		toptop.setPreferredSize(size2);
		top.setPreferredSize(size);

		topmiddle.setPreferredSize(size3);

		mid.setPreferredSize(size);
		low.setPreferredSize(size);

		PUCard.setBackground(Color.WHITE);
		PDCard.setBackground(Color.WHITE);



		//Chosen card being added to panel
		//		PUMid.add(Box.createRigidArea(new Dimension(90,0)));
		for(int i=0; i<ChosenCards.length; i++) {
			toptop.add(Winner[i]);
			toptop.add(Box.createRigidArea(new Dimension(10,0)));

		}

		if(numberOfCards > 0){
			for(int i=0; i<ChosenCards.length; i++) {
				top.add(ChosCard1[i]);
				top.add(Box.createRigidArea(new Dimension(10,0)));

			}
		}

		if(numberOfCards > 1){
			for(int i=0; i<ChosenCards.length; i++) {
				mid.add(ChosCard2[i]);
				mid.add(Box.createRigidArea(new Dimension(10,0)));

			}
		}

		if(numberOfCards > 2){
			for(int i=0; i<ChosenCards.length; i++) {
				low.add(ChosCard3[i]);
				low.add(Box.createRigidArea(new Dimension(10,0)));

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

		top.setBackground(Color.WHITE);
		mid.setBackground(Color.WHITE);
		low.setBackground(Color.WHITE);


		//		top.add(PUMid);

		toptop.setBackground(Color.WHITE);
		top.setBackground(Color.WHITE);

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

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
