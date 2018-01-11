import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.src.main.gui.MainGUI.RoundedBorder;

//Default layout
		mainLobby.setBackground(Color.WHITE);


		//Create buttons
		Dimension btnsize2 = new Dimension(180,70);

		// Create game button
		b1 = new JButton("Create Game");
		b1.setPreferredSize(btnsize2);
		b1.setBackground(Color.white);
		b1.setForeground(Color.black);
		b1.addActionListener(this);
		b1.setBorder(new RoundedBorder(30));
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setFont(new Font("calibri",1,21));
		b1.setBorderPainted(true);
		b1.setFocusPainted(false);
		b1.setEnabled(true);

		// Sign out button
		b2 = new JButton("Sign Out");
		b2.setPreferredSize(btnsize2);
		b2.setBorder(new RoundedBorder(30));
		b2.setBackground(Color.white);
		b2.setForeground(Color.black);
		b2.addActionListener(this);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setFont(new Font("calibri",1,21));
		b2.setBorderPainted(true);
		b2.setFocusPainted(false);
		b2.setEnabled(true);

		// Join game button
		b3 = new JButton("Join Game");
		b3.setPreferredSize(btnsize2);
		b3.setBorder(new RoundedBorder(30));
		b3.setBackground(Color.white);
		b3.setForeground(Color.black);
		b3.addActionListener(this);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setFont(new Font("calibri",1,21));
		b3.setBorderPainted(true);
		b3.setFocusPainted(false);
		b3.setEnabled(true);

		// Refresh button
		b4 = new JButton("Refresh");
		b4.setPreferredSize(btnsize2);
		b4.setBorder(new RoundedBorder(30));
		b4.setBackground(Color.white);
		b4.setForeground(Color.black);
		b4.addActionListener(this);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		b4.setFont(new Font("calibri",1,21));
		b4.setBorderPainted(true);
		b4.setFocusPainted(false);
		b4.setEnabled(true);

		//Makes Title
		l1 = new JLabel("Cards Against Humanity");
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		l1.setFont(new Font("AR JULIAN",Font.PLAIN,70));
		l1.setForeground(Color.BLACK);

		// Implementing pictures for white cards and black cards as JLabel
		l2 = new JLabel();
		l3 = new JLabel();
		l2.setIcon(new ImageIcon(new ImageIcon("BCLobby.png").getImage().getScaledInstance(249, 381, Image.SCALE_DEFAULT)));
		l3.setIcon(new ImageIcon(new ImageIcon("WCLobby.png").getImage().getScaledInstance(306, 556, Image.SCALE_DEFAULT)));
		l2.setAlignmentX(Component.CENTER_ALIGNMENT);
		l3.setAlignmentX(Component.CENTER_ALIGNMENT);


		// Create List

		//list = new JList(/*Client.getGameList().toArray()*/);
		//list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		//Creates panel for buttons
		JPanel p1 = new JPanel();

		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		p1.setLayout(flow);
		p1.setBackground(Color.WHITE);
		p1.add(b2);
		p1.add(b4);
		flow.setHgap(100);
		p1.add(b3);
		p1.add(b1);
		p1.add(Box.createRigidArea(new Dimension(0,200)));

		mainLobby.add(p1, BorderLayout.SOUTH);

		//Creates panel for labels
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.setBackground(Color.WHITE);

		// Panel for Title label
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		p2.setBackground(Color.WHITE);
		p2.add(Box.createRigidArea(new Dimension(50, 0)));
		p2.add(l1);

		// Panel for Black Card image
		p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
		p3.setBackground(Color.WHITE);

		p3.add(Box.createRigidArea(new Dimension(50, 0)));
		p3.add(l2);
		p3.add(Box.createRigidArea(new Dimension(50, 0)));


		// Panel for White Card image
		JPanel p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4, BoxLayout.LINE_AXIS));
		p4.setBackground(Color.WHITE);

		p4.add(Box.createRigidArea(new Dimension(50, 0)));
		p4.add(l3);

		mainLobby.add(p2, BorderLayout.NORTH);
		mainLobby.add(p3, BorderLayout.EAST);
		mainLobby.add(p4, BorderLayout.WEST);

		mainLobby.setVisible(false);
		// Panel for list of games available
		/*
		JPanel p5 = new JPanel();

		p5.setLayout(new BoxLayout(p5, BoxLayout.LINE_AXIS));
		p5.setBackground(Color.WHITE);
		p5.add(l5);

		mainLobby.add(p5, BorderLayout.CENTER);
		 */

	