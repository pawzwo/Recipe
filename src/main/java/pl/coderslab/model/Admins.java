package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admins {

    private int id = 0;
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "email";
    private String password = "password";
    private int superadmin = 0;
    private int enable = 0;

    public Admins(String firstName, String lastName, String email, String password, int superadmin, int enable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = hashPassword(password);
        this.superadmin = superadmin;
        this.enable = enable;
    }

    public Admins() {
    }

    public boolean checkPassword(String passwordToCheck) {
        return BCrypt.checkpw(passwordToCheck, this.password);
    }

    public String hashPassword (String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", superadmin=" + superadmin +
                ", enable=" + enable +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(int superadmin) {
        this.superadmin = superadmin;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
