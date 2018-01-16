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
	JTextArea chatBox;
	JTextField messageField;
	JButton sendButton;
	JPanel chatPanel, sendPanel;

	public Chat(int width, int height) {

		// Panels
		chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		chatPanel.setPreferredSize(new Dimension(width, height));
		sendPanel = new JPanel();
		sendPanel.setBackground(Color.WHITE);
		sendPanel.setLayout(new GridBagLayout());

		// Message field and send button
		messageField = new JTextField();
		messageField.requestFocusInWindow();
		messageField.setPreferredSize(new Dimension(width*2, 20));

		sendButton = new JButton(" Send ");
		sendButton.addActionListener(this);
		
		// Chat area
		chatBox = new JTextArea();
		chatBox.setEditable(false);
		chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
		chatBox.setLineWrap(true);
		
		// adding elements
		chatPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
		//sendPanel.add(messageField, Component.LEFT_ALIGNMENT);
		//sendPanel.add(sendButton, Component.RIGHT_ALIGNMENT);
		sendPanel.add(messageField);
		sendPanel.add(sendButton);
		chatPanel.add(BorderLayout.SOUTH, sendPanel);
		
		//SwingUtilities.getRootPane(sendButton).setDefaultButton(sendButton);
	}


	public void chatMessageRecived(String message) {
		chatBox.append(message+"\n");
	}

	public void actionPerformed(ActionEvent e){
		if ( e.getSource() == sendButton ) {
			if (messageField.getText().length() < 1) {
				// DO NOTHING
			} else {
				Client.sendChatMessage(messageField.getText());
				messageField.setText("");
			}
			messageField.requestFocusInWindow();
		}
	}
}