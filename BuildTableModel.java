/*Name: Robert Hollinger
 * Course: CNT 4714-Spring 2021
 * Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
 * Date: March 21st, 2021
 */

package sqlClientApp;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class BuildTableModel {
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData rsmd = rs.getMetaData();

        //Get the names of the columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = rsmd.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(rsmd.getColumnName(column));
        }

        //Get the data for the table
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
}
