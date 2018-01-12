package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.src.main.client.Client;
import common.src.main.client.GamePreview;

@SuppressWarnings("serial")
public class ReadyUpLobby extends JFrame implements ActionListener {

	// ReadyUpLobby
	private JButton BReady, BLeave, ready1, ready2, ready3, ready4, ready5, ready6, ready7, ready8;
	private JLabel LHead, LPicWC, LPicBC, Label1, Label2, Label3, Label4, Label5, Label6, Label7, Label8;
	private static JList playerList;
	private boolean color1 = false;; 
	
	
	public static void main(String[] args) {

		ReadyUpLobby main = new ReadyUpLobby();

		main.setTitle("Cards Against Humanity");
		main.setSize(1900,1000);
		main.setResizable(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		main.setLocationRelativeTo(null);

	}

	public void changeColor(){

		if (color1 == true) {			
			ready1.setBackground(new Color(76,153,0));
			color1 = false;
			System.out.println("Changed to Green");

		} else {
			ready1.setBackground(Color.RED);
			color1 = true;
			System.out.println("Changed to Red");

		}
	}
	
	Dimension lsize = new Dimension(99, 50);

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


	public ReadyUpLobby(){

		getContentPane().setLayout(new BorderLayout()); //Default layout
		getContentPane().setBackground(Color.WHITE);

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
		LHead = new JLabel("Cards Against Humanity");
		LHead.setAlignmentX(Component.CENTER_ALIGNMENT);
		LHead.setFont(new Font("AR JULIAN",Font.PLAIN,70));
		LHead.setForeground(Color.BLACK);


		// Implementing pictures for white cards and black cards as JLabel
		LPicBC = new JLabel();
		LPicWC = new JLabel();
		LPicBC.setIcon(new ImageIcon(new ImageIcon("BCLobby.png").getImage().getScaledInstance(249, 381, Image.SCALE_DEFAULT)));
		LPicWC.setIcon(new ImageIcon(new ImageIcon("WCLobby.png").getImage().getScaledInstance(306, 556, Image.SCALE_DEFAULT)));
		LPicBC.setAlignmentX(Component.CENTER_ALIGNMENT);
		LPicWC.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Creates panel for buttons
		JPanel BtnPanel = new JPanel();

		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		BtnPanel.setLayout(flow);
		BtnPanel.setBackground(Color.WHITE);
		BtnPanel.add(BLeave);
		flow.setHgap(100);
		BtnPanel.add(BReady);
		BtnPanel.add(Box.createRigidArea(new Dimension(0,200)));

		getContentPane().add(BtnPanel, BorderLayout.SOUTH);

		// Panel for Title label
		JPanel HeadPanel = new JPanel();
		HeadPanel.setLayout(new BoxLayout(HeadPanel, BoxLayout.PAGE_AXIS));
		HeadPanel.setBackground(Color.WHITE);
		HeadPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		HeadPanel.add(LHead);

		// Panel for Black Card image
		JPanel BCPanel = new JPanel();
		BCPanel.setLayout(new BoxLayout(BCPanel, BoxLayout.LINE_AXIS));
		BCPanel.setBackground(Color.WHITE);
		BCPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		BCPanel.add(LPicBC);
		BCPanel.add(Box.createRigidArea(new Dimension(50, 0)));


		// Panel for White Card image
		JPanel WCPanel = new JPanel();
		WCPanel.setLayout(new BoxLayout(WCPanel, BoxLayout.LINE_AXIS));
		WCPanel.setBackground(Color.WHITE);
		WCPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		WCPanel.add(LPicWC);

		Dimension maxsize = new Dimension(1000, 75);

		// Middle JPanels
		JPanel middle = new JPanel();
		middle.setPreferredSize(new Dimension(1000, 656));
		middle.setBackground(Color.white);

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setPreferredSize(maxsize);
		p1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p1.setBackground(Color.WHITE);

		// Name Label
		Label1 = new JLabel("   "+"Jeff");
		Label1.setMaximumSize(lsize);
		Label1.setFont(new Font("calibri",Font.PLAIN,30));
		Label1.setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		ready1 = new JButton();
		ready1.setPreferredSize(new Dimension(75, 75));
		ready1.setBackground(Color.RED);
		ready1.setBorderPainted(false);
		ready1.setEnabled(false);

		p1.add(Box.createRigidArea(new Dimension(1000,12)));
		p1.add(Label1, BorderLayout.WEST);
		p1.add(ready1, BorderLayout.EAST);


		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.setPreferredSize(maxsize);
		p2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p2.setBackground(Color.WHITE);

		// Name Label
		Label2 = new JLabel("   "+"Jeff");
		Label2.setMaximumSize(lsize);
		Label2.setFont(new Font("calibri",Font.PLAIN,30));
		Label2.setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		JButton ready2 = new JButton();
		ready2.setPreferredSize(new Dimension(75, 75));
		ready2.setBackground(Color.RED);
		ready2.setBorderPainted(false);
		ready2.setEnabled(false);

		p2.add(Box.createRigidArea(new Dimension(1000,12)));
		p2.add(Label2, BorderLayout.WEST);
		p2.add(ready2, BorderLayout.EAST);


		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.setPreferredSize(maxsize);
		p3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p3.setBackground(Color.WHITE);

		// Name Label
		Label3 = new JLabel("   "+"Jeff");
		Label3.setMaximumSize(lsize);
		Label3.setFont(new Font("calibri",Font.PLAIN,30));
		Label3.setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		JButton ready3 = new JButton();
		ready3.setPreferredSize(new Dimension(75, 75));
		ready3.setBackground(Color.RED);
		ready3.setBorderPainted(false);
		ready3.setEnabled(false);

		p3.add(Box.createRigidArea(new Dimension(1000,12)));
		p3.add(Label3, BorderLayout.WEST);
		p3.add(ready3, BorderLayout.EAST);


		JPanel p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		p4.setPreferredSize(maxsize);
		p4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p4.setBackground(Color.WHITE);

		// Name Label
		Label4 = new JLabel("   "+"Jeff");
		Label4.setMaximumSize(lsize);
		Label4.setFont(new Font("calibri",Font.PLAIN,30));
		Label4.setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		JButton ready4 = new JButton();
		ready4.setPreferredSize(new Dimension(75, 75));
		ready4.setBackground(Color.RED);
		ready4.setBorderPainted(false);
		ready4.setEnabled(false);

		p4.add(Box.createRigidArea(new Dimension(1000,12)));
		p4.add(Label4, BorderLayout.WEST);
		p4.add(ready4, BorderLayout.EAST);


		JPanel p5 = new JPanel();
		p5.setLayout(new BorderLayout());
		p5.setPreferredSize(maxsize);
		p5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p5.setBackground(Color.WHITE);

		// Name Label
		Label5 = new JLabel("   "+"Jeff");
		Label5.setMaximumSize(lsize);
		Label5.setFont(new Font("calibri",Font.PLAIN,30));
		Label5.setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		JButton ready5 = new JButton();
		ready5.setPreferredSize(new Dimension(75, 75));
		ready5.setBackground(Color.RED);
		ready5.setBorderPainted(false);
		ready5.setEnabled(false);

		p5.add(Box.createRigidArea(new Dimension(1000,12)));
		p5.add(Label5, BorderLayout.WEST);
		p5.add(ready5, BorderLayout.EAST);


		JPanel p6 = new JPanel();
		p6.setLayout(new BorderLayout());
		p6.setPreferredSize(maxsize);
		p6.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p6.setBackground(Color.WHITE);

		// Name Label
		Label6 = new JLabel("   "+"Jeff");
		Label6.setMaximumSize(lsize);
		Label6.setFont(new Font("calibri",Font.PLAIN,30));
		Label6.setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		JButton ready6 = new JButton();
		ready6.setPreferredSize(new Dimension(75, 75));
		ready6.setBackground(Color.RED);
		ready6.setBorderPainted(false);
		ready6.setEnabled(false);

		p6.add(Box.createRigidArea(new Dimension(1000,12)));
		p6.add(Label6, BorderLayout.WEST);
		p6.add(ready6, BorderLayout.EAST);


		JPanel p7 = new JPanel();
		p7.setLayout(new BorderLayout());
		p7.setPreferredSize(maxsize);
		p7.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p7.setBackground(Color.WHITE);

		// Name Label
		Label7 = new JLabel("   "+"Jeff");
		Label7.setMaximumSize(lsize);
		Label7.setFont(new Font("calibri",Font.PLAIN,30));
		Label7.setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		JButton ready7 = new JButton();
		ready7.setPreferredSize(new Dimension(75, 75));
		ready7.setBackground(Color.RED);
		ready7.setBorderPainted(false);
		ready7.setEnabled(false);

		p7.add(Box.createRigidArea(new Dimension(1000,12)));
		p7.add(Label7, BorderLayout.WEST);
		p7.add(ready7, BorderLayout.EAST);


		JPanel p8 = new JPanel();
		p8.setLayout(new BorderLayout());
		p8.setPreferredSize(maxsize);
		p8.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		p8.setBackground(Color.WHITE);

		// Name Label
		Label8 = new JLabel("   "+"Jeff");
		Label8.setMaximumSize(lsize);
		Label8.setFont(new Font("calibri",Font.PLAIN,30));
		Label8.setAlignmentX(Component.CENTER_ALIGNMENT);

		//"Button" 
		JButton ready8 = new JButton();
		ready8.setPreferredSize(new Dimension(75, 75));
		ready8.setBackground(Color.RED);
		ready8.setBorderPainted(false);
		ready8.setEnabled(false);

		p8.add(Box.createRigidArea(new Dimension(1000,12)));
		p8.add(Label8, BorderLayout.WEST);
		p8.add(ready8, BorderLayout.EAST);


		middle.add(p1);
		middle.add(p2);
		middle.add(p3);
		middle.add(p4);
		middle.add(p5);
		middle.add(p6);
		middle.add(p7);
		middle.add(p8);


		getContentPane().add(HeadPanel, BorderLayout.NORTH);
		getContentPane().add(BCPanel, BorderLayout.EAST);
		getContentPane().add(WCPanel, BorderLayout.WEST);

		//		getContentPane().setVisible(false);

		JPanel playerPanel = new JPanel();

		playerPanel.setBackground(Color.WHITE);
		playerPanel.setAlignmentX(CENTER_ALIGNMENT);
		playerPanel.setAlignmentY(CENTER_ALIGNMENT);
		playerPanel.add(middle);
		//		playerPanel.add(scrollPane, BorderLayout.CENTER);

		getContentPane().add(playerPanel, BorderLayout.CENTER);


	}

	String name, IP;
	

	


	public void actionPerformed(ActionEvent e){

		if (e.getSource() == BReady) {

			changeColor();
			
		}

	}

}

