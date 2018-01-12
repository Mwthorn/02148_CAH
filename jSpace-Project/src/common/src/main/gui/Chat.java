package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import common.src.main.client.Client;

@SuppressWarnings("serial")
public class Chat extends JFrame implements ActionListener {

	public static void main(String[] args) {

		Chat mainC = new Chat();

	}

	JTextArea chatArea;
	JTextField chatField;
	JButton sendButton;
	JScrollPane chatScroll;

	String username = "Yael";
	private int chatWidth, chatHeight;


	public Chat() {


		// PANEL
		JPanel preChatPanel = new JPanel();
		preChatPanel.setLayout(new BorderLayout());

		JPanel chatPanel = new JPanel();
		chatPanel.setBackground(Color.WHITE);
		chatPanel.setLayout(new GridBagLayout());

		// TEXTAREA
		chatArea = new JTextArea(300, 50);
		chatArea.setBackground(Color.BLUE);
		chatArea.setEditable(false);
		chatArea.setFont(new Font("calibri",Font.PLAIN,20));
		chatArea.setLineWrap(true);

		// TEXTFIELD
		chatField = new JTextField(50);
		chatField.requestFocusInWindow();
		chatField.setFont(new Font("calibri",Font.PLAIN,20));
		chatField.setForeground(Color.BLACK);
		chatField.setAlignmentX(Component.WIDTH);

		//Dimension txtfldsize = new Dimension(400, 30);


		// SEND BUTTON
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);

		// **** GBC for PANELS ***
		// LEFT - ChatField
		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.SOUTHWEST;
		left.fill = GridBagConstraints.HORIZONTAL;
		left.weightx = 512.0D;
		left.weighty = 1.0D;
		// RIGHT - SendButto
	    GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.SOUTHEAST;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;
        // NORTH - ChatArea
	    GridBagConstraints north = new GridBagConstraints();
        right.anchor = GridBagConstraints.NORTH;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;
        
        
        // GBC used
        
		preChatPanel.add(new JScrollPane(chatArea),BorderLayout.CENTER);

        chatPanel.add(chatArea, north);
		chatPanel.add(chatField, left);
		chatPanel.add(sendButton, right);

	
		preChatPanel.add(chatPanel);

		// ADD PANEL2FRAME
		add(BorderLayout.CENTER, preChatPanel);
		
		// JFRAME
		setTitle("Cards Against Humanity");
		chatWidth = 400;
		chatHeight = 550;
		setSize(chatWidth, chatHeight); //400,550);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e){
		if (chatField.getText().length() < 1) {
			// DO NOTHING
		} else if (chatField.getText().equals(".clear")) {
			chatArea.setText("Cleared all messages\n");
			chatField.setText("");	
		} else {
			//Client.sendChatMessage(chatField.getText());
			chatArea.append("<" + username + ">:  " + chatField.getText() + "\n");
			chatField.setText("");
		}
		chatField.requestFocusInWindow();
	}
}
