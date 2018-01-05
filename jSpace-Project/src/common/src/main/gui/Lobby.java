package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by Mwthorn on 04-01-2018.
 */
public class Lobby extends JFrame implements ActionListener{
	
	private JButton b1, b2;
	private JLabel l1,l2,l3,l4;
	
	
	
	public Lobby(){
		getContentPane().setLayout(new BorderLayout()); //Default layout
		
		
		//Create buttons
		Dimension btnsize1 = new Dimension(150,75);
		Dimension btnsize2 = new Dimension(150,55);
		
		b1 = new JButton("Create Game");
		b1.addActionListener(this);
		b1.setMaximumSize(btnsize1);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		b2 = new JButton("Sign Out");
		b2.addActionListener(this);
		b2.setMaximumSize(btnsize2);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Create label
		l1 = new JLabel("Cards Against Humanity");
		l1.setFont(new Font("Cards Against Humanity",Font.ITALIC,40));
		l1.setForeground(Color.BLACK);
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		l1.setBorder(border);
	    l1.setPreferredSize(new Dimension(150, 100));
		
		
		l2 = new JLabel("If you can't handle\n ______________,\nyou'd better stay\naway from\n____________.");
		l2.setAlignmentX(Component.CENTER_ALIGNMENT);
		//l2.setFont(new Font("Cards Against Humanity",10,40));
		l2.setForeground(Color.WHITE);
		l2.setBackground(Color.BLACK);
		
		/*
		l3 = new JLabel("Cards Against Humanity");
		l3.setAlignmentX(Component.CENTER_ALIGNMENT);
		l3.setFont(new Font("Cards Against Humanity",10,40));
		l3.setForeground(Color.BLACK);
		
		l4 = new JLabel("Cards Against Humanity");
		l4.setAlignmentX(Component.CENTER_ALIGNMENT);
		l4.setFont(new Font("Cards Against Humanity",10,40));
		l4.setForeground(Color.BLACK);
		*/
		
		//Creates panel for buttons
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		p1.setMaximumSize(p1.getPreferredSize());
		p1.setBackground(Color.WHITE);
		p1.add(Box.createRigidArea(new Dimension(50,490)));
		p1.add(b1);
		
		p2.add(Box.createRigidArea(new Dimension(50,510)));
		p2.setMaximumSize(p2.getPreferredSize());
		p2.setBackground(Color.WHITE);
		p2.add(b2);
		
		getContentPane().add(p1, BorderLayout.EAST);
		getContentPane().add(p2, BorderLayout.WEST);
		
		//Creates panel for label
		JPanel p3 = new JPanel();
		//JPanel p4 = new JPanel();
		
		p3.setBackground(Color.BLACK);
		p3.add(l1);
		p3.setPreferredSize(new Dimension(0, 100));
        p3.setMaximumSize(p3.getPreferredSize()); 
        p3.setMinimumSize(p3.getPreferredSize());
		
		
		/*
		p4.add(Box.createRigidArea(new Dimension(50,300)));
		p4.add(l1);
		p4.setBackground(Color.WHITE);
		p4.setSize(200, 200);
		*/
		
		getContentPane().add(p3, BorderLayout.NORTH);
		//getContentPane().add(p4, BorderLayout.EAST);
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()==b1){
			
		}
		
		if(e.getSource()==b2){
			
		}
		
	}
	
	public static void main(String[] args) {
		Lobby lobby = new Lobby();
			
		lobby.setSize(900, 600);
		lobby.setLocationRelativeTo(null);
		lobby.setDefaultCloseOperation(EXIT_ON_CLOSE);
		lobby.setTitle("Cards Against Humanity");
		lobby.setResizable(true);
		lobby.setVisible(true);
		
	}

}
