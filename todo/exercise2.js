
// funktio lisää listaan syötetyn asian
function addListItem(text) {
  // Luodaan elementti
  let lista = document.createElement("li");

  // Asetetaan parametrina saatu arvo
  lista.append(text);

  // Lisätään todo:n
  document.getElementById("todo").append(lista);
};



function submitHandler(e) {
    // Otetaan type-inputin arvo muuttujaan
    const x = document.getElementById ("type-input").value;
  
    /* Jos type-inputin arvo on muuta kuin tyhjä tai välilyöntejä niin siirretään
       type-inputin teksti receive-inputiin
       trim: poistaa välilyönnit Stringin molemmilta puolilta
     */
    if (x != "" && x.trim().length != 0) {
      // Kutsutaan funktiota
      addListItem(document.getElementById ("type-input").value);
   
      // Alustetaan type-input taas tyhjäksi
      document.getElementById ("type-input").value = "";
    }
   
    // preventdefault kertoo käyttäjäagentille, että jos tapahtumaa ei käsitellä 
    // nimenomaisesti, sen oletustoimenpidettä ei pidä suorittaa normaalisti. 
     e.preventDefault();
}


const form = document.getElementById("form");
// Funktion kutsu; addeventlistener kutsuu funktiota aina kun määritetty
// tapahtuma tapahtuu
form.addEventListener("submit", submitHandler);



function listClickHandler(e) {
  // Tarkistus
  if(e.target.tagName.value = "li") {
    // Jos luokalla on done
    if(e.target.classList.contains("done")) {
      // Poistaminen
      e.target.parentNode.removeChild(e.target)
    }
    // Jos luokalla ei ole done
    if (!e.target.classList.contains("done")) {
      // Lisätään done
      e.target.classList.add("done")
    }
  }
}

// Funktion kutsu
document.getElementById("todo").addEventListener('click', listClickHandler);
