package algos;

import core.*;

import java.util.List;

/**
 * Concrete Algorithm. This class can perform various rules to compute the solution
 */
public class ConcreteAlgorithm extends AbstractAlgorithm {

    /**
     * Class variables that indicates a selection rule to choose between two rides
     */
    public final static int LESS_WASTE = 0;
    public final static int WASTE_POINTS_REL = 1;
    public final static int WASTE_MINUS_SCORE = 2;
    public final static int BEST_SCORE = 3;

    /**
     * Class variables that indicates an optimization criterion
     */
    public final static int NO_OPTIMIZATION = -1;
    public final static int ADD_IF_BETTER = -2;

    /**
     * Selection rule chosen for this algorithm
     */
    private final int seletionRule;

    /**
     * Optimization rule chosen for this algorithm
     */
    private final int optimizationRule;

    /**
     * Construction of algorithm class
     * @param seletionRule the selection rule to use
     * @param optimizationRule the optimization rule to use
     */
    public ConcreteAlgorithm(int seletionRule, int optimizationRule){
        if(seletionRule < 0 || seletionRule > 3)
            System.out.println("Unknown selection rule");
        if(optimizationRule>=0 || optimizationRule<-2)
            System.out.println("Unknown optimization rule");

        // set rules
        this.seletionRule = seletionRule;
        this.optimizationRule = optimizationRule;
    }



    /**
     * Implementation of testDescription
     * @return a description of the algorithm
     */
    @Override
    public String testDescription() {
        return "todo";
    }

    /**
     * Compares the current best choice to assign to current car with another ride and return the best one.
     * @param model considered model
     * @param currentCar the car that will perform ride
     * @param currentTime time of completion of last ride or 0 if none assigned
     * @param currentPosition end position of last ride or (0,0) if none assigned
     * @param currentBest ride that is the best choice for now, null at the beginning
     * @param alternativeRide ride to be compared with current best choice
     * @param frontier list of possible ride to assign now (DO NOT MODIFY)
     * @return the best choice between current one and the other ride
     */
    @Override
    public Ride compareRides(Model model, Car currentCar, int currentTime, Position currentPosition, Ride currentBest, Ride alternativeRide, List<Ride> frontier) {
        // if current best is null (first loop or there's no assignable ride)
        if(currentBest == null)
            // return the alternative ride
            return alternativeRide;
        // else we have a choice and an alternative (they can both fit)
        else {
            // compute waste of time of the two rides
            // waste is the time spent reaching the starting position plus the eventual time waiting idle to start the ride
            int waste1 = Math.max(currentBest.getStartTime() - currentTime, Position.distance(currentPosition, currentBest.getStartPos()));
            int waste2 = Math.max(alternativeRide.getStartTime() - currentTime, Position.distance(currentPosition, alternativeRide.getStartPos()));

            //initialize score of the two rides with their length
            int score1 = currentBest.length();
            int score2 = alternativeRide.length();
            // add bonus to scores if deserved
            if (Position.distance(currentPosition, currentBest.getStartPos()) <= currentBest.getStartTime() - currentTime) {
                score1 += model.getBonus();
            }
            if (Position.distance(currentPosition, alternativeRide.getStartPos()) <= alternativeRide.getStartTime() - currentTime) {
                score2 += model.getBonus();
            }

            // switch between rules of selection
            switch (seletionRule){
                // Minor waste rule
                case 0 :
                    // if current choice has minor waste
                    if (waste1 < waste2)
                        // return current choice
                        return currentBest;
                    // else if alternative ride has minor waste
                    else if (waste1 > waste2)
                        // return the alternative ride
                        return alternativeRide;
                    // else if the two rides has equal waste choose the one that ends first
                    else if (waste1 + currentBest.length() <=  waste2 + alternativeRide.length())
                        return currentBest;
                    else
                        return alternativeRide;

                // Minor waste / score
                case 1 :
                    // if current choice has minor waste/score
                    if (waste1 / score1 < waste2 / score2)
                        // return current choice
                        return currentBest;
                    // else if alternative ride has minor waste/score
                    else if(waste1 / score1 > waste2 / score2)
                        // return alternative ride
                        return alternativeRide;
                    // else if there is a tie, choose the one that ends first
                    else if(waste1 + currentBest.length() <=  waste2 + alternativeRide.length())
                        return  currentBest;
                    else
                        return alternativeRide;

                // Completion time minus score
                case 2 :
                    // if current ride has minor completion time minus score
                    if (waste1 + currentBest.length() - score1 <  waste2 + alternativeRide.length() - score2)
                        return currentBest;
                        // else if alternative ride has minor completion time minus score
                    else if(waste1 + currentBest.length() - score1 >  waste2 + alternativeRide.length() - score2)
                        // return alternative ride
                        return alternativeRide;
                    // else if there is a tie, choose the one that ends first
                    else if(waste1 + currentBest.length() <=  waste2 + alternativeRide.length())
                        return  currentBest;
                    else
                        return alternativeRide;

                // Higher Score
                case 3:
                    // if current choice has higher score
                    if (score1 > score2) {
                        // return current choice
                        return currentBest;
                        // else if alternative ride has higher score
                    } else if(score1 < score2) {
                        // return alternative ride
                        return alternativeRide;
                        // else if there is a tie, choose the one that ends first
                    } else if(waste1 + currentBest.length() <=  waste2 + alternativeRide.length()) {
                        return currentBest;
                    } else
                        return alternativeRide;

            }
            return null;
        }
    }

    /**
     * Implementation of the abstract method to create a solution. This method switch between optimizing techniques.
     * @param model model we are considering
     * @param cars list of cars with assigned rides
     * @param unassignedRides list of rides that are not yet assigned
     * @return optimized solution
     */
    @Override
    public Solution createSolution(Model model, List<Car> cars, List<Ride> unassignedRides) {
        // switch between optimizing rules
        switch (optimizationRule){
            // No optimization rule
            case -1:
                // return the solution as it is
                return new Solution(cars);

            // Assign if better rule (hill climbing)
            case -2:
                // for every ride that has not been assigned
                for(Ride ride : unassignedRides) {
                    // flag to stop assignment of this ride
                    boolean assigned = false;
                    // for every car
                    for (Car car: cars){
                        // if ride has not been assigned
                        if(! assigned) {
                            // compute score gained with this car as it is
                            int initialScore = car.computeScore(model.getBonus());
                            // for each position in the list of rides assigned to this car
                            for (int i = 0; i < car.getRideList().size(); i++) {
                                // try to assign the ride in this position
                                car.getRideList().add(i, ride);
                                // if the car after the assignment is still legal and the score is equal or higher
                                if (car.isLegal() && car.computeScore(model.getBonus()) >= initialScore) {
                                    // leave the ride in that position and set flag to true
                                    assigned = true;
                                    // break this loop
                                    break;
                                }
                                // if assignment is not legal or score is lover
                                else {
                                    // remove ride from the list
                                    car.getRideList().remove(i);
                                }
                            }
                        }
                    }
                }
                // return the solution
                return new Solution(cars);


        }
        return null;
    }


}
