package hr.java.vjezbe.sortiranje;

import hr.java.vjezbe.entitet.Kolokvij;

import java.util.Comparator;

public class KolokvijSorter implements Comparator<Kolokvij> {

    public int compare(Kolokvij st1, Kolokvij st2) {

        int sortBodovi = st1.getBodovi().compareTo(st2.getBodovi());

        if (sortBodovi == 0) {

            return sortBodovi;
        }

        return st1.getBodovi().compareTo(st2.getBodovi())*-1;

    }
}
