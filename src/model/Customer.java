package model;

import java.io.Serializable;

public class Customer extends Person implements Serializable {
    public Customer(int id, String name, String email, String phone) {
        super(id, name, email, phone);
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + "', email='" + email + "', phone='" + phone + "'}";
    }
}
