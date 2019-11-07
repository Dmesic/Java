package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Kolokvij extends Entitet implements Serializable {

    private static final long serialVersionUID = 1L;
    private Predmet predmet;
    private Student student;
    private Profesor profesor;
    private Integer bodovi;
    private LocalDateTime datumIVrijeme;

    public Kolokvij(Long id, Predmet predmet, Student student, Profesor profesor, Integer bodovi, LocalDateTime datumIVrijeme) {
        super(id);
        this.predmet = predmet;
        this.student = student;
        this.profesor = profesor;
        this.bodovi = bodovi;
        this.datumIVrijeme = datumIVrijeme;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Integer getBodovi() {
        return bodovi;
    }

    public void setBodovi(Integer bodovi) {
        this.bodovi = bodovi;
    }

    public LocalDateTime getDatumIVrijeme() {
        return datumIVrijeme;
    }

    public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
        this.datumIVrijeme = datumIVrijeme;
    }
}
