## Olio ohjelmoinnin perusteet II -harjoitustyöstä
## Minttu Halme, minttu.halme@tuni.fi



## Yleistä

Ohjelma on tehty dokumenttien käsittelyyn. 

Oope2HT on ohjelman ajoluokka, eli siellä luodaan käyttölittymä-olio ja tulostetaan virheilmoitukset sekä 
käyttäjän tervehdykset ja jäähyväiset. Käyttöliittymä on nimensä mukaisesti ohjelman käyttöliittymä. Siellä ensiksi ladataan komentoriviparametreina saadut tiedostot. 
Sen jälkeen kysytään käyttäjältä komentoja, joiden mukaan toimitaan. Käyttöliittymässä on vain funktioiden kutsut ja paluuarvojen tulostukset. 

Kokoelma mallintaa kokoelmaa, johon on säilötty olioita. Kokoelma toteuttaa Kokoava-rajapinnan. Kokoelmalla on attribuutit tiedostojen 
(sulkusanalista ja se mistä ladataan dokumenttien tiedot) nimille ja Omalista-tyyppinen linkitetty lista, jonka tyyppiparametri on kiinnitetty Dokumentiksi. 
Dokumentti on luokka, joka mallintaa dokumenttia. Se toteuttaa Tietoinen ja Comparable -rajapinnat. Sillä on attribuutit tunnisteelle ja tekstille. 
Vitsi on luokka, joka mallintaa vitsiä, sillä on attribuutti vitsin lajille. Vitsi periytyy Dokumentista. Uutinen on luokka, joka mallintaa uutista. 
Sillä on attribuutti päivämäärälle. Myös Vitsi periytyy Dokumentista. Omalista on luokka, jossa on tehty oma linkitetty lista. Se periytyy Ooperoiva-rajapinnasta. 
Listalla pysytään käsittelemään minkä tahansa tyyppisiä olioita.

## Ohjelman käyttö

Tiedostot annetaan komentoriviparametreina.

Käyttäjä voi antaa komennoiksi:
echo: Parametriton. kaiuttaa käyttäjän antaman komennon kunnes annetaan echo uudelleen.
quit: Parametriton. Lopettaa ohjelman.
reset: Parametriton. Lataa kokoelman uudelleen.
print: Jos annetaan ilman parametria, tulostaa kaikki kokoelman tekstit. Voidaan myös antaa int tyyppinen parametri, ja jos sillä tunnisteella
       löydetään vastaavuus kokoelmasta, tulostetaan olio.
polish: Parametrina annetaan välimerkkejä. Käsittelee kokoelman. Poistaa annetut välimerkit, muuttaa isot kirjaimet pieniksi ja poistaa sulkusanalistan sanat
remove: Parametrina annetaan dokumentin tunniste. Poistetaan tunnistetta vastaava dokumentti.
add: Parametrina annetaan dokumentti tunniste///päivämäärä/vitsin laji///teksti -tyylillä. Lisätään annettu dokumenttiin
find: Parametreiksi annetaan sanoja. Etsii dokumentit, joissa kaikki sanat esiintyvät. Tulostaa tunnisteet, joista ne löydetään.



