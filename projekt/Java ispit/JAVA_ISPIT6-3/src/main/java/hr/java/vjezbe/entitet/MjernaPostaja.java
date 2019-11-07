package hr.java.vjezbe.entitet;

public class MjernaPostaja extends  Entitet {

    private String naziv;
    private String mjesto;
    private String GeografskaTocka;

    public MjernaPostaja(Long id, String naziv, String mjesto, String geografskaTocka) {
        super(id);
        this.naziv = naziv;
        this.mjesto = mjesto;
        GeografskaTocka = geografskaTocka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public String getGeografskaTocka() {
        return GeografskaTocka;
    }

    public void setGeografskaTocka(String geografskaTocka) {
        GeografskaTocka = geografskaTocka;
    }
}
