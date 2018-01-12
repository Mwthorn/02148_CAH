package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class GameGUI extends JFrame implements ActionListener {

	private JButton Card1, Card2, Card3, Card4, Card5, Card6, Card7, Card8, Card9, Card10;
	//	private List<JButton> Cards = new ArrayList<JButton>();
	private JLabel BlackCard,BlackCard2;
	String[] ChosenCards = new String[8];
	private JButton[] PlayerCards = new JButton[10];
	private JLabel[] ChosCard = new JLabel[ChosenCards.length];


	public static void main(String[] args) {

		GameGUI main = new GameGUI();

		main.setTitle("Cards Against Humanity");
		main.setSize(1200,720);
		main.setResizable(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		main.setLocationRelativeTo(null);

	}

	public GameGUI(){	

		getContentPane().setLayout(new BorderLayout());	

		int FontSizeOfCards = 16;
		int FontSizeOfChosenCards = 14;
		Dimension SizeOfPlayerCards = new Dimension(130,190);
		Dimension SizeOfBlackCards = new Dimension(180,240);
		Dimension SizeOfChosenCards = new Dimension(100,170);
		Border BorderForCards = BorderFactory.createLineBorder(Color.BLACK, 1);



		if(ChosenCards.length<9){

			for(int i=0; i<ChosenCards.length; i++){
				ChosCard[i] = new JLabel("Some Text"+i);

				ChosCard[i].setMaximumSize(SizeOfChosenCards);
				ChosCard[i].setFont(new Font("calibri",1,FontSizeOfChosenCards));
				ChosCard[i].setBorder(BorderForCards);
				ChosCard[i].setBackground(Color.WHITE);
				ChosCard[i].setForeground(Color.BLACK);
				ChosCard[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				ChosCard[i].setEnabled(true);	

			}
		}
		else if(ChosenCards.length<17){

			for(int i=0; i<ChosenCards.length; i++){
				ChosCard[i] = new JLabel("Some Text"+i);

				ChosCard[i].setMaximumSize(SizeOfChosenCards);
				ChosCard[i].setFont(new Font("calibri",1,FontSizeOfChosenCards));
				ChosCard[i].setBorder(BorderForCards);
				ChosCard[i].setBackground(Color.WHITE);
				ChosCard[i].setForeground(Color.BLACK);
				ChosCard[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				ChosCard[i].setEnabled(true);
			}
		}
		else if(ChosenCards.length<25){

			for(int i=0; i<ChosenCards.length; i++){
				ChosCard[i] = new JLabel("Some Text"+i);

				ChosCard[i].setMaximumSize(SizeOfChosenCards);
				ChosCard[i].setFont(new Font("calibri",1,FontSizeOfChosenCards));
				ChosCard[i].setBorder(BorderForCards);
				ChosCard[i].setBackground(Color.WHITE);
				ChosCard[i].setForeground(Color.BLACK);
				ChosCard[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				ChosCard[i].setEnabled(true);


			}
		}


		// Czars black card of choice
		BlackCard = new JLabel("SOME TEXT");
		BlackCard.setMaximumSize(SizeOfBlackCards);
		BlackCard.setFont(new Font("calibri",1,FontSizeOfCards));
		BlackCard.setBorder(BorderForCards);
		BlackCard.setBackground(Color.BLACK);
		BlackCard.setForeground(Color.WHITE);
		BlackCard.setAlignmentX(Component.CENTER_ALIGNMENT);
		BlackCard.setEnabled(true);

		// Czars black card of choice used to fill out
		BlackCard2 = new JLabel("SOME TEXT");
		BlackCard2.setMaximumSize(SizeOfBlackCards);
		BlackCard2.setFont(new Font("calibri",1,FontSizeOfCards));
		BlackCard2.setBorder(BorderForCards);
		BlackCard2.setBackground(Color.BLACK);
		BlackCard2.setForeground(Color.WHITE);
		BlackCard2.setAlignmentX(Component.CENTER_ALIGNMENT);
		BlackCard2.setEnabled(true);
		
		
		// Cards on players hand is being created
		if(ChosenCards.length<9){

			for(int i=0; i<10; i++){
				PlayerCards[i] = new JButton("Some Text"+i);

				PlayerCards[i].setMaximumSize(SizeOfChosenCards);
				PlayerCards[i].setMaximumSize(new Dimension(SizeOfPlayerCards));
				PlayerCards[i].setBackground(Color.white);
				PlayerCards[i].setForeground(Color.BLACK);
				PlayerCards[i].addActionListener(this);
				PlayerCards[i].setAlignmentX(Component.CENTER_ALIGNMENT);
				PlayerCards[i].setFont(new Font("calibri",1,FontSizeOfCards));
				PlayerCards[i].setBorderPainted(true);
				PlayerCards[i].setFocusPainted(false);
				PlayerCards[i].setEnabled(true);	

			}
		}
		


		JPanel PAll = new JPanel();

		PAll.setLayout(new BorderLayout());
		PAll.setSize(1200, 720);


		// Panel for the left side.
		JPanel PLeft = new JPanel();

		PLeft.setLayout(new BoxLayout(PLeft, BoxLayout.PAGE_AXIS));
		PLeft.setPreferredSize(new Dimension(200,800));
		PLeft.setBackground(Color.GRAY);
		PLeft.add(Box.createRigidArea(new Dimension(0,50)));
		PLeft.add(BlackCard);
		PAll.add(PLeft, BorderLayout.WEST);

		// Panel for the right side.
		JPanel PRight = new JPanel();

		PRight.setLayout(new BoxLayout(PRight, BoxLayout.PAGE_AXIS));
		PRight.setPreferredSize(new Dimension(200,800));
		PRight.setBackground(Color.GRAY);
		PRight.add(Box.createRigidArea(new Dimension(0,50)));
		PRight.add(BlackCard2);
		PAll.add(PRight, BorderLayout.EAST);


		// Panels for the center
		JPanel PMid = new JPanel();
		JPanel PUMid = new JPanel();
		JPanel PUCard = new JPanel();
		JPanel PDCard = new JPanel();

		PUMid.setLayout(new BoxLayout(PUMid, BoxLayout.LINE_AXIS));
		PUCard.setLayout(new BoxLayout(PUCard, BoxLayout.LINE_AXIS));
		PDCard.setLayout(new BoxLayout(PDCard, BoxLayout.LINE_AXIS));
		PUMid.setPreferredSize(new Dimension(800,300));
		PUCard.setPreferredSize(new Dimension(720,190));
		PDCard.setPreferredSize(new Dimension(720,190));
		PUCard.setBackground(Color.WHITE);
		PDCard.setBackground(Color.WHITE);

		if(ChosenCards.length<9){
			for(int i=0; i<ChosenCards.length;i++) {
				PUMid.add(ChosCard[i]);
			}
		}
		
		// First five cards on the hand is being added to the JPanel
		PUCard.add(Box.createRigidArea(new Dimension(20,0)));
		for(int i=0; i<5; i++) {
			PUCard.add(PlayerCards[i]);
		}
		PUCard.add(Box.createRigidArea(new Dimension(20,0)));
		
		// Last five cards on the hand is being added to the JPanel
		PDCard.add(Box.createRigidArea(new Dimension(20,0)));
		for(int i=5; i<10; i++) {
			PDCard.add(PlayerCards[i]);
		}
		PDCard.add(Box.createRigidArea(new Dimension(20,0)));


		getContentPane().add(PMid, BorderLayout.CENTER);
		PUMid.add(Box.createRigidArea(new Dimension(0,100)));
		PMid.add(PUMid,BorderLayout.NORTH);
		PMid.add(PUCard,BorderLayout.CENTER);
		PUMid.add(Box.createRigidArea(new Dimension(0,50)));
		PMid.add(PDCard,BorderLayout.SOUTH);

		PAll.add(PMid, BorderLayout.CENTER);
		PMid.setBackground(Color.WHITE);
		getContentPane().add(PAll);



	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
