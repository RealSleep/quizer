package com.quizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Quiz {
    private String quizName;
    private ArrayList<Question> questions;

    private Quiz() {
        questions = new ArrayList<>();
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

    public static Quiz loadFromFile(String fileName) throws FileNotFoundException, InvalidQuizFormatException {
        Quiz quiz = new Quiz();

        File quizFile = new File(fileName);
        Scanner readFile = new Scanner(quizFile);
        quiz.setName(quizFile.getName());

        while (readFile.hasNext()) {
            String descriptionString = readFile.nextLine();
            if (descriptionString.equals("")) continue;
            switch (quiz.typeQuestion(descriptionString)) {
                case "MCQ" : quiz.addQuestion(quiz.createMCQ(readFile, descriptionString));break;
                case "FIQ" : quiz.addQuestion(quiz.createFIQ(readFile, descriptionString));break;
            }
        }
        
        return quiz;
    }

    private String typeQuestion(String question) {
        return (!question.contains("{blank}"))? "MCQ":"FIQ" ;//identify type of question
    }

    private Question createFIQ(Scanner readFile, String descriptionString) throws InvalidQuizFormatException {
        String answer = null;
        try{
            answer = readFile.nextLine().toLowerCase();
        }catch (NoSuchElementException e) {
            throw new InvalidQuizFormatException("No answer found");
        }

        return new FillInQuestion(descriptionString, answer);
    }

    private Question createMCQ(Scanner readFile, String descriptionString) throws InvalidQuizFormatException {
        String[] options = new String[4];
        for(int i = 0; i < options.length; i++) {
            try{
                options[i] = readFile.nextLine();
            }catch(NoSuchElementException e) {
                throw new InvalidQuizFormatException("Not enough answers");
            }
            
        }
        return new MultipleChoiceQuestion(descriptionString, options[0], options);
    }

    @Override
    public String toString() {
        return null;
    }

    public void start() {
        java.util.Collections.shuffle(questions);

        Scanner scan = new Scanner(System.in);

        int numOfRights = 0;

        System.out.println("======================================================");
        System.out.println("\nWELCOME TO \""+this.quizName+"\" QUIZ!");
        System.out.println("______________________________________________________\n");

        for(int i = 0 ; i < (this.questions).size() ; i++) {

            Question question = this.questions.get(i);

            switch (typeQuestion(question.getDescription())) {
                case "MCQ" : numOfRights += printMCQ((MultipleChoiceQuestion) question, i, scan); break;
                case "FIQ" : numOfRights += printFIQ((FillInQuestion) question, i, scan); break;
            }
            
        }

        double numOfRightsInPercent = numOfRights*100.0/questions.size();
        
        System.out.printf("Correct Answers: %d/%d (%.1f%%)\n", numOfRights, questions.size(), numOfRightsInPercent);
    }

    private int printMCQ(MultipleChoiceQuestion question, int i, Scanner scan) {
        System.out.println((i+1)+". "+question.toString());

        int indexCorrAns = getIndexAnswer(question);
        System.out.println("-----------------------------------------------------");
        System.out.print("Enter the choice: ");

        String answer = scan.next();
        int indexAnswer = answer.charAt(0) - 'A';

        if(answer.length()==1 && answer.charAt(0)>='A' && answer.charAt(0)<='D' && indexAnswer == indexCorrAns) {
            System.out.println("Correct!");
            System.out.println("______________________________________________________\n"); 
            return 1;
        }else {
            System.out.println("Incorrect!");
            System.out.println("______________________________________________________\n");
            return 0;
        }

    }

    private int printFIQ(FillInQuestion question, int i, Scanner scan) {
        System.out.println("\n"+ (i+1)+". " +question.toString());

        System.out.println("-----------------------------------------------------");
        System.out.print("Type your answer: ");

        String answer = scan.next().toLowerCase();

        if(answer.equals(question.getAnswer())) {
            System.out.println("Correct!");
            System.out.println("______________________________________________________\n");
            return 1;
        }else{
            System.out.println("Incorrect!");
            System.out.println("______________________________________________________\n");
            return 0;
        }
    }

    private int getIndexAnswer(MultipleChoiceQuestion question) {
        String answer = question.getAnswer();
        String[] options = question.getOptions();
        for(int i = 0; i < options.length; i++){
            if(answer.equals(options[i])){
                return i;
            }
        }

        return -1;
    }
}
