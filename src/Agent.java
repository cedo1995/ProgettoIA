public class Agent {
    private Model model;
    private Algorithm algorithm;

    public Agent(Model model, Algorithm algorithm){
        this.model = model;
        this.algorithm = algorithm;
    }

    public void computeModelAndReplace(){
        this.model = algorithm.computeModel(model);
    }

    public Integer computeScore(){
        computeScore(this.model);
    }

    public Integer computeScore(Model model){
        /**
         * ToDo
         */
        return 0;
    }
}
