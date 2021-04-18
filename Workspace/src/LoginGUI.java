package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;			//imports, some may need removed at the end if they aren't used

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class LoginGUI implements ActionListener {		//public class that implements actionlistener, which are just actions that are performed when buttons are pressed for example
	
	int count = 0;		//ignore this
	JLabel label;		//define new global JLabel
	JFrame frame;		//define new global JFrame
	JPanel panel;		//define new global JPanel

	public LoginGUI() 
	{
		JFrame frame = new JFrame();		//new 'window' basically, e.g. the login page
		JPanel panel = new JPanel();		//panel that you can add stuff into e.g. like buttons and labels
		JTextField LoginText = new JTextField (20); //adds a text field for login 
		JTextField SignUpText = new JTextField(20);
		JButton button;				//one button
		JButton button2;			//two button
		button = new JButton("Sign Up");		//New button "sign up", will need position moved
	        button2 = new JButton("Login"); 		//New button "sign up", will need position moved
		button.addActionListener(this);		//action listener to allow us to assign a task once it has been 'clicked'	
		button2.addActionListener(this);	// ^^^^^^^
		
		label = new JLabel("Number of clicks: 0");	//label / text that is displayed to users
		LoginText.setBounds(50,50,710,1270); //sets the position and the layout of the text field 
                LoginText.setBounds(70,70,760,1290);  
		
       
		
		
		panel.setBorder(BorderFactory.createEmptyBorder(60, 60, 720, 1280)); //x, y, width, height (border of the objects)
		panel.setLayout(new GridLayout(0, 1));		////a grid layout, creating one column 
		panel.setBackground(Color.gray);		//background colour
		panel.add(button);				//add button to the panel
		panel.add(LoginText); //adds the panel 
		panel.add(SignUpText); //this adds the 2nd text field underneath the login button but we need to change the layout of the buttons 
		panel.add(button2);				// ^^^^^^^^^^^^^
		
		 
		panel.add(label);				//add our label to the panel
		label.setBounds(50, 50, 50, 50);		//position of the label 
		
		frame.add(panel, BorderLayout.CENTER);			//add the above panels to the frame and centre it I think ?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//closes the frame when you close the program so it is no longer loaded
		frame.setTitle("'Something Witty'");  //Application title	//title of the window - shown next to the 'X' and 'minimise' button
		frame.pack();							//not sure
		frame.setVisible(true);				//makes the frame visible to view
		
		
	}
	
	
			
	public static void main(String[] args) {			//main method
		new LoginGUI();						//creates new frame and reloads everything in LoginGUI
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {			//action events when buttons are pressed, this was a pain in the ass to do

		
		if (e.getActionCommand()=="Login")			//if the button 'Login' is pressed
        {	
			count++;					//ignore this part, it just adds a number to a count to test it works
			label.setText("No of clicks:" + count);		//changes the label, again just to test it works. We can remove this :)
        }
		
		else if (e.getActionCommand()=="Sign Up")		//if sign up button is pressed
        {
			count++;					//ignore this part, it just adds a number to a count to test it works
			label.setText("No 2 of clicks:" + count);	//changes the label, again just to test it works. We can remove this :)
        }
	
		
	};
	
	


}
