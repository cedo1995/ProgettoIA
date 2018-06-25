package core;

public class Ride {
    protected static int id;
    private Position startPos;
    private Position endPos;
    private int startTime;
    private int endTime;
    private int score = 0;


    public Ride(Position startPos, Position endPos, int startTime, int endTime) {
	    this.id+=1;
        this.startPos = startPos;
        this.endPos = endPos;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public static int getId() {
         return id;
    }

    public static void setId(int id) {
        Ride.id = id;
    }

    public Position getStartPos() {
        return startPos;
    }

    public void setStartPos(Position startPos) {
        this.startPos = startPos;
    }

    public Position getEndPos() {
        return endPos;
    }

    public void setEndPos(Position endPos) {
        this.endPos = endPos;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int length(){        //length of ride is the difference from startingPoint to endPoint
        return Position.distance(startPos,endPos);
    }

    public String toString(){
        String s = "Chiamata da : "+ startPos.toString() + " a "+ endPos.toString() + " \n";
        s += "inizio al tempo: "+startTime+" fine al tempo: "+endTime+ " \n";
        return s;
    }
}
