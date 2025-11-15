public class Batch {
    String batchId;
    int batchItemQuantity;
    int batchItemPrice;
    String batchDescription;
    int batchTotalCost;
    String batchType;

    public Batch(String batchId, int batchItemQuantity, int batchItemPrice, String batchDescription, String batchType) {
        this.batchDescription = batchDescription;
        this.batchId = batchId;
        this.batchItemPrice = batchItemPrice;
        this.batchItemQuantity = batchItemQuantity;
        this.batchTotalCost = batchItemQuantity * batchItemPrice;
        this.batchType = batchType;
    }

    public String getBatchId() {
        return batchId;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }
}