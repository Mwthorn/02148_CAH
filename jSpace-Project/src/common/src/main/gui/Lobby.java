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

<<<<<<< HEAD
	private static final String String = null;
	private JButton b1, b2, b3, b4;
	private JLabel l1,l2,l3,l4,l5;
	private JTextField WP;
	private JList<String> list;
	private static ArrayList<String> liste = new ArrayList();


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


	public Lobby() throws InterruptedException {
		getContentPane().setLayout(new BorderLayout()); //Default layout
		getContentPane().setBackground(Color.WHITE);
=======
>>>>>>> 6596ac86be6e934d7f98d09d0c309214d378d9bf

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
<<<<<<< HEAD
=======
		b3.setFont(new Font("calibri",1,21));
		b3.setBorderPainted(true);
		b3.setFocusPainted(false);
		b3.setEnabled(true);
>>>>>>> 6596ac86be6e934d7f98d09d0c309214d378d9bf

		// Refresh button
		b4 = new JButton("Refresh");
		b4.setPreferredSize(btnsize2);
		b4.setBorder(new RoundedBorder(30));
		b4.setBackground(Color.white);
		b4.setForeground(Color.black);
		b4.addActionListener(this);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
<<<<<<< HEAD

		//Create label
		l1 = new JLabel("Cards Against Humanity ");
		l1.setPreferredSize(new Dimension(480, 50));
		l1.setFont(new Font("Cards Against Humanity",Font.ITALIC,40));
		l1.setForeground(Color.BLACK);
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);
=======
		b4.setFont(new Font("calibri",1,21));
		b4.setBorderPainted(true);
		b4.setFocusPainted(false);
		b4.setEnabled(true);
>>>>>>> 6596ac86be6e934d7f98d09d0c309214d378d9bf

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
		DefaultListModel model = new DefaultListModel();
		list = new JList(model);
		for (int i = 0; i < 100; i++) {
			model.addElement("String " + i +"hello"+ i);
		}

		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);


		//Creates panel for buttons
		JPanel p1 = new JPanel();

		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		p1.setLayout(flow);
		p1.setBackground(Color.WHITE);
<<<<<<< HEAD
		//		p1.add(Box.createRigidArea(new Dimension(0,100)));
		p1.add(b2);
		//		p1.add(Box.createHorizontalGlue());
		p1.add(b4);
		//		p1.add(Box.createHorizontalGlue());
		p1.add(b3);
		//		p1.add(Box.createHorizontalGlue());
=======
		p1.add(b2);
		p1.add(b4);
		flow.setHgap(100);
		p1.add(b3);
>>>>>>> 6596ac86be6e934d7f98d09d0c309214d378d9bf
		p1.add(b1);
		p1.add(Box.createRigidArea(new Dimension(0,200)));

<<<<<<< HEAD
		flow.setHgap(30);


		getContentPane().add(p1, BorderLayout.SOUTH);
=======
		mainLobby.add(p1, BorderLayout.SOUTH);
>>>>>>> 6596ac86be6e934d7f98d09d0c309214d378d9bf

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

<<<<<<< HEAD
		//Window dimensions
		setSize(1080, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cards Against Humanity");
		setResizable(true);
		setVisible(true);


=======
		mainLobby.setVisible(false);
>>>>>>> 6596ac86be6e934d7f98d09d0c309214d378d9bf
		// Panel for list of games available

		JPanel p5 = new JPanel();

		p5.setLayout(new BoxLayout(p5, BoxLayout.LINE_AXIS));
		p5.setBackground(Color.WHITE);
		p5.add(list);

<<<<<<< HEAD
		getContentPane().add(p5, BorderLayout.CENTER);

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
=======
		mainLobby.add(p5, BorderLayout.CENTER);
		 */

	
>>>>>>> 6596ac86be6e934d7f98d09d0c309214d378d9bf
