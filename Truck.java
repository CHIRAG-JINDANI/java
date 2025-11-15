import java.util.ArrayList;
import java.util.HashMap;

public class Truck {
    String truckNumberPlate;
    String truckCompanyAndModel;
    ArrayList<Batch> assignedBatches = new ArrayList<>();
    HashMap<String, Boolean> batchUpdateStatus = new HashMap<>();

    public Truck(String truckNumberPlate, String truckCompanyAndModel) {
        this.truckNumberPlate = truckNumberPlate;
        this.truckCompanyAndModel = truckCompanyAndModel;
    }

    public String getTruckNumberPlate() {
        return this.truckNumberPlate;
    }

    public void setTruckNumberPlate(String value) {
        this.truckNumberPlate = value;
    }

    public String getTruckCompanyAndModel() {
        return this.truckCompanyAndModel;
    }

    public void setTruckCompanyAndModel(String value) {
        this.truckCompanyAndModel = value;
    }

    public void addBatch(Batch batch) {
        this.assignedBatches.add(batch);
        this.batchUpdateStatus.put(batch.batchId, false);
    }

    public void updateBatchStatus(String batchId, boolean status) {
        this.batchUpdateStatus.put(batchId, status);
    }

    public boolean getBatchStatus(String batchId) {
        return this.batchUpdateStatus.getOrDefault(batchId, false);
    }
}