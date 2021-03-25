/*Name: Robert Hollinger
 * Course: CNT 4714-Spring 2021
 * Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
 * Date: March 21st, 2021
 */

package sqlClientApp;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1168176304649182837L;
	
	JButton connect, clearCommand, execute;
	JTextField status;
	
	public ButtonsPanel() {
		setUpButtonsPanel();
	}
	
	private void setUpButtonsPanel() {
		//Set up status bar
		this.setLayout(new FlowLayout());
		this.setMaximumSize(new Dimension(1000, 35));
		this.status = new JTextField(20);
		this.status.setEditable(false);
		this.status.setMaximumSize(new Dimension(800, 20));
		this.status.setPreferredSize(new Dimension(800, 20));
		
		//Set up Buttons
		this.connect = new JButton("Connect to Database");
		this.clearCommand = new JButton("Clear SQL Command");
		this.execute = new JButton("Execute SQL Command");
		
		//Add buttons to object
		this.add(status);
		this.add(connect);
		this.add(clearCommand);
		this.add(execute);
	}

}
