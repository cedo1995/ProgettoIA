package core;

import java.util.List;

public class Car {
    private List<Ride> rideList;

    public Car(List<Ride> rideList) {   //constructor
        this.rideList = rideList;
    }

    public List<Ride> getRideList() {   //getter
        return rideList;
    }

    public void setRideList(List<Ride> rideList) {  //setter
        this.rideList = rideList;
    }

    public Position computePositionAtTime(int time){
        Position currentPos = new Position(0,0);
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

    public boolean isLegal(){
        int time = 0;
        Position position = new Position(0,0);
        for(Ride ride : rideList){
            time += Math.max(Position.distance(position, ride.getStartPos()), ride.getStartTime());
            time += ride.length();
            if(time > ride.getEndTime())
                return false;
            position = ride.getEndPos();
        }
        return true;
    }

    public String toString(){
        String s = "";

        for(Ride ride : rideList){
            s += ride.toString();
        }
        return s;
    }


}
