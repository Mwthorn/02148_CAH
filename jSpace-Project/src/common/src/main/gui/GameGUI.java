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

		int FontSizeOfCards = 18;
		Dimension SizeOfPlayerCards = new Dimension(135,190);
		Border BorderForBlackCard = BorderFactory.createLineBorder(Color.BLACK, 1);


		// Czars black card of choice
		BlackCard = new JLabel("SOME TEXT");
		BlackCard.setMaximumSize(SizeOfPlayerCards);
		BlackCard.setFont(new Font("calibri",1,FontSizeOfCards));
		BlackCard.setBorder(BorderForBlackCard);
		BlackCard.setBackground(Color.BLACK);
		BlackCard.setForeground(Color.WHITE);
		BlackCard.setAlignmentX(Component.CENTER_ALIGNMENT);
		BlackCard.setEnabled(true);

		// Czars black card of choice
		BlackCard2 = new JLabel("SOME TEXT");
		BlackCard2.setMaximumSize(SizeOfPlayerCards);
		BlackCard2.setFont(new Font("calibri",1,FontSizeOfCards));
		BlackCard2.setBorder(BorderForBlackCard);
		BlackCard2.setBackground(Color.BLACK);
		BlackCard2.setForeground(Color.WHITE);
		BlackCard2.setAlignmentX(Component.CENTER_ALIGNMENT);
		BlackCard2.setEnabled(true);

		// Cards on players hand
		Card1 = new JButton("Some Text");
		Card1.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card1.setBackground(Color.white);
		Card1.setForeground(Color.BLACK);
		Card1.addActionListener(this);
		Card1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card1.setFont(new Font("calibri",1,FontSizeOfCards));
		Card1.setBorderPainted(true);
		Card1.setFocusPainted(false);
		Card1.setEnabled(true);

		Card2 = new JButton("Some Text");
		Card2.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card2.setBackground(Color.white);
		Card2.setForeground(Color.BLACK);
		Card2.addActionListener(this);
		Card2.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card2.setFont(new Font("calibri",1,FontSizeOfCards));
		Card2.setBorderPainted(true);
		Card2.setFocusPainted(false);
		Card2.setEnabled(true);

		Card3 = new JButton("Some Text");
		Card3.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card3.setBackground(Color.white);
		Card3.setForeground(Color.BLACK);
		Card3.addActionListener(this);
		Card3.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card3.setFont(new Font("calibri",1,FontSizeOfCards));
		Card3.setBorderPainted(true);
		Card3.setFocusPainted(false);
		Card3.setEnabled(true);

		Card4 = new JButton("Some Text");
		Card4.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card4.setBackground(Color.white);
		Card4.setForeground(Color.BLACK);
		Card4.addActionListener(this);
		Card4.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card4.setFont(new Font("calibri",1,FontSizeOfCards));
		Card4.setBorderPainted(true);
		Card4.setFocusPainted(false);
		Card4.setEnabled(true);

		Card5 = new JButton("Some Text");
		Card5.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card5.setBackground(Color.white);
		Card5.setForeground(Color.BLACK);
		Card5.addActionListener(this);
		Card5.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card5.setFont(new Font("calibri",1,FontSizeOfCards));
		Card5.setBorderPainted(true);
		Card5.setFocusPainted(false);
		Card5.setEnabled(true);

		Card6 = new JButton("Some Text");
		Card6.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card6.setBackground(Color.white);
		Card6.setForeground(Color.BLACK);
		Card6.addActionListener(this);
		Card6.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card6.setFont(new Font("calibri",1,FontSizeOfCards));
		Card6.setBorderPainted(true);
		Card6.setFocusPainted(false);
		Card6.setEnabled(true);

		Card7 = new JButton("Some Text");
		Card7.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card7.setBackground(Color.white);
		Card7.setForeground(Color.BLACK);
		Card7.addActionListener(this);
		Card7.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card7.setFont(new Font("calibri",1,FontSizeOfCards));
		Card7.setBorderPainted(true);
		Card7.setFocusPainted(false);
		Card7.setEnabled(true);

		Card8 = new JButton("Some Text");
		Card8.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card8.setBackground(Color.white);
		Card8.setForeground(Color.BLACK);
		Card8.addActionListener(this);
		Card8.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card8.setFont(new Font("calibri",1,FontSizeOfCards));
		Card8.setBorderPainted(true);
		Card8.setFocusPainted(false);
		Card8.setEnabled(true);

		Card9 = new JButton("Some Text");
		Card9.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card9.setBackground(Color.white);
		Card9.setForeground(Color.BLACK);
		Card9.addActionListener(this);
		Card9.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card9.setFont(new Font("calibri",1,FontSizeOfCards));
		Card9.setBorderPainted(true);
		Card9.setFocusPainted(false);
		Card9.setEnabled(true);

		Card10 = new JButton("Some Text");
		Card10.setMaximumSize(new Dimension(SizeOfPlayerCards));
		Card10.setBackground(Color.white);
		Card10.setForeground(Color.BLACK);
		Card10.addActionListener(this);
		Card10.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card10.setFont(new Font("calibri",1,FontSizeOfCards));
		Card10.setBorderPainted(true);
		Card10.setFocusPainted(false);
		Card10.setEnabled(true);



		JPanel PAll = new JPanel();

		PAll.setLayout(new BorderLayout());
		PAll.setSize(1200, 720);


		// Panel for the left side.
		JPanel PLeft = new JPanel();

		PLeft.setLayout(new BoxLayout(PLeft, BoxLayout.PAGE_AXIS));
		PLeft.setPreferredSize(new Dimension(240,800));
		PLeft.setBackground(Color.GRAY);
		PLeft.add(Box.createRigidArea(new Dimension(0,50)));
		PLeft.add(BlackCard);
		PAll.add(PLeft, BorderLayout.WEST);

		// Panel for the right side.
		JPanel PRight = new JPanel();

		PRight.setLayout(new BoxLayout(PRight, BoxLayout.PAGE_AXIS));
		PRight.setPreferredSize(new Dimension(240,800));
		PRight.setBackground(Color.GRAY);
		PRight.add(Box.createRigidArea(new Dimension(0,50)));
		PRight.add(BlackCard2);
		PAll.add(PRight, BorderLayout.EAST);


		// Panels for the center
		JPanel PMid = new JPanel();
		JPanel PUMid = new JPanel();
		JPanel PUCard = new JPanel();
		JPanel PDCard = new JPanel();

		PUMid.setLayout(new BoxLayout(PUMid, BoxLayout.PAGE_AXIS));
		PUMid.setLayout(new BoxLayout(PUMid, BoxLayout.PAGE_AXIS));
		PUCard.setLayout(new BoxLayout(PUCard, BoxLayout.LINE_AXIS));
		PDCard.setLayout(new BoxLayout(PDCard, BoxLayout.LINE_AXIS));
		PUMid.setPreferredSize(new Dimension(720,300));
		PUCard.setPreferredSize(new Dimension(720,190));
		PDCard.setPreferredSize(new Dimension(720,190));
		PUCard.setBackground(Color.WHITE);
		PDCard.setBackground(Color.WHITE);
		
		PUCard.add(Box.createRigidArea(new Dimension(20,0)));
		PUCard.add(Card1);
		PUCard.add(Card2);
		PUCard.add(Card3);
		PUCard.add(Card4);
		PUCard.add(Card5);

		PDCard.add(Box.createRigidArea(new Dimension(20,0)));
		PDCard.add(Card6);
		PDCard.add(Card7);
		PDCard.add(Card8);
		PDCard.add(Card9);
		PDCard.add(Card10);


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
