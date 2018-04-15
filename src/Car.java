import java.util.Iterator;
import java.util.List;

public class Car {
    private List<Call> callList;

    public Car(List<Call> callList) {   //constructor
        this.callList = callList;
    }

    public List<Call> getCallList() {   //getter
        return callList;
    }

    public void setCallList(List<Call> callList) {  //setter
        this.callList = callList;
    }

    public Position computePositionAtTime(int time){
        Position currentPos = new Position(0,0);
        int currentTime = 0;
        for (Call call : callList) {
            int distanceFromStart = Position.distance(currentPos,call.getStartPos());
            int distanceToEnd = Position.distance(call.getStartPos(),call.getEndPos());

            int max = Math.max(currentTime+distanceFromStart, call.getStartTime());

            if(time > max+distanceToEnd){
                currentPos = call.getEndPos();
                currentTime += max+distanceToEnd;
            } else{
                if(time < max){
                    currentPos = Position.travel(call.getStartPos(), currentPos, max-time);
                } else{
                    currentPos = Position.travel(call.getStartPos(), call.getEndPos(),time-max);
                }
            }
        }
        return currentPos;
    }


}
