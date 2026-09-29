
// Concrete Implementer #1
public class PcmCodec implements AudioCodec {
    @Override
    public boolean supports(String format) {
        return "PCM".equalsIgnoreCase(format);
    }

    @Override
    public void initialize(AudioMetadata metadata) {
        // PCM does not need initialization in this example.
    }

    @Override
    public byte[] decodeToPcm(byte[] audio) {
        return audio;
    }
}
