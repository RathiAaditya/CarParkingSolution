package com.group.CarParking.model;

import java.util.HashMap;
import java.util.Map;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;

/**
 * A Booking Object that contains the startTime, endTime and the userId of the
 * user who booked the slot
 */
public class Booking {
  private Timestamp startTime, endTime;

  private DocumentReference user, slot;

  public boolean fieldsAreNotNull() {
    return this.startTime != null && this.endTime != null && this.user != null && this.slot != null;
  }

  /**
   * Create a <code>Booking</code> object to be used in booking slots.
   * 
   * @param startTime Booking check-in time
   * @param endTime   Booking check-out time
   * @param user      The document reference of the user who is booking the slot
   * @param slot      The document reference of the slot which is being booked.
   * @apiNote `slot` means <code>SlotModel</code>
   */
  public Booking(Timestamp startTime, Timestamp endTime, DocumentReference user, DocumentReference slot) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.user = user;
    this.slot = slot;
  }

  /**
   * Default constructor to be used by Firebase to convert database map to a
   * <code>Booking</code> Object.
   * 
   * @deprecated <strong>Do not use this yourself</strong>. Fields are not meant
   *             to be <code>null</code>
   */
  public Booking() {
  }

  /**
   * The ID of the booked Slot. May be <code>null</code> if this object was
   * created by a <code>SlotModel</code>.
   * 
   * @return ID of this booked slot.
   */
  public DocumentReference getSlot() {
    return slot;
  }

  public void setSlot(DocumentReference slot) {
    this.slot = slot;
  }

  /**
   * @param startTime
   * @param endTime
   * @param user
   */
  public Booking(Timestamp startTime, Timestamp endTime, DocumentReference user) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.user = user;
  }

  public DocumentReference getUser() {
    return user;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public void setUser(DocumentReference user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "[Booking from " + startTime + " to " + endTime + " by " + user.getId() + "]";
  }

  /**
   * Get the Map representation in order to update a document in Firebase
   * 
   * @throws IllegalStateException If any fields are <code>null</code>
   */
  public Map<String, Object> toMap() throws IllegalStateException {
    if (this.user == null || this.startTime == null || this.endTime == null)
      throw new IllegalStateException();
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("startTime", this.startTime);
    map.put("endTime", this.endTime);
    map.put("user", this.user);
    return map;
  }
}
