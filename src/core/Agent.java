package core;

public class Agent {
    private Model model;
    private Algorithm algorithm;

    public Agent(Model model, Algorithm algorithm){ //Agent's constructor
        this.model = model;
        this.algorithm = algorithm;
    }

    public Solution applyAlgorithm(){
        return algorithm.computeModel(model);
    }


    public int computeScore(Solution solution){ //calculate score for the solution in the parameter
        int score = 0;
        for (Car car: solution.getCarRides()) {
            for(Ride ride : car.getRideList()){
                if(car.computePositionAtTime(ride.getStartTime()).equals(ride.getStartPos())){
                    score += model.getBonus();
                }
                score += ride.length();
            }
        }
        return score;
    }


}
