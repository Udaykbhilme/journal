package com.journal.entry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JournalEntry
{
    private Date date;
    private String title;
    private String content;

    public JournalEntry(Date date, String title, String content)
    {
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public Date getDate()
    {
        return date;
    }

    public String getTitle()
    {
        return title;
    }

    public String getContent()
    {
        return content;
    }
    public static List<JournalEntry> getAllEntries() {
        List<JournalEntry> entries = new ArrayList<>();
        try
        {
            Connection con = CP.createConnection();
            Date currentDate = new Date();
            String sql = "SELECT title, content FROM journal_entries";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                String title = result.getString("title");
                String content = result.getString("content");
                JournalEntry entry = new JournalEntry(currentDate,title, content);
                entries.add(entry);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return entries;
    }
}
