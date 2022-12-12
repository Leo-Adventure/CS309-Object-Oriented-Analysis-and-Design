package AdapterPattern;

public class client {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp4", "hello.mp4");
        audioPlayer.play("vlc", "world.vlc");
        audioPlayer.play("mp3", "!.mp3");
    }
}
