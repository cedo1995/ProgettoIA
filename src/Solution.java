import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Car> carsRides;
    /**
     * Todo: trovare un modo elegante per prendere il bonus
     * es: Agente classe statica?
     */
    private int bonus = 2;

    public Solution(List<Car> rides){
        carsRides = rides;
    }

    public List<Car> getCarsRides() {
        return carsRides;
    }

    public int computeScore(){
        int score = 0;
        for (Car car: carsRides) {
            for(Call call: car.getCallList()){
                if(car.computePositionAtTime(call.getStartTime()) == call.getStartPos()){
                    score += bonus;
                }
                score += call.length();
            }
        }
        return score;
    }
}
