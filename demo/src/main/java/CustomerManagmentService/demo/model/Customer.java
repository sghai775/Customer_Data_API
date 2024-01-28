package CustomerManagmentService.demo.model;

import jakarta.persistence.*;

// represents a customer, with a name
@Entity
@Table(name = "customers")
public class Customer {

    // data fields
    // every customer has a unique ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "emailAddress")
    private String email;

    @Column(name = "phoneNumber")
    private long phone;

    @Column(name = "zipCode")
    private int zip;

    @Column(name = "premiumMember")
    private boolean membership;

    public Customer() {

    }

    // constructors
    public Customer(String name, String email, long phone, int zip, boolean membership) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.zip = zip;
        this.membership = membership;
    }

    // getters and setters, notice the ID cannot be changed
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public boolean getMembership() {
        return membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }
}
