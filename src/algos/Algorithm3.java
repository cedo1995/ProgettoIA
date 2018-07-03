package algos;

import core.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import core.*;
public class Algorithm3 implements Algorithm {

    @Override
    public Solution computeModel(Model model) {

        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < model.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }
        ScoredMap scoredMap=new ScoredMap(model);
        System.out.println(scoredMap.getStartingRidesMap()+"");
        Position actualPosition = new Position(0,0);
        int time=0;
        for (Car car:cars){
            List<Ride> rideListCurrentPosition=scoredMap.getStartingRidesMap(actualPosition);
            rideListCurrentPosition=isLegal(rideListCurrentPosition,time,actualPosition);
            List<Ride> bestRides=new ArrayList<>();
            List<Ride> possibleRides=new ArrayList<>();
            Ride bestChoice=null;
            for(Ride r:rideListCurrentPosition){

                if( r.getStartTime()==time){    //BONUS RIDE
                    bestRides.add(r);
                }else if(r.getStartTime()<time){
                    possibleRides.add(r);
                }
            }
            switch (bestRides.size()) {
                case 0:
                    if (possibleRides.size() == 0) {
                        //finish = true;
                    } else {
                        bestRides = sortMinimumLength(possibleRides);
                        //ora gestisco la scelta della migliore corsa a parità di distanza confrontando quante corse ci sono nella
                        //posizione finale delle prime corse, da aggiustare il fatto che queste nuove devono essere pronte a partire
                        bestChoice = sortByTopRides(possibleRides, scoredMap);
                    }
                    break;
                case 1:
                    bestChoice = bestRides.get(0);
                    //PROBLEMA: se vi è un'unica corsa con bonus ma questa porta lontano dalla partenza di altre viene comunque scelta
                    break;
                default:
                    bestRides = sortMinimumLength(bestRides);
                    //ora gestisco la scelta della migliore corsa a parità di distanza confrontando quante corse ci sono nella
                    //posizione finale delle prime corse, da aggiustare il fatto che queste nuove devono essere pronte a partire
                    bestChoice = sortByTopRides(bestRides, scoredMap);
                    break;

            }
            if (car.testRide(bestChoice)){
                List<Ride> newRideList=car.getRideList();
                newRideList.add(bestChoice);
                car.setRideList(newRideList);
                List<Boolean>setFree=new ArrayList<>();
                for(int i=0;i<bestChoice.length();i++){
                    setFree.add(false);
                }
                car.setIsFree(setFree);
            }

        }
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

    public List<Ride> isLegal(List<Ride> rideList,int prevTime, Position actualPosition){
        List<Ride> rideL=new ArrayList<>();
        int time=prevTime;
        for(Ride ride : rideList){
            time += ride.length();
            if(time <= ride.getEndTime())
                rideL.add(ride);
            time=prevTime;
        }
        return rideL;
    }

    public List<Ride> sortMinimumLength(List<Ride> rideList){
        rideList.sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2){
                return ride1.length() - ride2.length();
            }
        });
        return rideList;
    }

    public Ride sortByTopRides(List<Ride>rideList, ScoredMap map){
        rideList.sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return map.getStartingRidesMap(ride1.getEndPos()).size()-map.getStartingRidesMap(ride2.getEndPos()).size();
            }
        });
        return rideList.get(0);
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
