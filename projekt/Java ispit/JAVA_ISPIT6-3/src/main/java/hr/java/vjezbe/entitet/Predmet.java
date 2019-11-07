package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Predmet extends Entitet implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sifra;
    private String naziv;
    private Integer brojEctsBodova;
    private Profesor nositelj;
    private Set<Student> studenti;


    public Predmet(Long id, String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj, Set<Student> upisaniStudenti) {
        super(id);
        this.sifra = sifra;
        this.naziv = naziv;
        this.brojEctsBodova = brojEctsBodova;
        this.nositelj = nositelj;

    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getBrojEctsBodova() {
        return brojEctsBodova;
    }

    public void setBrojEctsBodova(Integer brojEctsBodova) {
        this.brojEctsBodova = brojEctsBodova;
    }

    public Profesor getNositelj() {
        return nositelj;
    }

    public void setNositelj(Profesor nositelj) {
        this.nositelj = nositelj;
    }

    public Set<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(Set<Student> studenti) {
        this.studenti = studenti;
    }

    public Set<Student> getUpisaniStudenti() {
        return upisaniStudenti;
    }

    public Set<Student> upisaniStudenti = new HashSet<>();

    @Override
    public String toString() {

        return getNaziv();
    }
}
