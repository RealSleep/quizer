package com.quizer.question;

/**
 * MultipleChoiceQuestion.
 */
public class MultipleChoiceQuestion extends Question {
    private int numOfOptions;
    private String[] options;

    public MultipleChoiceQuestion(String description, String answer) {
        super(description, answer);
    }

    /**
     * Constructor for creating multiple choice question.
     * @param description
     * @param answer
     * @param options
     */
    public MultipleChoiceQuestion(String description, String answer, String[] options) {
        super(description, answer);
        this.options = options;
        this.numOfOptions = options.length;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(String[] options) {
        this.options = options;
        this.numOfOptions = options.length;
    }

    /**
     * @return the options
     */
    public String[] getOptions() {
        return options;
    }

    @Override
    public String toString() {
        String result = super.getDescription() + "\n";
        for (int i = 0; i < numOfOptions; i++) {
            result += String.format("%c) %s\n", 'A' + i, options[i]);
        }
        return result;
    }

}
