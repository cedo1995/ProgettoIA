package core;

/**
 * Class representing the Agent
 */
public class Agent {
    /**
     * Agent keeps a problem's model and an algorithm to solve it
     */
    private Model model;
    private Algorithm algorithm;

    /**
     * Constructor of Agent
     * @param model model of the problem to solve
     * @param algorithm algorithm to solve the problem
     */
    public Agent(Model model, Algorithm algorithm){ //Agent's constructor
        // initialization of attributes
        this.model = model;
        this.algorithm = algorithm;
    }

    /**
     * Constructor without parameters
     */
    public Agent(){ }

    /**
     * Apply algorithm to model and get the solution
     * @return Solution to the problem
     */
    public Solution applyAlgorithm(){
        return algorithm.computeModel(model);
    }

    /**
     * Compute the score of a solution given the current problem's model
     * @param solution solution to be computed
     * @return score as an integer
     */
    public int computeScore(Solution solution){ //calculate score for the solution in the parameter
        // initialize total score
        int score = 0;
        // for each car in solution
        for (Car car: solution.getCarRides()) {
            // for each ride assigned to car
            for(Ride ride : car.getRideList()){
                // if the car takes the ride on time add the bonus
                if(car.computePositionAtTime(ride.getStartTime()).equals(ride.getStartPos())){
                    score += model.getBonus();
                }
                // add the length of the ride as score
                score += ride.length();
            }
        }
        // return the score
        return score;
    }

    /**
     * Set Agent's problem's model
     * @param model model of the problem
     */
    public void setModel(Model model){
        this.model = model;
    }

    /**
     * Set Agent's algorithm
     * @param algo to solve the problem
     */
    public void setAlgorithm(Algorithm algo){
        this.algorithm = algo;
    }
}
