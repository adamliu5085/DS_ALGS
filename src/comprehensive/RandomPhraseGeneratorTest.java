package comprehensive;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomPhraseGeneratorTest {

    @Test
    public void testPhraseGen() {
        for (int i = 0; i < 500; i++) {
            ByteArrayOutputStream a = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(a);
            PrintStream old = System.out;
            System.setOut(ps);
            RandomPhraseGenerator.main(new String[]{"C:/Users/adaml/eclipse-workspace/CS2420-Assignments/testGrammar.g", "1"});
            System.out.flush();
            System.setOut(old);
            String output = a.toString();
            String pattern = ("[1-9][1-9]*\r\n");
            assertTrue(output.matches(pattern));
        }
    }

    @Test
    public void testPhraseGen2() {
        for (int i = 0; i < 500; i++) {
            ByteArrayOutputStream a = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(a);
            PrintStream old = System.out;
            System.setOut(ps);
            RandomPhraseGenerator.main(new String[]{"C:/Users/adaml/eclipse-workspace/CS2420-Assignments/src/comprehensive/super_simple.g", "1"});
            System.out.flush();
            System.setOut(old);
            String output = a.toString();
            String pattern = ("The (cat|dog|mouse) (sat|stood) on the (cat|dog|mouse).\r\n");
            assertTrue(output.matches(pattern));
        }
    }

    @Test
    public void testPhraseGen3() {
        for (int i = 0; i < 500; i++) {
            ByteArrayOutputStream a = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(a);
            PrintStream old = System.out;
            System.setOut(ps);
            RandomPhraseGenerator.main(new String[]{"C:/Users/adaml/eclipse-workspace/CS2420-Assignments/src/comprehensive/poetic_sentence.g", "1"});
            System.out.flush();
            System.setOut(old);
            String output = a.toString();
            String pattern = ("The (waves|big yellow flowers|slugs) (sigh (warily|grumpily)|portend like (waves|big yellow flowers|slugs)|die (warily|grumpily)) tonight.\r\n");
            assertTrue(output.matches(pattern));
        }
    }
}


