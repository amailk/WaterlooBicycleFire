import com.google.cloud.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingSpot {

    private String type;
    private String description;
    private String address;
    private int capacity;
    private float longitude;
    private float latitude;
    private ArrayList<Float> location;

    public ParkingSpot(String type, String description, String address, int capacity, int longitude, int latitude) {
        this.type = type;
        this.description = description;
        this.address = address;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    @Override
    public String toString() {
        String s = "test:" + this.type + "," + this.description + "," + this.address + "," + this.capacity + ","
                + this.location;
        return s;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("type", this.type);
        data.put("location", new GeoPoint(latitude, longitude));
        data.put("description", this.description);
        data.put("address", this.address);
        data.put("capacity", this.capacity);

        return data;
    }

}

