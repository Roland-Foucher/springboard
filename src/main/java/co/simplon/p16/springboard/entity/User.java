package co.simplon.p16.springboard.entity;

/**
 * User have identity and password to connect to the website
 * Can have 3 roles : ROLE_INTERNAUT / ROLE_ARTIST / ROLE_PRO that define some displays mode
 * User is use to connect with Spring Boot
 * 
 */
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String role;

    //
    // CONSTRUCTORS
    //
    
    public User(Integer id, String firstName, String lastName, String mail, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    public User(String firstName, String lastName, String mail, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    //
    // GETTERS AND SETTERS
    //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //TO STRING
    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + ", mail=" + mail
                + ", password=" + password + ", role=" + role + "]";
    }

}
