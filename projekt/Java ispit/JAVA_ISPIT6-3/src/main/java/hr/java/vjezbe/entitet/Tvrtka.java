package hr.java.vjezbe.entitet;

import java.util.HashMap;
import java.util.List;

public class Tvrtka extends Entitet {

//    Rjesenje zadatka za sestu lab vjezbu prosiriti na nacin da se uvede nova datoteka pod nazivom
//    odrzavanje.txt koja ce sadrzavati zapise o tvrtkama koje odrzavaju mjerne postaje i senzore koji se
//    nalaze u tim mjernim postajama. Prvi redak svakog zapisa mora sadrzavati naziv tvrtke, drugi redak
//    identifikator mjernje postaje, a treci redak svakog zapisa mora sadrzavati identifikator senzora
//    koji se odrzava. Datoteka odrzavanje.txt mora moci sadrzavati proizvoljni broj zapisa o mjernim
//    postajama i pripadajucim senzorima(uvijek u grupi od 3 navedena podatka). Potrebno je kreirati
//    klasu Tvrtka koja sadrzava naziv String tipa te mapu kod koje je kljuc objekt klase MjernaPostaja,
//    a vrijednost list objekata klase Tvrtka i u mapu dodati mjerne postaje te liste senzora popuniti
//    objektima klase Senzor koji su dodjeljeni toj mjernoj postaji i tvrtki. Na kraju je potrebo ispisati
//    podatke o tvrtkama, mjernim postajama i senzorima unutar njih.


    private String naziv;
    private MjernaPostaja mjernaPostaja;
    private Senzor senzor;
    private HashMap<MjernaPostaja, List<Senzor>> postajaMapa = new HashMap<>();


    public Tvrtka(Long id, String naziv, MjernaPostaja mjernaPostaja, Senzor senzor) {
        super(id);
        this.naziv = naziv;
        this.mjernaPostaja = mjernaPostaja;
        this.senzor = senzor;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public MjernaPostaja getMjernaPostaja() {
        return mjernaPostaja;
    }

    public void setMjernaPostaja(MjernaPostaja mjernaPostaja) {
        this.mjernaPostaja = mjernaPostaja;
    }

    public Senzor getSenzor() {
        return senzor;
    }

    public void setSenzor(Senzor senzor) {
        this.senzor = senzor;
    }

    public HashMap<MjernaPostaja, List<Senzor>> getPostajaMapa() {
        return postajaMapa;
    }

    public void setPostajaMapa(HashMap<MjernaPostaja, List<Senzor>> postajaMapa) {
        this.postajaMapa = postajaMapa;
    }
}
