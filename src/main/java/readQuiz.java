//reference: https://www.geeksforgeeks.org/parse-json-java/

import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.Collections;


public class readQuiz { //this class will take the JSON file quiz created in writeQuiz.java and read it out to the user 1 by 1

    public static Scanner sc = new Scanner(System.in);

    public static JSONArray readQuiz(String name) throws IOException, ParseException {
        // parsing file "quiz.json"
        Object obj = new JSONParser().parse(new FileReader("quiz.JSON")); //read the previously created quiz

        JSONObject jo = (JSONObject) obj; //make object to parse with
        int totalQuestions = 0;
        int numberCorrect = 0;

        JSONArray quiz = (JSONArray) jo.get("Questions: "); //access the questions
        Collections.shuffle(quiz); //randomize question order

        System.out.print("Welcome to the quiz, " + name + "! Please follow these directions:\nFor \"TF\" questions, answer by typing T for true or F for false.\n"
        + "If a question is labelled \"Multiple Choice,\" answer A, B, C or D.\nLastly, for \"Fill in the Blank\" questions, just type out the answer.\n"
                + "Hit enter to move on to the next question. Good Luck!\n\n");

        JSONArray results = new JSONArray();

        quiz.forEach(item -> { //iterate through questions
            int countCorrect;
            int countTotal;

            JSONObject x = (JSONObject) item;
            System.out.println(x.get("Type"));
            System.out.println(x.get("Question"));

            if (x.get("Type").equals("Multiple Choice")){
                JSONArray choices = (JSONArray) x.get("Choices");
                choices.forEach(choice -> {
                    System.out.println(choice);
                });
            }

            System.out.println("Answer here: ");
            String userAnswer = sc.nextLine().toLowerCase().trim(); //sanitize user answer

            //Create record of question, correct answer, and user answer
            JSONObject question = new JSONObject();
            question.put("Question", x.get("Question"));
            question.put("Answer", x.get("Answer"));
            question.put("Response", userAnswer);

            if (userAnswer.equals(x.get("Answer"))){ //output whether user answered correctly
                System.out.println("Correct!\n");
            }
            else {
                System.out.println("Incorrect :(\n");
            }

            results.add(question);
            }
        );

        return results;
    }

    public static void printResults(JSONArray results, String name) throws IOException {
        String fileName = name + "_results.JSON";
        FileWriter file = new FileWriter(fileName);

        try {
            file.write(results.toJSONString());
            System.out.println("Successfully written results!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.flush();
            file.close();
        }

        System.out.println("Would you like to see your results? Enter yes or no:");
        if (sc.nextLine().toLowerCase().equals("no")) {
            System.out.println("Your results have been recorded. Goodbye!");
        }
        else {
            System.out.println(results);
        }
    }
}
