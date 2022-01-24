package co.simplon.p16.springboard.entity;

import java.util.List;

/**
 * artist is the page of an artist user. 
 * artist is link to an User by id.
 * 
 */
public class Artist {
    private Integer id;
    private String artistName;
    private String coverUrl;
    private String contact;
    private String webSite;
    private String city;
    private String bio;
    private Integer listenCount;
    private Integer voteCount;
    private Integer musicalStyleId;
    private Integer userId;
    private List <Show> listOfShow;

    //
    // CONSTRUCTORS
    //
    


    public Artist(String artistName, String coverUrl, String contact, String webSite, String city, String bio,
            Integer listenCount, Integer voteCount) {
        this.artistName = artistName;
        this.coverUrl = coverUrl;
        this.contact = contact;
        this.webSite = webSite;
        this.city = city;
        this.bio = bio;
        this.listenCount = listenCount;
        this.voteCount = voteCount;
    }

    public Artist(Integer id, String artistName, String coverUrl, String contact, String webSite, String city,
            String bio, Integer listenCount, Integer voteCount, Integer musicalStyleId, Integer userId) {
        this.id = id;
        this.artistName = artistName;
        this.coverUrl = coverUrl;
        this.contact = contact;
        this.webSite = webSite;
        this.city = city;
        this.bio = bio;
        this.listenCount = listenCount;
        this.voteCount = voteCount;
        this.musicalStyleId = musicalStyleId;
        this.userId = userId;
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
        return this.coverUrl;
    }
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getMusicalStyleId() {
        return musicalStyleId;
    }

    public void setMusicalStyleId(Integer musicalStyleId) {
        this.musicalStyleId = musicalStyleId;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "Artist [artistName=" + artistName + ", bio=" + bio + ", city=" + city + ", contact=" + contact
                + ", coverUrl=" + coverUrl + ", id=" + id + ", listenCount=" + listenCount + ", musicalStyleId="
                + musicalStyleId + ", userId=" + userId + ", voteCount=" + voteCount + ", webSite=" + webSite + "]";
    }

    public List<Show> getListOfShow() {
        return listOfShow;
    }

    public void setListOfShow(List<Show> listOfShow) {
        this.listOfShow = listOfShow;
    }


}
