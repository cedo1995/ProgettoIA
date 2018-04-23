import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> paths = new ArrayList<>();
        String[] user=new String[2];
        user[0]="Paolo";
        user[1]="cedo";
        if (user[0].equals(System.getProperty("user.name"))){
            paths.add("C:/Users/Paolo/Downloads/a_example.in");
            paths.add("C:/Users/Paolo/Downloads/b_should_be_easy.in");
            paths.add("C:/Users/Paolo/Downloads/c_no_hurry.in");
            paths.add("C:/Users/Paolo/Downloads/d_metropolis.in");
            paths.add("C:/Users/Paolo/Downloads/e_high_bonus.in");

        }else if(user[1].equals(System.getProperty("user.name"))){
            paths.add("/home/cedo/Downloads/IA/a_example.in");
            paths.add("/home/cedo/Downloads/IA/b_should_be_easy.in");
            paths.add("/home/cedo/Downloads/IA/c_no_hurry.in");
            paths.add("/home/cedo/Downloads/IA/d_metropolis.in");
            paths.add("/home/cedo/Downloads/IA/e_high_bonus.in");
        }

        int tot = 0;

        for(String path: paths) {
            ModelParser parser = new ModelParser();
            Model model = parser.parseFile(path);
            // System.out.println(model.toString());
            Agent agent = new Agent(model, new Algorithm1());

            Solution solution = agent.applyAlgorithm();
            int score = agent.computeScore(solution);
            System.out.println(score);
            tot += score;
           // System.out.println(solution.toString());
        }

        System.out.println("Total score = "+tot);

    }
}
