package core;

/**
 * Class representing the Agent
 */
public class Agent {
    /**
     * Agent keeps a problem and an algorithm to solve it
     */
    private Problem problem;
    private Algorithm algorithm;

    /**
     * Constructor of Agent
     * @param problem problem of the problem to solve
     * @param algorithm algorithm to solve the problem
     */
    public Agent(Problem problem, Algorithm algorithm){ //Agent's constructor
        // initialization of attributes
        this.problem = problem;
        this.algorithm = algorithm;
    }

    /**
     * Constructor without parameters
     */
    public Agent(){ }

    /**
     * Apply algorithm to problem and get the solution
     * @return Solution to the problem
     */
    public Solution applyAlgorithm(){
        return algorithm.solveProblem(problem);
    }

    /**
     * Compute the score of a solution given the current problem
     * @param solution solution to be computed
     * @return score as an integer
     */
    public int computeScore(Solution solution){ //calculate score for the solution in the parameter
        // initialize total score
        int score = 0;
        // for each car in solution
        for (Car car: solution.getCarRides())
            // sum score of each car
            score += car.computeScore(problem.getBonus());
        // return the score
        return score;
    }

    /**
     * Set Agent's problem
     * @param problem problem of the problem
     */
    public void setProblem(Problem problem){
        this.problem = problem;
    }

    /**
     * Set Agent's algorithm
     * @param algo to solve the problem
     */
    public void setAlgorithm(Algorithm algo){
        this.algorithm = algo;
    }
}
