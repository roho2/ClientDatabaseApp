/*Name: Robert Hollinger
 * Course: CNT 4714-Spring 2021
 * Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
 * Date: TODO: ADD DATE WHEN COMPLETE!!*!*!*!*!*!*!*!*!*!*!
 */

package sqlClientApp;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class BuildTableModel {
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData rsmd = rs.getMetaData();

	    //Get the names of the columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = rsmd.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(rsmd.getColumnName(column));
	    }

	    //Get the data for the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
