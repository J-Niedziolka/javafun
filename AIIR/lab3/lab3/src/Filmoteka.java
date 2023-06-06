import java.util.ArrayList;
import java.util.List;

public class Filmoteka {
    private List<Aktor> aktorzy;
    private List<Film> filmy;

    public Filmoteka() {
        aktorzy = new ArrayList<>();
        filmy = new ArrayList<>();
    }

    // Gettery i Settery

    public List<Aktor> getAktorzy() {
        return aktorzy;
    }

    public void setAktorzy(List<Aktor> aktorzy) {
        this.aktorzy = aktorzy;
    }

    public List<Film> getFilmy() {
        return filmy;
    }

    public void setFilmy(List<Film> filmy) {
        this.filmy = filmy;
    }
}
