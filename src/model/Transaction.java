package model;

public abstract class Transaction extends BaseEntity {
    private static final long serialVersionUID = 1L;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
