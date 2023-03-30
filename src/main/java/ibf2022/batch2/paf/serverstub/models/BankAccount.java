package ibf2022.batch2.paf.serverstub.models;

public class BankAccount {
    
    private Integer id;

    private String fullname;

    private Boolean isActive;

    private String accountType;

    private Float balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount [id=" + id + ", fullname=" + fullname + ", isActive=" + isActive + ", accountType="
                + accountType + ", balance=" + balance + "]";
    }

    
}
