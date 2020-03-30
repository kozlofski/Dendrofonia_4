public class Sound {
    private String path = "";
    private int soundFilePosition;      // in samples

    public Sound(String path, int soundFilePosition) {
        this.path = path;
        this.soundFilePosition = soundFilePosition;
    }
}
