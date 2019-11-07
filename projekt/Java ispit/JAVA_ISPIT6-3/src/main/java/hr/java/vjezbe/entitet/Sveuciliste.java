package hr.java.vjezbe.entitet;

import java.util.List;

public class Sveuciliste<T extends ObrazovnaUstanova>  {

    private List<T> listaSveucilista;

    /**
     * @param listaSveucilista
     */
    public Sveuciliste(List<T> listaSveucilista) {
        super();
        this.listaSveucilista = listaSveucilista;
    }

    public void dodajObrazovnuUstanovu(T t) {
        listaSveucilista.add(t);
    }

    public T dohvatiObrazovnuUstanovu(Integer i) {
        return listaSveucilista.get(i);
    }

    public List<T> dohvatiListuObrazovnaUstanova() {
        return listaSveucilista;
    }
}
