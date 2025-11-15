import java.util.HashMap;
import java.util.ArrayList;

public class ProjectDatabase {
    HashMap<Truck, Driver> truckDrivers = new HashMap<>();
    ArrayList<Truck> truckList = new ArrayList<>();
    HashMap<Truck, TypeTruck> truckTypeHashMap = new HashMap<>();
    ArrayList<Batch> batchList = new ArrayList<>();
    ArrayList<Worker> workerList = new ArrayList<>();
    ArrayList<Driver> driverList = new ArrayList<>();
    ArrayList<Shelf> shelfList = new ArrayList<>();
    ArrayList<String> shelfTypeList = new ArrayList<>();
    ArrayList<Batch> assignedBatchesList = new ArrayList<>();

    HashMap<String, String> userPasswords = new HashMap<>();
    HashMap<String, String> userTypes = new HashMap<>();

    public ArrayList<Truck> getTruckList() {
        return this.truckList;
    }

    public ArrayList<Batch> getBatchList() {
        return this.batchList;
    }

    public ArrayList<Worker> getWorkerList() {
        return this.workerList;
    }

    public ArrayList<Driver> getDriverList() {
        return this.driverList;
    }

    public ArrayList<Shelf> getShelfList() {
        return this.shelfList;
    }

    public ArrayList<String> getShelfTypeList() {
        return this.shelfTypeList;
    }

    public ArrayList<Batch> getAssignedBatchesList() {
        return this.assignedBatchesList;
    }

    public void setTruckList(ArrayList<Truck> value) {
        this.truckList = value;
    }
}