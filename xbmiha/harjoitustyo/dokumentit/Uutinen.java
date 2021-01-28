// Liitetään pakkaukseen
package harjoitustyo.dokumentit;
// Otetaan käyttöön
import harjoitustyo.dokumentit.Dokumentti;
import java.time.*;

/**
 * Luokka mallintaa uutista. Luokka periytyy Dokumentista.
 *
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 *
 * @author Minttu Halme (minttu.halme@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */
public class Uutinen extends Dokumentti {
   /**
    * LocalDate tyyppinen päivämäärä attribuutti
    *
    */
   private LocalDate päivämäärä;
   
   /**
    * Parametrillinen rakentaja
    * @param uutisenTunniste uutisen yksilöivä tunniste
    * @param pvm päivämäärä, jolloin uutinen on julkaistu
    * @param merkkijono itsessään uutisen teksti
    * @throws IllegalArgumentException, jos päivämäärä on null-arvoinen
    *
    */
   public Uutinen (int uutisenTunniste, LocalDate pvm, String merkkijono) 
   throws IllegalArgumentException {
      // Kutsutaan yliluokan rakentajaa
      super(uutisenTunniste, merkkijono);
      
      // Tarkistetaan onko oikeanlainen
      if (pvm != null) {
         päivämäärä = pvm;
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
   public LocalDate päivämäärä() {
      return päivämäärä;
   }
   /**
    * Päivämäärän asettava aksessori
    * @throws IllegalArgumentException, jos parametri on null-arvoinen
    */
   public void päivämäärä(LocalDate pvm) throws IllegalArgumentException {
      // Tarkistetaan onko oikeainlainen
      if (pvm != null) {
         päivämäärä = pvm;
      }
      else {
         // Heitetään poikkeus
         throw new IllegalArgumentException();
      }
   }
   
   /**
    * Korvaa Object-luokan toString metodin, muodostaa oliosta merkkijonoesityksen
    * Kutsuu yliluokan toString metodia
    * @return olion merkkijonoesitys
    *
    */
   @Override
   public String toString() {
      // Haetaan yliluokan merkkijonoesitys ja katkaistaan se /// kohdalta
      // ja sijoitetaa tiedot taulukkoon
      String[] splitattu = super.toString().split("///");
      
      // Palautetaan merkkijonoesitys
      return splitattu[0] + "///" + päivämäärä.getDayOfMonth() + "." + päivämäärä.getMonthValue()
      + "." + päivämäärä.getYear() + "///" + splitattu[1];
   }
}