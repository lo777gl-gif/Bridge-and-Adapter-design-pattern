
// Concrete Implementer #2
public class WavCodec implements AudioCodec {
    @Override
    public boolean supports(String format) {
        return "WAV".equalsIgnoreCase(format);
    }

    @Override
    public void initialize(AudioMetadata metadata) {
        // Placeholder for WAV decoder setup.
    }

    @Override
    public byte[] decodeToPcm(byte[] audio) throws AudioCodecException {
        if (audio == null || audio.length == 0) {
            throw new AudioCodecException("WAV audio is empty");
        }
        return audio;
    }
}
