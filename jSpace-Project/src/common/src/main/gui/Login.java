package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Login extends JFrame {

	//Initialiserer buttons, labels og textareas
	private JButton b1, b2, b3, b7, b5, b6;
	private JLabel l1, l2, l3;
	private JTextArea text1 = new JTextArea(3,60);
	private JTextArea text2 = new JTextArea(3,60);
	private ImageIcon i1;
	
	public Login(){
		
		//Specifikke farver til de forskellige dele af GUI
				Color c1 = new Color(57,109,29);
				Color c2 = new Color(255,215,0);
				Color c3 = new Color(153,0,0);

				//Der benyttes BorderLayout
				getContentPane().setLayout(new BorderLayout());

				//Dimensionerne af de forskellige knapper og tekstfelter
				Dimension btnsize = new Dimension(150,150);
				Dimension btnsize1 = new Dimension(200,200);
				Dimension btnsize2 = new Dimension(140,70);
				Dimension txtsize = new Dimension(500,165);

				//"Start New Game" button
				//SÊtter en specifik size
				//∆ndrer mange design aspekter
				b1 = new JButton("Start New Game");
				b1.setMaximumSize(btnsize1);
				b1.setAlignmentX(Component.CENTER_ALIGNMENT);
				b1.setFont(new Font("Start New Game",1,21));
				b1.setBorderPainted(false);
				b1.setFocusPainted(false);
				b1.setEnabled(true);
				b1.setForeground(c2);
				b1.setBackground(c3);

				//Laver "Hit" Button
				b2 = new JButton("");
				b2.setMaximumSize(btnsize);
				b2.setAlignmentX(Component.CENTER_ALIGNMENT);
				b2.setFont(new Font("Hit",1,21));
				b2.setBorderPainted(false);
				b2.setFocusPainted(false);
				b2.setEnabled(false);
				b2.setForeground(c1);
				b2.setBackground(c1);

				//Laver "Stand" Button
				b3 = new JButton("");
				b3.setMaximumSize(btnsize);
				b3.setAlignmentX(Component.CENTER_ALIGNMENT);
				b3.setFont(new Font("Stand",1,21));
				b3.setBorderPainted(false);
				b3.setFocusPainted(false);
				b3.setEnabled(false);
				b3.setForeground(c2);
				b3.setBackground(c1);

				//Laver "Show Result" button
				b7 = new JButton("");
				b7.setMaximumSize(btnsize);
				b7.setAlignmentX(Component.CENTER_ALIGNMENT);
				b7.setFont(new Font("Show Result",1,21));
				b7.setBorderPainted(false);
				b7.setFocusPainted(false);
				b7.setEnabled(false);
				b7.setForeground(c2);
				b7.setBackground(c1);

				//Laver "Quit" Button
				b5 = new JButton("Quit");
				b5.setMaximumSize(btnsize2);
				b5.setAlignmentX(Component.CENTER_ALIGNMENT);
				b5.setFont(new Font("Quit",1,21));
				b5.setBorderPainted(false);
				b5.setFocusPainted(false);
				b5.setEnabled(true);
				b5.setForeground(Color.WHITE);
				b5.setBackground(Color.black);

				//Laver "Rules" Button
				b6 = new JButton("Rules");
				b6.setMaximumSize(btnsize1);
				b6.setAlignmentX(Component.CENTER_ALIGNMENT);
				b6.setFont(new Font("Rules",1,21));
				b6.setBorderPainted(false);
				b6.setFocusPainted(false);
				b6.setEnabled(true);
				b6.setForeground(c2);
				b6.setBackground(c3);

				//Laver det store Blackjack label
				l1 = new JLabel("Cards Against Humanity");
				l1.setAlignmentX(Component.CENTER_ALIGNMENT);
				l1.setFont(new Font("AR JULIAN",Font.PLAIN,50));
				l1.setForeground(Color.BLACK);

				//Laver Dealer label
				l2 = new JLabel("Welcome to Cards Against Humanity");
				l2.setAlignmentX(Component.CENTER_ALIGNMENT);
				l2.setFont(new Font("calibri",Font.PLAIN,30));
				l2.setForeground(Color.black);

				//Laver Player label
				//l3 = new JLabel("Player");
				//l3.setAlignmentX(Component.CENTER_ALIGNMENT);
				//l3.setFont(new Font("Player",5,30));
				//l3.setForeground(c3);

				//Laver det f¯rste tekstfelt til Dealeren
				//G¯r sÂ man ikke kan Êndre i tekstfeltet, samt st¯rrelse
				text1 = new JTextArea("Welcome to Cards Against Humanity!");
				text1.setMaximumSize(txtsize);
				text1.setLineWrap(true);
				text1.setEditable(false);
				text1.setAlignmentX(Component.CENTER_ALIGNMENT);
				text1.setFont(new Font("Player",1,21));
				text1.setBackground(c1);
				text1.setForeground(c2);

				//Laver tesktfelt nummer 2
				text2 = new JTextArea("This is new text you haven't seen before!");
				text2.setMaximumSize(txtsize);
				text2.setLineWrap(true);
				text2.setEditable(false);
				text2.setAlignmentX(Component.CENTER_ALIGNMENT);
				text2.setFont(new Font("Player",1,21));
				text2.setBackground(Color.white);
				text2.setForeground(Color.black);

				//Laver det f¯rste JPanel, som skal vÊre i venstre side
				JPanel p1 = new JPanel();
				p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
				p1.setBackground(Color.white);

				//Laver det andret JPanel, som skal vÊre i h¯jre side
				JPanel p2 = new JPanel();
				p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
				p2.setBackground(Color.white);
				
				

				i1 = new ImageIcon("BCLogin.PNG");
				l3 = new JLabel(i1);
				
				//Left JPanel med Start New Game, Rules og Quit button
				//Laver ogsÂ mellemrum mellem knapperne
				p1.add(Box.createRigidArea(new Dimension(100,100)));
				p1.add(l3);
				p1.add(Box.createRigidArea(new Dimension(100,100)));

				//Right JPanel med Hit, Stand og Show Result
				p2.add(Box.createRigidArea(new Dimension(400,600)));
				p2.add(b5);
				p2.add(Box.createRigidArea(new Dimension(400,50)));

				//Siger hvor de forskellige Paneler skal vÊre
				getContentPane().add(p1, BorderLayout.WEST);
				getContentPane().add(p2, BorderLayout.EAST);

				
				
				JPanel p3 = new JPanel();
				p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
				p3.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
				p3.setBackground(Color.BLACK);

				
				
				p3.add(text2);
				
				//Laver Center JPanel med de forskellige labels og tekstfelter
				JPanel p7 = new JPanel();
				p7.setLayout(new BoxLayout(p7, BoxLayout.PAGE_AXIS));
				p7.add(l1);
				p7.add(Box.createRigidArea(new Dimension(320, 80)));
				p7.add(l2);
				p7.add(Box.createRigidArea(new Dimension(320, 80)));
				p7.add(p3);
				
				
				//SÊtter det til center
				getContentPane().add(p7, BorderLayout.CENTER);

				p7.setBackground(Color.WHITE);
				getContentPane().add(p7);
		
		
	}
	
	
	
	
	public static void main(String[] args) {
	
		Login game = new Login();

		game.setTitle("Cards Against Humanity");
		game.setSize(1300,710);
		game.setResizable(true);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
		game.setLocationRelativeTo(null);
		
	}
	
}

