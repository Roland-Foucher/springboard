package co.simplon.p16.springboard.entity;

import javax.validation.constraints.NotBlank;

/**
 * List of the socials networks by artists to display on artist page.
 * Artist can have 3 socialNetworks
 */
public class SocialNetwork {
    private Integer id;
    @NotBlank
    private String url;
    private Integer artistId;
    @NotBlank
    private String name;

    //
    // CONSTRUCTORS
    //
    public SocialNetwork(String url, String name) {
        this.name = name;
        this.url = url;
    }
    public SocialNetwork(Integer id, String url, String name, Integer artistId) {
        this.id = id;
        this.url = url;
        this.name = name;
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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
