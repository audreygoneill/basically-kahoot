
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;

public class client {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        //Write the quiz
        writeQuiz quiz = new writeQuiz();
        quiz.generateQuiz();

        System.out.println("\nEnter your name to take the quiz or enter done to exit:\n");
        String input = sc.nextLine();

        if (!input.equals("done")) {

            //If user wants to take the quiz, calls readQuiz method
            readQuiz read = new readQuiz();
            JSONArray results = read.readQuiz(input);

            //Calls printResults method in readQuiz.java to record user's results
            read.printResults(results, input);
        }
    }
}