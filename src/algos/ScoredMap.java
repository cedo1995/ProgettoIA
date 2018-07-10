package algos;

import core.*;

import java.util.*;
import java.util.List;

public class ScoredMap {
    private Problem problem;
    private Position point;
    private HashMap<Position,List<Ride> > startingRidesMap;
    private HashMap<Position,List<Ride> > endingRidesMap;
    public ScoredMap(Problem m){
        this.problem =m;
        //this.point=pos;
        List<Ride> rideList=m.getRideList();
        startingRidesMap = new HashMap<>();
        endingRidesMap = new HashMap<>();
        for (Ride r:rideList){
            for (int i = 0; i< problem.getDimI(); i++){
                for (int j = 0; j< problem.getDimJ(); j++){
                    Position pos=new Position(i,j);
                    if (r.getStartPos().equals(pos)){
                        startingRidesMap.get(pos).add(r);    //for each positions of the map there are rides that start in that point
                        endingRidesMap.get(r.getEndPos()).add(r); //for each positions of the map there are rides that end in that point
                    }
                }
            }
        }

    }

    public HashMap<Position,List<Ride>> getStartingRidesMap() {     //return HashMap that links Position and the rides that start there
        return startingRidesMap;
    }
    public List<Ride> getStartingRidesMap(Position p){      //return a list of rides that start in a given position
        return startingRidesMap.get(p);
    }

    public HashMap<Position,List<Ride> > getEndingRidesMap() {      //return HashMap that links Position and the rides that ends there
        return endingRidesMap;
    }
    public List<Ride> getEndingRidesMap(Position p){ return endingRidesMap.get(p); }


    public void sortCallsStart(Position p){         //return an ascending sort of rides in terms of their starting time
        startingRidesMap.get(p).sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ride1.getStartTime() - ride2.getStartTime();
            }
        });
    }

    public void sortCallsEnd(Position p){       //return an ascending sort of rides in terms of their ending time
        endingRidesMap.get(p).sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ride1.getEndTime() - ride2.getEndTime();
            }
        });
    }
}
