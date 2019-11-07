package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDate;

public class Student extends Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jmbag;
    private LocalDate datumRodjenja;
    private Boolean prijavljenNaNatjecanje;


    public Student(Long id, String ime, String prezime, String jmbag, LocalDate datumRodjenja,
                   Boolean prijavljenNaNatjecanje) {
        super(id, ime, prezime);
        this.jmbag = jmbag;
        this.datumRodjenja = datumRodjenja;
        this.prijavljenNaNatjecanje = prijavljenNaNatjecanje;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getPrijavljenNaNatjecanje() {
        return prijavljenNaNatjecanje;
    }

    public void setPrijavljenNaNatjecanje(Boolean prijavljenNaNatjecanje) {
        this.prijavljenNaNatjecanje = prijavljenNaNatjecanje;
    }
}
