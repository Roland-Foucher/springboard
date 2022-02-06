package co.simplon.p16.springboard.entity;

/**
 * Style affect to an Artist
 * Can be Rock, Pop, Electronic, Alternative, Ambiant, Metal, Hip-Hop/Rap,
 * Experimantal, Punk
 */
public class MusicalStyle {
    private Integer id;
    private String style;

    //
    // CONSTRUCTORS
    //
    public MusicalStyle(String style) {
        this.style = style;
    }

    public MusicalStyle(Integer id, String style) {
        this.id = id;
        this.style = style;
    }

    public MusicalStyle() {
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    //
    // TO STRING
    //
    @Override
    public String toString() {
        return "MusicalStyle [id=" + id + ", style=" + style + "]";
    }
}
