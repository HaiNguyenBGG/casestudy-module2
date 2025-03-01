package model;

public abstract class Person extends BaseEntity {
    protected String name;
    protected String email;
    protected String phone;

    public Person(int id, String name, String email, String phone) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
