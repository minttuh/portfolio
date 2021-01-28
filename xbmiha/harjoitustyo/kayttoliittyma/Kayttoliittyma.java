// Liitetään pakkaukseen 
package harjoitustyo.kayttoliittyma; 
// Otetaan käyttöön
import harjoitustyo.kokoelma.Kokoelma;
import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.dokumentit.Vitsi;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.omalista.OmaLista;
import java.util.Scanner;
import java.util.LinkedList;
import java.time.*;
import java.io.*;
/**
 * Ohjelman käyttöliittymä, kysytään tiedot käyttäjältä ja toimitaan niiden
 * mukaisesti. Tässä luokassa kutsutaan funktioita ja tulostetaan paluuarvoja.
 *
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 *
 * @author Minttu Halme (minttu.halme@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */


public class Kayttoliittyma {
   // Vakiot
   public static final String TULOSTA = "print";
   public static final String LISÄÄ = "add";
   public static final String HAE = "find";
   public static final String POISTA = "remove";
   public static final String ESIKÄSITTELE = "polish";
   public static final String LATAA = "reset";
   public static final String KAIUTUS = "echo";
   public static final String LOPETA = "quit";
   
   /**
    * Attribuutit
    * kokoelmaTiedostossa: sisältää komentoriviparametrina saadun tiedostonimen, jossa
    * on dokumentit
    * sulkusanalistaTiedostossa: sisältää komentoriviparametrina saadun tiedostonimen,
    * jossa on sulkusanat
    *
    */
   private String kokoelmaTiedostossa;
   private String sulkusanalistaTiedostossa;
   
   /**
    * Parametrillinen rakentaja
    * asettaa attribuuteille tiedot
    *
    */
   public Kayttoliittyma(String tiedostonNimi, String sulkusanat) {
      kokoelmaTiedostossa = tiedostonNimi;
      sulkusanalistaTiedostossa = sulkusanat;
   }
   
   /**
    * Ohjelman pääsilmukka, Sisältää if-else-valintarakenteen komennoille. 
    * @throws IOException jos jommankumman tiedoston lataaminen ei onnistu
    */
   public void pääsilmukka() throws IOException {
      // Luodaan kokoelma olio
      Kokoelma uusiKokoelma = new Kokoelma(kokoelmaTiedostossa, sulkusanalistaTiedostossa);
      
      // Pilkotaan taulukkoon kokoelmatiedoston nimi
      String[] kokoelmanNimi = kokoelmaTiedostossa.split("_");
      String tyyppi = kokoelmanNimi[0];
      
      // Alustetaan
      LinkedList<String> sulkusanatLinkitetyllälistalla = null;
      
      try {
         // Kutsutaan lataavia metodeja, parametrina välitetään olion tyyppi (news/jokes)
         uusiKokoelma.säilöKokoelmaan(tyyppi);
         // Paluuarvona sulkusanat sisältävä linkitetty lista
         sulkusanatLinkitetyllälistalla = uusiKokoelma.säilöSulkusanalista();
      }
      catch(IOException e) {
         // Jos tapahtuu virhe niin heitetään se 
         throw new IOException();
      }
      
      // Esitellään viite, luodaan syötevirtaa lukeva olio ja liitetään viite olioon
      Scanner lukija = new Scanner(System.in);
         
      // Alustetaan
      boolean jatketaan = true;
      // Alustetaan echo 
      boolean echoPäällä = false;
         
      while (jatketaan) {
         // Kysytään komentoa käyttäjältä 
         System.out.println("Please, enter a command:");
         String komento = lukija.nextLine();
         
         // Pilkotaan komento taulukkoon
         String[] komennonOsat = komento.split(" ");
         
        
         // Jos echo:n arvo on true niin tulostetaan komento
         if (echoPäällä) {
            System.out.println(komento);
         }
         
         // Jos komento on find
         if (komennonOsat[0].equals(HAE)) {
            // Kutsutaan funktiota
            String haetutTunnisteet = uusiKokoelma.haeDokumentitSanoilla(komennonOsat);
             
            // Tulostetaan
            if (haetutTunnisteet != null) {
               System.out.print(haetutTunnisteet);
            }
            else {
               System.out.println("Error!");
            }
         }
         // Jos komento on add (oletetaan että parametri aina kunnossa)
         else if (komennonOsat[0].equals(LISÄÄ)) {
            // Yritetään
            try {
               String[] osiot = komento.split(" ", 2);
               String lisättäväTeksti = osiot[1];
               
               // Kutsutaan metodia 
               boolean onnistuiko = uusiKokoelma.lisääKokoelmaan(lisättäväTeksti, tyyppi);
               if (!onnistuiko) {
                  // Jos paluarvo on false niin tulostetaan virheilmoitus
                  System.out.println("Error!");
               }
            }
            // Jos ei onnistu
            catch(Exception e) {
               System.out.println("Error!");
            }
         }
      
         else if (komennonOsat.length == 1) {
            // Apumuuttuja
            String teeTämä = komennonOsat[0];
            
            if (teeTämä.equals(KAIUTUS)) {
               // Jos arvo on true niin käännetään se falseksi
               if (echoPäällä) {
                  echoPäällä = false;
               }
               // Jos arvo on false niin käännetään se trueksi
               else if (!echoPäällä) {
                  // Tulostetaan vielä
                  System.out.println(teeTämä);
                  echoPäällä = true;
               }
            }
            
            // Jos komento on quit niin lopetetaan ohjelma
            else if (teeTämä.equals(LOPETA)) {
               jatketaan = false;
            }
            // Jos komento on tulosta ilman parametreja
            else if (teeTämä.equals(TULOSTA)) {
               // Kutsutaan metodia ja tulostetaan sen paluuarvo
               System.out.print(uusiKokoelma.haeKaikki());
            }
            // Jos komento on reset
            else if (teeTämä.equals(LATAA)) {
               // Kutsutaan funktiota
               uusiKokoelma.lataaUudelleen(tyyppi);
            }
            // Muutoin
            else {
               System.out.println("Error!");
            }
         }
         
         else if (komennonOsat.length == 2) {
            // Apumuuttujat
            String teeTämä = komennonOsat[0];
            String parametri = komennonOsat[1];
            
            // Jos komento on print ja parametri on int tyyppiä
            if (teeTämä.equals(TULOSTA)) {
               try {
                  // Muutetaan intiksi parametri
                  int tunniste = Integer.parseInt(parametri);
                  
                  // Kutsutaan funktiota
                  String haettu = uusiKokoelma.haeYksi(tunniste);
                  
                  if (haettu != null) {
                     // Tulostetaan
                     System.out.println(haettu);
                  }
                  else {
                     System.out.println("Error!");
                  }
               }
               catch (Exception e) {
                  System.out.println("Error!");
               }
            }
            // Jos komento on remove ja parametri on int tyyppinen
            else if (teeTämä.equals(POISTA)) {
               try {
                  int tunniste = Integer.parseInt(parametri);
                  // Kutsutaan poistavaa metodia
                  boolean onnistuiko = uusiKokoelma.poistaAlkio(tunniste);
                  
                  // Jos haettavalla tunnisteella ei oo alkioo
                  if (!onnistuiko) {
                     System.out.println("Error!");
                  }
               }
               catch (Exception e) {
                  System.out.println("Error!");
               }
            }
            // Jos komento on polish
            else if (teeTämä.equals(ESIKÄSITTELE)) {
               // Kutsutaan metodia, joka hoitaa esikäsittelyn
               uusiKokoelma.esikäsitteleTiedosto(sulkusanatLinkitetyllälistalla, parametri);
            }
            // Muutoin
            else {
               System.out.println("Error!");
            }
         }
         // Muutoin
         else {
            System.out.println("Error!");
         }    
      }
   }  
}