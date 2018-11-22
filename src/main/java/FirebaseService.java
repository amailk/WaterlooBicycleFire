import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1beta1.Write;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseService {

    private final Firestore firestore;

    public FirebaseService() {
        // Use a service account
        InputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("ServiceAccount.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.fromStream(serviceAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);

        firestore = FirestoreClient.getFirestore();

    }
    public void uploadData(List<ParkingSpot> parkingSpotList) {
        WriteBatch batch = firestore.batch();
        CollectionReference collecRef = firestore.collection("parkingspots");

        // parkingSpot in parkingSpotList
        for (ParkingSpot parkingSpot:parkingSpotList) {
            DocumentReference docRef = collecRef.document();
            batch.set(docRef, parkingSpot.toMap());
        }

        try {
            batch.commit().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
