import algos.*;
import core.*;

import java.util.ArrayList;

public class Main {

    /**
     * Main of the program
     * @param args
     */

    public static void main(String[] args) {
        // the Agent
        Agent agent = new Agent();
        // run some tests
        //runTests(agent,new LinearTimeTest(1,1, 1,1,1))      //1000000
        //runTests(agent, new ConcreteAlgorithm(ConcreteAlgorithm.LESS_WASTE,ConcreteAlgorithm.NO_OPTIMIZATION));             //39912000
        //runTests(agent, new ConcreteAlgorithm(ConcreteAlgorithm.LESS_WASTE,ConcreteAlgorithm.ADD_IF_BETTER));               //40039000
        //runTests(agent, new ConcreteAlgorithm(ConcreteAlgorithm.WASTE_POINTS_REL,ConcreteAlgorithm.NO_OPTIMIZATION));       //38131000
        //runTests(agent,new ConcreteAlgorithm(ConcreteAlgorithm.WASTE_POINTS_REL,ConcreteAlgorithm.ADD_IF_BETTER));          //39204603
        //runTests(agent,new ConcreteAlgorithm(ConcreteAlgorithm.WASTE_MINUS_SCORE,ConcreteAlgorithm.NO_OPTIMIZATION));       //48684000
        runTests(agent,new ConcreteAlgorithm(ConcreteAlgorithm.WASTE_MINUS_SCORE,ConcreteAlgorithm.ADD_IF_BETTER));           //48800000
        //runTests(agent, new ConcreteAlgorithm(ConcreteAlgorithm.BEST_SCORE,ConcreteAlgorithm.NO_OPTIMIZATION));             //37988302
        //runTests(agent, new ConcreteAlgorithm(ConcreteAlgorithm.BEST_SCORE,ConcreteAlgorithm.ADD_IF_BETTER));               //43997763
    }

    /**
     * Run a example tests given the agent and the algorithm
     * @param agent the agent
     * @param algo the algorithm
     */
    private static void runTests(Agent agent, Algorithm algo){
        // set the agent's algorithm
        agent.setAlgorithm(algo);

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
            // parse file to create the problem
            ProblemParser parser = new ProblemParser();
            Problem problem = parser.parseFile(path);

            // print problem info
            problem.printProblemInfo();

            // set the problem to agent
            agent.setProblem(problem);

            // let the agent compute a solution
            Solution solution = agent.applyAlgorithm();
            // compute the score of the solution
            int score = agent.computeScore(solution);
            // print partial score
            System.out.println("Partial score = " + score + "\n\n");
            // add partial score to total
            tot += score;
        }
        // print total score
        System.out.println(" ");
        System.out.println("TOTAL SCORE = "+tot);
    }
}
