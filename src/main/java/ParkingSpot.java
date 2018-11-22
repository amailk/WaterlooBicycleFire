import com.google.cloud.firestore.GeoPoint;

import java.util.HashMap;
import java.util.Map;

public class ParkingSpot {
    private final String type;
    private final float longitude;
    private final float latitude;
    private final String description;
    private final String address;
    private final int capacity;


    public ParkingSpot(String type, float longitude, float latitude, String description, String address, int capacity) {
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.address = address;
        this.capacity = capacity;

    }
    //testing fetching the data from firebase.

    @Override
    public String toString() {
        String parkingSpotInfo = String.format("type: %s, ln: %f, lat: %f, desc: %s, add: %s, cap: %d", type, longitude,latitude, description, address, capacity);
        return parkingSpotInfo;
    }
    // creating a map(like a dictionary in Python) thats maps from a name(string) to an object.
    public Map<String, Object> toMap() {
        Map<String,Object> data = new HashMap<>();
        data.put("type", this.type);
        data.put("location", new GeoPoint(latitude, longitude));
        data.put("description", this.description);
        data.put("address", this.address);
        data.put("capacity", this.capacity);

        return data;
    }
}