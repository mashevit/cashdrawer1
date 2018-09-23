package com.cookies.ar.gui;

import javax.swing.JApplet;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;

import com.cookies.ar.CashDrawer;
import com.cookies.ar.CashDrawerException;
import com.cookies.ar.CashDrawerReader;
import com.cookies.ar.CashDrawerReportWriter;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.Component;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class CashDrawerJApplet extends JApplet {
	private JTextField filenameTextField;
	private JTextArea textArea;

	/**
	 * Create the applet.
	 */
	
	
	public static void main(String[] args) {
		CashDrawerJApplet applet = new CashDrawerJApplet();
		JFrame frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		frame.setTitle("Cash Drawer Applet");
		
		///Dimension preferredSize=new Dimension(450, 300);
	///	frame.setPreferredSize(preferredSize);
		frame.pack();
		frame.setVisible(true);
	}
	public CashDrawerJApplet() {
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout());
		Dimension preferredSize=new Dimension(450, 300);
		mainPanel.setPreferredSize(preferredSize);
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new CompoundBorder(new EmptyBorder(4, 4, 4, 4), null));
		mainPanel.add(inputPanel, BorderLayout.NORTH);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
		
		JLabel filenameLabel = new JLabel("Enter filename:");
		filenameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		filenameLabel.setLocation(new Point(5, 5));
		filenameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		filenameLabel.setForeground(Color.BLUE);
		inputPanel.add(filenameLabel);
		
		filenameTextField = new JTextField();
		filenameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filenameTextField.setPreferredSize(new Dimension(7, 25));
		inputPanel.add(filenameTextField);
		filenameTextField.setColumns(10);
		
		JButton balanceButton = new JButton("Balance");
		balanceButton.setForeground(Color.MAGENTA);
		balanceButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		balanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				handleBalanceButtonClicked();
			}
		});
		inputPanel.add(balanceButton);
		textArea = new JTextArea();
		textArea.setForeground(new Color(128, 0, 128));
		textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(textArea);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
		
	/*	Dimension preferredSize1=new Dimension(0,0);
		mainPanel.setPreferredSize(preferredSize);
		JPanel i1= new JPanel();
		i1.setPreferredSize(null);
		mainPanel.add(i1, BorderLayout.SOUTH);*/
		//scrollPane.add();
		
	
		//mainPanel.add(comp)
	
		//getContentPane().add(textArea, BorderLayout.CENTER);

	}
	
	private void handleException(Exception exception) {
		
		System.out.println("--------- EXCEPTION in CashDrawerJApplet ---------");
		exception.printStackTrace();
	}
	private void handleBalanceButtonClicked() {
		try {
			
			String inputFile=filenameTextField.getText();
		
			CashDrawerReader reader= new CashDrawerReader(inputFile);
			reader.processFile();
			CashDrawer cashDrawer=reader.getCashDrawer();
	
			
			CashDrawerReportWriter writer = new CashDrawerReportWriter(cashDrawer);
			String report=writer.getBalanceReport();
			textArea.setText(report);
		}catch(CashDrawerException e) {
			textArea.setText("The cash drawer you entered cannot be found or\r\n" + 
					"			processed. Please reenter the cash drawer input file name.");
			handleException(e);
			
		}catch(Exception exc) {////
			handleException(exc);
		}
		
	}

}
