package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class LoginGUI implements ActionListener {
	
	int count = 0;
	JLabel label;
	JFrame frame;
	JPanel panel;

	public LoginGUI() 
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button;
		JButton button2;
		button = new JButton("Login");
	    button2 = new JButton("Sign Up"); //New button "Click me"
		button.addActionListener(this);
		button2.addActionListener(this);
		
		label = new JLabel("Number of clicks: 0");
		
		
       
		
		
		panel.setBorder(BorderFactory.createEmptyBorder(60, 60, 720, 1280)); //x, y, width, height
		panel.setLayout(new GridLayout(0, 1));
		panel.setBackground(Color.gray);
		panel.add(button);
		panel.add(button2);
		
		 
		panel.add(label);
		label.setBounds(50, 50, 50, 50);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("'Something Witty'");  //Application title
		frame.pack();
		frame.setVisible(true);	
		
		
	}
	
	
	
	public static void main(String[] args) {
		new LoginGUI();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		
		if (e.getActionCommand()=="Login")
        {
			count++;
			label.setText("No of clicks:" + count);
        }
		
		else if (e.getActionCommand()=="Sign Up")
        {
			count++;
			label.setText("No 2 of clicks:" + count);
        }
	
		
	};
	
	


}
