package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class Senzor extends Entitet{

    private String mjernaJedinica;
    private int preciznost;
    private int vrijednost;
    private DioSenzora dioSenzora;

    public Senzor(Long id, String mjernaJedinica, int preciznost, int vrijednost) {
        super(id);
        this.mjernaJedinica = mjernaJedinica;
        this.preciznost = preciznost;
        this.vrijednost = vrijednost;
    }

    public Senzor(Long id, DioSenzora dioSenzora) {
        super(id);
        this.dioSenzora = dioSenzora;
    }


    public DioSenzora getDioSenzora() {
        return dioSenzora;
    }

    public void setDioSenzora(DioSenzora dioSenzora) {
        this.dioSenzora = dioSenzora;
    }

    public String getMjernaJedinica() {
        return mjernaJedinica;
    }

    public void setMjernaJedinica(String mjernaJedinica) {
        this.mjernaJedinica = mjernaJedinica;
    }

    public int getPreciznost() {
        return preciznost;
    }

    public void setPreciznost(int preciznost) {
        this.preciznost = preciznost;
    }

    public int getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(int vrijednost) {
        this.vrijednost = vrijednost;
    }
}
