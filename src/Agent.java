public class Agent {
    private Model model;
    private Algorithm algorithm;

    public Agent(Model model, Algorithm algorithm){
        this.model = model;
        this.algorithm = algorithm;
    }

    public Solution applyAlgorithm(){
        return algorithm.computeModel(model);
    }


    public int computeScore(Solution solution){
        int score = 0;
        for (Car car: solution.getCarRides()) {
            for(Call call: car.getCallList()){
                if(car.computePositionAtTime(call.getStartTime()).equals(call.getStartPos())){
                    score += model.getBonus();
                }
                score += call.length();
            }
        }
        return score;
    }


}
