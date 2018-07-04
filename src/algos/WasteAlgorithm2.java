package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

public class WasteAlgorithm2 implements Algorithm {
    @Override
    public Solution computeModel(Model model) {
        //model.sortRideStart();

        List<Ride> unassignedRides = new ArrayList<>(model.getRideList());
        List<Car> cars = new ArrayList<>();
        for (int n = 0; n < model.getCarsNumber(); n++) {
            cars.add(new Car(new ArrayList<>()));
        }
        for (Car car : cars) {
            int currentTime = 0;
            Position currentPosition = new Position(0, 0);
            List<Ride> assignedRides = car.getRideList();
            List<Ride> frontier = new ArrayList<>(unassignedRides);

            while (frontier.size() > 0) {
                Ride bestChoice = null;
                int distance = 0;
                int start = 0;
                int initialWaste = 0;
                int finishAt = 0;
                int score = 0;

                for (int i = 0; i < frontier.size(); i++) {
                    Ride ride = frontier.get(i);
                    if (car.testRide(ride)) {
                        if (bestChoice == null) {
                            bestChoice = ride;
                            distance = Position.distance(currentPosition, bestChoice.getStartPos());
                            start = Math.max(bestChoice.getStartTime(), currentTime + distance);
                            initialWaste = start - currentTime;
                            finishAt = start + bestChoice.length();
                            if (bestChoice.getStartTime() == start)
                                score = model.getBonus() + bestChoice.length();
                            else
                                score = bestChoice.length();
                        } else {
                            int distance2 = Position.distance(currentPosition, ride.getStartPos());
                            int start2 = Math.max(ride.getStartTime(), currentTime + distance2);
                            int initialWaste2 = start2 - currentTime;
                            int finishAt2 = start2 + ride.length();
                            int score2;
                            if (ride.getStartTime() == start2)
                                score2 = model.getBonus() + ride.length();
                            else
                                score2 = ride.length();

                            if (finishAt2 - score2 < finishAt - score){
//                            if (initialWaste2 < initialWaste || (initialWaste2 == initialWaste && ride.getEndTime() <= bestChoice.getEndTime())){
                                bestChoice = ride;
                                distance = distance2;
                                start = start2;
                                initialWaste = initialWaste2;
                                finishAt = finishAt2;
                                score = score2;

                            }
                        }
                    } else {
                        frontier.remove(i);
                        i--;
                    }
                }
                if (bestChoice != null) {
                    frontier.remove(bestChoice);
                    assignedRides.add(bestChoice);
                    unassignedRides.remove(bestChoice);
                    currentTime = finishAt;
                    currentPosition = bestChoice.getEndPos();
                }
            }
        }

        if(unassignedRides.size() > 0) {
            for (Car car : cars) {
                for (int j = 0; j < unassignedRides.size(); j++) {
                    Ride ride = unassignedRides.get(j);
                    for (int i = 0; i < car.getRideList().size(); i++) {
                        car.getRideList().add(i, ride);
                        if (!car.isLegal())
                            car.getRideList().remove(i);
                        else
                            unassignedRides.remove(j);
                    }
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
        return "todo";
    }

}
