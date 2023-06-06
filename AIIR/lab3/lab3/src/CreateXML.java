import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.crypto.Data;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Należy przygotować dokument XML z filmoteką zawierającą minimum trzy swoje ulubione
//        filmy z powiązaniem do 5 aktorów występujących w tych filmach. Preferowanym
//        rozwiązaniem jest dodanie dwóch filmów, w których występuje ten sam aktor i powiązanie
//        ich z tym samym elementem aktora w pliku XML. Struktura dokumentu XML musi
//        odpowiadać przedstawionemu powyżej przykładowi (może być rozszerzona o inne elementy).
//class Film{
//    public String nazwa;
//    public int rok_premiery;
//    public List<Gatunek> gatunki;
//    public List<Rola> obsada;
//
//    public Film(String nazwa, int rok_premiery, List<Gatunek> gatunki, List<Rola> obsada){
//        this.nazwa = nazwa;
//        this.rok_premiery = rok_premiery;
//        this.gatunki = gatunki;
//        this.obsada = obsada;
//    }
//}
//
//class Aktor{
//    public String nazwisko;
//    public String imie;
//    public String plec;
//    public Date data_urodzenia;
//
//    public Aktor(String nazwisko, String imie, String plec, Date data_urodzenia){
//        this.nazwisko = nazwisko;
//        this.imie = imie;
//        this.plec = plec;
//        this.data_urodzenia = data_urodzenia;
//    }
//}
//
//class Gatunek{
//    public String nazwa;
//
//    public Gatunek(String nazwa){
//        this.nazwa = nazwa;
//    }
//}
//class Rola{
//
//}


//
public class CreateXML {
    public static void main(String[] args) {
        try {
            // Wczytanie dokumentu XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("../lab3/lab3/web/WEB-INF/zad1.xml");

            // Pobranie głównego elementu filmoteki
            Element filmotekaElement = document.getDocumentElement();

            // Tworzenie obiektu filmoteki
            Filmoteka filmoteka = new Filmoteka();

            // Przetwarzanie elementu aktorzy
            Element aktorzyElement = (Element) filmotekaElement.getElementsByTagName("aktorzy").item(0);
            NodeList aktorzyNodes = aktorzyElement.getElementsByTagName("aktor");

            for (int i = 0; i < aktorzyNodes.getLength(); i++) {
                Element aktorElement = (Element) aktorzyNodes.item(i);
                String nazwisko = aktorElement.getElementsByTagName("nazwisko").item(0).getTextContent();
                String imie = aktorElement.getElementsByTagName("imie").item(0).getTextContent();
                String plec = aktorElement.getElementsByTagName("plec").item(0).getTextContent();
                LocalDate dataUrodzenia = LocalDate.parse(aktorElement.getElementsByTagName("data_urodzenia").item(0).getTextContent());

                Aktor aktor = new Aktor(nazwisko, imie, plec, dataUrodzenia);
                filmoteka.getAktorzy().add(aktor);
            }

            // Przetwarzanie elementu filmy
            Element filmyElement = (Element) filmotekaElement.getElementsByTagName("filmy").item(0);
            NodeList filmyNodes = filmyElement.getElementsByTagName("film");

            for (int i = 0; i < filmyNodes.getLength(); i++) {
                Element filmElement = (Element) filmyNodes.item(i);
                String nazwa = filmElement.getElementsByTagName("nazwa").item(0).getTextContent();
                int rokPremiery = Integer.parseInt(filmElement.getElementsByTagName("rok_premiery").item(0).getTextContent());

                Film film = new Film(nazwa, rokPremiery);

                // Przetwarzanie elementu gatunki
                Element gatunkiElement = (Element) filmElement.getElementsByTagName("gatunki").item(0);
                NodeList gatunkiNodes = gatunkiElement.getElementsByTagName("gatunek");
                List<String> gatunki = new ArrayList<>();

                for (int j = 0; j < gatunkiNodes.getLength(); j++) {
                    Element gatunekElement = (Element) gatunkiNodes.item(j);
                    String gatunek = gatunekElement.getTextContent();
                    gatunki.add(gatunek);
                }

                film.setGatunki(gatunki);

                // Przetwarzanie elementu obsada
                Element obsadaElement = (Element) filmElement.getElementsByTagName("obsada").item(0);
                NodeList rolaNodes = obsadaElement.getElementsByTagName("rola");
                List<Aktor> obsada = new ArrayList<>();
                for (int j = 0; j < rolaNodes.getLength(); j++) {
                    Element rolaElement = (Element) rolaNodes.item(j);
                    int idAktora = Integer.parseInt(rolaElement.getAttribute("idAktora"));
                    String rola = rolaElement.getTextContent();
                    Aktor aktor = getAktorById(filmoteka.getAktorzy(), idAktora);
                    if (aktor != null) {
                        obsada.add(aktor);
                    }
                }

                film.setObsada(obsada);

                // Dodanie filmu do filmoteki
                filmoteka.getFilmy().add(film);
            }

            // Wyświetlanie zawartości obiektów
            System.out.println("Aktorzy:");
            for (Aktor aktor : filmoteka.getAktorzy()) {
                System.out.println("Nazwisko: " + aktor.getNazwisko());
                System.out.println("Imię: " + aktor.getImie());
                System.out.println("Płeć: " + aktor.getPlec());
                System.out.println("Data urodzenia: " + aktor.getDataUrodzenia());
                System.out.println();
            }

            System.out.println("Filmy:");
            for (Film film : filmoteka.getFilmy()) {
                System.out.println("Nazwa: " + film.getNazwa());
                System.out.println("Rok premiery: " + film.getRokPremiery());
                System.out.println("Gatunki: " + film.getGatunki());
                System.out.println("Obsada:");
                for (Aktor aktor : film.getObsada()) {
                    System.out.println("- " + aktor.getImie() + " " + aktor.getNazwisko());
                }
                System.out.println();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda pomocnicza do pobierania aktora po identyfikatorze
    public static Aktor getAktorById(List<Aktor> aktorzy, int id) {
        for (Aktor aktor : aktorzy) {
            // Zakładam, że identyfikator aktora jest unikalny
            if (aktorzy.toArray().length < id) {
                return aktorzy.get(id);
            }
        }
        return null;
    }
}
