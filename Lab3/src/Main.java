import MobileCommunication.MobileCommunication;
import VideoLibrary.VideoLibrary;

public class Main {
    public static void main(String[] args) {
        VideoLibrary videoLibraries = new VideoLibrary();
        MobileCommunication mobileCommunication = new MobileCommunication();
        videoLibraries.run();
        mobileCommunication.run();
    }
}