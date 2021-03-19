/*Name: Robert Hollinger
 * Course: CNT 4714-Spring 2021
 * Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
 * Date: TODO: ADD DATE WHEN COMPLETE!!*!*!*!*!*!*!*!*!*!*!
 */

package sqlClientApp;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ResultPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4402863995571453341L;
	
	ButtonsPanel buttons;
	JTextArea output;
	JButton clearResult;
	
	public ResultPanel() {
		setUpResultPanel();
	}
	
	public void setOutputText(String[][] data, String[] columnNames) {
		
		for(int i=0; i<columnNames.length; i++) {
			this.output.append(columnNames[i] + "\t\t\t");
		}
		this.output.append("\n");
		this.output.append("------------------------------------------------------------------------------------"
				+ "-----------------------------------------------------------------------\n");
		
		for(int i=0; i<data.length; i++) {
			for(int j=0; j<data[0].length; j++) {
					this.output.append(data[i][j] + "\t\t\t");
			}
			this.output.append("\n");
		}

	}
	
	public void clearResultText() {
		this.output.setText(null);
	}
	
	private void setUpResultPanel() {
		//Initialize object settings, buttons, and output
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.buttons = new ButtonsPanel();
		this.output = new JTextArea(5, 50);
		JScrollPane scroll = new JScrollPane(this.output);
		scroll.setMaximumSize(new Dimension(800, 310));
		scroll.setPreferredSize(new Dimension(800, 310));
		
		//Set output object settings
		this.output.setEditable(false);
		//this.output.setLineWrap(true);
		//this.output.setWrapStyleWord(true);
		this.clearResult = new JButton("Clear Result Window");
		
		//Add everything to the object
		this.add(this.buttons);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(scroll);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(this.clearResult);
	}
	
}
