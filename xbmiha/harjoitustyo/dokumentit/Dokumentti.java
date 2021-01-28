// Liitetään pakkaukseen
package harjoitustyo.dokumentit;
// Otetaan käyttöön
import harjoitustyo.apulaiset.Tietoinen;
import java.util.LinkedList;

/**
 * Luokka mallintaa Dokumenttia. Luokka toteuttaa Tietoinen sekä Comparable -rajapinnat.
 *
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 *
 * @author Minttu Halme (minttu.halme@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */
public abstract class Dokumentti implements Comparable<Dokumentti>, Tietoinen<Dokumentti>{
   /**
    * Attribuutit
    *
    * tunniste: dokumentin yksilöivä tunnistu
    * teksti: olion teksti
    */
   private int tunniste;
   
   private String teksti;
   
   /**
    * Parametrillinen rakentaja
    * @param x on tunniste
    * @param merkkijono on teksti
    * @throws IllegalArgumentException, jos merkkijono on null arvoinen tai tyhjä
    *
    */
   public Dokumentti(int x, String merkkijono) throws IllegalArgumentException {
      // Tarkistetaan onko parametrit oikeanlaisia
      if (merkkijono != null && merkkijono.length() > 0 && x > 0) {
         tunniste = x;
         teksti = merkkijono;
      }
      // Heitetään poikkeus pois
      else {
         throw new IllegalArgumentException();
      }
   }
   
   
   /**
    * Aksessorit
    *
    */
   public int tunniste() {
      return tunniste;
   }
   /**
    * Asettaa tunnisteen
    * @param x tunniste
    * @throws IllegalArgumentException, jos parametri on <= 0
    */
   public void tunniste(int x) throws IllegalArgumentException {
      // Tarkistetaan onko parametri oikeanlainen
      if (x > 0) {
         tunniste = x;
      }
      // Heitetään poikkeus pois
      else {
         throw new IllegalArgumentException();
      }
   }
   
   public String teksti() {
      return teksti;
   }
   
   /**
    * Asettaa tekstin
    * @param merkkijono on dokumentin teksti
    * @throws IllegalArgumentException, jos parametri on null-arvoinen tai tyhjä
    *
    */
   public void teksti(String merkkijono) throws IllegalArgumentException {
      // Tarkistetaan onko parametri oikeanlainen
      if (merkkijono != null && merkkijono.length() > 0) {
         teksti = merkkijono;
      }
      // Heitetään poikkeus pois
      else {
         throw new IllegalArgumentException();
      }
   }
   
   /**
    * Rajapinnan metodien toteutus
    *
    */
   /**
    * Tutkii sisältääkö dokumentin teksti kaikki siitä haettavat sanat. Kunkin
    * listan sanan on vastattava yhtä tai useampaa dokumentin sanan esiintymää
    * täysin.
    *
    * @param hakusanat lista dokumentin tekstistä haettavia sanoja.
    * @return true jos kaikki listan sanat löytyvät dokumentista. Muuten palautetaan
    * false.
    * @throws IllegalArgumentException jos sanalista on tyhjä tai se on null-arvoinen.
    */
   public boolean sanatTäsmäävät(LinkedList<String> hakusanat) throws IllegalArgumentException {
      if (hakusanat != null && hakusanat.size() > 0) {
         
         // Splitataan teksti-attribuutin sisältö taulukkoon
         String[] sisältöTaulukossa = teksti.split(" ");
         
         // Alustetaan apumuuttuja
         int kpl = 0;

         // Käydään kaikki hakusanat-listan alkiot läpi
         for (int i = 0; i < hakusanat.size(); i++) {
            // Alkio, jota verrataan dokumenttiin
            String hakusana = hakusanat.get(i);
            
            
            // Verrataan hakusanaa kaikkiin tekstin sanoihin
            for (int j = 0; j < sisältöTaulukossa.length; j++) {
               // Jos hakusana täsmää tekstin sanan kanssa
               if (hakusana.equals(sisältöTaulukossa[j])) {
                  // Lisätään apumuuttujaan yksi
                  kpl++;
                  // Lopetetaan vertailu tällä hakusanalla, jotta kpl:n ei lisätä liikaa
                  break;
               }
            } 
         }
         // Jos apumuuttujan arvo on sama kuin parametrina saatujen hakusanojen lukumäärä
         if (kpl == hakusanat.size()) {
            // Palautetaan true
            return true;
         }
         else {
            // Muulloin palautetaan false
            return false;
         }
      }
      else {
         // Heitetään poikkeus
         throw new IllegalArgumentException();
      }
    }


   /**
    * Muokkaa dokumenttia siten, että tiedonhaku on helpompaa ja tehokkaampaa.
    * <p>
    * Metodi poistaa ensin dokumentin tekstistä kaikki annetut välimerkit ja
    * muuntaa sitten kaikki kirjainmerkit pieniksi ja poistaa lopuksi kaikki
    * sulkusanojen esiintymät.
    *
    * @param sulkusanat lista dokumentin tekstistä poistettavia sanoja.
    * @param välimerkit dokumentin tekstistä poistettavat välimerkit merkkijonona.
    * @throws IllegalArgumentException jos jompikumpi parametri on tyhjä tai null-
    * arvoinen.
    */
   
   public void siivoa(LinkedList<String> sulkusanat, String välimerkit) throws IllegalArgumentException {
      // Jos jompokumpi parametreista on tyhjä tai null arvoinen niin heitetään poikkeus.
      if (sulkusanat == null || välimerkit == null || sulkusanat.size() == 0 || välimerkit.length() == 0 ) {
         throw new IllegalArgumentException();
      }
      else {
         // Tehdään taulukko välimerkeistä
         String[] välimerkitTaulukossa = välimerkit.split("");
         
         // Käydään silmukassa läpi välimerkit
         for (int i = 0; i < välimerkit.length(); i++) {
            // Avuksi
            String poistettava = välimerkitTaulukossa[i];
            
            teksti = teksti.replace(poistettava, "");
         }
         
         // Muutetaan kaikki alkiot pieniksi kirjaimiksi
         teksti = teksti.toLowerCase();

         // Splitataan teksti taulukkoon
         String[] apuTaulu = teksti.split(" ");
         // Käydään silmukassa läpi sulkusanat
         for (int j = 0; j < sulkusanat.size(); j ++) {
            // Avuksi
            String sulkusana = sulkusanat.get(j);
            
            // Käydään uudessa silmukassa
            for(int y = 0; y < apuTaulu.length; y++) {
               // Otetaan y. paikassa oleva tutkittava sana muuttujaan
               String tutkittavaSana = apuTaulu[y];
               
               // Jos tutkittava sana on sulkusana niin muutetaan se ""
               if (tutkittavaSana.equals(sulkusana)) {
                  apuTaulu[y] = "";
               }
            }
         }
         // Alustetaan 
         String trimmattu = "";
         
         
         for (int k = 0; k < apuTaulu.length; k++) {
            trimmattu = trimmattu + " " + apuTaulu[k];
         }
         // Poistetan turhat välit
         trimmattu = trimmattu.replaceAll("[ ]{2,}", " ");
         trimmattu = trimmattu.trim();
         
         // Asetetaan teksti
         teksti = trimmattu;
      }
   }
   
   
   
   /**
    * Korvattavat Object-luokan metodit  
    *
    */
   
   /**
    * Tekee oliosta merkkijonoesityksen
    * @return luotu merkkijonoesitys
    *
    */
   @Override
   public String toString() {
      // Palautetaan merkkijonoesitys
      return tunniste + "///" + teksti;
   }
   /**
    * Vertailee kahta oliota
    * @param obj vertailtava olio
    * @return true tai false sen mukaan, onko oliot samat equalsin mukaan
    *
    */
   @Override
   public boolean equals(Object obj) {
      // Käsitellään try-catch lauseen sisällä, koska metodi ei saa heittää poikkeusta.
      try {
         // Asetetaan olioon Dokumentti-luokan viite, jotta voidaan kutsua
         // Dokumentti-luokan aksessoreita.
         Dokumentti toinen = (Dokumentti)obj;

         // Oliot ovat samat, jos tunnisteiden arvot ovat samat.
         return (tunniste == toinen.tunniste());
      }
      catch (Exception e) {
         return false;
      }
   }

   /**
    * Korvataan Comparable-rajapinnan compareTo metodi. Geneerinen tyyppi T
    * on kiinnitetty Dokumentti-tyypiksi. Palauttaa arvon nolla aina, kun equals
    * palauttaa true-arvon. Vertaillaan tunnisteita.
    * @param toinen on olio, jota vertaillaan
    * @return -1, 0 tai 1
    */
   @Override
   public int compareTo(Dokumentti toinen) {
      // Tämä olio < parametrina saatu olio.
      if (tunniste < toinen.tunniste()) {
         return -1;
      }
      // Tämä olio == parametrina saatu olio.
      else if (tunniste == toinen.tunniste()) {
         return 0;
      }
      // Tämä olio > parametrina saatu olio.
      else {
         return 1;
      }
   }
}