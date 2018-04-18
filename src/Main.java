import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String path = "C:/Users/Paolo/Downloads/a_example.in";
        ModelParser parser = new ModelParser();
        Model model = parser.parseFile(path);
       // System.out.println(model.toString());
        Agent agent = new Agent(model,new Algorithm1());

        int score = agent.applyAlgorithm().computeScore();

        System.out.println(score);

    }
}
