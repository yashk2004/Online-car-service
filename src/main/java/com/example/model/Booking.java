package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;
    private String userId;
    private String name;
    private String carModel;
    private String serviceType;
    private String price;
    private String bookedAt;

    // New fields
    private String pickupOption;      // "pickup" or "drop"
    private String paymentMethod;     // "online" or "cash"
    private String estimatedTime;     // e.g. "2-3 hours"
    private String serviceBuddyName;
    private String serviceBuddyPhone;
    private String serviceBuddyPic;   // initials for avatar

    public Booking() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getBookedAt() { return bookedAt; }
    public void setBookedAt(String bookedAt) { this.bookedAt = bookedAt; }

    public String getPickupOption() { return pickupOption; }
    public void setPickupOption(String pickupOption) { this.pickupOption = pickupOption; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(String estimatedTime) { this.estimatedTime = estimatedTime; }

    public String getServiceBuddyName() { return serviceBuddyName; }
    public void setServiceBuddyName(String serviceBuddyName) { this.serviceBuddyName = serviceBuddyName; }

    public String getServiceBuddyPhone() { return serviceBuddyPhone; }
    public void setServiceBuddyPhone(String serviceBuddyPhone) { this.serviceBuddyPhone = serviceBuddyPhone; }

    public String getServiceBuddyPic() { return serviceBuddyPic; }
    public void setServiceBuddyPic(String serviceBuddyPic) { this.serviceBuddyPic = serviceBuddyPic; }
}
