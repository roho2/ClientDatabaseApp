/*Name: Robert Hollinger
 * Course: CNT 4714-Spring 2021
 * Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
 * Date: TODO: ADD DATE WHEN COMPLETE!!*!*!*!*!*!*!*!*!*!*!
 */

package sqlClientApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3566352670787638960L;
	public static CreateFrame mainFrame = null;
	public Connection myConnection;
	ButtonHandler bh = new ButtonHandler(this); 
	DBPanel dbPanel;
	ResultPanel resultPanel;
	CommandPanel comPanel;
	
	public CreateFrame() {
		createGUI();
	}
	
	public static CreateFrame getInstance() {
		
		if(mainFrame == null) {
			mainFrame = new CreateFrame();
		}
		return mainFrame;
	}
	
	public void createGUI() {
		this.setTitle("Client App - (CNT4714 - Spring 2021)");
		this.setSize(1024, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		dbPanel = new DBPanel();
		comPanel = new CommandPanel();
		resultPanel = new ResultPanel();
		
		//Create GridBagConstraints and add to this object
		GridBagConstraints c = new GridBagConstraints();
		
		//Constraints for dbPanel
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		this.add(dbPanel, c);
		
		//Constraints for comPanel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		this.add(comPanel, c);
		
		//Constraints for resultPanel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth=3;
		c.gridheight=2;
		this.add(resultPanel, c);
		
		//create action listeners for buttons
		resultPanel.buttons.clearCommand.addActionListener(bh);
		resultPanel.buttons.clearCommand.setActionCommand("clearCommand");
		
		resultPanel.buttons.connect.addActionListener(bh);
		resultPanel.buttons.connect.setActionCommand("connect");
		
		resultPanel.clearResult.addActionListener(bh);
		resultPanel.clearResult.setActionCommand("clearWindow");
		
		resultPanel.buttons.execute.addActionListener(bh);
		resultPanel.buttons.execute.setActionCommand("execute");
	}
	
	

	class ButtonHandler implements ActionListener
	{
		CreateFrame frame;
		
		public ButtonHandler(CreateFrame frame) 
		{
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			//Connect to database
			if(e.getActionCommand() == "connect") {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					myConnection = DriverManager.getConnection(mainFrame.dbPanel.getURL(), mainFrame.dbPanel.getUsername(), mainFrame.dbPanel.getPassword());
					mainFrame.resultPanel.buttons.status.setText("Connected to jdbc:mysql:127.0.0.1:3306");
				} catch (SQLException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null,"Check login information and try again", "Connection Error", JOptionPane.WARNING_MESSAGE);
				}
			}
			//Clear command window in top right
			else if(e.getActionCommand() == "clearCommand") {
				mainFrame.comPanel.clearCommandText();
			}
			//Clear result window at bottom
			else if(e.getActionCommand() == "clearWindow") {
				mainFrame.resultPanel.clearResultText();
			}
			//Execute command that is in command text box
			else if(e.getActionCommand() == "execute") {
				try {
					if(myConnection == null) {
						JOptionPane.showMessageDialog(null, "Must connect to database before executing commands", "Connection Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					//Create the SQL statement 
					//Parameters in createStatement NEEDED for rs.beforeFirst called later
					Statement sql = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						    ResultSet.CONCUR_READ_ONLY);
					boolean resultSetBool = sql.execute(mainFrame.comPanel.getCommandText());
					
					/*If execute returns true, then the query returns a ResultSet and is not
					 * a data manipulation query. If false, it is a manipulation so go to else
					 * and display that the change was successful
					 */
					if(resultSetBool == true) {
						ResultSet rs = sql.executeQuery(mainFrame.comPanel.getCommandText());
						ResultSetMetaData rsmd = rs.getMetaData();
						
						//Get number of columns and column names
						int numOfColumns = rsmd.getColumnCount();
						String[] columnNames = new String[numOfColumns];
						for(int i=0; i<numOfColumns; i++) {
							columnNames[i] = rsmd.getColumnName(i+1);
						}
						
						//find how many rows there are 
						int rsCount = 0;
						while(rs.next()) {
							rsCount++;
						}
						
						//Create data variable and iteration variables
						String[][] data = new String[rsCount][numOfColumns];
						int i = 0;
						int a = 0;
						
						//Return result set back to 0 position 
						rs.beforeFirst();
						
						//Loop through all rows and add data to the data array
						while(rs.next()) {
							for(int j=1; j<=numOfColumns; j++) {
								data[i][a] = rs.getString(j);
								a++;
							}
							a=0;
							i++;
						}
						mainFrame.resultPanel.setOutputText(data, columnNames);
					}
					else {
						JOptionPane.showMessageDialog(null, "Successfully updated table", "Success", JOptionPane.WARNING_MESSAGE);
					}
					
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "Database Error", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		}
	}
	
}
