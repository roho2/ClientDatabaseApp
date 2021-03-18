package sqlClientApp;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CommandPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1375561563421026834L;
	private JTextArea command;
	
	public CommandPanel() {
		setUpCommandPanel();
	}
	
	public String getCommandText() {
		return this.command.getText();
	}
	
	public void clearCommandText() {
		this.command.setText("");
	}
	
	private void setUpCommandPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.command = new JTextArea(5, 20);
		this.command.setMaximumSize(new Dimension(300, 200));
		this.command.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.command.setLineWrap(true);
		this.command.setWrapStyleWord(true);
		
		JLabel title = new JLabel("Enter an SQL Command");
		title.setMaximumSize(new Dimension(200, 50));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		this.add(title);
		this.add(command);
	}
	
	
}
