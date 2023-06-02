//<4,1,1,2-->
package engez;

import java.io.IOException;
public class ApplicationBlocker {


    

    
    private static final String APP_PATHS_REGISTRY_KEY = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths";

    public static void blockApplications(String... executablePaths) {
        for (String path : executablePaths) {
            try {
                Runtime.getRuntime().exec("reg add \"" + APP_PATHS_REGISTRY_KEY + "\\" + getExecutableName(path) + "\" /v \"Blocked\" /t REG_SZ /d \"\" /f");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Applications blocked successfully.");
    }

    public static void unblockApplications(String... executablePaths) {
        for (String path : executablePaths) {
            try {
                Runtime.getRuntime().exec("reg delete \"" + APP_PATHS_REGISTRY_KEY + "\\" + getExecutableName(path) + "\" /f");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Applications unblocked successfully.");
    }

    private static String getExecutableName(String path) {
        String[] parts = path.split("\\\\");
        return parts[parts.length - 1];
    }

    public static void main(String[] args) {
        // Example usage:
        String[] blockedPaths = {
            "C:\\Program Files\\ExampleApp1\\example.exe",
            "C:\\Program Files\\ExampleApp2\\example.exe"
        };

        // Block applications
        blockApplications(blockedPaths);

        // Unblock applications
        unblockApplications(blockedPaths);
    }
}
