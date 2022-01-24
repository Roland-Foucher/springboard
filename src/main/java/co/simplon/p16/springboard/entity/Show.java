package co.simplon.p16.springboard.entity;

import java.time.LocalDate;

/**
 * show are add by artists
 * Can search artists have show or access a list of shows
 */
public class Show {
    private Integer id;
    private LocalDate date;
    private String venue;
    private String adress;

    //
    // Constructors
    //
    public Show(Integer id, LocalDate date, String venue, String adress) {
        this.id = id;
        this.date = date;
        this.venue = venue;
        this.adress = adress;
    }
    public Show(LocalDate date, String venue, String adress) {
        this.date = date;
        this.venue = venue;
        this.adress = adress;
    }
    public Show() {
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    //
    // TO STRING
    //
    @Override
    public String toString() {
        return "Show [adress=" + adress + ", date=" + date + ", id=" + id + ", venue=" + venue + "]";
    }


}
