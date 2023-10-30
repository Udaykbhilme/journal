import com.journal.entry.EntryToDB;
import com.journal.entry.JournalEntry;

import java.io.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args)
    {

        System.out.println("Welcome to MINIMO");

        try
        {
            Scanner scanner = new Scanner(System.in);

            while (true)
            {
                System.out.println("A Minimal Journal Management System");
                System.out.println("1. Add Entry");
                System.out.println("2. List Entries");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                boolean f=false;
                int choice = scanner.nextInt();
                Date currentDate = new Date();

                try
                {
                    switch (choice)
                    {
                        case 1:
                            // Entry creation logic remains the same
                            System.out.print("Enter title: ");
                            String title = scanner.nextLine();

                            System.out.println("Enter content (Press Enter twice to finish):");
                            StringBuilder contentBuilder = new StringBuilder();
                            String line;

                            while (!(line = scanner.nextLine()).isEmpty()) {
                                contentBuilder.append(line).append(System.lineSeparator());
                            }

                            String content = contentBuilder.toString();

                            JournalEntry entry = new JournalEntry(currentDate,title, content);
                            EntryToDB.InsertToDB(entry);
                            System.out.println("Entry added successfully.");
                            break;

                        case 2:
                            System.out.println("Listing Entries:");
                            // Implement listing entries from the files here
                            List<JournalEntry> entries = JournalEntry.getAllEntries();
                            System.out.println("Journal Entries:");
                            for (JournalEntry e : entries)
                            {
                                System.out.println("Title: " + e.getTitle());
                                System.out.println("Date: " + e.getDate());
                                System.out.println("Content: " + e.getContent());
                                System.out.println();
                            }
                            break;

                        case 3:
                            scanner.close();
                            System.out.println("Thank you for using the application.");
                            System.out.println("See you soon.....");
                            System.out.println("BYE BYE !!");
                            System.exit(0);

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
                catch(InputMismatchException ipm)
                {
                    ipm.printStackTrace();
                    System.out.println("Something went wrong.....");
                    System.out.println("Please try again");
                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
