package com.quizer;

public class MultipleChoiceQuestion extends Question{
    private int numOfOptions;
    private String[] options;

    public MultipleChoiceQuestion(String description, String answer) {
        super(description, answer);
    }

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
        return null;
    }

}
