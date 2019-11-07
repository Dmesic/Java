package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenataException;

import javax.swing.plaf.metal.OceanTheme;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Visokoskolska {

    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta
            (List<Ispit> ispiti, Student studenti, int ocjenaPismenogDijelaZR, int ocjenaObraneZR);

    default BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> ispiti)
            throws NemoguceOdreditiProsjekStudenataException {
        BigDecimal prosjek = null;
        int zbroj = 0, brojac = 0;

        for (int i = 0; i < ispiti.size(); i++) {

            if (ispiti.get(i) != null)
                if (ispiti.get(i).getOcjena() != 1) {

                    zbroj += ispiti.get(i).getOcjena();
                    brojac++;
                } else {
                    throw new NemoguceOdreditiProsjekStudenataException(ispiti.get(i).getStudent());
                }

        }

        prosjek = BigDecimal.valueOf(zbroj / brojac);

        return prosjek;

    }

    private List<Ispit> filtrirajPolozeneIspite(List<Ispit> ispiti) {

        List<Ispit> prolazniIspiti = new ArrayList<>();

        for (int i = 0; i < ispiti.size(); i++) {
            if (ispiti.get(i).getOcjena() > Ocjena.NEDOVOLJAN.getOcjena()) {
                prolazniIspiti.add(ispiti.get(i));
            }
        }
        return prolazniIspiti;

    }

    default List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti, Student student) {

        List<Ispit> sortiraniIspiti = new ArrayList<Ispit>();
        long pocetak, kraj;
        pocetak = System.nanoTime();
        for (int i = 0; i < ispiti.size(); i++) {

            if (ispiti.get(i).getStudent().getJmbag().equals(student.getJmbag())) {

                sortiraniIspiti.add(ispiti.get(i));

            }
        }

        kraj = System.nanoTime();
        System.out.println("For petlja rezultat: " + (kraj - pocetak) + " nano sekundi");
        sortiraniIspiti.clear();

        pocetak = System.nanoTime();
        ispiti.stream().filter(varijabla -> varijabla.getStudent().equals(student))
                .forEach(varijabla2 -> sortiraniIspiti.add(varijabla2));
        kraj = System.nanoTime();

        System.out.println("Lambda rezultat: " + (kraj - pocetak) + " nano sekundi");

        return sortiraniIspiti;


    }
}

