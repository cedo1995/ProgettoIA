package algos;

import core.Position;
import core.Ride;

public class ScoredRide extends Ride {
    private int score = 0;

    public ScoredRide(int id,Position startPos, Position endPos, int startTime, int endTime) {
        super(id, startPos, endPos, startTime, endTime);
    }

    public ScoredRide(Ride ride){
        super(ride.getId(), ride.getStartPos(), ride.getEndPos(),ride.getStartTime(),ride.getEndTime());
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

}