import algos.Algorithm1;
import algos.Algorithm2;
import algos.WasteAlgorithm;
import algos.WasteAlgorithm2;
import core.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> paths = new ArrayList<>();

        paths.add("./test_models/a_example.in");
        paths.add("./test_models/b_should_be_easy.in");
        paths.add("./test_models/c_no_hurry.in");
        paths.add("./test_models/d_metropolis.in");
        paths.add("./test_models/e_high_bonus.in");

        //int tot = 0;
        //for (int i=-10;i<11;i++){
            int tot = 0;
            //System.out.println("I="+i);
            for(String path: paths) {
                ModelParser parser = new ModelParser();
                Model model = parser.parseFile(path);
                // System.out.println(model.toString());

                //Agent agent = new Agent(model, new Algorithm2(i,0,8,30,-1));
                Agent agent=new Agent(model, new WasteAlgorithm2());
                Solution solution = agent.applyAlgorithm();
                int score = agent.computeScore(solution);
                System.out.println(score);
                tot += score;
                //System.out.println(solution.toString());
            }

            System.out.println("Total score = "+tot);
        //}


    }
}
