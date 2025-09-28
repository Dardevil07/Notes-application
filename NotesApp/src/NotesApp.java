import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt"; // file to store notes

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Delete Notes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNote(sc);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    deleteNotes();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    // Method to add a note (append mode)
    private static void addNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(note);
            bw.newLine();
            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to view all notes
    private static void viewNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Your Notes ---");
            boolean empty = true;
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }
            if (empty) {
                System.out.println("(No notes found)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found. Add some notes first!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to delete all notes
    private static void deleteNotes() {
        try (PrintWriter pw = new PrintWriter(FILE_NAME)) {
            // overwrites the file with nothing
            System.out.println("All notes deleted.");
        } catch (FileNotFoundException e) {
            System.out.println("No notes found to delete.");
        }
    }
}

