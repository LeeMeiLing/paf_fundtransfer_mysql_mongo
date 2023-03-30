package ibf2022.batch2.paf.serverstub.models;

public class Payload {
    
    private String srcAcct;
    private String destAcct;
    private Float amount;

    public String getSrcAcct() {
        return srcAcct;
    }
    public void setSrcAcct(String srcAcct) {
        this.srcAcct = srcAcct;
    }
    public String getDestAcct() {
        return destAcct;
    }
    public void setDestAcct(String destAcct) {
        this.destAcct = destAcct;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        return "Payload [srcAcct=" + srcAcct + ", destAcct=" + destAcct + ", amount=" + amount + "]";
    }

    
}
