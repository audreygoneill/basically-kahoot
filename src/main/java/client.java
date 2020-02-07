
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;

public class client {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        writeQuiz quiz = new writeQuiz();
        quiz.generateQuiz();

        System.out.println("\nEnter your name to take the quiz or enter done to exit:\n");
        String input = sc.nextLine();

        if (!input.equals("done")) {
            readQuiz read = new readQuiz();
            JSONArray results = read.readQuiz(input);
            read.printResults(results, input);
        }
    }
}
