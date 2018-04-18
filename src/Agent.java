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



}
