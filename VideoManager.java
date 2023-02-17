import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VideoManager {
    static Scanner scanner;
    List<Video> videos = new ArrayList<Video>();

    public VideoManager() {
        this.scanner = new Scanner(System.in);
    }

    public void listVideos() {
        System.out.println("List of videos");

        for (Video video : videos) {
            System.out.println("Price code: " + video.getPriceCode() + "\tTitle: " + video.getTitle());
        }
        System.out.println("End of list");
    }

    void registerVideo() {
        System.out.println("Enter video title to register: ");
        String title = scanner.next();

        System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
        int videoType = scanner.nextInt();

        System.out.println("Enter price code( 1 for Regular, 2 for New Release ):");
        int priceCode = scanner.nextInt();

        Date registeredDate = new Date();
        Video video = new Video(title, videoType, priceCode, registeredDate);
        videos.add(video);
    }
}