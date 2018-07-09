package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Experimental algorithm with linear time complexity, assigns a score to a ride obtained only from information know a priori
 */
public class LinearTimeTest implements Algorithm {
    /**
     * Multipliers for each aspect considered
     */
    private final int startTimeMult;
    private final int endTimeMult;
    private final int startPosMult;
    private final int endPosMult;
    private final int lengthMult;

    /**
     * Center position of the map
     */
    private Position center = new Position(0,0);


    /**
     * Constructor that requires multipliers
     * @param stm Start time multiplier
     * @param etm End time multiplier
     * @param spm Start position distance from map center multiplier
     * @param epm End position distance from map center multiplier
     * @param lm Length of the ride
     */
    public LinearTimeTest(int stm, int etm, int spm, int epm, int lm){
        // fields assignment
        startTimeMult = stm;
        endTimeMult = etm;
        startPosMult = spm;
        endPosMult = epm;
        lengthMult = lm;
    }


    /**
     * Override of solveProblem method
     * @param problem problem to solve
     * @return a solution
     */
    @Override
    public Solution solveProblem(Problem problem) {
        // compute center position of the map
        center = new Position(problem.getDimI()/2, problem.getDimJ()/2);    //center position of map

        // create a scored problem, that is a problem with scored rides
        ScoredProblem scoredProblem = new ScoredProblem(problem);

        // set a score to each Ride
        for(Ride ride: scoredProblem.getRideList()){
            // compute score summing data * multiplier
            int score = ride.getStartTime()*startTimeMult;
            score += ride.getEndTime()*endTimeMult;
            score += Position.distance(ride.getStartPos(),center)*startPosMult;
            score += Position.distance(ride.getEndPos(),center)*endPosMult;
            score += ride.length()*lengthMult;

            // set score to ride (casting to scored ride)
            ((ScoredRide) ride).setScore(score);        //score of a ride is given by a combination of different terms
        }

        // sort rides with descending score value
        scoredProblem.sortRideScoreDesc();       //scores in descending order

        // List of rides not yet assigned
        List<Ride> unassignedRides = scoredProblem.getRideList();
        // Initializing list of cars
        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < scoredProblem.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }

        // for each car
        for(Car car: cars) {
            // for each unassigned ride
            for(int i = 0; i < unassignedRides.size(); i++){
                // get the ride
                Ride ride = unassignedRides.get(i);
                // try to assign the ride in tail
                car.getRideList().add(ride);
                // if the assignment is't legal
                if(! car.isLegal())
                    // remove ride from assignments
                    car.getRideList().remove(ride);
                else{
                    // else remove from unassigned
                    unassignedRides.remove(i);
                    // take back iterator
                    i--;
                }
            }
        }
        // return solution
        return new Solution(cars);
    }
}
