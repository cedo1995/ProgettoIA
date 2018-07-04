import algos.*;
import core.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    /**
     * Main of the program
     * @param args
     */
    public static void main(String[] args) {
        // the Agent
        Agent agent = new Agent();

        // run some test
        runTests(agent, new WasteAlgorithm(WasteAlgorithm.WASTE_MINUS_SCORE));
        //runTests(agent, new WasteAlgorithm2());
    }

    /**
     * Run a example tests given the agent and the algorithm
     * @param agent the agent
     * @param algo the algorithm
     */
    private static void runTests(Agent agent, Algorithm algo){
        // set the agent's algorithm
        agent.setAlgorithm(algo);
        // print some information about algorithm
        System.out.println(algo.testDescription());

        // initialize an array of relative paths and add the example files
        ArrayList<String> paths = new ArrayList<>();

        paths.add("./test_models/a_example.in");
        paths.add("./test_models/b_should_be_easy.in");
        paths.add("./test_models/c_no_hurry.in");
        paths.add("./test_models/d_metropolis.in");
        paths.add("./test_models/e_high_bonus.in");

        // initialize total score
        int tot = 0;
        // for every file
        for(String path: paths) {
            // parse file to create the model
            ModelParser parser = new ModelParser();
            Model model = parser.parseFile(path);

            // set the model to agent
            agent.setModel(model);

            // let the agent compute a solution
            Solution solution = agent.applyAlgorithm();
            // compute the score of the solution
            int score = agent.computeScore(solution);
            // print partial score
            System.out.println(score);
            // add partial score to total
            tot += score;
        }
        // print total score
        System.out.println("Total score = "+tot);
    }
}
