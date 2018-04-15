public class Main {

    public static void main(String[] args) {
        /* String path = "C:/Users/Paolo/Downloads/b_should_be_easy.in";
        ModelParser parser = new ModelParser();
        System.out.println(parser.parseFile(path));
        */
        Position pos1 = new Position(10, 10);
        Position pos2 = new Position(12, 24);
        System.out.println(Position.travel(pos1,pos2,10).toString());

    }
}
