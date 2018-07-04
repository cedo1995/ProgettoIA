package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

public abstract class AdvancedCompareAlgorithm implements Algorithm{

    @Override
    public Solution computeModel(Model model){
        List<Ride> unassignedRides = new ArrayList<>(model.getRideList());
        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < model.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }
        for(Car car: cars) {
            int currentTime = 0;
            Position currentPosition = new Position(0,0);
            List<Ride> assignedRides = car.getRideList();
            List<Ride> frontier = new ArrayList<>(unassignedRides);

            while (frontier.size() > 0){
                Ride bestChoice = null;
                for (int i = 0; i < frontier.size(); i++) {
                    Ride ride = frontier.get(i);
                    if(car.testRide(ride)){
                            bestChoice = compareRides(model, car ,currentTime, currentPosition, bestChoice, ride);
                    } else
                        frontier.remove(i);
                }
                if(bestChoice != null) {
                    frontier.remove(bestChoice);
                    assignedRides.add(bestChoice);
                    unassignedRides.remove(bestChoice);
                    currentTime = Math.max(bestChoice.getStartTime(), currentTime + Position.distance(currentPosition, bestChoice.getStartPos())) + bestChoice.length();
                    currentPosition = bestChoice.getEndPos();
                }
            }

        }
        return new Solution(cars);

    }

    public abstract Ride compareRides(Model model, Car currentCar, int currentTime, Position currentPosition, Ride currentBest, Ride alternativeRide);
}
