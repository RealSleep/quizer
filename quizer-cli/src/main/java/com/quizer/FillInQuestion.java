package com.quizer;

public class FillInQuestion extends Question {
    public FillInQuestion(String description, String answer) {
        super(description, answer);
    }

    @Override
    public String toString() {
        return super.getDescription().replace("{blank}", "___________");
    }
}
