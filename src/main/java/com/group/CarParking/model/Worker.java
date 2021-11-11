package com.group.CarParking.model;

import java.util.List;

import com.google.cloud.firestore.DocumentReference;

public class Worker {
  public Worker() {
  }

  private boolean available;
  private String name;
  private List<DocumentReference> locations;
  private double numberOfRatings;
  private double rating;
  private List<String> services;

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<DocumentReference> getLocations() {
    return locations;
  }

  public void setLocations(List<DocumentReference> locations) {
    this.locations = locations;
  }

  public double getNumberOfRatings() {
    return numberOfRatings;
  }

  public void setNumberOfRatings(double numberOfRatings) {
    this.numberOfRatings = numberOfRatings;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public List<String> getServices() {
    return services;
  }

  public void setServices(List<String> services) {
    this.services = services;
  }

}
