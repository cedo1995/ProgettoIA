package core;

/**
 * Class to manage positions on the map
 */
public class Position {
    /**
     * A position is a couple of coordinates x,y
     */
    private int x;
    private int y;

    /**
     * Travel from a position to another implies moving horizontally till the the same X coordinate is reached,
     * then moving vertically till position.
     * This method takes the a time to compute the current position after a certain time is passed.
     *
     * @param from start position
     * @param to end position
     * @param time time to compute how far new position is
     * @return position after time is passed while travelling
     */
    public static Position travel(Position from, Position to, int time){
        // horizontal and vertical distances
        int distX = to.getX() - from.getX();
        int distY = to.getY() - from.getY();

        // initializing new coordinates
        int newX = from.getX();
        int newY = from.getY();

        // if the distance covered is less than horizontal distance
        if(time < Math.abs(distX)){
            // new X coordinate is the previous plus the distance travelled
            // new Y is the same as start
            newX += time*Math.signum(distX);
        }
        else{
            // if the time exceeds the distance new coordinates are the destination coordinates
            if(time >= Math.abs(distX) + Math.abs(distY)){
                newX = to.getX();
                newY = to.getY();
            }
            else {
                // else the distance covers the horizontal movement but not vertical
                // new X is equal to the destination X
                // new Y is the start position plus the time less the time consumed moving horizontally
                newX = to.getX();
                time -= Math.abs(distX);
                newY += time * Math.signum(distY);
            }
        }
        // return position created with newX, newY
        return new Position(newX, newY);
    }

    /**
     * Computes the distance between two positions
     * @param pos1 first position
     * @param pos2 second position
     * @return the distance between pos1 and pos2
     */
    public static int distance(Position pos1, Position pos2){
        // vertical and horizontal distance
        int diffY = pos2.getY() - pos1.getY();
        int diffX = pos2.getX() - pos1.getX();
        // total distance is the sum of the modules of x-y distances
        return(Math.abs(diffX) + Math.abs(diffY));
    }

    /**
     * Constructor of Position
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Override of equals method
     * @param o object to confront
     * @return true if the object is an instance of Position with same coordinates
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * get X coordinate
     * @return X coordinate
     */
    public int getX(){ return x;}

    /**
     * get Y coordinate
     * @return Y coordinate
     */
    public int getY(){ return y;}


    /**
     * string representation of a Position
     * @return string representing a position
     */
    public String toString(){
        return "["+x+","+y+"]";
    }
}
