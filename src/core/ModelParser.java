package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Class useful to parse a Model from a file
 */
public class ModelParser {

    /**
     * Parse a file to obtain the model
     * @param path of the desired file
     * @return parsed Model
     */
    public Model parseFile(String path){    //read input file
        Model model = null;
        try {
            // initialize reader and tokenizer
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String firstLine = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(firstLine);

            // read first line containing global info (refer to docs)
            int dimI = Integer.parseInt(tokenizer.nextToken());
            int dimJ = Integer.parseInt(tokenizer.nextToken());
            int nCars = Integer.parseInt(tokenizer.nextToken());
            int nRides = Integer.parseInt(tokenizer.nextToken());
            int bonus = Integer.parseInt(tokenizer.nextToken());
            int maxTime = Integer.parseInt(tokenizer.nextToken());

            // create a list of cars
            List<Car> cars = new LinkedList<>();
            for(int c = 0; c < nCars; c++){     //create nCars
                cars.add(new Car(new ArrayList<>()));
            }

            // create a list of rides
            List<Ride> rides = new ArrayList<>();


            // read nRides from file
            for(int r = 0; r < nRides; r++){    //create nRides with their specs
                // read line
                String line = reader.readLine();
                StringTokenizer tok = new StringTokenizer(line);

                // read positions of the ride
                int startI = Integer.parseInt(tok.nextToken());
                int startJ = Integer.parseInt(tok.nextToken());
                int endI = Integer.parseInt(tok.nextToken());
                int endJ = Integer.parseInt(tok.nextToken());
                // read times of the ride
                int startT = Integer.parseInt(tok.nextToken());
                int endT = Integer.parseInt(tok.nextToken());

                // create positions instances
                Position startP = new Position(startI, startJ);
                Position endP = new Position(endI, endJ);
                // create a ride with growing id
                rides.add(new Ride(r,startP,endP, startT, endT));
            }
            // create the model
            model = new Model(dimI, dimJ, bonus, maxTime, nCars, rides);    //create final model

        } catch (IOException e) {
            e.printStackTrace();
        }
        // if no IOException has occurred return the model, else null
        return model;
    }

}
