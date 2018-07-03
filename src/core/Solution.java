package core;

import java.util.List;

/**
 * Class representing a solution of a problem
 */
public class Solution {
    /**
     * The solution of the problem is seen as a list of Cars with rides assigned
     */
    private List<Car> carsRides;

    /**
     * Constructor of Solution
     * @param cars cars with assigned rides
     */
    public Solution(List<Car> cars){
        carsRides = cars;
    }

    /**
     * Get the list of cars
     * @return
     */
    public List<Car> getCarRides(){
        return carsRides;
    }

    /**
     * String representation of a solution
     * @return a string representation of the solution
     */
    public String toString(){
        String s = "core.Solution is: \n";
        for(Car car: carsRides){
            s += "---core.Car---\n";
            s += car.toString();
        }
        return s;
    }
}
