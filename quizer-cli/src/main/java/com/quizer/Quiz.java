package com.quizer;

import java.io.File;
import java.util.ArrayList;

public class Quiz {
    private String quizName;
    private ArrayList<Question> questions;
    private File quizFile;

    private Quiz(String fileName) {
        this.quizFile = new File(fileName);
    }

    /**
     * @param quizName the name to set
     */
    public void setName(String quizName) {
        this.quizName = quizName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return quizName;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public static Quiz loadFromFile(String fileName) {
        return new Quiz(fileName);
    }

    @Override
    public String toString() {
        return null;
    }

    public void start() {
        
    }
}
