package algos;

import core.Problem;
import core.Ride;

import java.util.Comparator;
import java.util.List;

/**
 * Extension of problem to deal with ScoreRide
 */
public class ScoredProblem extends Problem {
    /**
     * Constructor using raw parameters
     * @param dimI height of the map
     * @param dimJ width of the map
     * @param bonus bonus of this problem
     * @param maxTime deadline of the problem
     * @param cars number of available vehicles
     * @param rideList list of rides to assign
     */
    public ScoredProblem(int dimI, int dimJ, int bonus, int maxTime, int cars, List<Ride> rideList) {
        // call super constructor
        super(dimI, dimJ, bonus, maxTime, cars, rideList);
    }

    /**
     * Constructor using an existing Problem
     * @param problem existing problem to copy into a ScoredProblem
     */
    public ScoredProblem(Problem problem){
        // call super constructor
        super(problem.getDimI(), problem.getDimJ(), problem.getBonus(), problem.getMaxTime(), problem.getCarsNumber(), problem.getRideList());
        // convert Rides in ScoredRides
        for(int i = 0; i < getRideList().size(); i++){
            Ride ride = getRideList().remove(i);
            ScoredRide scoredRide = new ScoredRide(ride);
            getRideList().add(i, scoredRide);
        }
    }

    /**
     * Sort rides using their score in decreasing order
     */
    public void sortRideScoreDesc(){
        // sort using comparator
        getRideList().sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ((ScoredRide)ride2).getScore() - ((ScoredRide)ride1).getScore();
            }
        });
    }
}
