package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.util.List;

public abstract class ObrazovnaUstanova extends Entitet implements Serializable {

    private static final long serialVersionUID = 1L;
    public String naziv;
    public List<Predmet> predmeti;
    public List<Profesor> profesori;
    public List<Student> studenti;
    public List<Ispit> ispiti;


    public ObrazovnaUstanova(Long id, String naziv, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
        super(id);
        this.naziv = naziv;
        this.predmeti = predmeti;
        this.profesori = profesori;
        this.studenti = studenti;
        this.ispiti = ispiti;
    }

    public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godina);


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<Profesor> profesori) {
        this.profesori = profesori;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public List<Ispit> getIspiti() {
        return ispiti;
    }

    public void setIspiti(List<Ispit> ispiti) {
        this.ispiti = ispiti;
    }


}
