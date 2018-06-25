package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

public class Algorithm1 implements Algorithm {
    //private int bonus = 2;

    @Override
    public Solution computeModel(Model model) {
        model.sortCallsStart();     //order the rides in terms of startingTime

        List<Ride> unassignedRides = model.getRideList();       //in this point the list contains all the rides in the model
        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < model.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }
        for(Car car: cars) {            //for each car
            boolean finish = false;
            List<Ride> assignedRides = car.getRideList();   //initially empty for each car
            while(!finish) {
                Ride bestChoice = null;
                for (Ride ride : unassignedRides) {
                    if (canTakeCall(car, ride)) {       //check if car can take ride in terms of time and distance to get at ride's startingPoint
                        if (bestChoice == null) {
                            bestChoice = ride;
                        }
                        else {
                            int score1 = bestChoice.length();
                            int score2 = ride.length();
                            if (canTakeWithBonus(car, bestChoice)) {
                                score1 += model.getBonus();
                            }
                            if (canTakeWithBonus(car, ride)) {
                                score2 += model.getBonus();
                            }
                            if (score2 > score1)        //compare scores to choose best ride
                                bestChoice = ride;
                        }
                    }
                }
                if (bestChoice == null)     //unassignedRides list is empty
                    finish = true;
                else{
                    assignedRides.add(bestChoice);      //assign the best ride to the car
                    unassignedRides.remove(bestChoice);     //remove the just assigned ride
                }
            }
        }
        return new Solution(cars);
    }

    private boolean canTakeCall(Car car, Ride ride){
        car.getRideList().add(ride);
        if(car.isLegal()){
            car.getRideList().remove(ride);
            return true;
        }
        else{
            car.getRideList().remove(ride);
            return false;
        }
    }

    private boolean canTakeWithBonus(Car car, Ride ride){
        if(car.getRideList().isEmpty()){
            int distance = Position.distance(new Position(0,0), ride.getStartPos());
            int takeBy = ride.getStartTime() - distance;
            if(takeBy >= 0){
                return true;
            } else{
                return false;
            }
        } else {
            //get last element
            Ride lastRide = car.getRideList().get(car.getRideList().size() - 1);
            int distance = Position.distance(lastRide.getEndPos(), ride.getStartPos());
            int takeBy = ride.getStartTime() - distance;
            // this check is no exact cause it can happens that a car is on the same place in another time (example)
            if(takeBy >=0 && car.computePositionAtTime(takeBy).equals(lastRide.getEndPos())){
                return true;
            } else{
                return false;
            }
        }
    }
}
