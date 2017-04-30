package pl.hubot.dev.learn_morse;

import pl.hubot.dev.learn_morse.util.ErrorHandler;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class BlocksTrainer extends Trainer {
    @Override
    public void train() {
        initialize();
        executor.execute(() -> {
            try {
                transceiver.blocksMethod();
            } catch (LineUnavailableException
                    | NoSuchFieldException
                    | IOException
                    | IllegalAccessException
                    | InterruptedException ex) {
                ErrorHandler.handleException(ex);
            }
        });
    }
}
