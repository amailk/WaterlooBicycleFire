import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedReader;


public class Parser {
    public static void main(String[] args) {
        String csvFile = "Bicycle_Parking.csv";
        String line = "";
        String cvsSplitBy = ",";

        BufferedReader br = null;

        List<ParkingSpot> parkingSpotList = new ArrayList();


        try {
            br new BufferedReader(new FileReader(csvFile));


            while ((line = br.readLine()) != null) {

                String[] values = line.split(cvsSplitBy);
                String type = values[3].trim();
                String description = values[5].trim();
                String address = values[6].trim();
                float longitude = Float.parseFloat(values[8].trim());
                float latitude = Float.parseFloat(values[9].trim());
                int capacity = 0;
                try {
                    capacity = Integer.parseInt(values[7].trim());
                } catch (NumberFormatException e) {
                    System.out.println("invalid input");
                }

                ParkingSpot parkingSpot= new ParkingSpot(type,longitude, latitude, description, address, capacity);
                parkingSpotList.add(parkingSpot);

                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(parkingSpotList.get(0));
        System.out.println(parkingSpotList.get(1));
        System.out.println(parkingSpotList.get(10));

        FirebaseService firebaseService = new FirebaseService();
        firebaseService.uploadData(parkingSpotList);
    }
}
