package com.group.CarParking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.group.CarParking.model.UserModel;

import java.util.concurrent.ExecutionException;

/**
 * @author Akshat-Oke A collection of methods which use the Firebase
 *         {@code Firestore} APIs for CRUD operations
 */
public class UserService {
    private static Firestore db = FirestoreClient.getFirestore();

    public static UserModel getUserDetails(String id) {
        // Get user from database and return
    }

    /**
     * To be used to signup new users as an async call from the frontend JavaScript.
     * <br/>
     * Create a new user from {@code UserModel}
     * 
     * @return ID of new user document as a JSON string
     */
    public static String createUser(UserModel userModel) {

        ApiFuture<DocumentReference> collectionsApiFuture = db.collection("users").add(userModel);
        try {
            String id = collectionsApiFuture.get().getId();
            return "{\"id\": \"" + id + "\"}";
        } catch (Exception exception) {
            return "{\"error\": \"Unexpected error\"}";
        }
    }

    public static boolean updateUser(String id, UserModel userModel) throws InterruptedException, ExecutionException {

        DocumentReference docRef = db.collection("users").document(id);
        ApiFuture<WriteResult> future = docRef.update(userModel.toMap());
        try {
            WriteResult result = future.get();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
