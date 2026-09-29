import java.util.List;

// Dynamic implementor selection
public class DynamicCodecRegistry {
    private final List<AudioCodec> codecs;

    public DynamicCodecRegistry(List<AudioCodec> codecs) {
        this.codecs = codecs;
    }

    public AudioCodec resolveCodec(String format) throws AudioCodecException {
        for (AudioCodec codec : codecs) {
            if (codec.supports(format)) {
                return codec;
            }
        }
        throw new AudioCodecException("Unsupported audio format: " + format);
    }
}
