import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;

//Referenced https://howtodoinjava.com/library/json-simple-read-write-json-examples/
public class client
{
    private static JSONObject writeQuestion(JSONArray quiz, String question, char tfAnswer){
        Scanner sc = new Scanner(System.in);

        JSONObject q = new JSONObject();

        q.put("Type", "TF");
        q.put("Question", question);
        q.put("Answer", tfAnswer);
        return q;
    }

    private static JSONObject writeQuestion(JSONArray quiz, String question, char MCAnswer, String a, String b, String c, String d) {
        Scanner sc = new Scanner(System.in);

        JSONObject q = new JSONObject();

        q.put("Type", "Multiple Choice");
        q.put("Question", question);
        q.put("Answer", MCAnswer);

        JSONArray choices = new JSONArray();
        choices.add(a);
        choices.add(b);
        choices.add(c);
        choices.add(d);
        q.put("Choices", choices);
        return q;
    }

    private static JSONObject writeQuestion(JSONArray quiz, String question, String answer){
        Scanner sc = new Scanner(System.in);

        JSONObject q = new JSONObject();

        q.put("Type", "Fill in the Blank");
        q.put("Question", question);
        q.put("Answer", answer);
        return q;
    }

    private static JSONArray checkQuestion(JSONArray quiz, String input){
        Scanner sc = new Scanner(System.in);

        if (input.equals("tf")) {
            System.out.println("Enter your question:");
            String question = sc.nextLine();
            System.out.println("Enter the answer, T or F:");
            char answer = sc.nextLine().charAt(0);

            quiz.add(writeQuestion(quiz, question, answer));
        }

        if (input.equals("mc")) {
            System.out.println("Enter your question:");
            String question = sc.nextLine();
            System.out.println("Enter choice A:");
            String a = sc.nextLine();
            System.out.println("Enter choice B:");
            String b = sc.nextLine();
            System.out.println("Enter choice C:");
            String c = sc.nextLine();
            System.out.println("Enter choice D:");
            String d = sc.nextLine();
            System.out.println("Enter the letter of the answer");
            char answer = sc.nextLine().charAt(0);

            quiz.add(writeQuestion(quiz, question, answer, a, b, c, d));
        }

        if (input.equals("blank")) {
            System.out.println("Enter your question:");
            String question = sc.nextLine();
            System.out.println("Enter the answer:");
            String answer = sc.nextLine();

            quiz.add(writeQuestion(quiz, question, answer));
        }

        return quiz;
    }

    private static String checkQuestionType() {
        Scanner sc = new Scanner(System.in);

        System.out.println("For True / False : Enter TF");
        System.out.println("For Multiple Choice : Enter MC");
        System.out.println("For Fill in the Blank : Enter blank");
        System.out.println("If done writing quiz, Enter done.");

        //Collect and sanitize user input
        String input = sc.nextLine().toLowerCase().trim();
        return input;
    }

    public static void main(String[] args) throws IOException
    {
        //Create the JSON Array quiz
        JSONArray quiz = new JSONArray();

        //Adds data to the quiz
        while (true) {
            String type = checkQuestionType();
            if (type.equals("done")) {
                break;
            }
            else {
                quiz = checkQuestion(quiz, type);
            }
        }

        //Writes quiz JSON file
        FileWriter file = new FileWriter("quiz.JSON");
        try {
            file.write(quiz.toJSONString());
            System.out.println("Successfully written quiz!");
            System.out.println("\nJSON Array: " + quiz);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.flush();
            file.close();
        }
    }
}