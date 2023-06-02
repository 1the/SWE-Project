//<--4,1,2,1-->
package engez;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
public class URLBlocker {

    public static void blockURLs(String[] urls) {
        try {
            // Get the path to the hosts file
            File hostsFile = new File("C:\\Windows\\System32\\drivers\\etc" + "/hosts");

            String filePath = "C:\\Windows\\System32\\drivers\\etc" + "/hosts";
            String backupFilePath = filePath + ".bak";
            System.out.println(hostsFile.exists());

            // Make sure the hosts file exists
            if (!hostsFile.exists()) {
                System.out.println("Hosts file not found.");
                return;
            }

            // Create a backup of the original hosts file
            Path originalFile = Path.of(filePath);
            Path backupFile = Path.of(backupFilePath);
            Files.copy(originalFile, backupFile, StandardCopyOption.REPLACE_EXISTING);

            // give the program permission to edit the hosts file
            hostsFile.setWritable(true);

            // Make sure the hosts file is writable
            if (hostsFile.canWrite()) {
                System.out.println("can write to hosts file.");
                System.out.println("url length: " + urls.length);
            } else {
                System.out.println("Cannot write to hosts file.");
                return;
            }
            // Open the hosts file in append mode
            try (
                     BufferedWriter writer = new BufferedWriter(new FileWriter(hostsFile, true))) {
                System.out.println("url length: " + urls.length);
                for (String url : urls) {
                    // Write the blocking entry to the hosts file
                    writer.write("127.0.0.1 " + url);
                    writer.newLine();
                    System.out.println("Added " + url + " to the blocked URLs list.");
                }
                // Close the writer
            }

            System.out.println("URLs blocked successfully.");

        } catch (IOException e) {
            System.out.println("Error blocking URLs: " + e.getMessage());
        }
    }

    public static void unBlockURLs() {
        String filePath = "C:\\Windows\\System32\\drivers\\etc" + "/hosts";
        String backupFilePath = filePath + ".bak";
        try {
            Path backupFile = Path.of(backupFilePath);
            Path originalFile = Path.of(filePath);
            Files.move(backupFile, originalFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
