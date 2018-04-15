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
}
