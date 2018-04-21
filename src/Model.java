import java.util.Comparator;
import java.util.List;

public class Model {
    private int dimI;
    private int dimJ;
    private int maxTime;
    private int bonus;
    private List<Call> callList;
    private int n_car;

    public Model(int dimI, int dimJ, int bonus, int maxTime, int cars, List<Call> callList) {
        this.dimI = dimI;
        this.dimJ = dimJ;
        this.maxTime = maxTime;
        this.bonus = bonus;
        this.callList = callList;
        this.n_car = cars;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public List<Call> getCallList() {
        return callList;
    }

    public void setCallList(List<Call> callList) {
        this.callList = callList;
    }

    public int getCarsNumber() {
        return n_car;
    }

    public void setCarList(int cars) {
        this.n_car = cars;
    }

    public void sortCallsStart(){
        callList.sort(new Comparator<Call>() {
            @Override
            public int compare(Call call1, Call call2) {
                return call1.getStartTime() - call2.getStartTime();
            }
        });
    }

    public void sortCallsEnd(){
        callList.sort(new Comparator<Call>() {
            @Override
            public int compare(Call call1, Call call2) {
                return call1.getEndTime() - call2.getEndTime();
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
        for (Call call : callList) {
            s += call.toString();
        }
        return s;
    }
}
