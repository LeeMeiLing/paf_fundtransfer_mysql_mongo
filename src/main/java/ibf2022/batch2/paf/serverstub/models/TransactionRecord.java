package ibf2022.batch2.paf.serverstub.models;

import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class TransactionRecord {
    
    private String transactionId;
    private String fromAcc;
    private String toAcc;
    private Float amountTransferred;
    private Boolean transactionSuccessful;

    public TransactionRecord() {
        this.transactionId = UUID.randomUUID().toString();
    }

    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getFromAcc() {
        return fromAcc;
    }
    public void setFromAcc(String fromAcc) {
        this.fromAcc = fromAcc;
    }
    public String getToAcc() {
        return toAcc;
    }
    public void setToAcc(String toAcc) {
        this.toAcc = toAcc;
    }
    public Float getAmountTransferred() {
        return amountTransferred;
    }
    public void setAmountTransferred(Float amountTransferred) {
        this.amountTransferred = amountTransferred;
    }
    public Boolean getTransactionSuccessful() {
        return transactionSuccessful;
    }
    public void setTransactionSuccessful(Boolean transactionSuccessful) {
        this.transactionSuccessful = transactionSuccessful;
    }
    

    public JsonObject toJson(){

        return Json.createObjectBuilder()
                    .add("transactionId",transactionId)
                    .add("fromAcc",fromAcc)
                    .add("toAcc", toAcc)
                    .add("amountTransferred", amountTransferred)
                    .add("transactionSuccessful",transactionSuccessful)
                    .build();
        
    }
    
    
}
