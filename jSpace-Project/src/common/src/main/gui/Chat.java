package common.src.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import common.src.main.client.Client;

@SuppressWarnings("serial")
public class Chat extends JFrame implements ActionListener {

	
	public static void main(String[] args) {
		 Chat chat = new Chat();
	}

	JTextArea chatBox;
	JTextField messageField;
	JButton sendButton;
	JPanel chatPanel, sendPanel;
	public String username;
	public String message;

	public Chat() {

		// Panels
		chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());

		sendPanel = new JPanel();
		sendPanel.setBackground(Color.WHITE);
		sendPanel.setLayout(new GridBagLayout());

		// Message field and send button
		messageField = new JTextField();
		messageField.requestFocusInWindow();

		sendButton = new JButton(" Send ");
		sendButton.addActionListener(this);
		
		// Chat area
		chatBox = new JTextArea();
		chatBox.setEditable(false);
		chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
		chatBox.setLineWrap(true);
		
		// GRIDBAG CONSTRAINTS
		// GBC for send button
		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.LINE_START;
		left.fill = GridBagConstraints.HORIZONTAL;
		left.weightx = 300.0D;
		left.weighty = 1.0D;
		// GBC for message field
		GridBagConstraints right = new GridBagConstraints();
		right.anchor = GridBagConstraints.LINE_END;
		right.fill = GridBagConstraints.NONE;
		right.weightx = 1.0D;
		right.weighty = 1.0D;

		// adding elements
		chatPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
		sendPanel.add(messageField, left);
		sendPanel.add(sendButton, right);
		chatPanel.add(BorderLayout.SOUTH, sendPanel);
		add(chatPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(275, 350);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		SwingUtilities.getRootPane(sendButton).setDefaultButton(sendButton);
	}


	public void sendChatMessage(String username, String message) {
		chatBox.append("<" + username + ">:  " + message + "\n");
	}

	public void actionPerformed(ActionEvent e){
		if ( e.getSource() == sendButton ) {
			if (messageField.getText().length() < 1) {
				// DO NOTHING
			} else {
				chatBox.append("<" + "Yael" + ">:  " + messageField.getText() + "\n"); // Skal slettes efter chat-test er færdige
				// Nedenstående skal køre, når clienten skal forbindes.
				// Client.sendChatMessage(messageField.getText());
				messageField.setText("");
			}
			messageField.requestFocusInWindow();
		}
	}
}