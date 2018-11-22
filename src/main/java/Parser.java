import java.io.BufferedReader;
import java.io.File;
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


        List<ParkingSpot> parkingSpotList = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));

                while (line = br.readLine()) {

                    String[] s = line.split(cvsSplitBy);
                    String type = s[3].trim();
                    String description = s[5].trim();
                    String address = s[6].trim();
                    int capacity = 0;
                    try {
                        capacity = Integer.parseInt(s[8].trim());
                    } catch (NumberFormatException e) {
                    }
                    float longitude = Float.parseFloat(s[8].trim());
                    float latitude = Float.parseFloat(s[9].trim());


                    ParkingSpot parkingSpotObject = new ParkingSpot(type, longitude, latitude, description, address, capacity);
                    parkingSpotList.add(parkingSpotObject);

                }
            } catch (FileNotFoundException e) {
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

            FirebaseService firebaseService = new FirebaseService();
            firebaseService.uploadData(parkingSpotList);
        }
    }
}
