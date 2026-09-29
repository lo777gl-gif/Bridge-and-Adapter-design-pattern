import java.nio.ByteBuffer;
import java.nio.ByteOrder;

// Concrete Implementer #3 + Adapter
public class FlacCodecAdapter implements AudioCodec {
    private final LibFlacNativeSdk sdk;

    public FlacCodecAdapter(LibFlacNativeSdk sdk) {
        this.sdk = sdk;
    }

    @Override
    public boolean supports(String format) {
        return "FLAC".equalsIgnoreCase(format);
    }

    @Override
    public void initialize(AudioMetadata metadata) throws AudioCodecException {
        int result = sdk.configureDecoder(
                metadata.channels(), metadata.sampleRate(), metadata.bitDepth());

        if (result != LibFlacNativeSdk.FLAC_OK) {
            throw new AudioCodecException("Could not initialize FLAC decoder");
        }
    }

    @Override
    public byte[] decodeToPcm(byte[] audio) throws AudioCodecException {
        if (audio == null || audio.length == 0) {
            throw new AudioCodecException("FLAC audio is empty");
        }

        short[] samples = new short[256];
        int result = sdk.processStreamBlock(audio, 0, audio.length, samples);
        if (result != LibFlacNativeSdk.FLAC_OK) {
            throw new AudioCodecException("Could not decode FLAC audio");
        }

        ByteBuffer buffer = ByteBuffer.allocate(samples.length * 2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        for (short sample : samples) {
            buffer.putShort(sample);
        }
        return buffer.array();
    }
}
