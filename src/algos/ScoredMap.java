package algos;

import core.*;

import java.util.*;
import java.util.List;

public class ScoredMap {
    private Model model;
    private Position point;
    private HashMap<Position,List<Ride> > startingRidesMap;
    private HashMap<Position,List<Ride> > endingRidesMap;
    public ScoredMap(Model m){
        this.model=m;
        //this.point=pos;
        List<Ride> rideList=m.getRideList();
        startingRidesMap = new HashMap<>();
        endingRidesMap = new HashMap<>();
        for (Ride r:rideList){
            for (int i=0;i<model.getDimI();i++){
                for (int j=0;j<model.getDimJ();j++){
                    Position pos=new Position(i,j);
                    if (r.getStartPos().equals(pos)){
                        startingRidesMap.get(pos).add(r);    //ad ogni posizione ci sono le corse che partono da quel punto
                        endingRidesMap.get(r.getEndPos()).add(r); //ad ogni posizione ci sono le corse che arrivano a quel punto
                    }
                }
            }
        }

    }

    public HashMap<Position,List<Ride>> getStartingRidesMap() {
        return startingRidesMap;
    }
    public List<Ride> getStartingRidesMap(Position p){
        return startingRidesMap.get(p);
    }

    public HashMap<Position,List<Ride> > getEndingRidesMap() {
        return endingRidesMap;
    }


    public void sortCallsStart(Position p){
        startingRidesMap.get(p).sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ride1.getStartTime() - ride2.getStartTime();
            }
        });
    }

    public void sortCallsEnd(Position p){
        endingRidesMap.get(p).sort(new Comparator<Ride>() {
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return ride1.getEndTime() - ride2.getEndTime();
            }
        });
    }
}
