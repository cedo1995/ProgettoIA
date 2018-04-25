package core;

import java.util.Comparator;
import java.util.List;

public class Model {
    private int dimI;
    private int dimJ;
    private int maxTime;
    private int bonus;
    private List<Ride> rideList;
    private int n_car;

    public Model(int dimI, int dimJ, int bonus, int maxTime, int cars, List<Ride> rideList) {
        this.dimI = dimI;
        this.dimJ = dimJ;
        this.maxTime = maxTime;
        this.bonus = bonus;
        this.rideList = rideList;
        this.n_car = cars;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public List<Ride> getRideList() {
        return rideList;
    }

    public void setRideList(List<Ride> rideList) {
        this.rideList = rideList;
    }

    public int getCarsNumber() {
        return n_car;
    }

    public void setCarList(int cars) {
        this.n_car = cars;
    }

    public void sortCallsStart(){
        rideList.sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ride1.getStartTime() - ride2.getStartTime();
            }
        });
    }

    public void sortCallsEnd(){
        rideList.sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ride1.getEndTime() - ride2.getEndTime();
            }
        });
    }

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
