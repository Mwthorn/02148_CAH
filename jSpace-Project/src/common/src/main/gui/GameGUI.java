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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameGUI extends JFrame implements ActionListener {

	private JButton Card1, Card2, Card3, Card4, Card5, Card6, Card7, Card8, Card9, Card10;
	//	private List<JButton> Cards = new ArrayList<JButton>();

	public GameGUI() {
		
		getContentPane().setLayout(new BorderLayout());

		
		Card1 = new JButton("Some Text");
		Card1.setMaximumSize(new Dimension(150, 40));
//		Card1.setBackground(Color.white);
//		Card1.setForeground(Color.RED);
		Card1.addActionListener(this);
		Card1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card1.setFont(new Font("calibri",1,21));
		Card1.setBorderPainted(true);
		Card1.setFocusPainted(false);
		Card1.setEnabled(true);
		
		Card2 = new JButton("Some Text");
		Card2.setMaximumSize(new Dimension(150, 40));
//		Card2.setBackground(Color.white);
//		Card2.setForeground(Color.RED);
		Card2.addActionListener(this);
		Card2.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card2.setFont(new Font("calibri",1,21));
		Card2.setBorderPainted(true);
		Card2.setFocusPainted(false);
		Card2.setEnabled(true);
		
		Card3 = new JButton("Some Text");
		Card3.setMaximumSize(new Dimension(150, 40));
//		Card3.setBackground(Color.white);
//		Card3.setForeground(Color.RED);
		Card3.addActionListener(this);
		Card3.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card3.setFont(new Font("calibri",1,21));
		Card3.setBorderPainted(true);
		Card3.setFocusPainted(false);
		Card3.setEnabled(true);
		
		Card4 = new JButton("Some Text");
		Card4.setMaximumSize(new Dimension(150, 40));
		Card4.setBackground(Color.white);
		Card4.setForeground(Color.RED);
		Card4.addActionListener(this);
		Card4.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card4.setFont(new Font("calibri",1,21));
		Card4.setBorderPainted(true);
		Card4.setFocusPainted(false);
		Card4.setEnabled(true);
		
		Card5 = new JButton("Some Text");
		Card5.setMaximumSize(new Dimension(150, 40));
		Card5.setBackground(Color.white);
		Card5.setForeground(Color.RED);
		Card5.addActionListener(this);
		Card5.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card5.setFont(new Font("calibri",1,21));
		Card5.setBorderPainted(true);
		Card5.setFocusPainted(false);
		Card5.setEnabled(true);
		
		Card6 = new JButton("Some Text");
		Card6.setMaximumSize(new Dimension(150, 40));
		Card6.setBackground(Color.white);
		Card6.setForeground(Color.RED);
		Card6.addActionListener(this);
		Card6.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card6.setFont(new Font("calibri",1,21));
		Card6.setBorderPainted(true);
		Card6.setFocusPainted(false);
		Card6.setEnabled(true);
		
		Card7 = new JButton("Some Text");
		Card7.setMaximumSize(new Dimension(150, 40));
		Card7.setBackground(Color.white);
		Card7.setForeground(Color.RED);
		Card7.addActionListener(this);
		Card7.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card7.setFont(new Font("calibri",1,21));
		Card7.setBorderPainted(true);
		Card7.setFocusPainted(false);
		Card7.setEnabled(true);
		
		Card8 = new JButton("Some Text");
		Card8.setMaximumSize(new Dimension(150, 40));
		Card8.setBackground(Color.white);
		Card8.setForeground(Color.RED);
		Card8.addActionListener(this);
		Card8.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card8.setFont(new Font("calibri",1,21));
		Card8.setBorderPainted(true);
		Card8.setFocusPainted(false);
		Card8.setEnabled(true);
		
		Card9 = new JButton("Some Text");
		Card9.setMaximumSize(new Dimension(150, 40));
		Card9.setBackground(Color.white);
		Card9.setForeground(Color.RED);
		Card9.addActionListener(this);
		Card9.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card9.setFont(new Font("calibri",1,21));
		Card9.setBorderPainted(true);
		Card9.setFocusPainted(false);
		Card9.setEnabled(true);
		
		Card10 = new JButton("Some Text");
		Card10.setMaximumSize(new Dimension(150, 40));
		Card10.setBackground(Color.white);
		Card10.setForeground(Color.RED);
		Card10.addActionListener(this);
		Card10.setAlignmentX(Component.CENTER_ALIGNMENT);
		Card10.setFont(new Font("calibri",1,21));
		Card10.setBorderPainted(true);
		Card10.setFocusPainted(false);
		Card10.setEnabled(true);
		
	
		
		
		JPanel PMid = new JPanel();
		PMid.setLayout(new BoxLayout(PMid, BoxLayout.PAGE_AXIS));
		
		PMid.add(Card1);
		PMid.add(Box.createRigidArea(new Dimension(20,0)));
		PMid.add(Card2);
		PMid.add(Box.createRigidArea(new Dimension(20,0)));
		PMid.add(Card3);
////		PMid.add(Box.createRigidArea(new Dimension(20,0)));
////		PMid.add(Card4);
////		PMid.add(Box.createRigidArea(new Dimension(20,0)));
////		PMid.add(Card5);
////		PMid.add(Box.createRigidArea(new Dimension(20,0)));
////		PMid.add(Card6);
////		PMid.add(Box.createRigidArea(new Dimension(20,0)));
////		PMid.add(Card7);
////		PMid.add(Box.createRigidArea(new Dimension(20,0)));
////		PMid.add(Card8);
////		PMid.add(Box.createRigidArea(new Dimension(20,0)));
////		PMid.add(Card9);
////		PMid.add(Box.createRigidArea(new Dimension(20,0)));
////		PMid.add(Card10);
////		PMid.add(Box.createRigidArea(new Dimension(20,0)));
//
		getContentPane().add(PMid, BorderLayout.CENTER);
		PMid.setBackground(Color.RED);
		getContentPane().add(PMid);

		
		
		


	}

	public static void main(String[] args) {

		JFrame gamegui = new JFrame();

		gamegui.setTitle("Cards Against Humanity");
		gamegui.setSize(1200,720);
		gamegui.setResizable(true);
		gamegui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamegui.setVisible(true);
		gamegui.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
