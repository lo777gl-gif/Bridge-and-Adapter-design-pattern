
// Abstraction
public abstract class AudioPlayer {
    protected final AudioCodec codec;

    protected AudioPlayer(AudioCodec codec) {
        this.codec = codec;
    }

    public abstract byte[] processAudio(byte[] audio) throws AudioCodecException;
}
