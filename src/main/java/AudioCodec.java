
// Implementator
public interface AudioCodec {
    boolean supports(String format);
    void initialize(AudioMetadata metadata) throws AudioCodecException;
    byte[] decodeToPcm(byte[] compressedAudio) throws AudioCodecException;
}
