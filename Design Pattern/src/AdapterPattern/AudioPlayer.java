package AdapterPattern;

public class AudioPlayer implements MediaPlayer{
    @Override
    public void play(String audioType, String filename) {
        if(audioType.equals("MP3")){
            System.out.println("Play MP3 through " + filename);
        }else if(audioType.equalsIgnoreCase("MP4") || audioType.equalsIgnoreCase("VLC")){
            MediaAdapter mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, filename);
        }else{
            System.out.println("Not support");
        }
    }
}
