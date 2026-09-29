
// Adaptee (Mustn't be changed)
    public class LibFlacNativeSdk {
    public static final int FLAC_OK = 0;
    public static final int FLAC_ERR_CONFIG = -10;
    public static final int FLAC_ERR_SYNC = -20;

    public int configureDecoder(int channels, int sampleRate, int bitsPerSample) {
        if (sampleRate < 8000 || sampleRate > 192000) {
            return FLAC_ERR_CONFIG;
        }
        return FLAC_OK;
    }

    public int processStreamBlock(byte[] inputBuffer, int offset, int length, short[] outputBuffer) {
        if (inputBuffer == null || length <= 0) {
            return FLAC_ERR_SYNC;
        }
        for (int i = 0; i < outputBuffer.length; i++) {
            outputBuffer[i] = (short) (i % 256);
        }
        return FLAC_OK;
    }
}
