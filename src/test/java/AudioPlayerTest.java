import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AudioPlayerTest {
    @Test
    void basicPlayerDelegatesToCodec() throws Exception {
        AudioCodec codec = mock(AudioCodec.class);
        byte[] input = {1, 2};
        byte[] output = {3, 4};
        when(codec.decodeToPcm(input)).thenReturn(output);

        assertSame(output, new BasicAudioPlayer(codec).processAudio(input));
        verify(codec).decodeToPcm(input);
    }

    @Test
    void equalizedPlayerDelegatesToCodec() throws Exception {
        AudioCodec codec = mock(AudioCodec.class);
        byte[] input = {1};
        byte[] output = {2};
        when(codec.decodeToPcm(input)).thenReturn(output);

        assertSame(output, new EqualizedAudioPlayer(codec, "Rock").processAudio(input));
        verify(codec).decodeToPcm(input);
    }

    @Test
    void adapterTranslatesSdkFailure() {
        LibFlacNativeSdk sdk = mock(LibFlacNativeSdk.class);
        when(sdk.processStreamBlock(any(), anyInt(), anyInt(), any())).thenReturn(LibFlacNativeSdk.FLAC_ERR_SYNC);

        FlacCodecAdapter adapter = new FlacCodecAdapter(sdk);
        assertThrows(AudioCodecException.class, () -> adapter.decodeToPcm(new byte[]{1}));
    }
}
