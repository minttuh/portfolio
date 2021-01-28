// Otetaan käyttöön 
import harjoitustyo.kayttoliittyma.Kayttoliittyma;
import harjoitustyo.omalista.OmaLista;
import harjoitustyo.apulaiset.Tietoinen;
import harjoitustyo.apulaiset.Ooperoiva;
import harjoitustyo.apulaiset.Kokoava;
import harjoitustyo.dokumentit.Vitsi;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.kokoelma.Kokoelma;
import java.io.*;
import java.time.*;
/**
 * Ohjelman ajoluokka, sisältää pääsilmukan
 *
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 * 
 * @author Minttu Halme (minttu.halme@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta, Tampereen yliopisto.
 *
 */


public class Oope2HT {
   public static void main(String[] args) {
      // Tervehditään käyttäjää
      System.out.println("Welcome to L.O.T.");
      
      // Jos komentoriviparametreja on väärä määrä niin tulostetaan virheilmoitus
      if (args.length != 2) {
         System.out.println("Wrong number of command-line arguments!");
      }
      
      else {
         // Luodaan olio
         Kayttoliittyma uusi = new Kayttoliittyma(args[0], args[1]);
         
         try {
            // Kutsutaan pääsilmukkaa
            uusi.pääsilmukka();
         }
         catch(IOException e) {
            // Tulostetaan virheilmoitus
            System.out.println("Missing file!");
         }
      }
      // Lopputervehdykset
      System.out.println("Program terminated.");
   }
}