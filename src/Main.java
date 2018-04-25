import algos.Algorithm1;
import core.Agent;
import core.Model;
import core.ModelParser;
import core.Solution;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> paths = new ArrayList<>();

        paths.add("./test_models/a_example.in");
        paths.add("./test_models/b_should_be_easy.in");
        paths.add("./test_models/c_no_hurry.in");
        paths.add("./test_models/d_metropolis.in");
        paths.add("./test_models/e_high_bonus.in");

        int tot = 0;

        for(String path: paths) {
            ModelParser parser = new ModelParser();
            Model model = parser.parseFile(path);
            // System.out.println(model.toString());
            Agent agent = new Agent(model, new Algorithm1());

            Solution solution = agent.applyAlgorithm();
            int score = agent.computeScore(solution);
            System.out.println(score);
            tot += score;
           // System.out.println(solution.toString());
        }

        System.out.println("Total score = "+tot);

    }
}
