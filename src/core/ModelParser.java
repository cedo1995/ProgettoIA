package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ModelParser {

    public Model parseFile(String path){    //read input file
        Model model = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String firstLine = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(firstLine);
            int dimI = Integer.parseInt(tokenizer.nextToken());
            int dimJ = Integer.parseInt(tokenizer.nextToken());
            int nCars = Integer.parseInt(tokenizer.nextToken());
            int nRides = Integer.parseInt(tokenizer.nextToken());
            int bonus = Integer.parseInt(tokenizer.nextToken());
            int maxTime = Integer.parseInt(tokenizer.nextToken());

            List<Car> cars = new LinkedList<>();
            for(int c = 0; c < nCars; c++){     //create nCars
                cars.add(new Car(new ArrayList<>()));
            }

            List<Ride> rides = new ArrayList<>();

            for(int r = 0; r < nRides; r++){    //create nRides with their specs
                String line = reader.readLine();
                StringTokenizer tok = new StringTokenizer(line);

                int startI = Integer.parseInt(tok.nextToken());
                int startJ = Integer.parseInt(tok.nextToken());
                int endI = Integer.parseInt(tok.nextToken());
                int endJ = Integer.parseInt(tok.nextToken());

                int startT = Integer.parseInt(tok.nextToken());
                int endT = Integer.parseInt(tok.nextToken());

                Position startP = new Position(startI, startJ);
                Position endP = new Position(endI, endJ);
                rides.add(new Ride(startP,endP, startT, endT));
            }

            model = new Model(dimI, dimJ, bonus, maxTime, nCars, rides);    //create final model

        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

}
