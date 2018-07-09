package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAlgorithm implements Algorithm{

    /**
     * Implementation of compute problem
     * @param problem problem to solve
     * @return a solution of the problem
     */
    @Override
    public Solution solveProblem(Problem problem){
        // list of rides not yet assigned
        List<Ride> unassignedRides = new ArrayList<>(problem.getRideList());
        // initialization of a list of cars for the solution
        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < problem.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }

        // variable used to print percentage only
        int carCount = 0;

        // each car is a sub-problem. For each car
        for(Car car: cars) {
            // initialize time variable to 0
            int currentTime = 0;
            // initialize position variable to (0,0)
            Position currentPosition = new Position(0,0);
            // make a copy of unassigned rides to use as a frontier
            List<Ride> frontier = new ArrayList<>(unassignedRides);

            // while the frontier isn't empty
            while (! frontier.isEmpty()){
                // initialize best ride to add as null
                Ride bestChoice = null;
                // for each ride in the frontier
                for (int i = 0; i < frontier.size(); i++) {
                    // get the ride
                    Ride ride = frontier.get(i);
                    // compute when the ride will start
                    int startsAt = Math.max(ride.getStartTime(), currentTime + Position.distance(currentPosition, ride.getStartPos()));
                    // if the rides ends before his endTime (not using Car::testRide due to performance optimization)
                    if(startsAt + ride.length() <= ride.getEndTime()){
                        // choose best solution comparing two rides
                        bestChoice = compareRides(problem, car ,currentTime, currentPosition, bestChoice, ride, frontier);
                    } else {
                        // if it is not legal remove it
                        frontier.remove(i);
                        // get iterator back one position due to removal
                        i--;
                    }
                }

                // if a best choice has been found
                if(bestChoice != null) {
                    // remove it from the frontier
                    frontier.remove(bestChoice);
                    // assign it to the car
                    car.getRideList().add(bestChoice);
                    // remove it from unassigned rides
                    unassignedRides.remove(bestChoice);
                    // update current time
                    currentTime = Math.max(bestChoice.getStartTime(), currentTime + Position.distance(currentPosition, bestChoice.getStartPos())) + bestChoice.length();
                    // update current position
                    currentPosition = bestChoice.getEndPos();
                }
            }

            // print percentage of completion
            carCount ++;
            int perc = (carCount*100)/ problem.getCarsNumber();
            System.out.print("\r Completed: "+perc+" %" );
        }
        // print
        System.out.println(" ");
        System.out.println("Performing optimization...");
        // return solution created through abstract method
        return createSolution(problem, cars, unassignedRides);
    }

    /**
     * Abstract method to compare rides
     * @param problem considered problem
     * @param currentCar the car that will perform ride
     * @param currentTime time of completion of last ride or 0 if none assigned
     * @param currentPosition end position of last ride or (0,0) if none assigned
     * @param currentBest ride that is the best choice for now, null at the beginning
     * @param alternativeRide ride to be compared with current best choice
     * @param frontier list of possible ride to assign now (DO NOT MODIFY)
     * @return the best ride between the current choice and the alternative
     */
    public abstract Ride compareRides(Problem problem, Car currentCar, int currentTime, Position currentPosition, Ride currentBest, Ride alternativeRide, List<Ride> frontier);

    /**
     * abstract creation of the solution. An optimization algorithm can be used in here
     * @param problem problem we are considering
     * @param cars list of cars with assigned rides
     * @param unassignedRides list of rides that are not yet assigned
     * @return the solution
     */
    public abstract Solution createSolution(Problem problem, List<Car> cars, List<Ride> unassignedRides);
}
