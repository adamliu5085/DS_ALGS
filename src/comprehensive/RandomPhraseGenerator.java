package comprehensive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class prints a given number of randomly generated phrases from a given grammar text file.
 *  @author Michael Wadley, Adam Liu
 *  @version December 6th, 2022.
 */
public class RandomPhraseGenerator {

    /**
     * Generates and returns a randomly generated phrase by utilizing a generated map of production rules.
     *
     * @return The randomly generated phrase.
     */
    private static String generatePhrase(HashMap<String, ArrayList<String>> map, Random randomGen) {
        StringBuilder phrase = new StringBuilder(750);
        // Build the phrase.
        buildPhrase("<start>", phrase, map, randomGen);
        // Return the phrase.
        return phrase.toString();
    }

    /**
     * Recursively builds a phrase using a randomly selected rule of the given non-terminal.
     * Iterates through the randomly selected rule and adds terminals to the phrase, all non-terminals are evaluated
     * and added to the phrase before continuing to iterate through the randomly selected rule.
     *
     * @param nonTerminal The nonTerminal to evaluate and add to the phrase.
     * @param phrase The phrase to add the evaluation to.
     */
    private static void buildPhrase(String nonTerminal, StringBuilder phrase, HashMap<String, ArrayList<String>> map, Random randomGen) {
        // Randomly select a rule pertaining to the provided non-terminal
        ArrayList<String> rules = map.get(nonTerminal);
        String rule = rules.get(randomGen.nextInt(rules.size()));
        int ruleLength = rule.length();
        for (int i = 0; i < ruleLength; i++) {
            char currentChar = rule.charAt(i);
            // If the rule character we are looking at is not the beginning of a non-terminal, it is added to the phrase.
            if (currentChar != '<') {
                phrase.append(currentChar);
            // If the rule character is the beginning of a non-terminal...
            } else {
                StringBuilder currentKey = new StringBuilder().append('<');
                // Keep looking at characters until the end of the non-terminal name is reached.
                while ((currentChar = rule.charAt(++i)) != '>') {
                    currentKey.append(currentChar);
                }
                // Evaluate the nonTerminal and add it to the phrase before continuing.
                buildPhrase(currentKey.append('>').toString(), phrase, map, randomGen);
            }
        }
    }

    /**
     * Creates a HashMap instance variable containing production rules from a provided grammar text file.
     * A production rule's name acts as its key in the map, and each of its rules is added to a list of values in the map.
     *
     * @param file The file to read and create a map of production rules for.
     */
    private static void createProductionRuleMap(String file, HashMap<String, ArrayList<String>> map) {
        String currentKey; //The key for our HashMap
        ArrayList<String> currentList; //An array holding the values for our hashMap.
        // Create a BufferedReader to read over the lines in our input file.
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            // Loops over the Strings in the section of curly brackets, assigns keys (non-terminals) and their
            // respective values to a hashMap.
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals("{")) {
                    // If we find the opening curly brace, then the following lines are a production rule.
                    currentKey = reader.readLine();
                    currentList = new ArrayList<>(25);
                    while (!((currentLine = reader.readLine()).equals("}"))) {
                        currentList.add(currentLine);
                        // Adds the production rules to the ArrayList otherwise continue searching through the file.
                    }
                    map.put(currentKey, currentList); // Add the non-terminal key and ArrayList values to the hashMap.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A program to generate and print by line, a given number of randomly generated phrases created from a provided grammar text file of specific format.
     *
     * @param args A list of String arguments, with the first argument being the filepath and name of the grammar file you wish to use.
     *             The second argument being the number of phrases you wish to randomly generate.
     */
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> map = new HashMap<>(50);
        Random randomGen = new Random();
        // Create a hashmap of production rules.
        createProductionRuleMap(args[0], map);
        // Generate a given number of phrases printed per line.
        int iterations = Integer.parseInt(args[1]);
        for (int i = 0; i < iterations; i++)
            System.out.println(generatePhrase(map, randomGen));
    }

}
