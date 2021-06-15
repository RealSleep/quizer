package com.quizer;

/**
 * Main class
 */
public class Main {
    /**
     * Entry point for project
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Quiz quiz = Quiz.loadFromFile(args[0]);
        quiz.start();
    }
}
