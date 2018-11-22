import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static void main(String[] args) {
        String csvFile = "Bicycle_Parking.csv";
        String line = "";
        String cvsSplitBy = ",";

        BufferedReader br = null;

        // list of type ParkingSpot = new array list.
        List<ParkingSpot> parkingSpotList = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                System.out.println(line);

                // use comma as seperator
                String[] values = line.split(cvsSplitBy);

                System.out.println(values[0].trim());

                // attributes of parking spot object.
                String type = values[3].trim();
                float longitude = Float.parseFloat(values[8].trim());
                float latitude = Float.parseFloat(values[9].trim());
                String description = values[5].trim();
                String address = values[6].trim();
                int capacity = 0;
                try {
                    capacity = Integer.parseInt(values[7].trim());
                } catch (NumberFormatException e){

                }

                ParkingSpot parkingSpot = new ParkingSpot(type, longitude, latitude, description, address, capacity);

                parkingSpotList.add(parkingSpot);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        testing
//        System.out.println(parkingSpotList.get(0));
//        System.out.println(parkingSpotList.get(1));
//        System.out.println(parkingSpotList.get(10));

        FirebaseService firebaseService = new FirebaseService();
        firebaseService.uploadData(parkingSpotList);
    }

}