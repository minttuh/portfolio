// Liitetään pakkaukseen
package harjoitustyo.dokumentit;
// Otetaan käyttöön
import harjoitustyo.dokumentit.Dokumentti;

/**
 * Luokka mallintaa Vitsiä. Luokka periytyy Dokumentista.
 *
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 *
 * @author Minttu Halme (minttu.halme@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */




public class Vitsi extends Dokumentti {
   /**
    * Attribuutti, kertoo vitsin lajin
    *
    */
   private String laji;
   
   /**
    * Parametrillinen rakentaja
    * @param tunniste vitsin yksilöivä tunniste
    * @param vitsinLaji kertoo vitsin lajin 
    * @param merkkijono itsessään vitsi
    * @throws IllegalArgumentException jos vitsinLaji on null-arvoinen tai tyhjä merkkijono
    */
   public Vitsi(int vitsinTunniste, String vitsinLaji, String merkkijono) 
   throws IllegalArgumentException {
      // Kutsutaan yliluokan rakentajaa
      super(vitsinTunniste, merkkijono);
      
      // Tarkistetaan onko oikeanlainen
      if (vitsinLaji != null && vitsinLaji.length() > 0) {
         laji = vitsinLaji;
      }
      else {
         // Heitetään poikkeus
         throw new IllegalArgumentException();
      }
   }
   
   /**
    * Aksessorit
    *
    */
   public String laji() {
      return laji;
   }
   /**
    * Asettaa vitsin lajin
    * @param vitsinLaji kertoo minkätyyppinen vitsi kyseessä
    * @throws IllegalArgumentException, jos parametri on null-arvoinen tai tyhjä merkkijono
    *
    */
   public void laji(String vitsinLaji) throws IllegalArgumentException {
      // Tarkistetaan onko oikeanlainen
      if (vitsinLaji != null && vitsinLaji.length() > 0) {
         laji = vitsinLaji;
      }
      else {
         // Heitetään poikkeus
         throw new IllegalArgumentException();
      }
   }
   
   
   /**
    * Object-luokasta korvattu metodi. Muuttaa Olion tekstimuotoon
    * Kutsuu yliluokan toString metodia
    * @return Olion merkkijonoesitys
    *
    */
   @Override
   public String toString() {
      // Haetaan yliluokan merkkijonoesitys ja katkaistaan se /// kohdalta
      // ja sijoitetaa tiedot taulukkoon
      String[] splitattu = super.toString().split("///");
      
      // Palautetaan merkkijonoesitys
      return splitattu[0] + "///" + laji + "///" + splitattu[1];
   }
}