package com.journal.entry;

import java.sql.Connection;
import java.sql.DriverManager;

public class CP
{
    static Connection con;
    public static Connection createConnection()
    {
        try
        {
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create the connection
            String user = "root";
            String password = "Narutopb2003@";
            String url = "jdbc:mysql://localhost:3306/oops";
            con = DriverManager.getConnection(url,user,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
