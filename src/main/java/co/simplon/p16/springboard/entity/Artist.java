package co.simplon.p16.springboard.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * artist is the page of an artist user.
 * artist is link to an User by id.
 * 
 */
public class Artist {
    private Integer id;
    private String artistName;
    private String coverUrl;
    private MultipartFile coverData;
    private String contact;
    private String webSite;
    private String city;
    private String bio;
    private Integer listenCount;
    private Integer voteCount;
    private boolean isOnArtistList;
    private Integer musicalStyleId;
    private Integer userId;
    private List<Show> showList;
    private List<SocialNetwork> socialNetworkList;
    private List<Track> TrackList;
    private String styleName;
    

    //
    // CONSTRUCTORS
    //

    public Artist(String artistName, String coverUrl, String contact, String webSite, String city, String bio,
            Integer listenCount, Integer voteCount, boolean isOnArtistList) {
        this.artistName = artistName;
        this.coverUrl = coverUrl;
        this.contact = contact;
        this.webSite = webSite;
        this.city = city;
        this.bio = bio;
        this.listenCount = listenCount;
        this.voteCount = voteCount;
        this.isOnArtistList = isOnArtistList;
    }

    public Artist(Integer id, String artistName, String coverUrl, String contact, String webSite, String city,
            String bio, Integer listenCount, Integer voteCount, boolean isOnArtistList, Integer musicalStyleId, Integer userId) {
        this.id = id;
        this.artistName = artistName;
        this.coverUrl = coverUrl;
        this.contact = contact;
        this.webSite = webSite;
        this.city = city;
        this.bio = bio;
        this.listenCount = listenCount;
        this.voteCount = voteCount;
        this.isOnArtistList = isOnArtistList;
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

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public List<Show> getShowList() {
        return showList;
    }

    public void setShowList(List<Show> showList) {
        this.showList = showList;
    }

    public List<SocialNetwork> getSocialNetworkList() {
        return socialNetworkList;
    }

    public void setSocialNetworkList(List<SocialNetwork> socialNetworkList) {
        this.socialNetworkList = socialNetworkList;
    }

    public List<Track> getTrackList() {
        return TrackList;
    }

    public void setTrackList(List<Track> trackList) {
        TrackList = trackList;
    }

    @Override
    public String toString() {
        return "Artist [artistName=" + artistName + ", bio=" + bio + ", city=" + city + ", contact=" + contact
                + ", coverUrl=" + coverUrl + ", id=" + id + ", listenCount=" + listenCount + ", musicalStyleId="
                + musicalStyleId + ", userId=" + userId + ", voteCount=" + voteCount + ", webSite=" + webSite + "]";
    }

    public boolean getIsOnArtistList() {
        return isOnArtistList;
    }

    public void setOnArtistList(boolean isOnArtistList) {
        this.isOnArtistList = isOnArtistList;
    }

    public MultipartFile getCoverData() {
        return coverData;
    }

    public void setCoverData(MultipartFile coverData) {
        this.coverData = coverData;
    }

}
