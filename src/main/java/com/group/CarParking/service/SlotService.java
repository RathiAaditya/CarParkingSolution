package com.group.CarParking.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.group.CarParking.model.SlotModel;
import com.group.CarParking.model.UserModel;

public class SlotService {
  private static Firestore db = FirestoreClient.getFirestore();

  /**
   * Get details about a parking slot using the document ID
   * 
   * @param id The document ID
   * @return SlotModel with fields populated from database
   * @throws ExecutionException
   * @throws InterruptedException
   */
  public static SlotModel getOneParkingSlot(String id) throws InterruptedException, ExecutionException {
    DocumentReference docRef = db.collection("parking-slots").document(id);
    ApiFuture<DocumentSnapshot> future = docRef.get();
    DocumentSnapshot document = future.get();
    SlotModel slotModel = null;
    if (document.exists()) {
      slotModel = document.toObject(SlotModel.class);
      docRef = db.collection("users").document(slotModel.getOwner().getId());
      future = docRef.get();
      document = future.get();
      if (document.exists()) {
        slotModel.setOwnerUserModel(document.toObject(UserModel.class));
      }
    }
    return slotModel;
  }
  // public static List<Object> getSlots(Map<String, Object> options) {

  // }
}
