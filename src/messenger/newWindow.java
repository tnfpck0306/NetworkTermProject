package messenger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.*;
import javax.swing.*;

public class newWindow extends JFrame {
	
	static String title;
	static String userName;
	static String serverAddress;
	static JPanel contentPane = new JPanel();
	static JTextArea messageField;
   	static JTextField textField2;
   	static Scanner in;
    static PrintWriter out;

	public newWindow() {
	   	setTitle(userName);
	   	setContentPane(contentPane);
	   	 
			setBounds(100, 100, 450, 300);
			
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			JButton exit = new JButton("Exit");
			contentPane.add(exit, BorderLayout.NORTH);
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					out.println("CLOSE / " + title);
				}
			});
			
			messageField = new JTextArea();
			contentPane.add(messageField, BorderLayout.CENTER);
			messageField.setColumns(10);
			
			textField2 = new JTextField();
			contentPane.add(textField2, BorderLayout.SOUTH);
			textField2.setColumns(10);
			
			textField2.setEditable(true);
		    messageField.setEditable(false); 
		    
		    textField2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                out.println("MESSAGE /" + userName + "> " + textField2.getText() + "/ " + title); 
	                textField2.setText("");
	            }
	        });
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
		}

}
