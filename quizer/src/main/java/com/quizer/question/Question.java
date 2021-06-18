package com.quizer.question;

/**
 * Question.
 */
public abstract class Question {
    private String description;
    private String answer;

    protected Question(String description, String answer) {
        this.description = description;
        this.answer = answer;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

}
