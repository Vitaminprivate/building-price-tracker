package com.buildingtracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a building with pricing information.
 */
public class Building implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String address;
    private double currentPrice;
    private List<PriceHistory> priceHistoryList;
    private PriceTrend recentTrend;

    /**
     * Constructor with name and address.
     */
    public Building(String name, String address) {
        this.name = name;
        this.address = address;
        this.currentPrice = 0.0;
        this.priceHistoryList = new ArrayList<>();
        this.recentTrend = new PriceTrend();
    }

    /**
     * Constructor with all fields.
     */
    public Building(String name, String address, double currentPrice) {
        this(name, address);
        this.currentPrice = currentPrice;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public List<PriceHistory> getPriceHistoryList() {
        return priceHistoryList;
    }

    public void setPriceHistoryList(List<PriceHistory> priceHistoryList) {
        this.priceHistoryList = priceHistoryList;
    }

    public PriceTrend getRecentTrend() {
        return recentTrend;
    }

    public void setRecentTrend(PriceTrend recentTrend) {
        this.recentTrend = recentTrend;
    }

    /**
     * Add a price history record.
     */
    public void addPriceHistory(PriceHistory history) {
        if (this.priceHistoryList == null) {
            this.priceHistoryList = new ArrayList<>();
        }
        this.priceHistoryList.add(history);
    }

    /**
     * Update the current price and record history.
     */
    public void updatePrice(double newPrice) {
        double changePercentage = 0.0;
        if (this.currentPrice > 0) {
            changePercentage = ((newPrice - this.currentPrice) / this.currentPrice) * 100;
        }

        PriceHistory history = new PriceHistory(newPrice, System.currentTimeMillis(), changePercentage);
        addPriceHistory(history);

        this.currentPrice = newPrice;
        updateTrend();
    }

    /**
     * Update the recent trend based on price history.
     */
    public void updateTrend() {
        if (this.priceHistoryList == null || this.priceHistoryList.isEmpty()) {
            this.recentTrend = new PriceTrend();
            return;
        }

        List<PriceHistory> recentEntries = getRecentEntries(10);
        this.recentTrend = calculateTrend(recentEntries);
    }

    /**
     * Get the most recent N price entries.
     */
    private List<PriceHistory> getRecentEntries(int count) {
        int startIndex = Math.max(0, this.priceHistoryList.size() - count);
        return new ArrayList<>(this.priceHistoryList.subList(startIndex, this.priceHistoryList.size()));
    }

    /**
     * Calculate trend from given price history entries.
     */
    private PriceTrend calculateTrend(List<PriceHistory> entries) {
        if (entries.isEmpty()) {
            return new PriceTrend();
        }

        double totalChange = 0;
        double maxChange = Double.NEGATIVE_INFINITY;
        double minChange = Double.POSITIVE_INFINITY;

        for (PriceHistory entry : entries) {
            double change = entry.getChangePercentage();
            totalChange += change;
            maxChange = Math.max(maxChange, change);
            minChange = Math.min(minChange, change);
        }

        double averageChange = totalChange / entries.size();
        String trendDirection = determineTrendDirection(averageChange);
        double volatility = maxChange - minChange;

        return new PriceTrend(trendDirection, averageChange, volatility);
    }

    /**
     * Determine trend direction based on average change.
     */
    private String determineTrendDirection(double averageChange) {
        if (averageChange > 0.5) {
            return "UP";
        } else if (averageChange < -0.5) {
            return "DOWN";
        } else {
            return "STABLE";
        }
    }

    @Override
    public String toString() {
        return "Building{"
                + "name='" + name + '\''
                + ", address='" + address + '\''
                + ", currentPrice=" + currentPrice
                + ", priceHistoryCount=" + (priceHistoryList != null ? priceHistoryList.size() : 0)
                + ", trend=" + recentTrend
                + '}';
    }
}