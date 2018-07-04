package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for an algorithm
 */
public abstract class SimpleCompareAlgorithm implements Algorithm {

    /**
     * Override of compute model.
     * This implementation loops through cars and available rides and calls the abstract method compare rides to choose the best
     * @param model problem's model
     * @return a Solution to the problem
     */
    @Override
    public Solution computeModel(Model model) {
        // list of unassigned Rides of the problem
        List<Ride> unassignedRides = model.getRideList();
        // Initialization of car list for the solution
        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < model.getCarsNumber(); n++){
            // Create a number of car equal to model's car number
            cars.add(new Car(new ArrayList<>()));
        }

        // loop on cars
        for(Car car: cars) {
            // finish boolean flag
            boolean finish = false;
            // list of rides assigned to current car
            List<Ride> assignedRides = car.getRideList();
            // while not finish
            while (!finish) {
                // current best car chosen by the algorithm
                Ride bestChoice = null;
                // for each ride not yet assigned
                for (Ride ride : unassignedRides) {
                    // call abstract comparison
                    bestChoice = compareRides(model, car, bestChoice, ride);
                }
                // when no ride is selected, there is no ride that can fit in the car
                if (bestChoice == null)
                    // set flag to true
                    finish = true;
                // else the best choice is selected
                else{
                    //assign the best ride to the car
                    assignedRides.add(bestChoice);
                    //remove the just assigned ride
                    unassignedRides.remove(bestChoice);
                }
            }
        }
        // return the solution made by current assignment
        return new Solution(cars);
    }

    /**
     * Abstract comparison. Classes that extends this class must override this method to compare rides.
     * @param model current model that may contain useful information
     * @param currentCar current carr, to access previously assigned rides
     * @param currentBest current best ride chosen
     * @param alternativeRide ride to compare with the best choice
     * @return new best ride
     */
    public abstract Ride compareRides(Model model, Car currentCar, Ride currentBest, Ride alternativeRide);

}
