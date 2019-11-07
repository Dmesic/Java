package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.sortiranje.KolokvijSorter;
import hr.java.vjezbe.sortiranje.StudentSorter;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class GlavnaDatoteke {


    public static final File ObrazovnaUstanovaFile = new File("Datoteke/ObrazovneUstanoveDat");
    private static final String DATUM = "dd.MM.yyyy.";
    private static final String DATUM_VRIJEME = "dd.MM.yyyy.-HH:mm";
    private static final File profesori = new File("Datoteke/Profesori.txt");
    private static final File studenti = new File("Datoteke/Studenti.txt");
    private static final File predmeti = new File("Datoteke/Predmeti.txt");
    private static final File ispiti = new File("Datoteke/Ispiti.txt");
    private static final File veleucilisteJave1 = new File("Datoteke/VeleucilisteJave.txt");
    private static final File fakultetRacunarstva1 = new File("Datoteke/FakultetRacunarstva.txt");
    private static final File asistenti = new File("Datoteke/Asistenti.txt");
    private static final File kolokviji = new File("Datoteke/kolokvij.txt");
    private static final File komisijskiIspiti = new File("Datoteke/komisijskiIspit.txt");
    private static final File drzavnoNatjecanjeFile = new File("Datoteke/drzavno_natjecanje.txt");

    static List<Profesor> listProfesor = new ArrayList<>();
    static List<Student> listStudent = new ArrayList<>();
    static List<Predmet> listPredmet = new ArrayList<>();
    static ArrayList<Ispit> listIspit = new ArrayList<>();
    static List<Asistent> listAsistent = new ArrayList<>();
    static List<Kolokvij> listKolokvij = new ArrayList<>();
    static List<KomisijskiIspit> listKomisijskiIspit = new ArrayList<>();
    static List<ObrazovnaUstanova> listVeleuciliste = new ArrayList<>();
    static List<ObrazovnaUstanova> listFekultet = new ArrayList<>();


    static DrzavnoNatjecanje drzavnoNatjecanje = null;


    static Map<Profesor, List<String>> predmetProfesori = new HashMap<>();

    public static void main(String[] args) {

        Scanner ucitaj = null;

        // deserilarizacija ustanova koja vraca ID
        if (ObrazovnaUstanovaFile.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(ObrazovnaUstanovaFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                List<ObrazovnaUstanova> procitaneUstanove = (List<ObrazovnaUstanova>) in.readObject();
                System.out.println("Deserijalizacija i Ispis obrazovnih ustanova: ");
                for (ObrazovnaUstanova o : procitaneUstanove) {
                    System.out.println(o.getId());
                }
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
            }
        }

        System.out.println("Ucitavanje profesora...");

        List<Profesor> uneseniProfesor = dohvatiProfesora(ucitaj);
        for (Profesor profesor : uneseniProfesor) {

            listProfesor.add(profesor);

        }

        for (Profesor profesor : listProfesor) {
            System.out.println(profesor.getId() + " " + profesor.getSifra() + " " + profesor.getIme() + " "
                    + profesor.getPrezime() + " " + profesor.getTitula());
        }

        System.out.println("Ucitavanje studenata...");

        List<Student> uneseniStudent = dohvatiStudenta(ucitaj);
        for (Student student : uneseniStudent) {

            listStudent.add(student);

        }

        for (Student student : listStudent) {
            System.out.println(student.getId() + " " + student.getIme() + " " + student.getPrezime() + " "
                    + student.getJmbag() + " " + student.getDatumRodjenja());
        }


////

        System.out.println("Ucitavanje drzavnog natjecanja...");

        drzavnoNatjecanje = dohvatiDrzavnaNatjecanja(ucitaj, listStudent);

////


        System.out.println("Ucitavanje predmeta...");

        List<Predmet> uneseniPredmet = dohvatipredmet(ucitaj);
        for (Predmet predmet : uneseniPredmet) {

            listPredmet.add(predmet);

        }

        for (Predmet predmet : listPredmet) {
            System.out.println(predmet.getId() + " " + predmet.getSifra() + " " + predmet.getNaziv() + " "
                    + predmet.getBrojEctsBodova() + " " + predmet.getNositelj().getPrezime() + " "
                    + predmet.getUpisaniStudenti());
        }

        System.out.println("Ucitavanje ispita i ocjena...");

        List<Ispit> uneseniIspit = dohvatiIspit(ucitaj);
        for (Ispit ispit : uneseniIspit) {

            listIspit.add(ispit);

        }

        for (Ispit ispit : listIspit) {
            System.out.println(ispit.getId() + " " + ispit.getPredmet().getNaziv() + " "
                    + ispit.getStudent().getPrezime() + " " + ispit.getOcjena());
        }

        System.out.println("Ucitavanje asistenata...");

        List<Asistent> uneseniAsistent = dohvatiAsistenta(ucitaj);
        for (Asistent asistent : uneseniAsistent) {

            listAsistent.add(asistent);

        }

        System.out.println("Ucitavanje komisijskih ispita...");

        List<KomisijskiIspit> uneseniKomisijskiIspiti = dohvatiKomisijskiIspit(ucitaj);
        for (KomisijskiIspit komisijskiIspit : uneseniKomisijskiIspiti) {

            listKomisijskiIspit.add(komisijskiIspit);

        }

        System.out.println("Ucitavanje kolokvija i bodova...");

        List<Kolokvij> uneseniKolokvij = dohvatiKolokvij(ucitaj);
        for (Kolokvij kolokvij : uneseniKolokvij) {

            listKolokvij.add(kolokvij);

        }

        for (Kolokvij kolokvij : listKolokvij) {
            System.out.println(kolokvij.getId() + " " + kolokvij.getPredmet().getNaziv() + " "
                    + kolokvij.getStudent().getPrezime() + " " + kolokvij.getProfesor().getPrezime() + " "
                    + kolokvij.getBodovi() + " " + kolokvij.getDatumIVrijeme());
        }

        Collections.sort(listKolokvij, new KolokvijSorter());

        System.out.println("------------------- 1. zadatak pocetak ----------------------------------");

        ispisKomisijskihIspita(listKomisijskiIspit,listIspit);

        System.out.println("------------------- 1. zadatak kraj    ----------------------------------");

        System.out.println("Studenti koji su bili u gornjoj polovici: ");

        for (int i = 0; i < listKolokvij.size() / 2; i++) {
            System.out.println(listKolokvij.get(i).getId() + " " + listKolokvij.get(i).getPredmet().getNaziv() + " "
                    + listKolokvij.get(i).getStudent().getPrezime() + " "
                    + listKolokvij.get(i).getProfesor().getPrezime() + " " + listKolokvij.get(i).getBodovi() + " "
                    + listKolokvij.get(i).getDatumIVrijeme());
        }


        System.out.println("Prosjecan broj bodova: ");

        int sum = 0;

        for (Kolokvij kolokvij : listKolokvij) {
            sum = sum + kolokvij.getBodovi();
        }

        int avg = sum / (listKolokvij.size());

        System.out.println(avg);

        System.out.println("Studenti koji imaju vise od prosjecnog broja bodova: ");

        for (Kolokvij kolokvij : listKolokvij) {

            if (kolokvij.getBodovi() > avg) {

                System.out.println("Student " + kolokvij.getStudent().getPrezime() + " " + kolokvij.getBodovi());

            }

        }


        System.out.println("Ucitavanje obrazovnih ustanova...");

        List<ObrazovnaUstanova> unesenoVeleuciliste = dohvatiVeleuciliste(ucitaj);
        for (ObrazovnaUstanova ustanova : unesenoVeleuciliste) {

            listVeleuciliste.add(ustanova);

        }

        for (ObrazovnaUstanova ustanova : listVeleuciliste) {
            System.out.println(ustanova.getId() + " " + ustanova.getNaziv());
        }

        List<ObrazovnaUstanova> uneseniFakultet = dohvatiFakultet(ucitaj);
        for (ObrazovnaUstanova ustanova : uneseniFakultet) {

            listFekultet.add(ustanova);

        }

        for (ObrazovnaUstanova ustanova : listFekultet) {
            System.out.println(ustanova.getId() + " " + ustanova.getNaziv());
        }

        for (Profesor profesor : predmetProfesori.keySet()) {

            System.out.println(
                    "Profesor " + profesor.getIme() + " " + profesor.getPrezime() + " predaje sljedece predmete: ");

            int x = 1;
            for (String nazivPredmeta : predmetProfesori.get(profesor)) {

                System.out.println((x++) + ". " + nazivPredmeta);

            }
        }

        for (Predmet predmet : listPredmet) {

            List<Student> listaStudenata = new ArrayList<>();

            for (Student student : predmet.upisaniStudenti) {

                listaStudenata.add(student);

            }

            Collections.sort(listaStudenata, new StudentSorter());

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

        for (Ispit ispit : listIspit) {

            if (ispit.getOcjena().equals(Ocjena.IZVRSTAN.getOcjena())) {

                System.out.println("Student " + ispit.getStudent().getIme() + " " + ispit.getStudent().getPrezime()
                        + " je ostvario ocjenu " + Ocjena.IZVRSTAN.getNaziv() + " na predmetu "
                        + ispit.getPredmet().getNaziv() + ".");
            }
        }


        System.out.println("------------------- 2. zadatak pocetak ----------------------------------");



        System.out.println("Na drzavno natjecanje "+drzavnoNatjecanje.getNaziv()+" idu studenti:");
        drzavnoNatjecanje.getListaStudenata()
                .stream()
                .forEach(student -> System.out.println(student.getIme() + " " + student.getPrezime()));


        try {

            FileOutputStream obrazovneUstanoveDat = new FileOutputStream("Datoteke/ObrazovneUstanoveDat");
            ObjectOutputStream ispis = new ObjectOutputStream(obrazovneUstanoveDat);

            ispis.writeObject(listVeleuciliste);

            obrazovneUstanoveDat.close();
            ispis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------------------- 2. zadatak kraj ----------------------------------");

    }

    private static void ispisKomisijskihIspita(List<KomisijskiIspit> komisijskiIspiti, List<Ispit> ispiti){

        for (KomisijskiIspit k : komisijskiIspiti){
            long brojIzlazaka = ispiti.stream().filter(i->i.getStudent().getId().equals(k.getStudent().getId()) &&
                    i.getPredmet().getId().equals(k.getPredmet().getId())).count();

            System.out.println("Student "+ k.getStudent().getPrezime() +" izlazi na komisijski ispit iz predmeta: " +
                    k.getPredmet()+" nakon "+brojIzlazaka+" pokusaja na standardnom ispitu");

            System.out.println("Clanovi komisije su: "+k.getClanKomisije1().getSifra()+", "+k.getClanKomisije2().getSifra()+", "
                    +k.getClanKomisije3().getSifra()+". ");
        }


    }

    private static DrzavnoNatjecanje dohvatiDrzavnaNatjecanja(Scanner ucitaj, List<Student> studenti){

        Long id = null;
        String naziv = null;
        try {

            ucitaj = new Scanner(drzavnoNatjecanjeFile);

            int x = 1;
            while (ucitaj.hasNextLine()) {

                for (int i = x; i < x + 3; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        naziv = ucitaj.nextLine();
                    }
                }
                x += 3;
            }

            ucitaj.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DrzavnoNatjecanje drzavnoNatjecanje = new DrzavnoNatjecanje(id, naziv);
        drzavnoNatjecanje.setListaStudenata(studenti.stream()
                .filter(i->i.getPrijavljenNaNatjecanje())
                .collect(Collectors.toList()));
        return drzavnoNatjecanje;

    }

    private static List<Profesor> dohvatiProfesora(Scanner ucitaj) {

        Long id = null;
        String sifra = null;
        String ime = null;
        String prezime = null;
        String titula = null;

        List<Profesor> noviProfesor = new ArrayList<>();

        try {

            ucitaj = new Scanner(profesori);

            int x = 1;
            while (ucitaj.hasNextLine()) {

                for (int i = x; i < x + 5; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        sifra = ucitaj.nextLine();
                    } else if (i == x + 2) {
                        ime = ucitaj.nextLine();
                    } else if (i == x + 3) {
                        prezime = ucitaj.nextLine();
                    } else if (i == x + 4) {
                        titula = ucitaj.nextLine();
                    }
                }
                x += 5;
                noviProfesor.add(new Profesor(id, sifra, ime, prezime, titula));
            }

            ucitaj.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return noviProfesor;

    }

    private static List<Student> dohvatiStudenta(Scanner ucitaj) {

        Long id = null;
        String ime = null;
        String prezime = null;
        String jmbag = null;
        LocalDate datumRodjenja = null;
        boolean ideNaDrzavni = false;

        List<Student> noviStudent = new ArrayList<>();

        try {

            ucitaj = new Scanner(studenti);

            int x = 1;
            while (ucitaj.hasNextLine()) {

                for (int i = x; i < x + 6; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        ime = ucitaj.nextLine();
                    } else if (i == x + 2) {
                        prezime = ucitaj.nextLine();
                    } else if (i == x + 3) {
                        jmbag = ucitaj.nextLine();
                    } else if (i == x + 4) {
                        String datumRodjenjaString = ucitaj.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATUM);
                        datumRodjenja = LocalDate.parse(datumRodjenjaString, formatter);
                    }
                    else if(i == x + 5){
                        ideNaDrzavni = Boolean.valueOf(ucitaj.nextLine());
                    }
                }
                x += 6;
                noviStudent.add(new Student(id, ime, prezime, jmbag, datumRodjenja, ideNaDrzavni));
            }

            ucitaj.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return noviStudent;
    }

    private static List<Predmet> dohvatipredmet(Scanner ucitaj) {

        Long id = null;
        String sifra = null;
        String naziv = null;
        Integer brojEctsBodova = null;
        Profesor nositelj = null;
        Set<Student> upisaniStudenti = new HashSet<>();

        List<Predmet> noviPredmet = new ArrayList<>();

        try {

            ucitaj = new Scanner(predmeti);

            int x = 1;
            while (ucitaj.hasNextLine()) {

                for (int i = x; i < x + 6; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        sifra = ucitaj.nextLine();
                    } else if (i == x + 2) {
                        naziv = ucitaj.nextLine();
                    } else if (i == x + 3) {
                        brojEctsBodova = ucitaj.nextInt();
                        ucitaj.nextLine();
                    } else if (i == x + 4) {
                        int idProfesora = ucitaj.nextInt();
                        nositelj = listProfesor.get(idProfesora - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 5) {
                        int idStudenta = ucitaj.nextInt();
                        upisaniStudenti.add(listStudent.get(idStudenta - 1));

                    }

                }
                x += 6;

                if (predmetProfesori.get(nositelj) == null) {
                    predmetProfesori.put(nositelj, new ArrayList<String>());
                }
                predmetProfesori.get(nositelj).add(naziv);

                noviPredmet.add(new Predmet(id, sifra, naziv, brojEctsBodova, nositelj, upisaniStudenti));
            }

            ucitaj.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return noviPredmet;

    }

    private static List<Ispit> dohvatiIspit(Scanner ucitaj) {

        Long id = null;
        Predmet predmet = null;
        Student student = null;
        Integer ocjena = null;
        LocalDateTime datumIVrijeme = null;

        List<Ispit> noviIspit = new ArrayList<>();

        try {

            ucitaj = new Scanner(ispiti);

            int x = 1;
            while (ucitaj.hasNextLine()) {

                for (int i = x; i < x + 5; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        int idPredmeta = ucitaj.nextInt();
                        predmet = listPredmet.get(idPredmeta - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 2) {
                        int idStudenta = ucitaj.nextInt();
                        student = listStudent.get(idStudenta - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 3) {
                        ocjena = ucitaj.nextInt();
                        ucitaj.nextLine();
                    } else if (i == x + 4) {
                        String datumIVrijemeString = ucitaj.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATUM_VRIJEME);
                        datumIVrijeme = LocalDateTime.parse(datumIVrijemeString, formatter);
                    }
                }
                x += 5;
                noviIspit.add(new Ispit(id, predmet, student, ocjena, datumIVrijeme));
            }

            ucitaj.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return noviIspit;
    }

    private static List<KomisijskiIspit> dohvatiKomisijskiIspit(Scanner ucitaj) {

        Long id = null;
        Predmet predmet = null;
        Student student = null;
        Integer ocjena = null;
        LocalDateTime datumIVrijeme = null;
        Profesor komisionar1 = null;
        Profesor komisionar2 = null;
        Profesor komisionar3 = null;

        List<KomisijskiIspit> noviKomisijskiIspit = new ArrayList<>();

        try {

            ucitaj = new Scanner(komisijskiIspiti);

            int x = 1;
            while (ucitaj.hasNextLine()) {

                for (int i = x; i < x + 8; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        int idPredmeta = ucitaj.nextInt();
                        predmet = listPredmet.get(idPredmeta - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 2) {
                        int idStudenta = ucitaj.nextInt();
                        student = listStudent.get(idStudenta - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 3) {
                        ocjena = ucitaj.nextInt();
                        ucitaj.nextLine();
                    } else if (i == x + 4) {
                        String datumIVrijemeString = ucitaj.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATUM_VRIJEME);
                        datumIVrijeme = LocalDateTime.parse(datumIVrijemeString, formatter);
                    } else if (i == x + 5) {
                        int idKomisionara1 = ucitaj.nextInt();
                        komisionar1 = listProfesor.get(idKomisionara1 - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 6) {
                        int idKomisionara2 = ucitaj.nextInt();
                        komisionar2 = listProfesor.get(idKomisionara2 - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 7) {
                        int idKomisionara3 = ucitaj.nextInt();
                        komisionar3 = listProfesor.get(idKomisionara3 - 1);
                    }
                }
                x += 8;
                noviKomisijskiIspit.add(new KomisijskiIspit(id, predmet, student, ocjena, datumIVrijeme
                        , komisionar1,komisionar2,komisionar3));
            }

            ucitaj.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return noviKomisijskiIspit;
    }

    private static List<Asistent> dohvatiAsistenta(Scanner ucitaj) {

        Long id = null;
        String sifra = null;
        String ime = null;
        String prezime = null;
        Predmet predmet = null;

        List<Asistent> noviAsistent = new ArrayList<>();

        try {

            ucitaj = new Scanner(asistenti);

            int x = 1;
            while (ucitaj.hasNextLine()) {

                for (int i = x; i < x + 5; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        sifra = ucitaj.nextLine();
                    } else if (i == x + 2) {
                        ime = ucitaj.nextLine();
                    } else if (i == x + 3) {
                        prezime = ucitaj.nextLine();
                    } else if (i == x + 4) {
                        int idPredmeta = ucitaj.nextInt();
                        predmet = listPredmet.get(idPredmeta - 1);
                    }
                }
                x += 5;
                noviAsistent.add(new Asistent(id, sifra, ime, prezime, predmet));
            }

            ucitaj.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return noviAsistent;
    }


    private static List<ObrazovnaUstanova> dohvatiVeleuciliste(Scanner ucitaj) {

        Long id = null;
        String naziv = null;
        Student student1 =null, student2=null;
        Integer ocjenaZavrsnog1 =0, ocjenaZavrsnog2=0;
        Integer ocjenaObraneZavrsnog1=0, ocjenaObraneZavrsnog2=0;

        List<ObrazovnaUstanova> veleucilisteJave = new ArrayList<>();

        try {

            ucitaj = new Scanner(veleucilisteJave1);

            int x = 1;
            while (ucitaj.hasNext()) {

                for (int i = x; i < x + 8; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        naziv = ucitaj.nextLine();
                    } else if (i == x + 2) {
                        int a = ucitaj.nextInt();
                        student1 = listStudent.get(a - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 3) {
                        ocjenaZavrsnog1 = ucitaj.nextInt();
                        ucitaj.nextLine();
                    } else if (i == x + 4) {
                        ocjenaObraneZavrsnog1 = ucitaj.nextInt();
                    } else if (i == x + 5) {
                        int a = ucitaj.nextInt();
                        student2 = listStudent.get(a - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 6) {
                        ocjenaZavrsnog2 = ucitaj.nextInt();
                        ucitaj.nextLine();
                    } else if (i == x + 7) {
                        ocjenaObraneZavrsnog2 = ucitaj.nextInt();
                    }
                }

                x += 8;
                veleucilisteJave
                        .add(new VeleucilisteJave(id, naziv, listPredmet, listProfesor, listStudent, listIspit));
                System.out.println(ocjenaObraneZavrsnog1 + " " + ocjenaZavrsnog2 + " " + ocjenaObraneZavrsnog1 + " "
                        + ocjenaZavrsnog2);

            }

            ucitaj.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        VeleucilisteJave y = new VeleucilisteJave(id, naziv, listPredmet, listProfesor, listStudent, listIspit);
        BigDecimal konacnaOcjena1 = y.izracunajKonacnuOcjenuStudijaZaStudenta(listIspit, student1, ocjenaZavrsnog1,
                ocjenaObraneZavrsnog1);

        System.out.println("Konacna ocjena studija za studenta " + student1.getIme() + " " + student1.getPrezime()
                + " je:" + konacnaOcjena1);
        BigDecimal konacnaOcjena2 = y.izracunajKonacnuOcjenuStudijaZaStudenta(listIspit, student2, ocjenaZavrsnog2,
                ocjenaObraneZavrsnog2);

        System.out.println("Konacna ocjena studija za studenta " + student2.getIme() + " " + student2.getPrezime()
                + " je:" + konacnaOcjena2);

        Student najbolji = null;

        if (konacnaOcjena1.compareTo(konacnaOcjena2) == 1)
            najbolji = student1;
        else
            najbolji = student2;

        System.out.println("Najbolji student je " + najbolji.getIme() + " " + najbolji.getPrezime() + " JMBAG:"
                + najbolji.getJmbag());

        return veleucilisteJave;
    }

    private static List<ObrazovnaUstanova> dohvatiFakultet(Scanner ucitaj) {

        Long id = null;
        String naziv = null;
        Student student1 = null, student2 = null;
        int ocjenaDiplomskog1 = 0, ocjenaDiplomskog2 = 0;
        Integer ocjenaObraneDiplomskog1 = 0, ocjenaObraneDiplomskog2 = 0;

        List<ObrazovnaUstanova> fakultetRacunarstva = new ArrayList<>();

        try {

            ucitaj = new Scanner(fakultetRacunarstva1);

            int x = 1;
            while (ucitaj.hasNext()) {

                for (int i = x; i < x + 8; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        naziv = ucitaj.nextLine();
                    } else if (i == x + 2) {
                        int a = ucitaj.nextInt();
                        student1 =listStudent.get(a - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 3) {
                        ocjenaDiplomskog1 = ucitaj.nextInt();
                        ucitaj.nextLine();
                    } else if (i == x + 4) {
                        ocjenaObraneDiplomskog1 = ucitaj.nextInt();
                    } else if (i == x + 5) {
                        int a = ucitaj.nextInt();
                        student2 = listStudent.get(a - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 6) {
                        ocjenaDiplomskog2 = ucitaj.nextInt();
                        ucitaj.nextLine();
                    } else if (i == x + 7) {
                        ocjenaObraneDiplomskog2 = ucitaj.nextInt();
                    }
                }

                x += 8;
                fakultetRacunarstva
                        .add(new FakultetRacunarstva(id, naziv, listPredmet, listProfesor, listStudent, listIspit));
                System.out.println(ocjenaDiplomskog1 + " " + ocjenaObraneDiplomskog1 + " " + ocjenaDiplomskog2 + " "
                        + ocjenaObraneDiplomskog2);
            }

            ucitaj.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FakultetRacunarstva y = new FakultetRacunarstva(id, naziv, listPredmet, listProfesor, listStudent,
                listIspit);
        BigDecimal konacnaOcjena1 = y.izracunajKonacnuOcjenuStudijaZaStudenta(listIspit, student1, ocjenaDiplomskog1,
                ocjenaObraneDiplomskog1);

        System.out.println("Konacna ocjena studija za studenta " + student1.getIme() + " " + student1.getPrezime()
                + " je:" + konacnaOcjena1);

        BigDecimal konacnaOcjena2 = y.izracunajKonacnuOcjenuStudijaZaStudenta(listIspit, student1, ocjenaDiplomskog2,
                ocjenaObraneDiplomskog2);

        System.out.println("Konacna ocjena studija za studenta " + student2.getIme() + " " + student2.getPrezime()
                + " je:" + konacnaOcjena2);

        Student najbolji = null;

        if (konacnaOcjena1.compareTo(konacnaOcjena2) == 1)
            najbolji = student1;
        else
            najbolji = student2;

        System.out.println("Najbolji student je " + najbolji.getIme() + " " + najbolji.getPrezime() + " JMBAG:"
                + najbolji.getJmbag());

        System.out.println(
                "Student koji je dobio rektorovu nagradu je " + najbolji.getIme() + " " + najbolji.getPrezime());

        return fakultetRacunarstva;
    }

    private static List<Kolokvij> dohvatiKolokvij(Scanner ucitaj) {

        Long id = null;
        Predmet predmet = null;
        Student student = null;
        Profesor profesor = null;
        Integer bodovi = null;
        LocalDateTime datumIVrijeme = null;

        List<Kolokvij> noviKolokvij = new ArrayList<>();

        try {

            ucitaj = new Scanner(kolokviji);

            int x = 1;
            while (ucitaj.hasNextLine()) {

                for (int i = x; i < x + 6; i++) {

                    if (i == x) {
                        id = ucitaj.nextLong();
                        ucitaj.nextLine();
                    } else if (i == x + 1) {
                        int idPredmeta = ucitaj.nextInt();
                        predmet = listPredmet.get(idPredmeta - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 2) {
                        int idStudenta = ucitaj.nextInt();
                        student = listStudent.get(idStudenta - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 3) {
                        int idProfesora = ucitaj.nextInt();
                        profesor = listProfesor.get(idProfesora - 1);
                        ucitaj.nextLine();
                    } else if (i == x + 4) {
                        bodovi = ucitaj.nextInt();
                        ucitaj.nextLine();
                    } else if (i == x + 5) {
                        String datumIVrijemeString = ucitaj.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATUM_VRIJEME);
                        datumIVrijeme = LocalDateTime.parse(datumIVrijemeString, formatter);
                    }
                }
                x += 6;
                noviKolokvij.add(new Kolokvij(id, predmet, student, profesor, bodovi, datumIVrijeme));
            }

            ucitaj.close();


        } catch (FileNotFoundException | NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Pogreska u datoteci");
            System.exit(0);
        }

        return noviKolokvij;
    }
}
