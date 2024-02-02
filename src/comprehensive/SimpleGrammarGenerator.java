package comprehensive;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class SimpleGrammarGenerator {

    public static void main(String[] args) {
        int numberOfNonTerminals = Integer.parseInt(args[0]);
        try (PrintWriter output = new PrintWriter(new FileWriter("testGrammer.g"))) {
			for (int i = 0; i < numberOfNonTerminals; i++){
                output.println("{");
                if (i == 0) {
                    output.println("<start>");
                        for (int j = 1; j < 6; j++){
                            output.println(j + "<string" + j + ">");
                        }
                }
                else {
                    output.println("<string" + i + ">");
                    for (int j = 1; j < 6; j++) {
                        if (j == new Random().nextInt(5))
                            output.println(j + "<string" + j + ">");
                        else
                            output.println(j);
                    }
                }
                output.println("}\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
