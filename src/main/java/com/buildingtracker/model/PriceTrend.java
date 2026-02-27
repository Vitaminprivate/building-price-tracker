package com.buildingtracker.model;

import java.io.Serializable;

/**
 * Represents price trend analysis.
 */
public class PriceTrend implements Serializable {
    private static final long serialVersionUID = 1L;

    private String trendDirection;  // UP, DOWN, STABLE
    private double averageChange;   // Average percentage change
    private double volatility;      // Price volatility indicator

    /**
     * Default constructor.
     */
    public PriceTrend() {
        this.trendDirection = "STABLE";
        this.averageChange = 0.0;
        this.volatility = 0.0;
    }

    /**
     * Constructor with all fields.
     */
    public PriceTrend(String trendDirection, double averageChange, double volatility) {
        this.trendDirection = trendDirection;
        this.averageChange = averageChange;
        this.volatility = volatility;
    }

    // Getters and Setters
    public String getTrendDirection() {
        return trendDirection;
    }

    public void setTrendDirection(String trendDirection) {
        this.trendDirection = trendDirection;
    }

    public double getAverageChange() {
        return averageChange;
    }

    public void setAverageChange(double averageChange) {
        this.averageChange = averageChange;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    /**
     * Get a human-readable trend description.
     */
    public String getTrendDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Trend: ").append(trendDirection);
        description.append(" | Average Change: ").append(String.format("%.2f", averageChange)).append("%");
        description.append(" | Volatility: ").append(String.format("%.2f", volatility));
        return description.toString();
    }

    @Override
    public String toString() {
        return "PriceTrend{" +
                "direction='" + trendDirection + '\'' +
                ", averageChange=" + String.format("%.2f", averageChange) + "%" +
                ", volatility=" + String.format("%.2f", volatility) +
                '}';
    }
}