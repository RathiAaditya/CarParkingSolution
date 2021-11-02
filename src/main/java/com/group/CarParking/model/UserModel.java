package com.group.CarParking.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.DocumentReference;

public class UserModel {
    public String getEmail() {
        return email;
    }

    public List<DocumentReference> getSlots() {
        return slots;
    }

    public void setSlots(List<DocumentReference> slots) {
        this.slots = slots;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email, name, uid, password;
    private List<DocumentReference> slots;

    public UserModel() {
    }

    public UserModel(String email, String name, String uid) {
        this.email = email;
        this.name = name;
        this.uid = uid;
    }

    public static UserModel fromRegister(String email, String name, String password) {
        UserModel userModel = new UserModel(email, name, "");
        userModel.setPassword(password);
        return userModel;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("email", this.email);
        map.put("password", this.password);
        return map;
    }
}
