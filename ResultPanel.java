/*Name: Robert Hollinger
 * Course: CNT 4714-Spring 2021
 * Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
 * Date: March 21st, 2021
 */

package sqlClientApp;

import java.awt.Dimension;
import java.io.Serial;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ResultPanel extends JPanel{
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 4402863995571453341L;
	
	ButtonsPanel buttons;
	JTable output;
	JButton clearResult;
	
	public ResultPanel() {
		setUpResultPanel();
	}
	
	//Set JTable equal to data from JTable formatted with query results
	public void setOutputText(JTable result) {
		this.output.setModel(result.getModel());	
	}
	
	//Create new blank model and use to clear JTable
	public void clearResultText() {
		DefaultTableModel clearTable = new DefaultTableModel(0, 0);
		this.output.setModel(clearTable);
	}
	
	private void setUpResultPanel() {
		//Initialize object settings, buttons, and output
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.buttons = new ButtonsPanel();
		this.output = new JTable();
		JScrollPane scroll = new JScrollPane(this.output);
		scroll.setMaximumSize(new Dimension(800, 310));
		scroll.setPreferredSize(new Dimension(800, 310));
		this.clearResult = new JButton("Clear Result Window");
		
		
		//Add everything to the object
		this.add(this.buttons);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(scroll);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(this.clearResult);
	}
	
}
