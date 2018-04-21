import java.util.ArrayList;
import java.util.List;

public class Algorithm1 implements Algorithm {
    //private int bonus = 2;

    @Override
    public Solution computeModel(Model model) {
        model.sortCallsStart();

        List<Call> unassignedCalls = model.getCallList();
        List<Car> cars = new ArrayList<>();
        for(int n = 0; n < model.getCarsNumber(); n++){
            cars.add(new Car(new ArrayList<>()));
        }
        for(Car car: cars) {
            boolean finish = false;
            List<Call> assignedCalls = car.getCallList();
            while(!finish) {
                Call bestChoice = null;
                for (Call call : unassignedCalls) {
                    if (canTakeCall(car, call)) {
                        if (bestChoice == null) {
                            bestChoice = call;
                        }
                        else {
                            int score1 = bestChoice.length();
                            int score2 = call.length();
                            if (canTakeWithBonus(car, bestChoice)) {
                                score1 += model.getBonus();
                            }
                            if (canTakeWithBonus(car, call)) {
                                score2 += model.getBonus();
                            }
                            if (score2 > score1)
                                bestChoice = call;
                        }
                    }
                }
                if (bestChoice == null)
                    finish = true;
                else{
                    assignedCalls.add(bestChoice);
                    unassignedCalls.remove(bestChoice);
                }
            }
        }
        return new Solution(cars);
    }

    private boolean canTakeCall(Car car, Call call){
        car.getCallList().add(call);
        if(car.isLegal()){
            car.getCallList().remove(call);
            return true;
        }
        else{
            car.getCallList().remove(call);
            return false;
        }
    }

    private boolean canTakeWithBonus(Car car,Call call){
        if(car.getCallList().isEmpty()){
            int distance = Position.distance(new Position(0,0), call.getStartPos());
            int takeBy = call.getStartTime() - distance;
            if(takeBy >= 0){
                return true;
            } else{
                return false;
            }
        } else {
            //get last element
            Call lastCall = car.getCallList().get(car.getCallList().size() - 1);
            int distance = Position.distance(lastCall.getEndPos(), call.getStartPos());
            int takeBy = call.getStartTime() - distance;
            // this check is no exact cause it can happens that a car is on the same place in another time (example)
            if(takeBy >=0 && car.computePositionAtTime(takeBy).equals(lastCall.getEndPos())){
                return true;
            } else{
                return false;
            }
        }
    }
}
