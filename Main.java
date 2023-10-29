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
                System.out.println("3. View Entry by Title");
                System.out.println("4. Exit");
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
                            scanner.nextLine(); // Consume the newline
                            System.out.print("Enter title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter content: ");
                            String content = scanner.nextLine();
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
                                System.out.println("Content: " + e.getContent());
                                System.out.println();
                            }
                            break;

                        case 3:
                            scanner.nextLine(); // Consume the newline
                            System.out.print("Enter the title to search for: ");
                            String searchTitle = scanner.nextLine();

                            File[] files = new File(".").listFiles();
                            boolean found = false;

                            try
                            {
                                {
                                    for (File file : files)
                                    {
                                        if (file.getName().startsWith("journal_") && file.getName().contains(searchTitle))
                                        {
                                            found = true;

                                            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                                                String line;
                                                while ((line = reader.readLine()) != null)
                                                {
                                                    System.out.println(line);
                                                }
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                                System.err.println("Failed to read the entry for the specified title.");
                                            }
                                        }
                                    }
                                }
                            }
                            catch(NullPointerException np)
                            {
                                np.printStackTrace();
                            }

                            if (!found)
                            {
                                System.out.println("No entry found for the specified title.");
                            }
                            break;

                        case 4:
                            scanner.close();
                            System.out.println("Goodbye!");
                            System.exit(0);

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
                catch(InputMismatchException ipm)
                {
                    System.out.println("Something went wrong.....");
                    System.out.println("Please try again");
                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Thank you for using the application.");
        System.out.println("See you soon.....");
        System.out.println("BYE BYE !!");
    }
}