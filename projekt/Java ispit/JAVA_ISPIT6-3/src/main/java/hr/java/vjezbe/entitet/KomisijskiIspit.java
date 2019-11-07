package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

public class KomisijskiIspit extends Ispit{

    // ------------- 1. zadatak ----------
//    Prosiriti rjesenja zadatka za sestu laboratorijsku vjezbu na nacin da se uvede entitet "KomisijskiIspit"
//    koji se organizira kada student cetvrti put izlazi na neki ispit. Kreirati klasu KomisijskiIspit koja
//    nasljeduje klasu Ispit te je prosiruje s tri clana komisije koji su objekt klase Profesor. Kreirati datoteku
//    komisijskiIspit.txt koja sadrzi sve podatke kao i datoteka ispit.txt, uz tri identifikatora koji su dodjeljeni
//    profesorima koji su u komisiji. Kod pokretanja programa se svi podaci o komisijskim ispitima moraju ucitati,
//    kreirati odgovarajuci objekti, te uz svaki od njih ispisati koliko puta je student na komisijskom ispitu
//    prethodno izasao na "standardni" ispit.

    private Profesor clanKomisije1;
    private Profesor clanKomisije2;
    private Profesor clanKomisije3;

    public KomisijskiIspit(Long id, Predmet predmet, Student student, Integer ocjena,
                           LocalDateTime datumIVrijeme, Profesor clanKomisije1, Profesor clanKomisije2, Profesor clanKomisije3) {
        super(id, predmet, student, ocjena, datumIVrijeme);
        this.clanKomisije1 = clanKomisije1;
        this.clanKomisije2 = clanKomisije2;
        this.clanKomisije3 = clanKomisije3;
    }

    public Profesor getClanKomisije1() {
        return clanKomisije1;
    }

    public void setClanKomisije1(Profesor clanKomisije1) {
        this.clanKomisije1 = clanKomisije1;
    }

    public Profesor getClanKomisije2() {
        return clanKomisije2;
    }

    public void setClanKomisije2(Profesor clanKomisije2) {
        this.clanKomisije2 = clanKomisije2;
    }

    public Profesor getClanKomisije3() {
        return clanKomisije3;
    }

    public void setClanKomisije3(Profesor clanKomisije3) {
        this.clanKomisije3 = clanKomisije3;
    }
}
