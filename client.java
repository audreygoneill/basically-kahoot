
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class client {
    public static void main(String[] args) throws IOException, ParseException {
       writeQuiz quiz = new writeQuiz();
       quiz.generateQuiz();

       System.out.println("\n\n");

       readQuiz read = new readQuiz();
       read.readQuiz();

       //let client control when quiz starts
    }
}
