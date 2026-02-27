package com.buildingtracker.storage;

import com.buildingtracker.model.Building;
import java.util.List;

/**
 * Interface for building storage operations.
 */
public interface BuildingStorage {
    /**
     * Save all buildings to storage.
     */
    void saveBuildings(List<Building> buildings);

    /**
     * Load all buildings from storage.
     */
    List<Building> loadBuildings();

    /**
     * Save a single building to storage.
     */
    void saveBuilding(Building building);

    /**
     * Load a specific building by name.
     */
    Building loadBuilding(String buildingName);

    /**
     * Delete a building from storage.
     */
    void deleteBuilding(String buildingName);

    /**
     * Check if storage file exists.
     */
    boolean exists();

    /**
     * Clear all data from storage.
     */
    void clearAll();
}