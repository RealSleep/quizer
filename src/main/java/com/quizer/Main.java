package com.quizer;

import com.quizer.exception.InvalidQuizFormatException;

/**
 * Main class.
 */
public final class Main {
    private Main() {
        throw new IllegalStateException();
    }

    /**
     * Entry point for project.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Quiz quiz;
        try {
            quiz = Quiz.loadFromFile(args[0]);
            quiz.start();
        } catch (InvalidQuizFormatException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Usage: java Main <filePath> |\n java -jar quizer-<version>.jar <filePath>");
        }
    }
}
