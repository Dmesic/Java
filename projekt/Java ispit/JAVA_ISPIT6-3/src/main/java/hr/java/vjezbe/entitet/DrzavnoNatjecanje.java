package hr.java.vjezbe.entitet;

import java.util.List;

// ------------------- 2. zadatak ------------
//Prosiriti rjesenja zadatka za 6. lab vj na nacin da se uvede entitet “drzavno natjecanje” koje se organizira za
//odabrane studente. Klasa DrzavnoNatjecanje koju je potrebno kreirati mora sadrzavati varijable id, naziv, te
//sadrzavati listu studenta koji su prijavljeni na drzavno natjecanje. svakom studentu se mora uvesti nova varijabla
//tipa Boolean koja oznacava da li student pristupa drzavnom natjecanju ili ne. Taj podatak je potrebno dodati u
//datoteku studenti.txt i kod pokretanja ga postaviti u objekt klase Student. Takoder je potrebno kreirati datoteku drzavno_natjecanje.txt
//iz koje procitaju podaci o ID-u i nazivu natjecanja na osnovi kojeg se kreira objekt klase
//DrzavnoNatjecanje te popuni lista studenata prijavljenih na to natjecanje

public class DrzavnoNatjecanje {

    private Long id;
    private String naziv;
    private List<Student> listaStudenata;


    public DrzavnoNatjecanje(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Student> getListaStudenata() {
        return listaStudenata;
    }

    public void setListaStudenata(List<Student> listaStudenata) {
        this.listaStudenata = listaStudenata;
    }
}
