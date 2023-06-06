import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Tworzenie obiektów filmoteki, aktorów i filmów
        Filmoteka filmoteka = new Filmoteka();

        // Dodawanie aktorów i filmów do filmoteki
        // ...

        try {
            // Tworzenie dokumentu XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Tworzenie głównego elementu filmoteka
            Element filmotekaElement = document.createElement("filmoteka");
            document.appendChild(filmotekaElement);

            // Tworzenie elementu aktorzy
            Element aktorzyElement = document.createElement("aktorzy");
            filmotekaElement.appendChild(aktorzyElement);

            // Dodawanie elementów aktorów
            for (Aktor aktor : filmoteka.getAktorzy()) {
                Element aktorElement = document.createElement("aktor");
                aktorzyElement.appendChild(aktorElement);

                // Dodawanie elementów wewnątrz aktora
                Element nazwiskoElement = document.createElement("nazwisko");
                nazwiskoElement.appendChild(document.createTextNode(aktor.getNazwisko()));
                aktorElement.appendChild(nazwiskoElement);

                Element imieElement = document.createElement("imie");
                imieElement.appendChild(document.createTextNode(aktor.getImie()));
                aktorElement.appendChild(imieElement);

                Element plecElement = document.createElement("plec");
                plecElement.appendChild(document.createTextNode(aktor.getPlec()));
                aktorElement.appendChild(plecElement);

                Element dataUrodzeniaElement = document.createElement("data_urodzenia");
                dataUrodzeniaElement.appendChild(document.createTextNode(aktor.getDataUrodzenia().toString()));
                aktorElement.appendChild(dataUrodzeniaElement);

                // Dodawanie elementu wiek
                Element wiekElement = document.createElement("wiek");
                LocalDate currentDate = LocalDate.now();
                int age = Period.between(aktor.getDataUrodzenia(), currentDate).getYears();
                wiekElement.appendChild(document.createTextNode(String.valueOf(age)));
                aktorElement.appendChild(wiekElement);
            }

            // Tworzenie elementu filmy
            Element filmyElement = document.createElement("filmy");
            filmotekaElement.appendChild(filmyElement);

            // Dodawanie elementów filmów
            for (Film film : filmoteka.getFilmy()) {
                Element filmElement = document.createElement("film");
                filmyElement.appendChild(filmElement);

                // Dodawanie elementów wewnątrz filmu
                Element nazwaElement = document.createElement("nazwa");
                nazwaElement.appendChild(document.createTextNode(film.getNazwa()));
                filmElement.appendChild(nazwaElement);

                Element rokPremieryElement = document.createElement("rokPremiery");
                rokPremieryElement.appendChild(document.createTextNode(String.valueOf(film.getRokPremiery())));
                filmElement.appendChild(rokPremieryElement);
                // Dodawanie elementu gatunki
                Element gatunkiElement = document.createElement("gatunki");
                filmElement.appendChild(gatunkiElement);

                // Dodawanie elementów gatunków
                for (String gatunek : film.getGatunki()) {
                    Element gatunekElement = document.createElement("gatunek");
                    gatunekElement.appendChild(document.createTextNode(gatunek));
                    gatunkiElement.appendChild(gatunekElement);
                }

                // Dodawanie elementu obsada
                Element obsadaElement = document.createElement("obsada");
                filmElement.appendChild(obsadaElement);

                // Dodawanie elementów roli
                for (Aktor aktor : film.getObsada()) {
                    Element rolaElement = document.createElement("rola");
                    List<Aktor> obsada = film.getObsada();
                    rolaElement.setAttribute("idAktora", String.valueOf(aktor.getId_aktora()));
                    rolaElement.appendChild(document.createTextNode(String.valueOf(aktor.getId_aktora())));
                    obsadaElement.appendChild(rolaElement);
                }
            }

            // Zapisywanie dokumentu XML do pliku
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("../lab3/lab3/web/WEB-INF/filmoteka_output.xml"));

            transformer.transform(source, result);

            System.out.println("Dane zostały zapisane do pliku XML.");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

