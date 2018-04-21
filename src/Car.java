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
            int realStartTime = Math.max(currentTime+distanceFromStart, call.getStartTime());

            if(time <= realStartTime){
                return Position.travel(call.getStartPos(), currentPos,realStartTime - time);
            }else{
                if(time < realStartTime+call.length()){
                    return Position.travel(call.getStartPos(), currentPos,time - realStartTime);
                }
                else{
                    currentTime = realStartTime+call.length();
                    currentPos = call.getEndPos();
                }
            }
        }
        return currentPos;
    }

    public boolean isLegal(){
        int time = 0;
        Position position = new Position(0,0);
        for(Call call: callList){
            time += Math.max(Position.distance(position, call.getStartPos()),call.getStartTime());
            time += call.length();
            if(time > call.getEndTime())
                return false;
            position = call.getEndPos();
        }
        return true;
    }

    public String toString(){
        String s = "";

        for(Call call: callList){
            s += call.toString();
        }
        return s;
    }


}
