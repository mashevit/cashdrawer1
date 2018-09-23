/*package com.cookies.ar.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class CashDrawerJAppletAppW {

	private JFrame frame;
	private JTextField textField;

	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CashDrawerJAppletAppW window = new CashDrawerJAppletAppW();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*//**
	 * Create the application.
	 *//*
	public CashDrawerJAppletAppW() {
		initialize();
	}

	*//**
	 * Initialize the contents of the frame.
	 *//*
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("New label");
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_1.add(textField);
		
		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		JTextArea textArea = new JTextArea();
		scrollPane.add(textArea);
		
	
		//frame.getContentPane().add(textArea, BorderLayout.SOUTH);
	}

}
*/