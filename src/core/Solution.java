package core;

import java.util.List;

public class Solution {
    private List<Car> carsRides;

    public Solution(List<Car> rides){
        carsRides = rides;
    }

    public List<Car> getCarRides(){
        return carsRides;
    }

    public String toString(){
        String s = "core.Solution is: \n";
        for(Car car: carsRides){
            s += "---core.Car---\n";
            s += car.toString();
        }
        return s;
    }
}
