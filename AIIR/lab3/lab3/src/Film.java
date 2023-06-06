import java.util.ArrayList;
import java.util.List;

public class Film {
    private String nazwa;
    private int rokPremiery;
    private List<String> gatunki;
    private List<Aktor> obsada;

    public Film(String nazwa, int rokPremiery) {
        this.nazwa = nazwa;
        this.rokPremiery = rokPremiery;
        gatunki = new ArrayList<>();
        obsada = new ArrayList<>();
    }

    // Gettery i Settery

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getRokPremiery() {
        return rokPremiery;
    }

    public void setRokPremiery(int rokPremiery) {
        this.rokPremiery = rokPremiery;
    }

    public List<String> getGatunki() {
        return gatunki;
    }

    public void setGatunki(List<String> gatunki) {
        this.gatunki = gatunki;
    }

    public List<Aktor> getObsada() {
        return obsada;
    }

    public void setObsada(List<Aktor> obsada) {
        this.obsada = obsada;
    }
}
