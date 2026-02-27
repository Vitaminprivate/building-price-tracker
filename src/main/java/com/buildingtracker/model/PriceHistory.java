package com.buildingtracker.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Represents a historical price record with timestamp.
 */
public class PriceHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private double price;
    private long timestamp;
    private double changePercentage;

    /**
     * Constructor with all fields.
     */
    public PriceHistory(double price, long timestamp, double changePercentage) {
        this.price = price;
        this.timestamp = timestamp;
        this.changePercentage = changePercentage;
    }

    /**
     * Constructor with price and timestamp (change percentage defaults to 0).
     */
    public PriceHistory(double price, long timestamp) {
        this(price, timestamp, 0.0);
    }

    // Getters and Setters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getChangePercentage() {
        return changePercentage;
    }

    public void setChangePercentage(double changePercentage) {
        this.changePercentage = changePercentage;
    }

    /**
     * Get formatted date and time.
     */
    public String getFormattedDateTime() {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "PriceHistory{" +
                "price=" + price +
                ", date=" + getFormattedDateTime() +
                ", changePercentage=" + String.format("%.2f", changePercentage) + "%" +
                '}';
    }
}