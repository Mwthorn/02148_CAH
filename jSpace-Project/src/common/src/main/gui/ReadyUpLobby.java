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
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.src.main.client.Client;
import common.src.main.client.GamePreview;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReadyUpLobby extends JFrame implements ActionListener{

	// ReadyUpLobby
	private JButton BReady, BLeave;
	private JLabel LHead, LPicWC, LPicBC;
	private static JList playerList;


	

	JPanel mainReadyUpLobby = new JPanel();

	public ReadyUpLobby(){

		setTitle("Cards Against Humanity");
		setSize(1900,1000);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setSize(1900,1000);

		runReadyUpLobby();
	}

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

	/////////////////////////////////////////////// READYUPLOBBY //////////////////////////////////////////////////////////////////

	public void runReadyUpLobby(){
		mainReadyUpLobby.setLayout(new BorderLayout()); //Default layout
		mainReadyUpLobby.setBackground(Color.WHITE);

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

		// Create List
		DefaultListModel model = new DefaultListModel();
		playerList = new JList(model);

		JScrollPane scrollPane = new JScrollPane(playerList);

		scrollPane.setPreferredSize(new Dimension(1000, 650));

		playerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		playerList.setVisibleRowCount(-1);
		playerList.setLayoutOrientation(JList.VERTICAL);
		//		playerList.ensureIndexIsVisible(list.getSelectedIndex());
		playerList.setFont(new Font("calibri",Font.PLAIN,25));
		playerList.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));

		String name = "Jonas";
		String status = "WAITING";
		Boolean lock = true;
		int current = 7;
		int max = 8;
		int id = 4738920;
		String islocked = "";

		String blank = "          ";

		for (int i = 0; i < 15; i++) {

			if (lock == true) {
				islocked = "LOCKED";
			} else {
				islocked = "      ";
			}

			model.addElement(name+blank+status+blank+islocked+blank+Integer.toString(current)+"/"+Integer.toString(max));

		}

		//Creates panel for buttons
		JPanel BtnPanel = new JPanel();

		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		BtnPanel.setLayout(flow);
		BtnPanel.setBackground(Color.WHITE);
		BtnPanel.add(BLeave);
		flow.setHgap(100);
		BtnPanel.add(BReady);
		BtnPanel.add(Box.createRigidArea(new Dimension(0,200)));

		mainReadyUpLobby.add(BtnPanel, BorderLayout.SOUTH);

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

		mainReadyUpLobby.add(HeadPanel, BorderLayout.NORTH);
		mainReadyUpLobby.add(BCPanel, BorderLayout.EAST);
		mainReadyUpLobby.add(WCPanel, BorderLayout.WEST);

		JPanel playerPanel = new JPanel();

		playerPanel.setBackground(Color.WHITE);
		playerPanel.setAlignmentX(CENTER_ALIGNMENT);
		playerPanel.setAlignmentY(CENTER_ALIGNMENT);
		playerPanel.add(scrollPane, BorderLayout.CENTER);

		mainReadyUpLobby.add(playerPanel, BorderLayout.CENTER);

		getContentPane().add(mainReadyUpLobby, BorderLayout.CENTER);
		
	}
	/////////////////////////////////////////////// READYUPLOBBY //////////////////////////////////////////////////////////////////


	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==BLeave){
		}

	}
	
	public static void main(String[] args) {

		ReadyUpLobby main = new ReadyUpLobby();

	}

}
