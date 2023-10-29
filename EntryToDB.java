package com.journal.entry;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntryToDB
{
    public static void InsertToDB(JournalEntry entry)
    {
        try
        {
            //jdbc code...
            Connection con = CP.createConnection();
            String sql = "INSERT INTO journal_entries (date, title, content) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(entry.getDate().getTime()));
            statement.setString(2, entry.getTitle());
            statement.setString(3, entry.getContent());
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
