
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        writeQuiz quiz = new writeQuiz();
       quiz.generateQuiz();

       System.out.println("\nEnter your name:\n\n");
       String name = sc.nextLine();

       readQuiz read = new readQuiz();
       read.readQuiz(name);

       //let client control when quiz starts
    }
}
