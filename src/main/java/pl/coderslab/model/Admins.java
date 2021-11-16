package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admins {

    private int id = 0;
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "email";
    private String password = "password";
    private int superAdmin = 0;
    private int enable = 1;

    public Admins(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = hashPassword(password);

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
                ", superadmin=" + superAdmin +
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

    public int getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(int superAdmin) {
        this.superAdmin = superAdmin;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
