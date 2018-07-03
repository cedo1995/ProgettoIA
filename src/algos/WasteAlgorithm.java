package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

public class WasteAlgorithm implements Algorithm {
    public final static int LESS_WASTE = 1;
    public final static int WASTE_POINTS_REL = 2;
    public final static int SCORE_MINUS_WASTE = 3;

    private int algorihmVariant = LESS_WASTE;

    @Override
    public Solution computeModel(Model model){
        //model.sortRideStart();

        List<Ride> unassignedRides = new ArrayList<>(model.getRideList());
        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < model.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }
        for(Car car: cars) {
            int currentTime = 0;
            Position currentPosition = new Position(0,0);
            List<Ride> assignedRides = car.getRideList();
            List<Ride> border = new ArrayList<>(unassignedRides);

            while (border.size() > 0){
                Ride bestChoice = null;
                for (int i = 0; i < border.size(); i++) {
                    Ride ride = border.get(i);
                    if(car.testRide(ride)){
                        if(bestChoice == null)
                            bestChoice = ride;
                        else
                            bestChoice = chooseRide(currentTime, currentPosition, bestChoice, ride, model.getBonus());
                    } else
                        border.remove(i);
                }
                if(bestChoice != null) {
                    border.remove(bestChoice);
                    assignedRides.add(bestChoice);
                    unassignedRides.remove(bestChoice);
                    currentTime = Math.max(bestChoice.getStartTime(), currentTime + Position.distance(currentPosition, bestChoice.getStartPos())) + bestChoice.length();
                    currentPosition = bestChoice.getEndPos();
                }
            }

        }
        return new Solution(cars);

    }

    private Ride chooseRide(int currentTime, Position currentPosition, Ride ride1, Ride ride2, int bonus){
        int waste1 = Math.max(ride1.getStartTime() - currentTime, Position.distance(currentPosition, ride1.getStartPos()));
        int waste2 = Math.max(ride2.getStartTime() - currentTime, Position.distance(currentPosition, ride2.getStartPos()));

        int score1 = ride1.length();
        int score2 = ride2.length();
        if(Position.distance(currentPosition, ride1.getStartPos()) <= ride1.getStartTime() - currentTime){
            score1 += bonus;
        }
        if(Position.distance(currentPosition, ride2.getStartPos()) <= ride2.getStartTime() - currentTime){
            score2 += bonus;
        }

        if(algorihmVariant == 1){
            if(waste1 < waste2)
                return ride1;
            else if(waste1 > waste2){
                return ride2;
            } else {
                if(ride1.getEndTime() <= ride2.getEndTime())
                    return ride1;
                else
                    return ride2;
            }

        } else if (algorihmVariant == 2){
            if(waste1 == 0 || waste2 == 0) {
                if (waste1 <= waste2)
                    return ride1;
                else
                    return ride2;
            }

            if(score1/waste1 <= score2/waste2)
                return ride1;
            else
                return ride2;

        } else if (algorihmVariant == 3){
            if(score1 - waste1 <= score2 - waste2)
                return ride1;
            else
                return ride2;

        }
        return null;
    }


    public void changeAlgorithmVariant(int variant){
        if(variant > 0 && variant < 4)
            algorihmVariant = variant;
    }

    /**
     * Implementation of testDescription
     * @return a description of the algorithm
     */
    @Override
    public String testDescription() {
        return "todo";
    }
}
