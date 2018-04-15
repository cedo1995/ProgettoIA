import java.util.List;

public class Model {
    private int dimI;
    private int dimJ;
    private int maxTime;
    private int bonus;
    private List<Call> callList;
    private List<Car> carList;

    public Model(int dimI, int dimJ, int bonus, int maxTime, List<Call> callList, List<Car> carList) {
        this.dimI = dimI;
        this.dimJ = dimJ;
        this.maxTime = maxTime;
        this.bonus = bonus;
        this.callList = callList;
        this.carList = carList;
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

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public String toString() {
        String s = "Modello con: \n";
        s += dimI + " righe \n";
        s += dimJ + " colonne \n";
        s += maxTime + " come tempo massimo \n";
        s += bonus + " bonus \n\n";
        s += "Chiamate: \n";
        for (Call call : callList) {
            s += call.toString();
        }
        s += "Macchine: \n";
        return s;
    }

}
