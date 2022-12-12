package AdapterPattern;

public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String filename) {
        System.out.println("Not support");
    }

    @Override
    public void playMp4(String filename) {
        System.out.println("Play MP4 through " + filename);
    }
}
