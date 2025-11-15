import java.util.ArrayList;

public class ProjectMethods {
    int batchNum = 0;

    public void getTruckOptions() {
        System.out.println("1.Drivers Of truck\n2.Batches in truck\n");
    }

    public Object findTruck() {
        return null;
    }

    public int setBatchIdNum() {
        return batchNum;
    }

    public Boolean findTruckById(ProjectDatabase projectDatabase, String truckId) {
        ArrayList<Truck> truckList = projectDatabase.getTruckList();
        for (Truck truck : truckList) {
            if (truck.truckNumberPlate.equals(truckId)) {
                return true;
            }
        }
        return false;
    }

    public Boolean findBatchById(ProjectDatabase projectDatabase, String batchId) {
        ArrayList<Batch> batchList = projectDatabase.getBatchList();
        for (Batch batch : batchList) {
            if (batch.batchId.equals(batchId)) {
                return true;
            }
        }
        return false;
    }

    public Batch getBatchById(ProjectDatabase projectDatabase, String batchId) {
        ArrayList<Batch> batchList = projectDatabase.getBatchList();
        for (Batch batch : batchList) {
            if (batch.batchId.equals(batchId)) {
                return batch;
            }
        }
        return null;
    }

    public Truck getTruckById(ProjectDatabase projectDatabase, String truckId) {
        ArrayList<Truck> truckList = projectDatabase.getTruckList();
        for (Truck truck : truckList) {
            if (truck.truckNumberPlate.equals(truckId)) {
                return truck;
            }
        }
        return null;
    }

    public Driver getDriverByName(ProjectDatabase projectDatabase, String driverName) {
        ArrayList<Driver> driverList = projectDatabase.getDriverList();
        for (Driver driver : driverList) {
            if (driver.driverName.equals(driverName)) {
                return driver;
            }
        }
        return null;
    }

    public boolean isBatchAssigned(ProjectDatabase projectDatabase, Batch batch) {
        return projectDatabase.getAssignedBatchesList().contains(batch);
    }

    public boolean isBatchOnShelf(ProjectDatabase projectDatabase, String batchId) {
        ArrayList<Shelf> shelfList = projectDatabase.getShelfList();
        for (Shelf shelf : shelfList) {
            if (shelf.getBatchId() != null && shelf.getBatchId().equals(batchId)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPendingBatches(Truck truck) {
        for (Batch batch : truck.assignedBatches) {
            if (!truck.getBatchStatus(batch.getBatchId())) {
                return true;
            }
        }
        return false;
    }

    public void createShelfType(ProjectDatabase projectDatabase, String shelfType) {
        projectDatabase.shelfTypeList.add(shelfType);
        for (int i = 1; i <= 100; i++) {
            String shelfNumber = shelfType.substring(0, 1).toUpperCase() + "-" + String.format("%03d", i);
            Shelf shelf = new Shelf(shelfNumber, shelfType);
            projectDatabase.shelfList.add(shelf);
        }
    }

    public Shelf getNextAvailableShelf(ProjectDatabase projectDatabase, String shelfType) {
        ArrayList<Shelf> shelfList = projectDatabase.getShelfList();
        for (Shelf shelf : shelfList) {
            if (shelf.getShelfType().equals(shelfType) && !shelf.isFilled()) {
                return shelf;
            }
        }
        return null;
    }

    public void assignBatchToShelf(ProjectDatabase projectDatabase, Batch batch) {
        Shelf shelf = getNextAvailableShelf(projectDatabase, batch.getBatchType());
        if (shelf != null) {
            shelf.setFilled(true);
            shelf.setBatchId(batch.getBatchId());
        }
    }

    public void removeBatchFromShelf(ProjectDatabase projectDatabase, String batchId) {
        ArrayList<Shelf> shelfList = projectDatabase.getShelfList();
        for (Shelf shelf : shelfList) {
            if (shelf.getBatchId() != null && shelf.getBatchId().equals(batchId)) {
                shelf.setFilled(false);
                shelf.setBatchId(null);
                break;
            }
        }
    }

    public ArrayList<Shelf> getShelvesOfType(ProjectDatabase projectDatabase, String shelfType) {
        ArrayList<Shelf> result = new ArrayList<>();
        ArrayList<Shelf> shelfList = projectDatabase.getShelfList();
        for (Shelf shelf : shelfList) {
            if (shelf.getShelfType().equals(shelfType)) {
                result.add(shelf);
            }
        }
        return result;
    }

    public void removeTruckById(ProjectDatabase projectDatabase, String truckId) {
        Truck truckToRemove = getTruckById(projectDatabase, truckId);
        if (truckToRemove != null) {
            for (Batch batch : truckToRemove.assignedBatches) {
                projectDatabase.assignedBatchesList.remove(batch);
            }
            projectDatabase.truckList.remove(truckToRemove);
            projectDatabase.truckTypeHashMap.remove(truckToRemove);
            projectDatabase.truckDrivers.remove(truckToRemove);
        }
    }

    public void removeBatchById(ProjectDatabase projectDatabase, String batchId) {
        Batch batchToRemove = getBatchById(projectDatabase, batchId);
        if (batchToRemove != null) {
            projectDatabase.batchList.remove(batchToRemove);
            projectDatabase.assignedBatchesList.remove(batchToRemove);
            removeBatchFromShelf(projectDatabase, batchId);
            for (Truck truck : projectDatabase.truckList) {
                truck.assignedBatches.remove(batchToRemove);
                truck.batchUpdateStatus.remove(batchId);
            }
        }
    }
}