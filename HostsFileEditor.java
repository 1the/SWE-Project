//<--6-->
package engez;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class HostsFileEditor {

    public static void main(String[] args) {
        String filePath = "C:\\Windows\\System32\\drivers\\etc" + "/hosts";
        String backupFilePath = filePath + ".bak";

        try {
            // Create a backup of the original hosts file
            Path originalFile = Path.of(filePath);
            Path backupFile = Path.of(backupFilePath);
            Files.copy(originalFile, backupFile, StandardCopyOption.REPLACE_EXISTING);

            // Modify the hosts file
            FileWriter writer = new FileWriter(filePath, true);
            writer.write("\n127.0.0.1   example.com"); // Add a new entry
            writer.close();

            System.out.println("Hosts file has been updated successfully.");
        } catch (IOException e) {
            // Restore the original hosts file from the backup
            try {
                Path backupFile = Path.of(backupFilePath);
                Path originalFile = Path.of(filePath);
                Files.move(backupFile, originalFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
