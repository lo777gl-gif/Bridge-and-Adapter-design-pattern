
// Refined Abstraction #2
public class EqualizedAudioPlayer extends AudioPlayer {
    private final String preset;

    public EqualizedAudioPlayer(AudioCodec codec, String preset) {
        super(codec);
        this.preset = preset;
    }

    @Override
    public byte[] processAudio(byte[] audio) throws AudioCodecException {
        byte[] pcm = codec.decodeToPcm(audio);
        System.out.println("Equalizer preset: " + preset);
        return pcm;
    }
}
