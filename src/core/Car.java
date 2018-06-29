package core;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private List<Ride> rideList;
    private List<Boolean> isFree=new ArrayList<>();     //useful for Algorithm3

    public Car(List<Ride> rideList) {
        this.rideList = rideList;
    }

    public List<Ride> getRideList() {   //getter rideList
        return rideList;
    }

    public void setRideList(List<Ride> rideList) {  //setter rideList
        this.rideList = rideList;
    }

    public Position computePositionAtTime(int time){
        Position currentPos = new Position(0,0);    //startingPoint from the docs
        int currentTime = 0;

        for (Ride ride : rideList) {
            int distanceFromStart = Position.distance(currentPos, ride.getStartPos());
            int realStartTime = Math.max(currentTime+distanceFromStart, ride.getStartTime());

            if(time <= realStartTime){
                return Position.travel(ride.getStartPos(), currentPos,realStartTime - time);
            }else{
                if(time < realStartTime+ ride.length()){
                    return Position.travel(ride.getStartPos(), currentPos,time - realStartTime);
                }
                else{
                    currentTime = realStartTime+ ride.length();
                    currentPos = ride.getEndPos();
                }
            }
        }
        return currentPos;
    }

    public boolean isLegal(){       //check if all rides that are already assigned are possible in terms of time
        int time = 0;
        Position position = new Position(0,0);      //startingPoint from the docs
        for(Ride ride : rideList){
            time = Math.max(time + Position.distance(position, ride.getStartPos()), ride.getStartTime());
            time += ride.length();
            if(time > ride.getEndTime())
                return false;
            position = ride.getEndPos();
        }
        return true;
    }

    public boolean testRide(Ride ride){     //useful to check if a ride is legal or not
        rideList.add(ride);
        boolean result = isLegal();
        rideList.remove(ride);
        return result;
    }

    public boolean getIsFree(int index) {       //useful to check if a car is already assigned or not to another ride
        return isFree.get(index);
    }

    public void setIsFree(List<Boolean> isFree) {       //free multiple cars
        this.isFree = isFree;
    }

    public void setIsFree(boolean value) {      //add a car to the list of the car you want to free
        this.isFree.add(value);
    }

    public String toString(){
        String s = "";

        for(Ride ride : rideList){
            s += ride.toString();
        }
        return s;
    }


}
