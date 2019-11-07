package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Denis
 *
 * Racuna konacnu ocjenu studenta i odreduje najuspjesnijeg studenta na veleucilistu
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {

    private static final Logger log = LoggerFactory.getLogger(VeleucilisteJave.class);

    public VeleucilisteJave(Long id, String naziv, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
        super(id, naziv, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Student studenti, int ocjenaPismenogDijelaZR, int ocjenaObraneZR) {
        BigDecimal aBd =null;

        try {
            aBd = (new BigDecimal(2).multiply(odrediProsjekOcjenaNaIspitima(ispiti)))
                    .add(new BigDecimal(ocjenaPismenogDijelaZR + ocjenaObraneZR));
        }catch (NemoguceOdreditiProsjekStudenataException e){
            System.out.println("Student " + studenti.getIme() + studenti.getPrezime() +
                    " nije dobio prolaznu ocjenu iz svakog predmeta, zbog toga ima prosjek 1");
            log.info("student nije dobio prolaznu ocjenu");
        }
        BigDecimal bBd = new BigDecimal(4);
        BigDecimal konacnaOcjena = aBd.divide(bBd);
//        BigDecimal konacnaOcjena = new BigDecimal("4");

        return konacnaOcjena;
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {

        List<Student> studenti = getStudenti();
        List<Ispit>  ispiti = getIspiti();
        Student najboljiStudent = null;
        BigDecimal prosjek = new BigDecimal(0);
        BigDecimal najboljiProsjek = new BigDecimal(0);


        for (int i = 0; i < studenti.size(); i++) {

            List <Ispit> filtriranIspit = filtrirajIspitePoStudentu(ispiti, studenti.get(i));
            try {
                prosjek = odrediProsjekOcjenaNaIspitima(filtriranIspit);
            } catch (NemoguceOdreditiProsjekStudenataException e) {
                System.out.println("Student " + studenti.get(i).getIme() + studenti.get(i).getPrezime() +
                        " nije dobio prolaznu ocjenu iz svakog predmeta, zbog toga ima prosjek 1");
                log.info("student nije dobio prolaznu ocjenu");

            }


            if (((prosjek.compareTo(najboljiProsjek)) == 1)
                    || (prosjek.compareTo(najboljiProsjek) == 0)) {
                najboljiStudent = studenti.get(i);
            }

        }

        return najboljiStudent;

    }
}
