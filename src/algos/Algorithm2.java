package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

public class Algorithm2 implements Algorithm {
    private final int startTimeMult;
    private final int endTimeMult;
    private final int startPosMult;
    private final int endPosMult;
    private final int lengthMult;

    private Position center = new Position(0,0);


    public Algorithm2(int stm, int etm, int spm, int epm, int lm){
        startTimeMult = stm;
        endTimeMult = etm;
        startPosMult = spm;
        endPosMult = epm;
        lengthMult = lm;
    }


    @Override
    public Solution computeModel(Model model) {
        center = new Position(model.getDimI()/2, model.getDimJ()/2);    //center position of map

        ScoredModel scoredModel = new ScoredModel(model);
        for(Ride ride: scoredModel.getRideList()){
            int score = ride.getStartTime()*startTimeMult;
            score += ride.getEndTime()*endTimeMult;
            score += Position.distance(ride.getStartPos(),center)*startPosMult;
            score += Position.distance(ride.getEndPos(),center)*endPosMult;
            score += ride.length()*lengthMult;

            ((ScoredRide) ride).setScore(score);        //score of a ride is given by a combination of different terms
        }

        scoredModel.sortCallsScoreDesc();       //scores in descending order

        List<Ride> unassignedRides = scoredModel.getRideList();
        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < scoredModel.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }

        for(Car car: cars) {
            List<Ride> assignedRides = car.getRideList();       //recharge the already assigned rides to car
            for(int i = 0; i < unassignedRides.size(); i++){    //indexing the unassigned rides
                Ride ride = unassignedRides.remove(i);
                assignedRides.add(ride);
                if(! car.isLegal()){
                    assignedRides.remove(ride);
                    unassignedRides.add(i, ride);
                }
            }
        }

        return new Solution(cars);
    }

    /**
     * Implementation of testDescription
     * @return a description of the algorithm
     */
    @Override
    public String testDescription() {
        return "This algorithm compares two rides using a score ....";
    }
}
