package com.quizer;

import java.io.FileNotFoundException;

/**
 * Main class
 */
public class Main {
    /**
     * Entry point for project
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Quiz quiz;
        try {
            quiz = Quiz.loadFromFile(args[0]);
            quiz.start();
        } catch (FileNotFoundException | InvalidQuizFormatException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Usage: java Main <fileName>");
        }
    }
}
