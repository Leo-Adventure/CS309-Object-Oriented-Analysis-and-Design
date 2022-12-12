package AdapterPattern;

public class VlcPlayer implements AdvancedMediaPlayer{

    @Override
    public void playVlc(String filename) {
        System.out.println("Play VLC through " + filename);
    }

    @Override
    public void playMp4(String filename) {
        System.out.println("Not Support");
    }
}
