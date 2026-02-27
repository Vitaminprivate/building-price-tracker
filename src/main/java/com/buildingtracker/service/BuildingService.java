package com.buildingtracker.service;

import com.buildingtracker.model.Building;
import com.buildingtracker.storage.BuildingStorage;
import com.buildingtracker.storage.JsonBuildingStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for managing buildings and their prices.
 */
public class BuildingService {
    private BuildingStorage storage;
    private List<Building> buildingCache;

    /**
     * Constructor with default JSON storage.
     */
    public BuildingService() {
        this(new JsonBuildingStorage());
    }

    /**
     * Constructor with custom storage implementation.
     */
    public BuildingService(BuildingStorage storage) {
        this.storage = storage;
        this.buildingCache = storage.loadBuildings();
    }

    /**
     * Add a new building.
     */
    public void addBuilding(Building building) {
        if (building == null || building.getName() == null || building.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Building and name cannot be null or empty.");
        }

        // Check if building already exists
        if (buildingCache.stream().anyMatch(b -> b.getName().equals(building.getName()))) {
            throw new IllegalArgumentException("Building with name '" + building.getName() + "' already exists.");
        }

        buildingCache.add(building);
        storage.saveBuilding(building);
        System.out.println("Building '" + building.getName() + "' added successfully.");
    }

    /**
     * Get a building by name.
     */
    public Building getBuilding(String name) {
        return buildingCache.stream()
                .filter(b -> b.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get all buildings.
     */
    public List<Building> getAllBuildings() {
        return new ArrayList<>(buildingCache);
    }

    /**
     * Record a price change for a building.
     */
    public void recordPriceChange(String buildingName, double newPrice) {
        Building building = getBuilding(buildingName);
        if (building == null) {
            throw new IllegalArgumentException("Building '" + buildingName + "' not found.");
        }

        if (newPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        building.updatePrice(newPrice);
        storage.saveBuilding(building);
        System.out.println("Price updated for '" + buildingName + "' to: " + newPrice);
    }

    /**
     * Update building information.
     */
    public void updateBuilding(String buildingName, String newAddress, double newPrice) {
        Building building = getBuilding(buildingName);
        if (building == null) {
            throw new IllegalArgumentException("Building '" + buildingName + "' not found.");
        }

        if (newAddress != null && !newAddress.trim().isEmpty()) {
            building.setAddress(newAddress);
        }

        if (newPrice >= 0) {
            building.updatePrice(newPrice);
        }

        storage.saveBuilding(building);
        System.out.println("Building '" + buildingName + "' updated successfully.");
    }

    /**
     * Delete a building.
     */
    public void deleteBuilding(String buildingName) {
        Building building = getBuilding(buildingName);
        if (building == null) {
            throw new IllegalArgumentException("Building '" + buildingName + "' not found.");
        }

        buildingCache.remove(building);
        storage.deleteBuilding(buildingName);
    }

    /**
     * Get building price history.
     */
    public void printPriceHistory(String buildingName) {
        Building building = getBuilding(buildingName);
        if (building == null) {
            System.out.println("Building '" + buildingName + "' not found.");
            return;
        }

        System.out.println("\n=== Price History for " + buildingName + " ===");
        if (building.getPriceHistoryList().isEmpty()) {
            System.out.println("No price history available.");
        } else {
            building.getPriceHistoryList().forEach(System.out::println);
        }
    }

    /**
     * Get building trend information.
     */
    public void printTrendInfo(String buildingName) {
        Building building = getBuilding(buildingName);
        if (building == null) {
            System.out.println("Building '" + buildingName + "' not found.");
            return;
        }

        System.out.println("\n=== Trend Information for " + buildingName + " ===");
        System.out.println(building.getRecentTrend().getTrendDescription());
    }

    /**
     * Print all buildings information.
     */
    public void printAllBuildings() {
        System.out.println("\n=== All Buildings ===");
        if (buildingCache.isEmpty()) {
            System.out.println("No buildings available.");
        } else {
            buildingCache.forEach(System.out::println);
        }
    }

    /**
     * Refresh cache from storage.
     */
    public void refreshCache() {
        this.buildingCache = storage.loadBuildings();
        System.out.println("Cache refreshed from storage.");
    }
}