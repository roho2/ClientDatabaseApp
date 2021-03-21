# ClientDatabaseApp
Java GUI application to connect to database and execute commands
-----------------------------------------------------------------
This application allows you to connect to your MySql server and execute sql commands from the GUI.
It is built in Java using Swing to create the GUI.

NOTE: To have program work properly you must download JDBC Driver and add to CLASS path.
      Download driver here: https://dev.mysql.com/downloads/connector/j/

There are 3 sections to the GUI. 

1. Login Area:
        In the top left you log into your SQL Server
2. Command Area:
        In the top right is a large text area to input SQL commands you want executed
3. Result Area:
        The bottom portion of the screen. Includes a status indicator to if you are connected, 
        a button to connect to the database, a button to clear the SQL Command Area, and a button
        to execute the SQL Command in the Command Window. This section also includes an output text 
        area that displays the results of the SQL query and a button on the bottom to clear that area.
