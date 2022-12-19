package AdapterPattern;

public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;// Adaptee Class

    public MediaAdapter(String autoType) {
        if (autoType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        }else if(autoType.equalsIgnoreCase("mp4")){
            advancedMediaPlayer = new Mp4Player();
        }
    }

    public void play(String autoType, String filename) {
        if (autoType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(filename);
        } else if (autoType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(filename);
        }
    }
}
