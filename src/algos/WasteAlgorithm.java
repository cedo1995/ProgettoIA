package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

public class WasteAlgorithm extends AdvancedCompareAlgorithm {
    public final static int LESS_WASTE = 0;
    public final static int WASTE_POINTS_REL = 1;
    public final static int WASTE_MINUS_SCORE = 2;

    private final int seletionRule;

    public WasteAlgorithm(int seletionRule){
        if(seletionRule >= 0 && seletionRule <= 2){
            this.seletionRule = seletionRule;
        }
        else{
            this.seletionRule = 0;
        }
    }


//    @Override
//    public Solution computeModel(Model model){
//        //model.sortRideStart();
//
//        List<Ride> unassignedRides = new ArrayList<>(model.getRideList());
//        List<Car> cars = new ArrayList<>();
//        for(int n = 0; n < model.getCarsNumber(); n++){
//            cars.add(new Car(new ArrayList<>()));
//        }
//        for(Car car: cars) {
//            int currentTime = 0;
//            Position currentPosition = new Position(0,0);
//            List<Ride> assignedRides = car.getRideList();
//            List<Ride> frontier = new ArrayList<>(unassignedRides);
//
//            while (frontier.size() > 0){
//                Ride bestChoice = null;
//                for (int i = 0; i < frontier.size(); i++) {
//                    Ride ride = frontier.get(i);
//                    if(car.testRide(ride)){
//                        if(bestChoice == null)
//                            bestChoice = ride;
//                        else
//                            bestChoice = chooseRide(currentTime, currentPosition, bestChoice, ride, model.getBonus());
//                    } else
//                        frontier.remove(i);
//                }
//                if(bestChoice != null) {
//                    frontier.remove(bestChoice);
//                    assignedRides.add(bestChoice);
//                    unassignedRides.remove(bestChoice);
//                    currentTime = Math.max(bestChoice.getStartTime(), currentTime + Position.distance(currentPosition, bestChoice.getStartPos())) + bestChoice.length();
//                    currentPosition = bestChoice.getEndPos();
//                }
//            }
//
//        }
//        return new Solution(cars);
//
//    }
//
//    private Ride chooseRide(int currentTime, Position currentPosition, Ride ride1, Ride ride2, int bonus){
//        int waste1 = Math.max(ride1.getStartTime() - currentTime, Position.distance(currentPosition, ride1.getStartPos()));
//        int waste2 = Math.max(ride2.getStartTime() - currentTime, Position.distance(currentPosition, ride2.getStartPos()));
//
//        int score1 = ride1.length();
//        int score2 = ride2.length();
//        if(Position.distance(currentPosition, ride1.getStartPos()) <= ride1.getStartTime() - currentTime){
//            score1 += bonus;
//        }
//        if(Position.distance(currentPosition, ride2.getStartPos()) <= ride2.getStartTime() - currentTime){
//            score2 += bonus;
//        }
//
//        if(algorihmVariant == 1){
//            if(waste1 < waste2)
//                return ride1;
//            else if(waste1 > waste2){
//                return ride2;
//            } else {
//                if(ride1.getEndTime() <= ride2.getEndTime())
//                    return ride1;
//                else
//                    return ride2;
//            }
//
//        } else if (algorihmVariant == 2){
//            if(waste1 == 0 || waste2 == 0) {
//                if (waste1 <= waste2)
//                    return ride1;
//                else
//                    return ride2;
//            }
//
//            if(score1/waste1 <= score2/waste2)
//                return ride1;
//            else
//                return ride2;
//
//        } else if (algorihmVariant == 3){
//            if(score1 - waste1 <= score2 - waste2)
//                return ride1;
//            else
//                return ride2;
//
//        }
//        return null;
//    }


    /**
     * Implementation of testDescription
     * @return a description of the algorithm
     */
    @Override
    public String testDescription() {
        return "todo";
    }

    @Override
    public Ride compareRides(Model model, Car currentCar, int currentTime, Position currentPosition, Ride currentBest, Ride alternativeRide) {
        if(currentBest == null)
            return alternativeRide;
        else {
            int waste1 = Math.max(currentBest.getStartTime() - currentTime, Position.distance(currentPosition, currentBest.getStartPos()));
            int waste2 = Math.max(alternativeRide.getStartTime() - currentTime, Position.distance(currentPosition, alternativeRide.getStartPos()));

            int score1 = currentBest.length();
            int score2 = alternativeRide.length();
            if (Position.distance(currentPosition, currentBest.getStartPos()) <= currentBest.getStartTime() - currentTime) {
                score1 += model.getBonus();
            }
            if (Position.distance(currentPosition, alternativeRide.getStartPos()) <= alternativeRide.getStartTime() - currentTime) {
                score2 += model.getBonus();
            }

            switch (seletionRule){
                case 0 :
                    if (waste1 < waste2) {
                        return currentBest;
                    } else if (waste1 > waste2) {
                        return alternativeRide;
                    } else if (currentBest.getEndTime() <= alternativeRide.getEndTime()){
                        return currentBest;
                    } else
                        return alternativeRide;

                case 1 :
                    if (waste1 / score1 < waste2 / score2)
                        return currentBest;
                    else if(waste1 / score1 > waste2 / score2) {
                        return alternativeRide;
                    } else if(currentBest.getEndTime() > alternativeRide.getEndTime()){
                        return  alternativeRide;
                    }
                    return currentBest;

                case 2 :
                    if (waste1 - 1.2*score1 <=  waste2 - 1.2*score2)
                        return currentBest;
                    else
                        return alternativeRide;
            }
            return null;
        }
    }
}
