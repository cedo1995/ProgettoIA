package core;


import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Car
 */
public class Car {
    /**
     * List of rides that this car has to perform FIFO order
     */
    private List<Ride> rideList;
    private List<Boolean> isFree=new ArrayList<>();     //useful for Algorithm3

    /**
     * Constructor given a list of rides
     * @param rideList becomes car's rideList
     */
    public Car(List<Ride> rideList) {
        this.rideList = rideList;
    }

    /**
     * Get car's rideList
     * @return the list of rides
     */
    public List<Ride> getRideList() {   //getter rideList
        return rideList;
    }

    /**
     * Change current rideList with a given list
     * @param rideList = new list of rides
     */
    public void setRideList(List<Ride> rideList) {  //setter rideList
        this.rideList = rideList;
    }

    /**
     * Get the position of this car at a given time, considering current assigned rides
     * @param time query time
     * @return the position of the car at query time
     */
    public Position computePositionAtTime(int time){
        // start at position (0,0) at time 0
        Position currentPos = new Position(0,0);
        int currentTime = 0;

        // for each ride in this car's list
        for (Ride ride : rideList) {
            // distance from the start position of next ride
            int distanceFromStart = Position.distance(currentPos, ride.getStartPos());
            // time in which next ride will start
            int realStartTime = Math.max(currentTime+distanceFromStart, ride.getStartTime());

            //if query time is less than next ride's start time
            if(time < realStartTime){
                // position is obtained travelling from current position to starting position
                return Position.travel(currentPos, ride.getStartPos(),time - currentTime);
            }else{
                // else if query time is between next ride's stating time and completion time
                if(time <= realStartTime + ride.length()){
                    // position is obtained travelling from next ride's start position to end position
                    return Position.travel(ride.getStartPos(), ride.getEndPos(),time - realStartTime);
                }
                else{
                    // else query time is past this ride completion, update position and time and go on
                    currentTime = realStartTime+ ride.length();
                    currentPos = ride.getEndPos();
                }
            }
        }
        // if there is no ride or time exceeds every ride, return current position
        return currentPos;
    }

    /**
     * check if all rides that are already assigned are feasible
     *
     * @return boolean legal = true, non legal = false
     */
    public boolean isLegal(){
        int time = 0;
        Position position = new Position(0,0);      //startingPoint from the docs
        // for each assigned ride
        for(Ride ride : rideList){
            // time needed to start next ride
            time = Math.max(time + Position.distance(position, ride.getStartPos()), ride.getStartTime());
            // time at completion
            time += ride.length();
            // if time at completion exceeds the deadline return false
            if(time > ride.getEndTime())
                return false;
            // else update position with the end position of the ride
            position = ride.getEndPos();
        }
        // if no deadline is missed then return true
        return true;
    }

    /**
     * Check if adding a ride as the last one is legal or not
     * @param ride to be tested
     * @return boolean: legal = true, non legal = false
     */
    public boolean testRide(Ride ride){
        // add ride
        rideList.add(ride);
        // perform is legal
        boolean result = isLegal();
        // remove ride
        rideList.remove(ride);
        // return result
        return result;
    }

    /**
     * Computes total score gained with this car
     * @param bonusValue value of bonus
     * @return int score gained
     */
    public int computeScore(int bonusValue){
        // initialize score variable to 0
        int score =0;
        // initialize time variable to 0
        int time = 0;
        // initialize position variable to position (0,0)
        Position position = new Position(0,0);
        // for each ride assigned to this car
        for(Ride ride : rideList){
            // compute at what time the ride will start
            int startAt = Math.max(ride.getStartTime(), time + Position.distance(position,ride.getStartPos()));
            // if the ride start at it's startTime
            if(startAt == ride.getStartTime())
                // add a bonus
                score += bonusValue;
            // add length of the ride as score
            score += ride.length();

            // update current time with the time in which the ride ends
            time = startAt + ride.length();
            // update current position with ride's end position;
            position = ride.getEndPos();
        }
        // return total score
        return score;
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

    /**
     * String representation of a Car
     * @return string describing the car
     */
    public String toString(){
        String s = "";

        for(Ride ride : rideList){
            s += ride.toString();
        }
        return s;
    }


}
