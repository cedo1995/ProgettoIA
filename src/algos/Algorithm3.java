package algos;

import core.*;

import java.util.ArrayList;
import java.util.List;

public class Algorithm3 implements Algorithm {

    @Override
    public Solution computeModel(Model model) {
        model.sortCallsStart();

        List<Ride> unassignedRides = model.getRideList();
        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < model.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }
        List<Ride> possibleRides = new ArrayList<>();
        ScoredMap a=new ScoredMap(model);
        System.out.println(a.getStartingRidesMap()+"");

        /*List<Car> cars = new ArrayList<>();
        for(int n = 0; n < model.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }
        for (int i=0;i<model.getDimI();i++){
            for (int j=0;j<model.getDimJ();j++){
                Position p=new Position(i,j);
                List<Ride> alreadyDone=new ArrayList<>();
                for (Ride r:a.getStartingRidesMap().get(p)){
                    if (alreadyDone.isEmpty()){
                        Ride bestRide=chooseBestRide(p);
                    }
                }
            }
        }*/
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
}
