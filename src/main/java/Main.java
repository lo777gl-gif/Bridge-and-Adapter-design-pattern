import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibFlacNativeSdk flacSdk = new LibFlacNativeSdk();

        AudioCodec pcmCodec = new PcmCodec();
        AudioCodec wavCodec = new WavCodec();
        AudioCodec flacCodec = new FlacCodecAdapter(flacSdk);

        DynamicCodecRegistry registry = new DynamicCodecRegistry(List.of(pcmCodec, wavCodec, flacCodec));

        System.out.println("\n1: BasicAudioPlayer and WAV");
        playBasicAudio(registry, "WAV", new byte[]{10, 20, 30});

        System.out.println("\n2: EqualizedAudioPlayer and FLAC (through Adapter)");
        playEqualizedAudio(registry, "FLAC", new byte[]{1, 2, 3, 4});

        System.out.println("\n3: Unsupportable format");
        playBasicAudio(registry, "MP3", new byte[]{5, 5});
    }


    private static void playBasicAudio(DynamicCodecRegistry registry, String format, byte[] audioData) {
        try {
            AudioCodec codec = registry.resolveCodec(format);

            AudioMetadata metadata = new AudioMetadata(2, 44100, 16);
            codec.initialize(metadata);

            AudioPlayer player = new BasicAudioPlayer(codec);;

            byte[] pcmData = player.processAudio(audioData);
            System.out.println("Got PCM data (bytes): " + pcmData.length);

        } catch (AudioCodecException e) {
            System.err.println("Error in basic audio processing: " + e.getMessage());
        }
    }

    private static void playEqualizedAudio(DynamicCodecRegistry registry, String format, byte[] audioData) {
        try {
            AudioCodec codec = registry.resolveCodec(format);

            AudioMetadata metadata = new AudioMetadata(2, 44100, 16);
            codec.initialize(metadata);

            AudioPlayer player = new EqualizedAudioPlayer(codec, "Rock Preset");

            byte[] pcmData = player.processAudio(audioData);
            System.out.println("Got PCM data (bytes): " + pcmData.length);

        } catch (AudioCodecException e) {
            System.err.println("Error in equalized audio processing: " + e.getMessage());
        }
    }
}