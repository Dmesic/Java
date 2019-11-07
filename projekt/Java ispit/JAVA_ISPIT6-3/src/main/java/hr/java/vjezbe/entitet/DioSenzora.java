package hr.java.vjezbe.entitet;

public enum DioSenzora {

    //  Rjesenje zadatka za 6. vjezbu prosiriti na nacin da se uvede entitet DioSenzora koji ce biti u
//    obliku enumeracija. dijelovi senzora mogu biti napajanje, elektronika i komunikacija. Unutar klase
//    Senzor mora postojati list dijelova senzora koji taj senzor ima. Kod pokretanja programa se svi
//    dijelovi moraju ucitati iz datoteke dijeloviSenzora.txt koja ima zapise u parovima podataka "ID senzora"
//    i naziv dijela senzora(u odvojenim retcima). Svaki senzor mora imati bar 1 dio, ali ne smije imati
//    vise istih dijelova. U slucajevima kada se iz datoteke procitaju podaci koji krse ta pravila, potrebno
//    je ispisati poruku o pogresci kod pokretanja programa i zaustaviti izvrsavanje programa.

    NAPAJANJE("napajanje1",5),
    ELEKTRONIKA("elektronika1",4),
    KOMUNIKACIJA("komunikator",2),
    ;

    private String naziv;
    private int kolicina;

    DioSenzora(String naziv, int kolicina) {
        this.naziv = naziv;
        this.kolicina = kolicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
