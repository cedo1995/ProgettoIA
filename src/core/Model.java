package core;

import java.util.Comparator;
import java.util.List;

/**
 * Class representing problem's model
 */
public class Model {
    /**
     * dimension of the map expressed in coordinates I for height and J for width
     */
    private final int dimI;
    private final int dimJ;
    /**
     * maximum time of the problem
     */
    private final int maxTime;        //specified in each file
    /**
     * bonus for taking a ride on time
     */
    private final int bonus;      //specified in each file
    /**
     * list of all the rides available in the problem
     */
    private final List<Ride> rideList;
    /**
     * number of cars available for the problem
     */
    private final int n_car;      //specified in each file


    /**
     * Constructor of problem's model
     * @param dimI height of map
     * @param dimJ width of map
     * @param bonus bonus for taking a ride on time
     * @param maxTime deadline of the problem
     * @param cars  number of cars available
     * @param rideList list of available rides
     */
    public Model(int dimI, int dimJ, int bonus, int maxTime, int cars, List<Ride> rideList) {
        // initialization of fields
        this.dimI = dimI;
        this.dimJ = dimJ;
        this.maxTime = maxTime;
        this.bonus = bonus;
        this.rideList = rideList;
        this.n_car = cars;
    }

    /**
     * Get bonus of this problem
     * @return int bonus
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * Get the list of rides
     * @return List<Ride> of rides
     */
    public List<Ride> getRideList() {
        return rideList;
    }

    /**
     * Get number of cars available for the problem
     * @return int cars
     */
    public int getCarsNumber() {
        return n_car;
    }

    /**
     * Get height of the map
     * @return int height
     */
    public int getDimI(){ return dimI; }

    /**
     * Get width of the map
     * @return int width
     */
    public int getDimJ(){ return dimJ; }

    /**
     * Get deadline of the problem
     * @return int maxTime
     */
    public int getMaxTime() {return  maxTime;}

    /**
     * Sort rideList by ride's Start Time
     */
    public void sortRideStart(){       // sorting using comparator
        rideList.sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ride1.getStartTime() - ride2.getStartTime();
            }
        });
    }

    /**
     * Sort rideList by ride's End Time
     */
    public void sortRideEnd(){     // sorting using comparator
        rideList.sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ride1.getEndTime() - ride2.getEndTime();
            }
        });
    }

    /**
     * String representation of the model
     * @return string representation of the model
     */
    public String toString() {
        String s = "Modello con: \n";
        s += dimI + " righe \n";
        s += dimJ + " colonne \n";
        s += maxTime + " come tempo massimo \n";
        s += bonus + " bonus \n";
        s += n_car + " macchine \n\n";
        s += "Chiamate: \n";
        for (Ride ride : rideList) {
            s += ride.toString();
        }
        return s;
    }

}
