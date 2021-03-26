/*Name: Robert Hollinger
 * Course: CNT 4714-Spring 2021
 * Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
 * Date: March 21st, 2021
 */

package sqlClientApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.sql.*;

public class CreateFrame extends JFrame {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -3566352670787638960L;

    public static CreateFrame mainFrame = null;
    public Connection myConnection;
    private final ButtonHandler bh = new ButtonHandler(this);
    private DBPanel dbPanel;
    private ResultPanel resultPanel;
    private CommandPanel comPanel;

    public CreateFrame() {
        createGUI();
    }

    public static CreateFrame getInstance() {

        if (mainFrame == null) {
            mainFrame = new CreateFrame();
        }
        return mainFrame;
    }

    public void createGUI() {
        setUpFrameDetails();
        setConstraints();
        createButtonActionListeners();
    }

    private void setUpFrameDetails(){
        this.setTitle("Client App - (CNT4714 - Spring 2021)");
        this.setSize(1024, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        setUpPanels();
    }

    private void setUpPanels(){
        dbPanel = new DBPanel();
        comPanel = new CommandPanel();
        resultPanel = new ResultPanel();
    }

    private void setConstraints(){
        Constraints constraint = new Constraints();

        constraint.setConstraintsForDBPanel();
        this.add(dbPanel, constraint.getConstraint());
        constraint.setConstraintsForCommandPanel();
        this.add(comPanel, constraint.getConstraint());
        constraint.setConstraintsForResultPanel();
        this.add(resultPanel, constraint.getConstraint());
    }

    private void createButtonActionListeners() {
        resultPanel.buttons.clearCommand.addActionListener(bh);
        resultPanel.buttons.clearCommand.setActionCommand("clearCommand");

        resultPanel.buttons.connect.addActionListener(bh);
        resultPanel.buttons.connect.setActionCommand("connect");

        resultPanel.clearResult.addActionListener(bh);
        resultPanel.clearResult.setActionCommand("clearWindow");

        resultPanel.buttons.execute.addActionListener(bh);
        resultPanel.buttons.execute.setActionCommand("execute");
    }


    class ButtonHandler implements ActionListener {
        CreateFrame frame;

        public ButtonHandler(CreateFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "connect" -> tryConnection();
                case "clearCommand" -> mainFrame.comPanel.clearCommandText();
                case "clearWindow" -> mainFrame.resultPanel.clearResultText();
                case "execute" -> executeCommand();
            }
        }

        private void tryConnection(){
            try {
                createConnection();
            } catch (SQLException | ClassNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "Check login information and try again", "Connection Error", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void createConnection() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection = DriverManager.getConnection(mainFrame.dbPanel.getURL(), mainFrame.dbPanel.getUsername(), mainFrame.dbPanel.getPassword());
            mainFrame.resultPanel.buttons.status.setText("Connected to jdbc:mysql:127.0.0.1:3306");
        }

        private void executeCommand(){
            try {
                if (myConnection == null) {
                    JOptionPane.showMessageDialog(null, "Must connect to database before executing commands", "Connection Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                runCommand();
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Database Error", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void runCommand() throws SQLException{
            Statement sql = myConnection.createStatement();
            boolean doesCommandReturnResultSet = sql.execute(mainFrame.comPanel.getCommandText());

            if (doesCommandReturnResultSet) {
                executeQuery(sql);
            } else {
                JOptionPane.showMessageDialog(null, "Successfully updated table", "Success", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void executeQuery(Statement sql) throws SQLException{
            ResultSet rs = sql.executeQuery(mainFrame.comPanel.getCommandText());
            JTable table = new JTable(BuildTableModel.buildTableModel(rs));
            mainFrame.resultPanel.setOutputText(table);
        }

    }

}
