package common.src.main.gui;

import common.src.main.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;

public class CreateGame extends JFrame implements ActionListener {

	//Initialize Buttons, Labels, Images and Textfields.
	private JButton BQuit, BSignIn;
	private JLabel LTitle, LText, LFigure1, LName, LIP, LFigure2;
	private JTextField txtfld1, txtfld2;
	
	public CreateGame(){

		//Der benyttes BorderLayout
		getContentPane().setLayout(new BorderLayout());

		// Dimensions of buttons
		Dimension btnsize4 = new Dimension(110,40);
		Dimension btnsize2 = new Dimension(120,60);

		//Textfield laves
		Dimension txtfldsize = new Dimension(400, 30);
		
		//Labels
		Dimension lsize = new Dimension(99, 50);
		
		// Make Quit Button
		BQuit = new JButton("Quit");
		BQuit.setMaximumSize(btnsize2);
		BQuit.addActionListener(this);
		BQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
		BQuit.setFont(new Font("calibri",1,21));
		BQuit.setBorderPainted(false);
		BQuit.setFocusPainted(false);
		BQuit.setEnabled(true);
		BQuit.setForeground(Color.WHITE);
		BQuit.setBackground(Color.black);

		// Make Sign In button
		BSignIn = new JButton("Sign In");
		BSignIn.setMaximumSize(btnsize4);
		BSignIn.addActionListener(this);
		BSignIn.setAlignmentX(Component.CENTER_ALIGNMENT);
		BSignIn.setFont(new Font("calibri",1,21));
		BSignIn.setBorderPainted(false);
		BSignIn.setFocusPainted(false);
		BSignIn.setEnabled(true);
		BSignIn.setForeground(Color.WHITE);
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
		
		
		
		
		//Left JPanel
		JPanel PCenter = new JPanel();
		PCenter.setLayout(new GridLayout(0, 2));
		
		PCenter.add(BQuit);
		
		
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
		PRight.add(Box.createRigidArea(new Dimension(365,200)));
		PRight.add(BQuit);
		PRight.add(Box.createRigidArea(new Dimension(365,50)));

		//Siger hvor de forskellige Paneler skal vÊre
		getContentPane().add(PLeft, BorderLayout.WEST);
		getContentPane().add(PRight, BorderLayout.EAST);
		
		//Laver Center JPanel med de forskellige labels og tekstfelter
		JPanel PMiddle = new JPanel();
		PMiddle.setLayout(new BoxLayout(PMiddle, BoxLayout.PAGE_AXIS));
		PMiddle.setSize(400, 1000);
		PMiddle.add(LTitle);
		PMiddle.add(Box.createRigidArea(new Dimension(100, 100)));
		PMiddle.add(PCenter);
		PMiddle.add(Box.createRigidArea(new Dimension(400, 150)));
		PMiddle.add(Box.createRigidArea(new Dimension(400, 800)));				
				
		//Add PMiddle and set center
		getContentPane().add(PMiddle, BorderLayout.CENTER);

		PMiddle.setBackground(Color.WHITE);
		getContentPane().add(PMiddle);
		
		
	}
	
	String name;
	String IP;
	
	public void actionPerformed(ActionEvent e){

		/////////////////////////////////////////////////////////////////
		// Quit button closes game
		if(e.getSource() == BQuit){
			
			System.exit(0);
			
		} else if (e.getSource() == BSignIn){
			this.name = txtfld1.getText();
			this.IP = txtfld2.getText();
			
			System.out.println(name);
			System.out.println(IP);
			
			try {
				
				Client.loginUser(IP, name);
			
			} catch (SocketException e1) {
				txtfld2.setText("Incorrect IP");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {				
				e1.printStackTrace();
			}
		}
	}
	
//	public String getName() {
//		return name;
//	}
//	
//	public String getIP() {
//		return IP;
//	}
	
	
	public static void main(String[] args) {
	
		CreateGame game = new CreateGame();

		game.setTitle("Cards Against Humanity");
		game.setSize(1900,1000);
		game.setResizable(true);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
		game.setLocationRelativeTo(null);
	}
}

