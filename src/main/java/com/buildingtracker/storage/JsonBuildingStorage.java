import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class JsonBuildingStorage {

    private final String filePath;
    private final Gson gson;

    public JsonBuildingStorage(String filePath) {
        this.filePath = filePath;
        this.gson = new Gson();
    }

    public void saveBuildings(List<Building> buildings) throws IOException {
        String json = gson.toJson(buildings);
        Files.write(Paths.get(filePath), json.getBytes());
    }

    public List<Building> loadBuildings() throws IOException {
        Type buildingListType = new TypeToken<List<Building>>(){}.getType();
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        return gson.fromJson(json, buildingListType);
    }
}

class Building {
    private String name;
    private String address;
    private int floors;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getFloors() { return floors; }
    public void setFloors(int floors) { this.floors = floors; }
}