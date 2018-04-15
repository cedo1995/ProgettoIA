import java.util.Objects;


public class Position {
    private int x;
    private int y;

    public static Position travel(Position from, Position to, int distance){
        int distX = to.getX() - from.getX();
        int distY = to.getY() - from.getY();

        int newX = from.getX();
        int newY = from.getY();

        if(distance < Math.abs(distX)){
            newX += distance*Math.signum(distX);
        } else{
            newX = to.getX();
            distance -= Math.abs(distX);
            newY += distance*Math.signum(distY);
        }

        return new Position(newX, newY);
    }

    public static int distance(Position pos1, Position pos2){
        int diffY = pos2.getY() - pos1.getY();
        int diffX = pos2.getX() - pos1.getX();
        return(Math.abs(diffX) + Math.abs(diffY));
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    public int getX(){ return x;}
    public int getY(){ return y;}


    public String toString(){
        return "["+x+","+y+"]";
    }
}
