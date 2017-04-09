package pl.hubot.dev.learn_morse;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Test runner.
 */
public final class TestRunner {
    /**
     * Constructor.
     */
    private TestRunner() {
    }

    /**
     * Entry point for test runner.
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        Result result = JUnitCore.runClasses(EncodeTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}

