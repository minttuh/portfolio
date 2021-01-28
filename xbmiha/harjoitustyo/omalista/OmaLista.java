//Liitetään pakkaukseen
package harjoitustyo.omalista;
// Otetaan käyttöön 
import harjoitustyo.apulaiset.Ooperoiva;
import java.util.LinkedList;

/**
 * LinkedLististä peritty OmaLista-luokka, joka toteuttaa Ooperoiva-rajapinnan.
 * Listalla pystytään käsittelmään  minkä tahansa tyyppisiä alkioita.
 *
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 *
 * @author Minttu Halme (minttu.halme@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {
   /**
    * Lisää listalle uuden alkion sen suuruusjärjestyksen mukaiseen paikkaan.
    * Uusi alkio lisätään listalle siten, että alkio sijoittuu kaikkien itseään pienempien
    * tai yhtä suurien alkioiden jälkeen ja ennen kaikkia itseään suurempia alkioita.
    * Alkioiden suuruusjärjestys päätellään Comparable-rajapinnan compareTo-metodilla.
    *
    * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
    * toteuttaneen Comparable-rajapinnan.
    * @throws IllegalArgumentException jos lisäys epäonnistui, koska uutta alkiota
    * ei voitu vertailla. Vertailu epäonnistuu, kun parametri on null-arvoinen
    * tai siihen liittyvän olion luokka tai luokan esivanhempi ei vastoin oletuksia
    * toteuta Comparable-rajapintaa.
    */
   @SuppressWarnings({"unchecked"})
   public void lisää(E uusi) throws IllegalArgumentException {
      // Jos parametrina saatu arvo ei ole null-arvoinen
      if (uusi != null ) {
         try {
            // Asetetaan
            Comparable uusiAlkio = (Comparable) uusi;
            
            // Jos lista on tyhjä niin asetetaan uusi alkio listan ensimmäiseksi
            if (size() == 0) {
               addFirst(uusi);
            }
            else {
               // Alustetaan 
               boolean jatketaan = true;
               int i = 0;
               
               // Ollaan silmukassa niin kauan kun jatketaan == true ja i < oman listan koko
               while (jatketaan && i < size()) {
                  // Avuksi
                  int testi = uusiAlkio.compareTo(get(i));
                  
                  // Jos compareTo:n mukaan i. paikassa oleva alkio > parametrina saatu alkio niin
                  // lisätään parametrina saatu alkio i. paikkaan
                  if (testi < 0) {
                     add(i, uusi);
                     // Kun alkio on lisätty niin lopetetaan silmukka
                     jatketaan = false;
                  }
                  // Kasvatetaan indeksiä
                  i++;
               }
               // Jos indeksi == omalistan koko niin lisätään parametrina saatu alkio listan loppuun
               if(i == size()) {
                  addLast(uusi);
               }
            }
         }   
         // Jos havaitaan virhe
         catch(Exception e) {
            // Heitetään IllegalArgumentException
            throw new IllegalArgumentException();
         }
      }         
      else {
         throw new IllegalArgumentException();
      }
   }
}