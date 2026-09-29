
// Refined Abstraction #1
public class BasicAudioPlayer extends AudioPlayer {
    public BasicAudioPlayer(AudioCodec codec) {
        super(codec);
    }

    @Override
    public byte[] processAudio(byte[] audio) throws AudioCodecException {
        return codec.decodeToPcm(audio);
    }
}
