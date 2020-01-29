//reference: https://www.geeksforgeeks.org/parse-json-java/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.Collections;


public class readQuiz { //this class will take the JSON file quiz created in writeQuiz.java and read it out to the user 1 by 1

    public static void readQuiz() throws IOException, ParseException {
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("quiz.JSON")); //read the previously created quiz

        JSONObject jo = (JSONObject) obj; //make object to parse with

        Scanner sc = new Scanner(System.in);

        JSONArray quiz = (JSONArray) jo.get("Questions: "); //access the questions
        Collections.shuffle(quiz); //randomize question order

        System.out.print("Welcome to the quiz! Please follow these directions:\nFor \"TF\" questions, answer by typing T for true or F for false.\n"
        + "If a question is labelled \"Multiple Choice,\" answer A,B,C or D.\nLastly, for \"Fill in the Blank\" questions, just type out the answer.\n"
                + "Hit enter to move on to the next question. Good Luck!\n\n");

        quiz.forEach(item -> { //iterate through questions
            JSONObject x = (JSONObject) item;
            System.out.println(x.get("Type"));
            System.out.println("Question: " + x.get("Question"));
            if (x.get("Type").equals("Multiple Choice")){
                System.out.println(x.get("Choices"));
            }
            System.out.println("Answer here: ");
            String userAnswer = sc.nextLine().toLowerCase().trim(); //take user answer
            if (userAnswer.equals(x.get("Answer"))){ //output whether user answered correctly
                System.out.println("Correct!\n");
            }
            else {
                System.out.println("Incorrect :(\n");
            }
        });

        //this is really basic so here's a list of thing i want to work on
        //format the quiz to be more aesthetically pleasing
        //created a method to ask the user what kind of questions they want to answer (just mc, just blank, all, etc)
        //list the multiple choice options so that the user sees a,b,c,d and knows to answer with a letter
        //maybe print instructions to the screen before the quiz so that it is more user friendly

    }
}
