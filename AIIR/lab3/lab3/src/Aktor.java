import java.time.LocalDate;

public class Aktor {
    private int id_aktora;
    private String nazwisko;
    private String imie;
    private String plec;
    private LocalDate dataUrodzenia;

    public Aktor(int id_aktora, String nazwisko, String imie, String plec, LocalDate dataUrodzenia) {
        this.id_aktora = id_aktora;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.plec = plec;
        this.dataUrodzenia = dataUrodzenia;
    }

    // Gettery i Settery
    public int getId_aktora(){
        return id_aktora;
    }

    public void setId_aktora(int id_aktora){
        this.id_aktora = id_aktora;
    }
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
}
