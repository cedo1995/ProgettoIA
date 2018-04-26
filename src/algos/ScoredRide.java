package algos;

import core.Position;
import core.Ride;

public class ScoredRide extends Ride {
    private int score = 0;

    public ScoredRide(Position startPos, Position endPos, int startTime, int endTime) {
        super(startPos, endPos, startTime, endTime);
    }

    public ScoredRide(Ride ride){
        super(ride.getStartPos(), ride.getEndPos(),ride.getStartTime(),ride.getEndTime());
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

}