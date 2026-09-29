import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Creating incompatible Adaptee
        LibFlacNativeSdk flacSdk = new LibFlacNativeSdk();

        // 2. Initialization of codecs (Concrete Implementers and Adapter)
        AudioCodec pcmCodec = new PcmCodec();
        AudioCodec wavCodec = new WavCodec();
        AudioCodec flacCodec = new FlacCodecAdapter(flacSdk);

        // 3. Creating of dynamic registers
        DynamicCodecRegistry registry = new DynamicCodecRegistry(List.of(pcmCodec, wavCodec, flacCodec));

        // 4. Demo
        System.out.println("\n1: BasicAudioPlayer и WAV");
        playAudio(registry, "WAV", new byte[]{10, 20, 30}, false);

        System.out.println("\n2: EqualizedAudioPlayer и FLAC (through Adapter)");
        playAudio(registry, "FLAC", new byte[]{1, 2, 3, 4}, true);

        System.out.println("\n3: Unsupportable format");
        playAudio(registry, "MP3", new byte[]{5, 5}, false);
    }


    private static void playAudio(DynamicCodecRegistry registry, String format, byte[] audioData, boolean useEqualizer) {
        try {
            AudioCodec codec = registry.resolveCodec(format);

            AudioMetadata metadata = new AudioMetadata(2, 44100, 16);
            codec.initialize(metadata);

            AudioPlayer player;
            if (useEqualizer) {
                player = new EqualizedAudioPlayer(codec, "Rock Preset");
            } else {
                player = new BasicAudioPlayer(codec);
            }

            byte[] pcmData = player.processAudio(audioData);
            System.out.println("Got PCM data (bytes): " + pcmData.length);

        } catch (AudioCodecException e) {
            System.err.println("Error in audio proccessing: " + e.getMessage());
        }
    }
}