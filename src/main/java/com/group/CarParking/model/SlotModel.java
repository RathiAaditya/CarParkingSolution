package com.group.CarParking.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

/**
 * Parking slot model
 */
public class SlotModel {
  public SlotModel(String location, int maxVehicles, Timestamp startTime, Timestamp endTime, double ratings,
      List<CarParkService> workers) {
    this.setLocation(location);
    this.maxVehicles = maxVehicles;
    this.startTime = startTime;
    this.endTime = endTime;
    this.ratings = ratings;
    this.workers = workers;
  }

  public UserModel getOwnerUserModel() {
    return ownerUserModel;
  }

  public void setOwnerUserModel(UserModel ownerUserModel) {
    this.ownerUserModel = ownerUserModel;
  }

  public DocumentReference getOwner() {
    return owner;
  }

  public void setOwner(DocumentReference owner) {
    this.owner = owner;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  private String location;

  private int maxVehicles;
  private Timestamp startTime;
  private Timestamp endTime;
  private double ratings;
  private List<CarParkService> workers;
  private DocumentReference owner;
  private UserModel ownerUserModel;

  public List<CarParkService> getWorkers() {
    return this.workers;
  }

  public void setWorkers(List<CarParkService> w) {
    this.workers = w;
  }

  public SlotModel() {
  }

  public int getMaxVehicles() {
    return maxVehicles;
  }

  public void setMaxVehicles(int maxVehicles) {
    this.maxVehicles = maxVehicles;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public double getRatings() {
    return ratings;
  }

  public void setRatings(double ratings) {
    this.ratings = ratings;
  }

  public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
    ClassLoader classLoader = SlotModel.class.getClassLoader();
    File file = new File(Objects.requireNonNull(classLoader.getResource("firebase-service-key.json")).getFile());
    InputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
    GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
    FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
    FirebaseApp.initializeApp(options);

    Firestore db = FirestoreClient.getFirestore();
    DocumentReference docRef = db.collection("parking-slots").document("test");
    ApiFuture<DocumentSnapshot> future = docRef.get();
    DocumentSnapshot document = future.get();
    SlotModel slotModel;
    if (document.exists()) {
      slotModel = document.toObject(SlotModel.class);
      System.out.println(slotModel);
      System.out.println(slotModel.getOwner());
    } else {
      System.out.println("Not found object");
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (CarParkService map : workers) {
      stringBuilder.append(map.toString());
    }
    return stringBuilder.toString();
  }
}
