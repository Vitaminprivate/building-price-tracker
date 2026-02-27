package com.buildingtracker.app;

import com.buildingtracker.model.Building;
import com.buildingtracker.service.BuildingService;

/**
 * Main application class demonstrating the Building Price Tracker functionality.
 */
public class BuildingPriceTrackerApp {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Welcome to Building Price Tracker");
        System.out.println("========================================\n");

        // Initialize the service
        BuildingService service = new BuildingService();

        try {
            // Create and add buildings
            System.out.println("--- Adding Buildings ---");
            Building building1 = new Building("Downtown Tower", "123 Main Street", 500000.0);
            service.addBuilding(building1);

            Building building2 = new Building("Riverside Plaza", "456 River Avenue", 750000.0);
            service.addBuilding(building2);

            Building building3 = new Building("Central Park Residency", "789 Park Lane", 600000.0);
            service.addBuilding(building3);

            // Print all buildings
            service.printAllBuildings();

            // Record price changes
            System.out.println("\n--- Recording Price Changes ---");
            service.recordPriceChange("Downtown Tower", 510000.0);
            service.recordPriceChange("Downtown Tower", 520000.0);
            service.recordPriceChange("Downtown Tower", 515000.0);
            service.recordPriceChange("Downtown Tower", 525000.0);

            service.recordPriceChange("Riverside Plaza", 760000.0);
            service.recordPriceChange("Riverside Plaza", 770000.0);
            service.recordPriceChange("Riverside Plaza", 765000.0);

            service.recordPriceChange("Central Park Residency", 595000.0);
            service.recordPriceChange("Central Park Residency", 590000.0);
            service.recordPriceChange("Central Park Residency", 585000.0);

            // Print all buildings with updated information
            service.printAllBuildings();

            // Print price history for each building
            service.printPriceHistory("Downtown Tower");
            service.printPriceHistory("Riverside Plaza");
            service.printPriceHistory("Central Park Residency");

            // Print trend information
            service.printTrendInfo("Downtown Tower");
            service.printTrendInfo("Riverside Plaza");
            service.printTrendInfo("Central Park Residency");

            // Update building information
            System.out.println("\n--- Updating Building Information ---");
            service.updateBuilding("Downtown Tower", "125 Main Street", 530000.0);

            // Demonstrate individual building retrieval
            System.out.println("\n--- Individual Building Details ---");
            Building retrieved = service.getBuilding("Downtown Tower");
            if (retrieved != null) {
                System.out.println("Building: " + retrieved.getName());
                System.out.println("Address: " + retrieved.getAddress());
                System.out.println("Current Price: " + retrieved.getCurrentPrice());
                System.out.println("Price History Records: " + retrieved.getPriceHistoryList().size());
                System.out.println("Trend: " + retrieved.getRecentTrend().getTrendDescription());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n========================================");
        System.out.println("Building Price Tracker - Session Complete");
        System.out.println("========================================");
    }
}