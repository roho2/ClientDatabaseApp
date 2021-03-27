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

    private static ResultSetMetaData resultSetMetaData;
    private static int columnCount;

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        Vector<String> columnNames = getColumnNames(rs);
        Vector<Vector<Object>> data = getTableData(rs);
        return new DefaultTableModel(data, columnNames);
    }

    private static Vector<Vector<Object>> getTableData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return data;
    }

    private static Vector<String> getColumnNames(ResultSet resultSet) throws SQLException{
        resultSetMetaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<>();
        columnCount = resultSetMetaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(resultSetMetaData.getColumnName(column));
        }
        return columnNames;
    }
}
