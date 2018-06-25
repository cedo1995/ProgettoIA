package algos;

import core.Model;
import core.Ride;

import java.util.Comparator;
import java.util.List;

public class ScoredModel extends Model {
    public ScoredModel(int dimI, int dimJ, int bonus, int maxTime, int cars, List<Ride> rideList) {
        super(dimI, dimJ, bonus, maxTime, cars, rideList);
    }

    public ScoredModel(Model model){
        super(model.getDimI(),model.getDimJ(),model.getBonus(),model.getMaxTime(),model.getCarsNumber(),model.getRideList());
        for(int i = 0; i < getRideList().size(); i++){
            Ride ride = getRideList().remove(i);
            ScoredRide scoredRide = new ScoredRide(ride);
            getRideList().add(i, scoredRide);
        }
    }

    public void sortCallsScoreDesc(){       //return a descending sort of rides in terms of their score
        getRideList().sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ((ScoredRide)ride2).getScore() - ((ScoredRide)ride1).getScore();
            }
        });
    }
}
