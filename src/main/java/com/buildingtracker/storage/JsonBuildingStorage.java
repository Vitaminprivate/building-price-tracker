package com.buildingtracker.storage;

import com.buildingtracker.model.Building;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON-based implementation of BuildingStorage.
 */
public class JsonBuildingStorage implements BuildingStorage {
    private final String dataDirectory;
    private final String dataFilePath;
    private final Gson gson;

    /**
     * Constructor with custom data directory.
     */
    public JsonBuildingStorage(String dataDirectory) {
        this.dataDirectory = dataDirectory;
        this.dataFilePath = dataDirectory + File.separator + "buildings.json";
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        // Create directory if it doesn't exist
        Path path = Paths.get(dataDirectory);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.err.println("Failed to create data directory: " + e.getMessage());
        }
    }

    /**
     * Default constructor using 'data' directory.
     */
    public JsonBuildingStorage() {
        this("data");
    }

    @Override
    public void saveBuildings(List<Building> buildings) {
        try (FileWriter writer = new FileWriter(dataFilePath)) {
            gson.toJson(buildings, writer);
            System.out.println("Buildings saved successfully to: " + dataFilePath);
        } catch (IOException e) {
            System.err.println("Error saving buildings: " + e.getMessage());
        }
    }

    @Override
    public List<Building> loadBuildings() {
        try {
            if (!Files.exists(Paths.get(dataFilePath))) {
                return new ArrayList<>();
            }

            String content = new String(Files.readAllBytes(Paths.get(dataFilePath)));
            if (content.trim().isEmpty()) {
                return new ArrayList<>();
            }

            Type listType = new TypeToken<List<Building>>(){}.getType();
            return gson.fromJson(content, listType);
        } catch (IOException e) {
            System.err.println("Error loading buildings: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void saveBuilding(Building building) {
        List<Building> buildings = loadBuildings();
        
        // Remove existing building with same name
        buildings.removeIf(b -> b.getName().equals(building.getName()));
        
        // Add the new building
        buildings.add(building);
        
        // Save all buildings
        saveBuildings(buildings);
    }

    @Override
    public Building loadBuilding(String buildingName) {
        List<Building> buildings = loadBuildings();
        return buildings.stream()
                .filter(b -> b.getName().equals(buildingName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteBuilding(String buildingName) {
        List<Building> buildings = loadBuildings();
        buildings.removeIf(b -> b.getName().equals(buildingName));
        saveBuildings(buildings);
        System.out.println("Building '" + buildingName + "' deleted successfully.");
    }

    @Override
    public boolean exists() {
        return Files.exists(Paths.get(dataFilePath));
    }

    @Override
    public void clearAll() {
        try {
            Files.deleteIfExists(Paths.get(dataFilePath));
            System.out.println("All data cleared.");
        } catch (IOException e) {
            System.err.println("Error clearing data: " + e.getMessage());
        }
    }

    /**
     * Get the path to the data file.
     */
    public String getDataFilePath() {
        return dataFilePath;
    }
}