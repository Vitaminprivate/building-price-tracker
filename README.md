# Building Price Tracker

A comprehensive Maven-based Java application to track, store, and analyze building prices over time.

## Features

- **Track Building Information**: Store building name, address, and pricing details
- **Price History**: Maintain a complete history of price changes with timestamps
- **Trend Analysis**: Analyze recent price trends to identify patterns
- **Persistent Storage**: Save data to JSON files for long-term storage
- **Easy to Use**: Simple API for adding, updating, and retrieving building data

## Project Structure

```
src/main/java/com/buildingtracker/
├── app/
│   └── BuildingPriceTrackerApp.java      # Main application entry point
├── model/
│   ├── Building.java                     # Building data model
│   ├── PriceHistory.java                 # Historical price tracking
│   └── PriceTrend.java                   # Price trend analysis
├── service/
│   └── BuildingService.java              # Business logic layer
└── storage/
    ├── BuildingStorage.java              # Storage interface
    └── JsonBuildingStorage.java          # JSON-based storage implementation
```

## Requirements

- Java 11 or higher
- Maven 3.6.0 or higher

## Installation

1. Clone the repository:
```bash
git clone https://github.com/Vitaminprivate/building-price-tracker.git
cd building-price-tracker
```

2. Build the project:
```bash
mvn clean install
```

## Usage

### Running the Application

```bash
mvn exec:java -Dexec.mainClass="com.buildingtracker.app.BuildingPriceTrackerApp"
```

### Basic Example

```java
// Create a building
Building building = new Building("Downtown Tower", "123 Main Street");
building.setCurrentPrice(500000.0);

// Add to service
BuildingService service = new BuildingService();
service.addBuilding(building);

// Record price changes
service.recordPriceChange("Downtown Tower", 510000.0);

// Get building information
Building retrieved = service.getBuilding("Downtown Tower");
System.out.println(retrieved);

// Analyze trends
PriceTrend trend = retrieved.getRecentTrend();
System.out.println("Trend: " + trend.getTrendDirection());
```

## Data Model

### Building
- **Name**: Building identifier
- **Address**: Physical location
- **Current Price**: Latest price amount
- **Price History**: List of historical prices with dates
- **Recent Trend**: Calculated trend based on recent prices

### PriceHistory
- **Price**: Historical price amount
- **Date**: When the price was recorded
- **Change Percentage**: Percentage change from previous price

### PriceTrend
- **Trend Direction**: UP, DOWN, or STABLE
- **Average Change**: Average percentage change
- **Volatility**: Price volatility indicator

## Storage

The application uses JSON-based storage to persist building data. Data is automatically saved when changes are made and loaded on application startup.

## Building and Packaging

To create an executable JAR:

```bash
mvn clean package
java -jar target/building-price-tracker-1.0.0.jar
```

## Testing

Run unit tests:

```bash
mvn test
```

## Dependencies

- **Gson 2.8.9**: JSON serialization and deserialization
- **JUnit 4.13.2**: Unit testing framework

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

Vitaminprivate

## Support

For issues, questions, or suggestions, please open an issue on GitHub.