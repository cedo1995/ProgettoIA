import java.util.List;

public class Model {
    private int bonus;
    private List<Call> callList;
    private List<Car> carList;

    public Model(int bonus, List<Call> callList, List<Car> carList) {
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
}
