package co.simplon.p16.springboard.entity;

/**
 * artist is the page of an artist user. 
 * artist is link to an User by id.
 * 
 */
public class Artist {
    private Integer id;
    private String artistName;
    private String CoverUrl;
    private String contact;
    private String webSite;
    private String city;
    private String bio;
    private Integer listenCount;
    private Integer UserId;

    //
    // CONSTRUCTORS
    //
    
    public Artist(String artistName, String coverUrl, String contact, String webSite, String city, String bio,
            Integer listenCount) {
        this.artistName = artistName;
        CoverUrl = coverUrl;
        this.contact = contact;
        this.webSite = webSite;
        this.city = city;
        this.bio = bio;
        this.listenCount = listenCount;
    }

    public Artist(Integer id, String artistName, String coverUrl, String contact, String webSite, String city,
            String bio, Integer listenCount, Integer userId) {
        this.id = id;
        this.artistName = artistName;
        CoverUrl = coverUrl;
        this.contact = contact;
        this.webSite = webSite;
        this.city = city;
        this.bio = bio;
        this.listenCount = listenCount;
        UserId = userId;
    }

    public Artist() {
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
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public String getCoverUrl() {
        return CoverUrl;
    }
    public void setCoverUrl(String coverUrl) {
        CoverUrl = coverUrl;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getWebSite() {
        return webSite;
    }
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public Integer getListenCount() {
        return listenCount;
    }
    public void setListenCount(Integer listenCount) {
        this.listenCount = listenCount;
    }
    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    //
    // TO STRING
    //
    @Override
    public String toString() {
        return "Artist [CoverUrl=" + CoverUrl + ", artistName=" + artistName + ", bio=" + bio + ", city=" + city
                + ", contact=" + contact + ", id=" + id + ", listenCount=" + listenCount + ", webSite=" + webSite + "]";
    }


}
