package hr.java.vjezbe.entitet;

import java.io.Serializable;

public class Asistent extends Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sifra;
    private Predmet predmet;

    public Asistent(Long id,String sifra, String ime, String prezime,  Predmet predmet) {
        super(id, ime, prezime);
        this.sifra = sifra;
        this.predmet = predmet;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
}
