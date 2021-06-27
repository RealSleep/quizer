package com.quizer;

import com.quizer.exception.InvalidQuizFormatException;
import com.quizer.question.FillInQuestion;
import com.quizer.question.MultipleChoiceQuestion;
import com.quizer.question.Question;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Quiz.
 */
public final class Quiz {
    private static final double HUNDREDPERCENT = 100.0;
    private static final int NUMBEROFOPTIONS = 4;

    private String quizName;
    private ArrayList<Question> questions;

    private Quiz() {
        questions = new ArrayList<>();
    }

    /**
     * @param newName the name to set
     */
    public void setName(String newName) {
        this.quizName = newName;
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

    /**
     * Create and return new quiz object with questions in given file.
     * @param fileName
     * @return Quiz
     * @throws InvalidQuizFormatException
     */
    public static Quiz loadFromFile(String fileName) throws InvalidQuizFormatException {
        Quiz quiz = new Quiz();

        File quizFile = new File(fileName);
        try (Scanner readFile = new Scanner(quizFile)) {
            quiz.setName(quizFile.getName());

            while (readFile.hasNext()) {
                String description = readFile.nextLine();
                if ("".equals(description)) {
                    continue;
                }
                String answer = readFile.nextLine();
                switch (quiz.typeQuestion(description)) {
                    case "MCQ" : quiz.addQuestion(quiz.readOptions(readFile, description, answer));
                    break;
                    case "FIQ" : quiz.addQuestion(new FillInQuestion(description, answer.toLowerCase()));
                    break;
                    default : throw new InvalidQuizFormatException("Invalid type question");
                }
            }
        } catch (FileNotFoundException e) {
            throw new InvalidQuizFormatException(e.getMessage());
        }
        return quiz;
    }

    private String typeQuestion(String question) {
        return (!question.contains("{blank}")) ? "MCQ" : "FIQ";
    }

    private Question readOptions(Scanner readFile, String title, String answer) throws InvalidQuizFormatException {
        String[] options = new String[NUMBEROFOPTIONS];
        options[0] = answer;
        try {
            for (int i = 1; i < NUMBEROFOPTIONS; i++) {
                options[i] = readFile.nextLine();
            }
        } catch (NoSuchElementException e) {
            throw new InvalidQuizFormatException("No answer found");
        }
        return new MultipleChoiceQuestion(title, answer, options);
    }

    /**
     * Method run quiz.
     */
    public void start() {
        java.util.Collections.shuffle(questions);

        Scanner scan = new Scanner(System.in);

        int numOfRights = 0;

        System.out.println("======================================================");
        System.out.println("\nWELCOME TO \"" + this.quizName + "\" QUIZ!");
        System.out.println("______________________________________________________\n");

        for (int i = 0; i < this.questions.size(); i++) {
            Question question = this.questions.get(i);
            switch (typeQuestion(question.getDescription())) {
                case "MCQ" : numOfRights += print(scan, question, i);
                break;
                case "FIQ" : numOfRights += print(scan, question, i);
                break;
                default : System.err.println("Something happend wrong, contact support");
            }
        }

        double rightInPercent = numOfRights * HUNDREDPERCENT / questions.size();

        System.out.printf("Correct Answers: %d/%d (%.1f%%)%n", numOfRights, questions.size(), rightInPercent);
    }

    private int print(Scanner scan, Question question, int i) {
        System.out.println((i + 1) + ". " + question.toString());

        System.out.println("-----------------------------------------------------");
        System.out.print("Enter the answer: ");

        String answer = scan.next().toLowerCase();
        boolean isCorrect = false;

        if (question instanceof MultipleChoiceQuestion) {
            char choice = answer.charAt(0);
            isCorrect = choice - 'a' < NUMBEROFOPTIONS;
            isCorrect = isCorrect && choice - 'a' == getIndexAnswer((MultipleChoiceQuestion) question);
        }

        if (question instanceof FillInQuestion) {
            isCorrect = answer.equals(question.getAnswer());
        }

        System.out.printf("%9s!%n%s%n", isCorrect ? "Correct" : "Incorrect", "______________");
        return isCorrect ? 1 : 0;
    }

    private int getIndexAnswer(MultipleChoiceQuestion question) {
        String answer = question.getAnswer();
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            if (answer.equals(options[i])) {
                return i;
            }
        }
        return -1;
    }
}
