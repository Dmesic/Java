package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import hr.java.vjezbe.entitet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.sortiranje.StudentSorter;

/**
 * Glavna klasa u kojoj je implementiran unos podataka o profesorima,
 * studentima, predmetima i ispitima te unos ocijena diplomskog ili završnog
 * rada i odabir najboljeg studenta.
 *
 * Sastoji se od main metode i zasebnih metoda za unos profesora, studenata
 * predmeta, ispita i unosa obrazovnih ustanova.
 *
 * @author dmesic
 *
 */
public class Glavna {

    private static final int BROJ_PROFESORA = 2;
    private static final int BROJ_PREDMETA = 3;
    public static final int BROJ_STUDENATA = 2;
    public static final String FORMAT_DATUMA = "dd.MM.yyyy.";
    public static final String FORMAT_DATUMA_I_VREMENA = "dd.MM.yyyy.'T'HH:mm";
    private static final int BROJ_ISPITA = 2;
    private static final String REGEX = "^[a-zA-Z0-9]+$";
    public static final int OCJENA_ODLICAN = 5;

    private static boolean ponoviUnos = false;

    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    static List<Profesor> profesori = new ArrayList<>();
    static List<Student> studenti = new ArrayList<>();
    static List<Predmet> predmeti = new ArrayList<>();
    static List<Ispit> ispiti = new ArrayList<>();
    static Sveuciliste<ObrazovnaUstanova> sveucilista = new Sveuciliste<ObrazovnaUstanova>(
            new ArrayList<ObrazovnaUstanova>());

    static Map<Profesor, List<String>> predmetProfesori = new HashMap<>();



    public static void main(String[] args) {

        logger.info("Pocetak programa");

        int brojObrazovnihUstanova = 0;

        Scanner unos = new Scanner(System.in);

        do {
            try {

                System.out.println("Unesite broj obrazovnih ustanova:");
                brojObrazovnihUstanova = unos.nextInt();
                unos.nextLine();
                ponoviUnos = false;

            } catch (InputMismatchException e) {

                logger.info("Neispravan broj obrazovnih ustanova.");

                System.out.println("Pogresan unos! Molimo ponovite:");
                unos.nextLine();
                ponoviUnos = true;
            }
        } while (ponoviUnos);

        for (int a = 0; a < brojObrazovnihUstanova; a++) {

            System.out.println("Unesite podatke za " + (a + 1) + ". obrazovnu ustanovu: ");

            for (int i = 0; i < BROJ_PROFESORA; i++) {

                Profesor uneseniProfesor = unesiProfesora(unos, i);
                profesori.add(uneseniProfesor);

            }

            for (int j = 0; j < BROJ_PREDMETA; j++) {

                Predmet uneseniPredmet = unesiPredmet(unos, j);
                predmeti.add(uneseniPredmet);

            }

            System.out.println("Ispis predmeta po profesorima: ");
            for (Profesor profesor : predmetProfesori.keySet()) {

                System.out.println("Profesor " + profesor.getIme() + " " + profesor.getPrezime() + " predaje: ");

                int x = 1;
                for (String nazivPredmeta : predmetProfesori.get(profesor)) {

                    System.out.println((x++) + ". " + nazivPredmeta);

                }
            }

            for (int s = 0; s < BROJ_STUDENATA; s++) {

                Student uneseniStudent = unesiStudenta(unos, s);
                studenti.add(uneseniStudent);
            }

            for (int z = 0; z < BROJ_ISPITA; z++) {


                Ispit uneseniIspit = unesiIspit(unos, z);
                ispiti.add(uneseniIspit);
            }

            // Ispis studenata po predmetima - "for"
            long pocetak, kraj;

            pocetak = System.nanoTime();
            System.out.println("Ispis studenata po predmetima: ");

            for (Predmet predmet : predmeti) {

                List<Student> listaStudenata = new ArrayList<>();

                for (Student student : predmet.upisaniStudenti) {

                    listaStudenata.add(student);

                }


                if (listaStudenata.isEmpty()) {

                    System.out.println("Nitko nije upisan na predmet " + predmet.getNaziv());
                } else {

                    System.out.println("Predmet " + predmet.getNaziv() + " su upisali: ");

                    int y = 1;
                    for (Student student : listaStudenata) {

                        System.out.println((y++) + ". " + student.getIme() + " " + student.getPrezime());

                    }

                }

            }
            kraj = System.nanoTime();
            System.out.println("For petlja rezultat: " + (kraj - pocetak) + " nano sekundi");

            // Ispis studenata na predmetima s lambdom

            pocetak = System.nanoTime();
            predmeti.forEach(predmet -> {
                if (predmet.getUpisaniStudenti().isEmpty()) {
                    System.out.println("Nema studenata upisanih na predmet '" + predmet.getNaziv() + "'.");
                } else {
                    System.out.println("Studenti upisani na predmet '" + predmet.getNaziv() + "' su:");
                    predmet.getUpisaniStudenti().stream()
                            .sorted(Comparator.comparing(Student::getPrezime).thenComparing(Student::getIme))
                            .forEach(System.out::println);
                }
            });
            kraj = System.nanoTime();
            System.out.println("Lambda rezultat: " + (kraj - pocetak) + " nano sekundi");


            // ispis Odlican "for"
            pocetak = System.nanoTime();
            for (int is = 0; is < BROJ_ISPITA; is++) {

                if (ispiti.get(is).getOcjena().equals(Ocjena.IZVRSTAN.getOcjena())) {

                    logger.info("Student " + ispiti.get(is).getStudent().getIme() + " "
                            + ispiti.get(is).getStudent().getPrezime() + " je ostvario ocjenu 'izvrstan' na predmetu "
                            + ispiti.get(is).getPredmet().getNaziv() + ".");

                    System.out.println("Student " + ispiti.get(is).getStudent().getIme() + " "
                            + ispiti.get(is).getStudent().getPrezime() + " je ostvario ocjenu "
                            + Ocjena.IZVRSTAN.getNaziv() + " na predmetu " + ispiti.get(is).getPredmet().getNaziv()
                            + ".");
                }
            }
            kraj = System.nanoTime();
            System.out.println("For petlja rezultat: " + (kraj - pocetak) + " nano sekundi");

            // ispis Odlican lambda

            pocetak = System.nanoTime();
            ispiti.forEach(odlican -> {
                if (odlican.getOcjena() == OCJENA_ODLICAN) {
                    System.out.println("Student " + odlican.getStudent().getPrezime() + " je dobio ocjenu "
                            + Ocjena.IZVRSTAN + " na predmetu " + odlican.getPredmet().getNaziv() + ".");
                }
            });
            kraj = System.nanoTime();
            System.out.println("Lambda rezultat: " + (kraj - pocetak) + " nano sekundi");

            // sortiranje profesora kojima prezinje pocinje na slovo m, a završava na slovo r, ispisano uzlazno
            System.out.println("Profesori kojima prezime počinje sa slovom m, a završava sa slovom r, ispisano uzlazno: ");
            profesori.stream().filter(profesori->profesori.getPrezime().toLowerCase().startsWith("m")).filter(profesori->profesori.getPrezime().toLowerCase().endsWith("r"))
                    .sorted(Comparator.comparing(Profesor::getPrezime).reversed()).forEach(System.out::println);

            // ispis najkraceg prezimena profesora
            System.out.println("Ispis najkraceg prezimena profesora: ");
            Profesor najkraceprezime = profesori.stream().min(Comparator.comparing(profesor->profesor.getPrezime().length())).get();
            System.out.println(najkraceprezime);

            // ispis najduzeg prezimena profesora
            System.out.println("Ispis najduzeg prezimena profesora: ");
            Profesor najduzeprezime = profesori.stream().max(Comparator.comparing(profesor->profesor.getPrezime().length())).get();
            System.out.println(najduzeprezime);


            // prosjek duzine svih prezimena profesora
            System.out.println("Prosjek duzine svih prezimena profesora: ");
            OptionalDouble prosjekprezimena = profesori.stream().mapToInt(profesor->profesor.getPrezime().length()).average();
            System.out.println(prosjekprezimena);

            // suma svih prezimena profesora
            System.out.println("Suma svih prezimena profesora: ");
            int sumprezimena = profesori.stream().mapToInt(profesor->profesor.getPrezime().length()).sum();
            System.out.println(sumprezimena);


            unosObrazovneUstanove(unos, a);

        }


        logger.info("Ovo je dobro prošlo. Program završio s izvođenjem.");

        System.out.println("Ovo je dobro prošlo. Program završio s izvođenjem.");

    }


//    private static void sortiranjeIspisaUstanova(Sveuciliste<ObrazovnaUstanova> sveucilista) {
//        System.out.println("Sortirane obrazovne ustanove prema broju studenata:");
//
//        Function<ObrazovnaUstanova, Integer> premaBrojuStudenata = ObrazovnaUstanova::getSizeListStudent;
//        Function<ObrazovnaUstanova, String> premaNazivuUstanove = ObrazovnaUstanova::getNazivUstanove;
//
//        sveucilista.dohvatiListuObrazovnaUstanova().stream()
//                .sorted(Comparator.comparing(premaBrojuStudenata).thenComparing(premaNazivuUstanove))
//                .forEach(System.out::println);
//    }



//    private static void ispisPraksanata(Praksanti<Profesor> praksanti) {
//        System.out.println("Ispis praksanata:");
//
//        Function<Profesor, String> premaNazivuProfesora = Profesor::getIme;
//
//        praksanti.dohvatiListuPraksanata().stream()
//                .sorted(Comparator.comparing(premaNazivuProfesora))
//                .forEach(System.out::println);
//    }


    private static Sveuciliste<ObrazovnaUstanova> unosObrazovneUstanove(Scanner unos, int a) {

        logger.info(
                "Odabir obrazovne ustanove za navedene podatke koju želite unijeti (1 - Veleučilište Jave, 2 - Fakultet računarstva): ");

        System.out.println(
                "Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 - Veleuèilište Jave, 2 - Fakultet raèunarstva): ");
        int odabirUstanove = unos.nextInt();
        unos.nextLine();

        if (odabirUstanove == 1) {

            System.out.println("Unesite naziv obrazovne ustanove: ");
            String naziv = unos.nextLine();

            VeleucilisteJave veleucilisteJave = new VeleucilisteJave(null, naziv , predmeti, profesori, studenti,
                    ispiti);
            sveucilista.dodajObrazovnuUstanovu(veleucilisteJave);

            for (int st = 0; st < BROJ_STUDENATA; st++) {
                int ocjenaZavrsnog = 0;
                int ocjenaObraneZavrsnog = 0;

                for (int isp = 0; isp < BROJ_ISPITA; isp++) {
                    if (ispiti.get(isp).getOcjena() != 1
                            && ispiti.get(isp).getStudent().getJmbag().equals(studenti.get(st).getJmbag())) {

                        do {
                            try {

                                System.out.println("Unesite ocjenu završnog rada za " + (st + 1) + " studenta: "
                                        + studenti.get(st).getIme() + " " + studenti.get(st).getPrezime() + " :");
                                ocjenaZavrsnog = unos.nextInt();
                                unos.nextLine();
                                ponoviUnos = false;

                            } catch (InputMismatchException ex1) {

                                logger.info("Neispravno unesena ocijena");

                                System.out.println("Morate unijeti numeričku vrijednost ocijene: ");
                                unos.nextLine();
                                ponoviUnos = true;
                            }

                        } while (ponoviUnos);

                        do {
                            try {

                                System.out.println("Unesite ocjenu obrane završnog rada za " + (st + 1) + " studenta: "
                                        + studenti.get(st).getIme() + " " + studenti.get(st).getPrezime() + " :");
                                ocjenaObraneZavrsnog = unos.nextInt();
                                unos.nextLine();
                                ponoviUnos = false;
                            } catch (InputMismatchException ex2) {

                                logger.info("Neispravno unesena ocijena");

                                System.out.println("Morate unijeti numeričku vrijednost ocijene: ");
                                unos.nextLine();
                                ponoviUnos = true;
                            }

                        } while (ponoviUnos);
                    }
                }

                BigDecimal konacnaOcjena = ((VeleucilisteJave) sveucilista.dohvatiObrazovnuUstanovu(a))
                        .izracunajKonacnuOcjenuStudijaZaStudenta(ispiti, studenti.get(st), ocjenaZavrsnog,
                                ocjenaObraneZavrsnog);

                System.out.println("Konačna ocjena studija za " + (st + 1) + " studenta " + studenti.get(st).getIme()
                        + " " + studenti.get(st).getPrezime() + " je:" + konacnaOcjena);

            }

            Student najbolji = ((VeleucilisteJave) sveucilista.dohvatiObrazovnuUstanovu(a))
                    .odrediNajuspjesnijegStudentaNaGodini(ispiti.get(a).getDatumIVrijeme().getYear());

            System.out.println("Najbolji student " + ispiti.get(a).getDatumIVrijeme().getYear() + " godine je "
                    + najbolji.getIme() + " " + najbolji.getPrezime() + " JMBAG:" + najbolji.getJmbag());

            // sveucilista.add(veleucilisteJave);

        }

        else if (odabirUstanove == 2) {

            System.out.println("Unesite naziv obrazovne ustanove:");
            String nazivUstanove = unos.nextLine();

            FakultetRacunarstva fakultetRacunarstva = new FakultetRacunarstva(null, nazivUstanove, predmeti, profesori,
                    studenti, ispiti);
            sveucilista.dodajObrazovnuUstanovu(fakultetRacunarstva);

            for (int st = 0; st < BROJ_STUDENATA; st++) {

                int ocjenaDiplomskog = 0;
                int ocjenaObraneDiplomskog = 0;

                for (int isp = 0; isp < BROJ_ISPITA; isp++) {
                    if (ispiti.get(isp).getOcjena() != 1
                            && ispiti.get(isp).getStudent().getJmbag().equals(studenti.get(st).getJmbag())) {

                        do {
                            try {

                                System.out.println("Unesite ocjenu diplomskog rada za studenta: "
                                        + studenti.get(st).getIme() + " " + studenti.get(st).getPrezime());
                                ocjenaDiplomskog = unos.nextInt();
                                unos.nextLine();
                                ponoviUnos = false;

                            } catch (InputMismatchException ex3) {

                                logger.info("Neispravno unesena ocijena");

                                System.out.println("Morate unijeti numeričku vrijednost ocijene: ");
                                unos.nextLine();
                                ponoviUnos = true;

                            }
                        } while (ponoviUnos);

                        do {
                            try {

                                System.out.println("Unesite ocjenu obrane diplomskog rada za studenta: "
                                        + studenti.get(st).getIme() + " " + studenti.get(st).getPrezime());
                                ocjenaObraneDiplomskog = unos.nextInt();
                                unos.nextLine();
                                ponoviUnos = false;

                            } catch (InputMismatchException ex4) {

                                logger.info("Neispravno unesena ocijena");

                                System.out.println("Morate unijeti numeričku vrijednost ocijene: ");
                                unos.nextLine();
                                ponoviUnos = true;
                            }

                        } while (ponoviUnos);
                    }
                }

                BigDecimal konacnaOcjena = ((FakultetRacunarstva) sveucilista.dohvatiObrazovnuUstanovu(a))
                        .izracunajKonacnuOcjenuStudijaZaStudenta(ispiti, studenti.get(st), ocjenaDiplomskog,
                                ocjenaObraneDiplomskog);

                System.out.println("Konačna ocjena studija za studenta " + studenti.get(st).getIme() + " "
                        + studenti.get(st).getPrezime() + " je:" + konacnaOcjena);

            }

            Student najbolji = ((FakultetRacunarstva) sveucilista.dohvatiObrazovnuUstanovu(a)) // tu sam stao
                    .odrediNajuspjesnijegStudentaNaGodini(ispiti.get(a).getDatumIVrijeme().getYear());

            System.out.println("Najbolji student " + ispiti.get(a).getDatumIVrijeme().getYear() + " godine je "
                    + najbolji.getIme() + " " + najbolji.getPrezime() + " JMBAG:" + najbolji.getJmbag());

            Student rektorovaNagrada = ((FakultetRacunarstva) sveucilista.dohvatiObrazovnuUstanovu(a))
                    .odrediStudentaZaRektorovuNagradu();

            System.out.println("Student koji je dobio rektorovu nagradu je " + rektorovaNagrada.getIme() + " "
                    + rektorovaNagrada.getPrezime());

            // sveucilista.add(fakultetRacunarstva);

        }

        else
            System.out.println("Netočan izbor ustanove!");

        return sveucilista;
    }



    private static Ispit unesiIspit(Scanner unos, int z) {

        Predmet predmetiispit = null;
        Student studentiispit = null;
        Integer ocjena = null;
        LocalDateTime datumIVrijeme = null;

        System.out.println("Unesite " + (z + 1) + ". ispit");

        do {
            try {

                System.out.println("Odaberite predmet:");

                for (int p = 0; p < BROJ_PREDMETA; p++) {

                    System.out.println((p + 1) + " " + predmeti.get(p).getNaziv());
                }

                System.out.println("Odabir:");
                Integer br1 = unos.nextInt();

                predmetiispit = predmeti.get(br1 - 1);

                ponoviUnos = false; // tu sam stao

            } catch (InputMismatchException | ArrayIndexOutOfBoundsException ex9) {

                logger.info("Neispravan odabir predmeta");

                System.out.println("Pogrešan odabir! Morate ponoviti: ");
                unos.nextLine();
                ponoviUnos = true;

            }

        } while (ponoviUnos);

        do {
            try {
                System.out.println("Odaberite studenta:");

                for (int s = 0; s < BROJ_STUDENATA; s++) {

                    System.out.println((s + 1) + " " + studenti.get(s).getIme() + " " + studenti.get(s).getPrezime());
                }

                System.out.println("Odabir:");
                Integer br2 = unos.nextInt();

                studentiispit = studenti.get(br2 - 1);

                ponoviUnos = false;

            } catch (InputMismatchException | ArrayIndexOutOfBoundsException ex10) {

                logger.info("Neispravan odabir studenta");

                System.out.println("Pogrešan odabir! Morate ponoviti: ");
                unos.nextLine();
                ponoviUnos = true;

            }
        } while (ponoviUnos);

        predmetiispit.upisaniStudenti.add(studentiispit);

        do {
            try {
                System.out.println("Unesite ocijenu na ispitu (1-5):");
                ocjena = unos.nextInt();
                unos.nextLine();

                ponoviUnos = false;

            } catch (InputMismatchException ex11) {

                logger.info("Neispravno unesena ocijena");

                System.out.println("Morate unijeti numerièku vrijednost: ");
                unos.nextLine();
                ponoviUnos = true;

            }
        } while (ponoviUnos);

        do {
            try {
                System.out.println("Unesite datum i vrijeme ispita u formatu(dd.MM.yyyy. THH:mm):");
                String datumIVrijemString = unos.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATUMA_I_VREMENA);
                datumIVrijeme = LocalDateTime.parse(datumIVrijemString, formatter);

                ponoviUnos = false;

            } catch (InputMismatchException | java.time.format.DateTimeParseException ex12) {

                logger.info("Neispravno unesen datum ispita");

                System.out.println("Morate unijeti datum ispita u obliku: dd.MM.yyyy.'T'HH:mm : ");
                unos.nextLine();
                ponoviUnos = true;

            }

        } while (ponoviUnos);

        return new Ispit(null,predmetiispit, studentiispit, ocjena, datumIVrijeme);
    }



    private static Student unesiStudenta(Scanner unos, int i) {

        LocalDate datum = null;
        Boolean greska = false;
        String jmbag;

        System.out.println("Unesite ime " + (i + 1) + ". studenta:");
        String ime = unos.nextLine();

        System.out.println("Unesite prezime " + (i + 1) + ". studenta:");
        String prezime = unos.nextLine();

        do {
            greska = false;
            System.out.println("Unesite JMBAG " + (i + 1) + ". studenta:");
            jmbag = unos.nextLine();

            if (jmbag.isEmpty()) {
                System.out.println("JMBAG ne smije biti prazan. ");
                greska = true;
            }

            for (int j = 0; j < studenti.size(); j++) { // tu sam stao
                if (studenti.get(j) != null && studenti.get(j).getJmbag().equals(jmbag) && !jmbag.isEmpty()) {
                    System.out.println("Uneseni JMBAG se veæ koristi. Molim Vas da unesete ispravni JMBAG: ");
                    greska = true;
                }
            }

        } while (greska);

        do {
            try {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATUMA);
                String petGodinaString = "01.02.2013.";
                LocalDate petGodina = LocalDate.parse(petGodinaString, formatter);

                System.out.println("Unesite datum rođenja (dd.MM.yyyy.) ");
                String datumString = unos.nextLine();
                datum = LocalDate.parse(datumString, formatter);

                while (!datum.isAfter(petGodina)) {

                    System.out.println("Unijeli ste datum stariji od 5 godina! Molimo Vas unesite ponovo");
                    datumString = unos.nextLine();
                    datum = LocalDate.parse(datumString, formatter);

                }

            } catch (InputMismatchException | java.time.format.DateTimeParseException ex8) {

                logger.info("Neispravno unesen datum roðenja");

                System.out.println("Morate unijeti datum roðenja u obliku: dd.MM.yyyy. : ");
                String datumString = unos.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATUMA);
                datum = LocalDate.parse(datumString, formatter);
                ponoviUnos = true;

            }

        } while (ponoviUnos);

        return new Student(null, ime, prezime,jmbag, datum,true);
    }



    private static Predmet unesiPredmet(Scanner unos, int i) {
        Integer brojEctsBodova = null;
        Profesor nositelj = null;
        Integer upisaniStudenti = null;
        Long id = null;

        System.out.println("Unesite šifru " + (i + 1) + ". predmeta:");
        String sifra = unos.nextLine();

        Boolean greska = false;
        String naziv;
        do {
            greska = false;
            System.out.println("Unesite naziv " + (i + 1) + ". predmeta");
            naziv = unos.nextLine();

            if (naziv.isEmpty()) {
                System.out.println("Naziv predmeta ne smije biti prazan.");
                greska = true;
            }

        } while (greska);

        do {
            try {

                System.out.println("Unesite broj ECTS bodova za predmet " + naziv);
                brojEctsBodova = unos.nextInt();
                ponoviUnos = false;

            } catch (InputMismatchException ex5) {

                logger.info("Neispravno uneseni ECTS bodovi");

                System.out.println("Morate unijeti numerièku vrijednost ECTS bodova: ");
                unos.nextLine();
                ponoviUnos = true;

            }
        } while (ponoviUnos);

        do {
            try {

                System.out.println("Unesite broj profesora:");

                for (int p = 0; p < BROJ_PROFESORA; p++) {

                    System.out.println((p + 1) + " " + profesori.get(p).getIme() + " " + profesori.get(p).getPrezime());
                }

                System.out.println("Odabir:");
                Integer broj = unos.nextInt();

                nositelj = profesori.get(broj - 1);
                ponoviUnos = false;

            } catch (InputMismatchException | ArrayIndexOutOfBoundsException ex6) {

                logger.info("Neispravan odabir profesora");

                System.out.println("Pogrešan odabir! Morate ponoviti: ");
                unos.nextLine();
                ponoviUnos = true;
            }

        } while (ponoviUnos);

        do {
            try {

                System.out.println("Unesite broj studenata za predmet " + naziv);
                upisaniStudenti = unos.nextInt();
                unos.nextLine();
                ponoviUnos = false;

            } catch (InputMismatchException ex7) {

                logger.info("Neispravno unesen broj studenata");

                System.out.println("Morate unijeti numerièku vrijednost: ");
                unos.nextLine();
                ponoviUnos = true;

            }
        } while (ponoviUnos);

        if (predmetProfesori.get(nositelj) == null) {
            predmetProfesori.put(nositelj, new ArrayList<String>());
        }
        predmetProfesori.get(nositelj).add(naziv);

        Set<Student> upisaniStudenti1 = null;
        Predmet noviPredmet = new Predmet(id, sifra, naziv, brojEctsBodova, nositelj, null);

        unos.nextLine();

        return noviPredmet;
    }


    private static Profesor unesiProfesora(Scanner unos, int i) {

        Boolean greska = false;
        String sifra;

        do {
            greska = false;
            System.out.println("Unesite šifru " + (i + 1) + ". profesora: ");
            sifra = unos.nextLine();

            if (sifra.isEmpty()) {
                System.out.println("Unesena šifra ne smije biti prazna, molimo unesite ponovno");
                greska = true;
            }

            for (int j = 0; j < profesori.size(); j++) {
                if (profesori.get(j) != null && profesori.get(j).getSifra().equals(sifra) && !sifra.isEmpty()) {
                    System.out.println("Unesena šifra se veæ koristi. Molim Vas da unesete ispravnu šifru: ");
                    greska = true;
                }
            }
        } while (greska);

        String ime;

        System.out.println("Unesite ime " + (i + 1) + ". profesora slovima:  ");
        ime = unos.nextLine();

        Pattern pattern = Pattern.compile(REGEX);

        Matcher matcher = pattern.matcher(ime);

        while (!matcher.find()) {
            System.out.println("Unesite ime samo alfanumerickim znakovima: ");
            ime = unos.nextLine();
            matcher = pattern.matcher(ime);

        }

        System.out.println("Unesite prezime " + (i + 1) + ". profesora: ");
        String prezime = unos.nextLine();

        System.out.println("Unesite titulu " + (i + 1) + ". profesora: ");
        String titula = unos.nextLine();

        return new Profesor(null, sifra, ime, prezime,titula);

    }

}
