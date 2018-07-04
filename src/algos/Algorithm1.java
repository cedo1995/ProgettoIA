package algos;

import core.*;

/**
 * Greedy algorithm that compares rides only using score
 * Extends SimpleCompareAlgorithm
 */
public class Algorithm1 extends SimpleCompareAlgorithm {

    /**
     * Override of abstract compareRides
     * @param model current model that may contain useful information
     * @param currentCar current carr, to access previously assigned rides
     * @param currentBest current best ride chosen
     * @param alternativeRide ride to compare with the best choice
     * @return alternativeRide if has an higher score compared to currentBest, currentBest if not.
     */
    @Override
    public Ride compareRides(Model model, Car currentCar, Ride currentBest, Ride alternativeRide){
        // check if the alternativeRide can fit in current car
        if (canTakeCall(currentCar, alternativeRide)) {
            // if there is no current best choice
            if (currentBest == null) {
                // alternative ride becomes best choice
                return  alternativeRide;
            }
            // else there is another best choice
            else {
                // init score of best choice with it's length
                int score1 = currentBest.length();
                // init score of alternative ride with it's length
                int score2 = alternativeRide.length();

                // check if current best choice can be taken with bonus
                if (canTakeWithBonus(currentCar, currentBest)) {
                    // if so add bonus to it's score
                    score1 += model.getBonus();
                }
                // check if alternative choice can be taken with bonus
                if (canTakeWithBonus(currentCar, alternativeRide)) {
                    // if so add bonus to it's score
                    score2 += model.getBonus();
                }
                // if alternative score is greater than current choice's score
                if (score2 > score1)
                    // return alternative ride
                    return alternativeRide;
            }
        }
        // if no alternative ride is returned return current choice
        return currentBest;
    }

    /**
     * Implementation of testDescription
     * @return a description of the algorithm
     */
    @Override
    public String testDescription() {
        return "Greedy algorithm, takes the ride with best score";
    }

    /**
     * Check if a ride can fit in a car
     * @param car that should accept ride
     * @param ride that we want to assign
     * @return boolean true if it can, false if not
     */
    private boolean canTakeCall(Car car, Ride ride){
        // add ride to car's list
        car.getRideList().add(ride);
        // check if car is legal in this way, if it is
        if(car.isLegal()){
            // remove ride from car's list
            car.getRideList().remove(ride);
            // return true
            return true;
        }
        // if it is not legal
        else{
            // remove ride from car's list
            car.getRideList().remove(ride);
            // return false
            return false;
        }
    }

    /**
     * Check if a ride assigned to a car will grant a bonus
     * BE CAREFUL: this method doesn't check if the ride is legal in the car
     *              call it only after canTakeCall method.
     * @param car to check
     * @param ride to check
     * @return boolean true if the ride grants a bonus, false if not
     */
    private boolean canTakeWithBonus(Car car, Ride ride){
        // add ride to car's list
        car.getRideList().add(ride);
        // if position at ride's start time is equal to ride's start Position
        if(car.computePositionAtTime(ride.getStartTime()).equals(ride.getStartPos())) {
            // remove ride from car's list
            car.getRideList().remove(ride);
            // return true
            return true;
        }
        // else at ride's start time car is not in the right position
        else {
            // remove ride from car's list
            car.getRideList().remove(ride);
            // return false
            return false;
        }
    }


}
