/*Name: Robert Hollinger
 * Course: CNT 4714-Spring 2021
 * Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
 * Date: March 21st, 2021
 */

package sqlClientApp;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DBPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1225376522146990596L;
	private JComboBox<String> driver;
	private JComboBox<String> url;
	private JTextField username;
	private JTextField password;
	
	public DBPanel() {
		setUpDBPanel();
	}
	
	public String getURL() {
		return this.url.getSelectedItem().toString();
	}
	
	public String getUsername() {
		return this.username.getText();
	}
	
	public String getPassword() {
		return this.password.getText();
	}
	
	private void setUpDBPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Panels to add Label + input field
		JPanel titleP = new JPanel();
		JPanel driverP = new JPanel();
		JPanel urlP = new JPanel();
		JPanel userP = new JPanel();
		JPanel passP = new JPanel();
		driverP.setMaximumSize(new Dimension(400, 35));
		urlP.setMaximumSize(new Dimension(400, 35));
		userP.setMaximumSize(new Dimension(400, 35));
		passP.setMaximumSize(new Dimension(400, 35));
		titleP.setMaximumSize(new Dimension(400, 35));
		
		//Create Labels and initialize input fields
		JLabel title = new JLabel("Enter Database Information");
		String[] driverOptions =  {"Select a Driver", "com.mysql.cj.jdbc.Driver", "Other"};
		String[] urlOptions = {"Select database URL", "jdbc:mysql://127.0.0.1:3306/project3?useTimezone=true&serverTimezone=UTC", "Other"};
		JLabel driverL = new JLabel("JDBC Driver");
		JLabel urlL = new JLabel("Database URL");
		JLabel userL = new JLabel("Username");
		JLabel passL = new JLabel("Password");
		this.driver = new JComboBox<String>(driverOptions);
		this.url = new JComboBox<String>(urlOptions);
		this.username = new JTextField(20);
		this.password = new JTextField(20);
	
		//Add labels and input fields to Sub-Panels
		driverP.add(driverL);
		driverP.add(this.driver);
		urlP.add(urlL);
		urlP.add(this.url);
		userP.add(userL);
		userP.add(this.username);
		passP.add(passL);
		passP.add(this.password);
		titleP.add(title);
		
		this.url.setPreferredSize(new Dimension(200, 25));
		
		//Add Sub-Panels to object
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(titleP);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		this.add(driverP);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		this.add(urlP);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		this.add(userP);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		this.add(passP);
	}
	
}
