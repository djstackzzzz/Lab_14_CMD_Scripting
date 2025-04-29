import javax.swing.*;
import java.io.*;
import java.util.Scanner;

class FileScan {

    public static void main(String[] args) {
        File file = null;

        // Check if there is a command-line argument
        if (args.length > 0) {
            file = new File(args[0]);
            if (!file.exists()) {
                System.out.println("File not found: " + args[0]);
                return;
            }
        } else {
            // No command-line argument provided, use JFileChooser
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                System.out.println("No file selected. Exiting program.");
                return;
            }
        }

        // Process the file
        int charCount = 0;
        int wordCount = 0;
        int lineCount = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;
                charCount += line.length();
                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
            }
            // Display results
            System.out.println("File: " + file.getName());
            System.out.println("Characters: " + charCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Lines: " + lineCount);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + file.getName());
            e.printStackTrace();
        }
    }
}
