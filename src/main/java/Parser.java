

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Nandani\\cp104\\ws\\cp213ws\\bicycle\\src\\Bicycle_Parking.csv";
        String line = "";
        //String cvsSplitBy = ",";
        ArrayList<ParkingSpot> parkingSpotList = new ArrayList();

        File file = new File(csvFile);
        try {
            Scanner sc = new Scanner(file);

            try {

                while (sc.hasNextLine()) {
                    line = sc.next();

                    String[] s = line.split(",");
                    String type = s[3];
                    String description = s[5];
                    String address = s[6];
                    int capacity = 0;
                    try {
                        if (!s[7].equals("")) {
                            capacity = Integer.parseInt(s[8]);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("invalid input");
                    }
                    int longitude = Integer.parseInt(s[8]);
                    int latitude = Integer.parseInt(s[9]);
                    ParkingSpot parkingSpotObject = new ParkingSpot(type, description, address, capacity, longitude, latitude);
                    ParkingSpotList.add(parkingSpotObject);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (sc != null) {
                    try {
                        sc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
