package co.simplon.p16.springboard.entity;

/**
 * List of the socials networks by artists to display on artist page.
 * Artist can have 3 socialNetworks
 */
public class SocialNetwork {
    private Integer id;
    private String url;
    private Integer artistId;

    //
    // CONSTRUCTORS
    //
    public SocialNetwork(String url) {
        this.url = url;
    }
    public SocialNetwork(Integer id, String url, Integer artistId) {
        this.id = id;
        this.url = url;
        this.artistId = artistId;
    }
    public SocialNetwork() {
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
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getArtistId() {
        return artistId;
    }
    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    // TO STRING
    @Override
    public String toString() {
        return "SocialNetwork [artistId=" + artistId + ", id=" + id + ", url=" + url + "]";
    }
}
