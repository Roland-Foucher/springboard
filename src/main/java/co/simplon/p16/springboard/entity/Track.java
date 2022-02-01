package co.simplon.p16.springboard.entity;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

/**
 * Track assigned to an artist page, artist can have 3 tracks.
 */
public class Track {
    private Integer id;
    @NotBlank
    private String name;
    private String url;
    private Integer artistId;
  

    //
    // CONSTRUCTORS
    //
    public Track(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public Track() {
    }
    public Track(Integer id, String name, String url, Integer artistId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.artistId = artistId;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    //
    // TO STRING
    // 
    @Override
    public String toString() {
        return "Track [artistId=" + artistId + ", id=" + id + ", name=" + name + ", url=" + url + "]";
    }

}
