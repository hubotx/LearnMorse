package pl.hubot.dev.learn_morse;

import org.junit.Test;
import pl.hubot.dev.learn_morse.model.Encoder;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EncodeTest {
    @Test
    public final void testEncode() {
        final String expectedResult =
                "._   ._..   ._       __"
                + "   ._       _._   ___  "
                + " _   ._   ._._._   ";
        final String input = "Ala ma kota.";
        Encoder encoder = new Encoder();
        try {
            final String output = encoder.encode(input);
            assertEquals(expectedResult, output);
        } catch (IOException | NoSuchFieldException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public final void testDecode() {
        final String expectedResult =
                "a   l   a       m   a       k   o   t   a   . ";
        final String input =
                "._   ._..   ._       __"
                + "   ._       _._   ___  "
                + " _   ._   ._._._   ";
        Encoder encoder = new Encoder();
        try {
            final String output = encoder.decode(input);
            assertEquals(expectedResult, output);
        } catch (IOException | NoSuchFieldException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
