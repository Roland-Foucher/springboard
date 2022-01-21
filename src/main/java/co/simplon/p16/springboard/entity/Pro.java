package co.simplon.p16.springboard.entity;

/**
 * pro is an user have access to more informations about artists.
 * Pro Appeare in pro List, have access to pro and artist lists.
 * 
 */
public class Pro {
    private Integer id;
    private String comagnieName;
    private String activity;
    private String contact;
    private String city;
    private String siret;
    private Integer userId;

    //
    // Constructors
    //
    
    public Pro(String comagnieName, String activity, String contact, String city, String siret) {
        this.comagnieName = comagnieName;
        this.activity = activity;
        this.contact = contact;
        this.city = city;
        this.siret = siret;
    }
    public Pro(Integer id, String comagnieName, String activity, String contact, String city, String siret,
            Integer userId) {
        this.id = id;
        this.comagnieName = comagnieName;
        this.activity = activity;
        this.contact = contact;
        this.city = city;
        this.siret = siret;
        this.userId = userId;
    }
    public Pro() {
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
    public String getComagnieName() {
        return comagnieName;
    }
    public void setComagnieName(String comagnieName) {
        this.comagnieName = comagnieName;
    }
    public String getActivity() {
        return activity;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getSiret() {
        return siret;
    }
    public void setSiret(String siret) {
        this.siret = siret;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    //
    // To String
    //
    @Override
    public String toString() {
        return "Pro [activity=" + activity + ", city=" + city + ", comagnieName=" + comagnieName + ", contact="
                + contact + ", id=" + id + ", siret=" + siret + ", userId=" + userId + "]";
    }

}
