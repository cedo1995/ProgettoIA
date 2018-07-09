package algos;

import core.Position;
import core.Ride;

/**
 * Extension of Ride, represent a ride with a score assigned
 */
public class ScoredRide extends Ride {
    /**
     * score assigned to ride
     */
    private int score = 0;

    /**
     * Constructor of ScoredRide given parameters
     * @param id ride id
     * @param startPos ride start Position
     * @param endPos ride end Position
     * @param startTime ride start time
     * @param endTime ride end time
     */
    public ScoredRide(int id,Position startPos, Position endPos, int startTime, int endTime) {
        // calling super constructor
        super(id, startPos, endPos, startTime, endTime);
    }

    /**
     * Constructor from an existing Ride
     * @param ride construct a ScoredRide as a copy of ride
     */
    public ScoredRide(Ride ride){
        // calling super constructor
        super(ride.getId(), ride.getStartPos(), ride.getEndPos(),ride.getStartTime(),ride.getEndTime());
    }

    /**
     * Set ride's score
     * @param score int value score
     */
    public void setScore(int score){
        // assign score
        this.score = score;
    }

    /**
     * Get ride's score
     * @return ride's score
     */
    public int getScore(){
        // return score
        return score;
    }

}