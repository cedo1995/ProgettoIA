import java.util.ArrayList;
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
        String s = "Solution is: \n";
        for(Car car: carsRides){
            s += "---Car---\n";
            s += car.toString();
        }
        return s;
    }
}
