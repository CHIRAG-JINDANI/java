public class Shelf {
    String shelfNumber;
    String shelfType;
    boolean isFilled;
    String batchId;

    public Shelf(String shelfNumber, String shelfType) {
        this.shelfNumber = shelfNumber;
        this.shelfType = shelfType;
        this.isFilled = false;
        this.batchId = null;
    }

    public String getShelfNumber() {
        return shelfNumber;
    }

    public String getShelfType() {
        return shelfType;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}