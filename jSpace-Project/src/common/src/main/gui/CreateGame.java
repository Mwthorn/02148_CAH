package common.src.main.gui;

import common.src.main.client.Client;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;

public class CreateGame extends JFrame implements ActionListener {

	//Initialize Buttons, Labels, Images and Textfields.
	private JButton BQuit, BSignIn, BCreateGame;
	private JLabel LTitle, LText, LFigure1, LName, LIP, LFigure2, LRounds, LTime, LPass, LPassword;
	private JTextField txtfld1, txtfld2, txtfld3, txtfld4, txtfld5;
	
	
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
	
	public CreateGame(){

		//Der benyttes BorderLayout
		getContentPane().setLayout(new BorderLayout());

		// Dimensions of buttons
		Dimension btnsize4 = new Dimension(110,40);
		Dimension btnsize2 = new Dimension(120,60);

		//Textfield laves
		Dimension txtfldsize = new Dimension(300, 30);
		
		//Labels
		Dimension lsize = new Dimension(200,30);
		
		// Text font size:
		int fontsize = 25;
		
		// Make Quit Button
		BQuit = new JButton("Back");
		BQuit.setMaximumSize(btnsize2);
		BQuit.addActionListener(this);
		BQuit.setBorder(new RoundedBorder(30));
		//BQuit.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));		
		BQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
		BQuit.setFont(new Font("calibri",1,21));
		BQuit.setBorderPainted(true);
		BQuit.setFocusPainted(false);
		BQuit.setEnabled(true);
		BQuit.setForeground(Color.BLACK);
		BQuit.setBackground(Color.WHITE);

		// Make Sign In button
		BCreateGame = new JButton("Create Game");
		BCreateGame.setMinimumSize(new Dimension(250,100));
		BCreateGame.setMaximumSize(new Dimension(250,100));
		BCreateGame.setBorder(new RoundedBorder(30));
		//BCreateGame.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));		
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
		txtfld1 = new JTextField(50);
		txtfld1.setMaximumSize(txtfldsize);
		txtfld1.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld1.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Name textfield
		txtfld2 = new JTextField(50);
		txtfld2.setMaximumSize(txtfldsize);
		txtfld2.setFont(new Font("calibri",Font.PLAIN,20));
		txtfld2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
		PR.add(txtfld1);
		PR.add(Box.createRigidArea(new Dimension(350,50)));
		PR.add(txtfld2);
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
		PMiddle.add(LText);
		PMiddle.add(Box.createRigidArea(new Dimension(100, 50)));
		PMiddle.add(PC);
		PMiddle.add(Box.createRigidArea(new Dimension(400, 100)));
		PMiddle.add(BCreateGame);
		PMiddle.add(Box.createRigidArea(new Dimension(400, 400)));				
				
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

