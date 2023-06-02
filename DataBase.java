//<--5-->
package engez;

import java.util.*;

public class DataBase {

    public static HashMap<String, String> accounts = new HashMap<>();
    public static HashMap<String, String> Websites = new HashMap<>();
    public static HashMap<String, String> BlockedWebsites = new HashMap<>();
    public static HashMap<String, String> BlockedApps = new HashMap<>();
    public static HashMap<String, String> Apps = new HashMap<>();
    public static String[] BlockedWebsitesList;
    public static String[] BlockedAppsList;
    public static ArrayList<Task> tasksList = new ArrayList<>();
    public static ArrayList<Note> notesList = new ArrayList<>();

    DataBase() {
        accounts.put("Alaa@gmail.com", "1900104");
        accounts.put("Tasbeeh@gmail.com", "1900134");
        accounts.put("Omar@gmail.com", "1900249");
        accounts.put("Ayman@gmail.com", "1900124");
        accounts.put("Mohammed@gmail.com", "1600267");
        accounts.put("1", "1");

        // add websites and its URL to the hashmap
        Websites.put("Facebook", "www.facebook.com");
        Websites.put("Whatsapp", "www.whatsapp.com");
        Websites.put("Telegram", "www.telegram.com");
        Websites.put("Instagram", "www.instagram.com");
        Websites.put("Twitter", "www.twitter.com");
        Websites.put("Youtube", "www.youtube.com");
        Websites.put("Netflix", "www.netflix.com");
        Websites.put("Yahoo", "www.yahoo.com");
        Websites.put("Bing", "www.bing.com");
        Websites.put("Amazon", "www.amazon.com");
        Websites.put("Ebay", "www.ebay.com");
        Websites.put("Aliexpress", "www.aliexpress.com");
        Websites.put("Hulu", "www.hulu.com");
        Websites.put("Twitch", "www.twitch.com");
        Websites.put("Reddit", "www.reddit.com");
        Websites.put("Wikipedia", "www.wikipedia.com");
        Websites.put("Linkedin", "www.linkedin.com");
        Websites.put("Spotify", "www.spotify.com");
        Websites.put("Groove Music", "www.groovemusic.com");
        Websites.put("Soundcloud", "www.soundcloud.com");
        Websites.put("Apple Music", "www.applemusic.com");
        Websites.put("Deezer", "www.deezer.com");

        // add apps and its path to the hashmap
        Apps.put("Telegram", "C:\\Users\\oed51\\AppData\\Roaming\\Telegram Desktop\\Telegram.exe");
        Apps.put("Photoshop", "C:\\Program Files\\Adobe\\Adobe Photoshop 2023\\Photoshop.exe");
        Apps.put("Google Chrome", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
    }

    public static HashMap<String, String> getAccounts() {
        return accounts;
    }

    public static HashMap<String, String> getWebsites() {
        return Websites;
    }

    public static void BlockWebsiteFromTheComputer(String website) {
        BlockedWebsites.put(website, Websites.get(website));
    }

}
