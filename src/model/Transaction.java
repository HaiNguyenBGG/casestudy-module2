package model;

public abstract class Transaction extends BaseEntity {
    protected double amount;
    protected String status;

    public Transaction(int id, double amount, String status) {
        super(id);
        this.amount = amount;
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
