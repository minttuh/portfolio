// Lisätään pakkaukseen
package harjoitustyo.kokoelma;
// Otetaan käyttön pakkaukset
import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.dokumentit.Vitsi;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.omalista.OmaLista;
import harjoitustyo.apulaiset.Kokoava;
// Otetaan käyttöön javan pakkauksia
import java.util.LinkedList;
import java.time.*;
import java.util.Scanner;
import java.io.*;

/**
 * Mallintaa kokoelmaa. Attribuutteina tiedoston ja sulkusanalistan nimet sekä OmaLista tyyppinen
 * dokumentit. Luokka sisältää funktioita kokoelman käsittelyyn.
 *
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 *
 * @author Minttu Halme (minttu.halme@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */
public class Kokoelma implements Kokoava<Dokumentti>{
   /**
    * Attribuutit
    *
    * dokumentit: säilöö dokumenit OmaLista tyyppiseen tietorakenteeseen, jonka tyyppiparametri 
    * kiinnitetty dokumentiksi
    * kokoelmaTiedostossa: tiedoston nimi, jossa on kokoelma
    * sulkusanalistaTiedostossa: tiedoston nimi, jossa on sulkusanalista
    *
    */
   private OmaLista<Dokumentti> dokumentit; 

   private String kokoelmaTiedostossa;
   
   private String sulkusanalistaTiedostossa;
   
   /**
    * Oletusrakentaja, alustaa dokumentit
    *
    */
   public Kokoelma() {
      dokumentit = new OmaLista<Dokumentti>();
   }
   
   /**
    * Parametrillinen rakentaja
    * Asettaa kokoelmaTiedostossa ja sulkusanalistaTiedostossa ja 
    * alustaa dokumentit
    * @param tiedostonNimi on tiedoston nimi, jossa kokoelma on
    * @param sulkusanat on tiedoston nimi, jossa sulkusanat on
    */
   public Kokoelma(String tiedostonNimi, String sulkusanat) {
      kokoelmaTiedostossa = tiedostonNimi;
      sulkusanalistaTiedostossa = sulkusanat;
      dokumentit = new OmaLista<Dokumentti>();
   }
   
   /**
    * Lukeva aksessori
    *
    */
   public OmaLista<Dokumentti> dokumentit() {
      return dokumentit;
   }
   
   
   /**
    * Metodi säilöö tekstitiedoston kokoelmaan.
    * @param tyyppi kertoo onko vitsi vai uutinen
    * @throws IOException, jos säilöminen ei onnistu
    * 
    */
   public void säilöKokoelmaan(String tyyppi) throws IOException {
      // Esitellään ulkona try lohkosta
      Scanner tiedostonLukija = null;
      try { 
         // Luodaan tiedostoon liittyvä olio
         File tiedosto1 = new File(kokoelmaTiedostossa);
         
         // Liitetään lukija tiedostoon.
         tiedostonLukija = new Scanner(tiedosto1);
         
         while (tiedostonLukija.hasNextLine()) {
            // Luetaan seuraava rivi ja asetetaan
            String teksti = tiedostonLukija.nextLine();
            
            // Kutsutaan oliota luovaa metodia
            Dokumentti uusi = luoAlkio(teksti, tyyppi);
            
            // Lisätään kokoelma olioon alkio, joka on joko uutinen tai vitsi
            try {
               lisää(uusi);
            }
            catch(IllegalArgumentException e) {
            }
         }
         
         // Suljetaan tiedoston lukija.
         tiedostonLukija.close();
      }
      catch(IOException e) {
         // Jos tapahtuu virhe
         throw new IOException();
      }
   }
   
   /**
    * Säilöö sulkusanalistan tiedoston tiedot linkitetylle listalle
    * @throws IOException, jos säilöminen ei onnistu
    * @return sulkusanat linkitetyllä listalla
    *
    */
   public LinkedList<String> säilöSulkusanalista() throws IOException {
      // Esitellään ulkona try lohkosta
      Scanner tiedostonLukija2 = null;
      try {         
         // Luodaan tiedostoon liittyvä olio
         File tiedosto2 = new File(sulkusanalistaTiedostossa);
         // Liitetään lukija tiedostoon.
         tiedostonLukija2 = new Scanner(tiedosto2);
         
         // Luodaan 
         LinkedList<String> sulkusanat = new LinkedList<String>();
           
         // Käydään silmukassa läpi kaikki tiedoston rivit   
         while (tiedostonLukija2.hasNextLine()) {
            // Luetaan rivi ja asetetaan
            String teksti = tiedostonLukija2.nextLine();
            sulkusanat.add(teksti);
         }
         // Suljetaan tiedoston lukija.
         tiedostonLukija2.close();
         // Palautetaan sulkusanat
         return sulkusanat;
      }
      catch(IOException e) {
         // Jos tapahtuu virhe

         throw new IOException();
      }
   }
   
   /**
    * Hakee dokumentit, joissa esiintyy haettavat sanat vähintään kerran
    * @param komennonOsat on taulukko, jossa on käyttäjän antama komento eli sanat joilla haetaan, 
    * josta muodostetaan täällä linkitetty lista
    * @return dokumentin tunnisteet, joista hakusanat löytyvät
    *
    */
   public String haeDokumentitSanoilla(String[] komennonOsat) {
      // Luodaan linkitetty lista
      LinkedList<String> hakusanat = new LinkedList<String>();
      
      // Lisätään hakusanat linkitetylle listalle
      for (int i = 1; i < komennonOsat.length; i++) {
         hakusanat.add(komennonOsat[i]);
      }
      
      // Alustetaan
      String tunnisteet = "";
      try {
         // Käydään dokumentit yksitellen läpi
         for (int i = 0; i < dokumentit.size(); i++) {
            // Otetaan dokumentin viite ja kutsutaan funktiota
            boolean kaikkiSanatLöytyy = dokumentit.get(i).sanatTäsmäävät(hakusanat);
            
            // Jos paluuarvo on true
            if (kaikkiSanatLöytyy) {
               // Otetaan tunniste
               int löydetty = dokumentit.get(i).tunniste();
               // Lisätään apumuuttujaan dokumentin tunniste muutettuna merkkijonoksi ja rivinvaihto
               tunnisteet = tunnisteet + String.valueOf(löydetty) + System.lineSeparator();
            }
         }
         
         // Palautetaan tunnisteet
         return tunnisteet;
      }
      // Jos tapahtuu virhe 
      catch(IllegalArgumentException e) {
         return null;
      }
   }
   
   /**
    * Hakee kaikki dokumentit ja muuttaa ne merkkijonoiksi
    * @return olioiden tiedot merkkijonona
    *
    */
   public String haeKaikki() {
      // Alustetaan
      String kaikkiDokumentit = "";
      
      // Käydään kaikki läpi
      for (int i = 0; i < dokumentit.size(); i++) {
         // Haetaan
         Dokumentti haettu = dokumentit.get(i);
         
         if (haettu != null) {
            // Muutetaan merkkijonoksi haettu
            String lisättävä = haettu.toString();
            // Lisätään merkkijonoon
            kaikkiDokumentit = kaikkiDokumentit + lisättävä + System.lineSeparator();
         }
         
      }
      // Palautetaan
      return kaikkiDokumentit;
   }
   
   /**
    * Hakee yhden dokumentin ja muuttaa sen merkkijonoksi
    * @param nro dokumentin tunniste joka haetaan
    * @return merkkijonoesitys dokumentista
    *
    */
   public String haeYksi(int nro) {
      // Haetaan dokumentti
      Dokumentti haettuAlkio = hae(nro);
      
      if (haettuAlkio != null) {
         // Palautetaan
         return haettuAlkio.toString();
      }
      else {
         return null;
      }
   }
   
   
   /**
    * Muuttaa lisättävän alkion oikeaan muotoon
    * @param dokumenttiMerkkijonona merkkijonona ///:lla erotettuna olion tiedot 
    * muotoa tunniste///uutinen:pvm ja vitsi: laji ///teksti
    * @param tyyppi onko vitsi vai uutinen
    * @return palautetaan luotu olio
    *
    */
   public Dokumentti luoAlkio(String dokumenttiMerkkijonona, String tyyppi) {
      // Splitataan taulukkoon
      String[] dokumenttiTaulukossa = dokumenttiMerkkijonona.split("///");
      
      // Muutetaan stringistä kokonaisluvuksi
      int tunniste = Integer.parseInt(dokumenttiTaulukossa[0]);
      
      // Alustetaan
      Dokumentti uusi = null;
      
      // Jos on uutinen
      if (tyyppi.equals("jokes")) {
         uusi = new Vitsi(tunniste, dokumenttiTaulukossa[1], dokumenttiTaulukossa[2]);
      }
      if (tyyppi.equals("news")) {
         // Splitataan taulukkoon, jotta voidaan muodostaa LocalDate
         String[] päivämääränOsat = dokumenttiTaulukossa[1].split("\\.");
         // Muutetaan stringistä intiksi
         int vuosi = Integer.parseInt(päivämääränOsat[2]);
         int kuukausi = Integer.parseInt(päivämääränOsat[1]);
         int päivä = Integer.parseInt(päivämääränOsat[0]);
         // Muodostetaan LocalDate
         LocalDate pvm = LocalDate.of(vuosi, kuukausi, päivä);
         
         // Luodaan olio
         uusi = new Uutinen(tunniste, pvm, dokumenttiTaulukossa[2]);
      }
      // Palautetaan
      return uusi;
   }
   
   /**
    * Metodi poistaa alkion kokoelmasta
    * @param tunniste tällä tunnistella oleva alkio poistetaan
    *
    */
   public boolean poistaAlkio(int tunniste) {
      // Haetaan olio
      Dokumentti poistettava = hae(tunniste);
      
      // Jos löydetään alkio
      if (poistettava != null) {
         // Poistetaan
         dokumentit.remove(poistettava);
         return true;
      }
      // Jos alkiota ei löydetä niin palautetaan merkiksi
      else {
         return false;
      }
   }
   
   /**
    * Metodi esikäsittelee tiedoston.
    * @param sulkusanat poistettavat sanat linkitetyssä listassa
    * @param välimerkit poistettava välimerkit merkkijonona
    *
    */
   public void esikäsitteleTiedosto (LinkedList<String> sulkusanat, String välimerkit) {
      try {
         for (int i = 0; i < dokumentit.size() ; i++) {
            // Haetaan dokumentin yksi alkio 
            Dokumentti esikäsiteltäväAlkio = dokumentit.get(i);
            // Esikäsitellään haettu alkio
            esikäsiteltäväAlkio.siivoa(sulkusanat, välimerkit);
         }
      }
      // Ei tehdä mitään jos tapahtuu virhe
      catch (IllegalArgumentException e) {
      }
   }
   
   /**
    * Metodi lataa tiedoston uudelleen
    * @param tyyppi kertoo onko vitsi vai uutinen
    *
    */
   public void lataaUudelleen(String tyyppi) {
      // Tehdään try catchissa
      try {
         // Poistetaan kaikki alkiot listalta
         for (int i = 0; i < dokumentit.size(); i = i) {
            dokumentit.remove(i);
         }
         säilöKokoelmaan(tyyppi);
      }
      catch(IOException e) {
      }
   }
   
   /**
    * Tarkistaa voiko tekstistä luoda uutisen
    * @param teksti käyttäjän antama teksti
    * @return totuus voiko vai ei
    */
   public boolean voikoLuodaUutisen(String teksti) {
      // Splitataan taulukkoon
      String[] osat = teksti.split("///");
      try {
         // Splitataan taulukkoon, jotta voidaan muodostaa LocalDate
         String[] päivämääränOsat = osat[1].split("\\.");
         // Muutetaan stringistä intiksi
         int vuosi = Integer.parseInt(päivämääränOsat[2]);
         int kuukausi = Integer.parseInt(päivämääränOsat[1]);
         int päivä = Integer.parseInt(päivämääränOsat[0]);
         // Muodostetaan LocalDate
         LocalDate pvm = LocalDate.of(vuosi, kuukausi, päivä);
         
         // Palautetaan true jos onnistuu
         return true;
      }
      catch (Exception e) {
         // Palautetaan false, jos ei onnistu
         return false;
      }
   }
   /**
    * Lisää kokoelmaan parametrina annetun 
    * @param lisättäväTeksti teksti muodossa lisättävä olio
    * @return totuus voiko lisätä vai ei
    */
   public boolean lisääKokoelmaan(String lisättäväTeksti, String tyyppi) {
      try {
         // Kutsutaan metodia, joka tarkistaa voiko luoda uutisen 
         boolean onnistuiko = voikoLuodaUutisen(lisättäväTeksti);
         
         // Jos tyyppi on vitsi ja pvm luonti ei onnistu niin lisätään tai jos tyyppi
         // on uutinen ja pvm luonti onnistui
         if (tyyppi.equals("jokes") && !onnistuiko || tyyppi.equals("news") && 
         onnistuiko) {
            // Kutsutaan olion luovaa metodia, parametrina välitetään olion tyyppi
            Dokumentti lisättävä = luoAlkio(lisättäväTeksti, tyyppi);
            // Kutsutaan lisäävää metodia
            lisää(lisättävä);
            // Palautetaan true onnistumisen merkiksi
            return true;
         }
         // Jos tyyppi on vitsi ja pvm onnistuu tai jos pvm luonti ei onnistu 
         // ja tyyppi on uutinen niin palautetaan false
         else if (tyyppi.equals("jokes") && onnistuiko || tyyppi.equals("news") && !onnistuiko) {
            return false;
         }
         else {
            // Muulloin palautetaan 
            return false;
         }
      }
      // Jos on jo kokoelmassa
      catch(IllegalArgumentException e) {
         return false;
      }
   }
   
   
   /**
    * Rajapinnan metodien toteutus
    *
    */
   /**
    * Lisää kokoelmaan annetun dokumentin.
    * <p>
    * Metodi kutsuu oman listan lisää-metodia kokoelman dokumentit-attribuutin
    * kautta, jotta uusi dokumentti saadaan lisätyksi oikeaan paikkaan listalla.
    * <p>
    * Lisäys onnistuu, jos parametri liittyy dokumenttiin, jota voidaan vertailla 
    * Comparable-rajapinnan compareTo-metodilla ja jos kokoelmassa ei ole vielä
    * Dokumentti-luokan equals-metodilla mitaten samanlaista dokumenttia.
    * Null-arvon lisäys epäonnistuu aina.
    *
    * @param uusi viite lisättävään dokumenttiin.
    * @throws IllegalArgumentException jos dokumentin vertailu Comparable-rajapintaa
    * käyttäen ei ole mahdollista, listalla on jo equals-metodin mukaan sama
    * dokumentti eli dokumentti, jonka tunniste on sama kuin uuden dokumentin
    * tai parametri on null.
    * @see Ooperoiva
    */
   public void lisää(Dokumentti uusi) throws IllegalArgumentException {
      // Jos parametri ei ole null arvoinen
      if (uusi != null) {
         try {
            // Käydään silmukassa läpi kaikki dokumentin alkiot
            for (int i = 0; i < dokumentit.size(); i++) {
               if (dokumentit.get(i).equals(uusi)) {
                  // Jos löydetään alkio, joka on sama kuin parametrina saatu niin 
                  // heitetään poikkeus
                  throw new IllegalArgumentException();
               }
            }
            // Kutsutaan oman listan lisää-metodia kokoelman dokumentit-attribuutin kautta
               dokumentit.lisää(uusi); 
         }
         catch (Exception e) {
            // Heitetään poikkeus
            throw new IllegalArgumentException();
         }
      }
      // Jos parametri on null arvoinen
      else {
         // Heitetään poikkeus
         throw new IllegalArgumentException();
      }
   }

   /**
    * Hakee kokoelmasta dokumenttia, jonka tunniste on sama kuin parametrin
    * arvo.
    *
    * @param tunniste haettavan dokumentin tunniste.
    * @return viite löydettyyn dokumenttiin. Paluuarvo on null, jos haettavaa
    * dokumenttia ei löydetty.
    *
    */
   public Dokumentti hae(int tunniste) {
      // Käydään kaikki dokumentin alkiot silmukassa läpi
      for (int i = 0; i < dokumentit.size(); i++) {
         // Otetaan tutkittavan olion tunniste
         int vertailtavaTunniste = dokumentit.get(i).tunniste();
         
         // Jos haettava tunniste löydetään
         if (tunniste == vertailtavaTunniste) {
            // Palautetaan viite olioon
            return dokumentit.get(i);
         }  
      }
      // Jos ei löytynyt vastaavuutta mihinkään
      return null;
   }
}