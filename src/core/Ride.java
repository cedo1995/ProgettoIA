package core;

/**
 * Class representing a ride
 */
public class Ride {
    /**
     * ride's id
     */
    private final int id;
    /**
     * ride's starting position
     */
    private final Position startPos;
    /**
     * ride's position of arrival
     */
    private final Position endPos;
    /**
     * ride's earliest starting time
     */
    private final int startTime;
    /**
     * ride's deadline
     */
    private final int endTime;


    /**
     * Constructor of a ride
     * @param id ride's id
     * @param startPos ride's starting position
     * @param endPos ride's position of arrival
     * @param startTime ride's earliest starting time
     * @param endTime ride's deadline
     */
    public Ride(int id, Position startPos, Position endPos, int startTime, int endTime) {
	    this.id = id;
        this.startPos = startPos;
        this.endPos = endPos;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Get ride's id
     * @return int id
     */
    public int getId() {
         return id;
    }

    /**
     * Get ride's starting position
     * @return position
     */
    public Position getStartPos() {
        return startPos;
    }

    /**
     * Get ride's position of arrival
     * @return position
     */
    public Position getEndPos() {
        return endPos;
    }

    /**
     * Get ride's earliest starting time
     * @return start time
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Get ride's deadline
     * @return deadline
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Get ride's length
     * @return length
     */
    public int length(){        //length of ride is the difference from startingPoint to endPoint
        return Position.distance(startPos,endPos);
    }

    /**
     * String representation of a ride
     * @return string representing a ride
     */
    public String toString(){
        String s = "Chiamata da : "+ startPos.toString() + " a "+ endPos.toString() + " \n";
        s += "inizio al tempo: "+startTime+" fine al tempo: "+endTime+ " \n";
        return s;
    }
}
