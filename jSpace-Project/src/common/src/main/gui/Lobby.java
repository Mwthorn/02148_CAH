package common.src.main.gui;

import common.src.main.client.Client;
import common.src.main.client.GamePreview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Lobby extends JFrame implements ActionListener{

	private static final String String = null;
	private JButton b1, b2, b3, b4;
	private JLabel l1,l2,l3,l4,l5;
	private JTextField WP;
	private static JList list;

	
	public static ArrayList<GamePreview> putList() throws InterruptedException{
		for (GamePreview gp : Client.getGameList()) {
			gp.getCurrentPlayerSize();
			gp.getGameName();
			gp.getGameStatus();
			gp.getId();
			gp.getMaxPlayerSize();
			gp.hasPassword();
			gp.isPasswordProtected();
		}

		return Client.getGameList();
	}


	public Lobby() throws InterruptedException{
		getContentPane().setLayout(new BorderLayout()); //Default layout
		getContentPane().setBackground(Color.WHITE);

		//Create buttons
		Dimension btnsize1 = new Dimension(200,80);
		Dimension btnsize2 = new Dimension(150,80);

		// Create game button
		b1 = new JButton("Create Game");
		b1.setPreferredSize(btnsize1);
		b1.setBackground(Color.BLACK);
		b1.addActionListener(this);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Sign out button
		b2 = new JButton("Sign Out");
		b2.setPreferredSize(btnsize2);
		b2.setBackground(Color.BLACK);
		b2.addActionListener(this);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Join game button
		b3 = new JButton("Join Game");
		b3.setPreferredSize(btnsize2);
		b3.setBackground(Color.BLACK);
		b3.addActionListener(this);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Refresh button
		b4 = new JButton("Refresh");
		b4.setPreferredSize(btnsize2);
		b4.setBackground(Color.BLACK);
		b4.addActionListener(this);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Create label
		l1 = new JLabel("Cards Against Humanity ");
		l1.setPreferredSize(new Dimension(480, 50));
		l1.setFont(new Font("Cards Against Humanity",Font.ITALIC,40));
		l1.setForeground(Color.BLACK);
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);


		// Implementing pictures for white cards and black cards as JLabel
		l2 = new JLabel();
		l3 = new JLabel();

		l2.setIcon(new ImageIcon(new ImageIcon("BCLobby.png").getImage().getScaledInstance(187, 288, Image.SCALE_DEFAULT)));
		l3.setIcon(new ImageIcon(new ImageIcon("WCLobby.png").getImage().getScaledInstance(221, 328, Image.SCALE_DEFAULT)));

		// Create List

		//list = new JList(/*Client.getGameList().toArray()*/);
		//list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		//Creates panel for buttons
		JPanel p1 = new JPanel();

		p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
		p1.setBackground(Color.WHITE);
		p1.add(Box.createRigidArea(new Dimension(0,100)));
		p1.add(b2);
		p1.add(Box.createHorizontalGlue());
		p1.add(b4);
		p1.add(Box.createHorizontalGlue());
		p1.add(b3);
		p1.add(Box.createHorizontalGlue());
		p1.add(b1);

		getContentPane().add(p1, BorderLayout.SOUTH);

		//Creates panel for labels
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.setBackground(Color.WHITE);

		// Panel for Title label
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		p2.setBackground(Color.WHITE);
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
		p4.add(Box.createRigidArea(new Dimension(50, 0)));
		p4.setBackground(Color.WHITE);
		p4.add(l3);

		getContentPane().add(p2, BorderLayout.NORTH);
		getContentPane().add(p3, BorderLayout.EAST);
		getContentPane().add(p4, BorderLayout.WEST);


		//Window dimensions
		setSize(1080, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cards Against Humanity");
		setResizable(true);
		setVisible(true);
		
		
		// Panel for list of games available
		/*
		JPanel p5 = new JPanel();

		p5.setLayout(new BoxLayout(p5, BoxLayout.LINE_AXIS));
		p5.setBackground(Color.WHITE);
		p5.add(l5);

		getContentPane().add(p5, BorderLayout.CENTER);
		 */
	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource()==b1){
			
		}
		if(e.getSource()==b2){
			dispose();
			new Login();
		}

		/*
		if(e.getSource()==b3 && Client.getGameList() != null){
			JPanel p6 = new JPanel();
			JFrame ePassword = new JFrame("Enter Password");
			ePassword.pack();
			ePassword.setVisible(true);
			ePassword.setResizable(false);
			ePassword.setLocationRelativeTo(null);

			l5 = new JLabel("Enter Password");
			WP = new JTextField(10);
			b4 = new JButton("Enter");
			b4.addActionListener(this);
			p6.add(ePassword);
			p6.add(WP);
			p6.add(b4);

			if(e.getSource()==b4 && WP == Client.getGameList()){
				new ReadyUpLobby();
				ePassword.dispose();
			}
			else{
				l5 = new JLabel("Wrong Password");
				add(l5);
			}

		}*/

	}

	
	public static void main(String[] args) throws InterruptedException {

		Lobby lobby = new Lobby();

		lobby.setSize(1080, 720);
		lobby.setLocationRelativeTo(null);
		lobby.setDefaultCloseOperation(EXIT_ON_CLOSE);
		lobby.setTitle("Cards Against Humanity");
		lobby.setResizable(true);
		lobby.setVisible(true);

		System.out.println(Client.getGameList());

	} 

}
