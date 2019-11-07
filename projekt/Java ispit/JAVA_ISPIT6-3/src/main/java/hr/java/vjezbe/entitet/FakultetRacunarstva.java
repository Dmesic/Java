package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenataException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Denis
 * <p>
 * Sadrzava metode za izracunavanje konacne ocjene, odredivanje najuspjesnijih studenata,
 * te odreduje studenta za rektorovu nagradu na fakultetu
 */

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

    private static final Logger log = LoggerFactory.getLogger(FakultetRacunarstva.class);

    public FakultetRacunarstva(Long id, String naziv, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
        super(id, naziv, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Student studenti,
                                                              int ocjenaPismenogDijelaDR, int ocjenaObraneDR) {

        BigDecimal aBd = null;

        try {
            aBd = (new BigDecimal(3).multiply(odrediProsjekOcjenaNaIspitima(ispiti)))
                    .add(new BigDecimal(ocjenaPismenogDijelaDR + ocjenaObraneDR));
        } catch (NemoguceOdreditiProsjekStudenataException e) {
            System.out.println("Student " + studenti.getIme() + studenti.getPrezime() +
                    " nije dobio prolaznu ocjenu iz svakog predmeta, zbog toga ima prosjek 1");
            log.info(" nije dobio prolaznu ocjenu iz svakog predmeta, zbog toga ima prosjek 1");
        }


        BigDecimal bBd = new BigDecimal(5);
        BigDecimal konacnaOcjena = aBd.divide(bBd);

        return konacnaOcjena;
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {

        List<Student> studenti = getStudenti();
        List<Ispit> ispiti = getIspiti();
        int[] ocjene = new int[studenti.size()];

        int izvrstan = 5;
        int index = 0;
        int max = 0;


        for (int i = 0; i < ispiti.size(); i++) {

            if (godina == ispiti.get(i).getDatumIVrijeme().getYear()) {
                if (ispiti.get(i).getOcjena().equals(izvrstan)) {
                    for (int j = 0; j < studenti.size(); j++) {

                        ocjene[j] = 0;
                        if (ispiti.get(j).getStudent().equals(studenti.get(j))) {
                            ocjene[j]++;
                        }
                    }
                }
            }

        }
        for (int i = 0; i < ocjene.length; i++) {
            if (ocjene[i] > max) {
                max = ocjene[i];
                index = i;
            }
        }

        Student najboljiStudent = studenti.get(index);
        return najboljiStudent;

    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() {

        List<Student> studenti = getStudenti();
        List<Ispit> ispiti = getIspiti();
        List<Student> studentiSaNajboljimProsjekom = new ArrayList<>(2);
        Student nagrada = null;
        BigDecimal prosjek = new BigDecimal(0);
        BigDecimal prosjekOcjena = null;

        for (int i = 0; i < studenti.size(); i++) {

            studentiSaNajboljimProsjekom = null;

            List<Ispit> poljeIspitFiltrirano = filtrirajIspitePoStudentu(ispiti, studenti.get(i));
            try {
                prosjekOcjena = odrediProsjekOcjenaNaIspitima(poljeIspitFiltrirano);
            } catch (NemoguceOdreditiProsjekStudenataException e) {
                System.out.println("Student " + studenti.get(i).getIme() + studenti.get(i).getPrezime() +
                        " nije dobio prolaznu ocjenu iz svakog predmeta, zbog toga ima prosjek 1");
                e.getMessage();
            }


            if (prosjekOcjena.compareTo(prosjek) == 1
                    || prosjekOcjena.compareTo(prosjek) == 0) {
                prosjek = prosjekOcjena;
                studentiSaNajboljimProsjekom = studenti;
                nagrada = studentiSaNajboljimProsjekom.get(i);
            }
        }

        try {
            for(int n=0; n < studentiSaNajboljimProsjekom.size(); n++) {
                if(studentiSaNajboljimProsjekom.get(n).getDatumRodjenja().equals(nagrada.getDatumRodjenja())
                        && !nagrada.getJmbag().equals(studentiSaNajboljimProsjekom.get(n).getJmbag()))
                    System.out.println("Najmladi studenti su: " + nagrada.getIme() + " "
                            + nagrada.getPrezime() + " i " + studentiSaNajboljimProsjekom.get(n).getIme() + " "
                            + studentiSaNajboljimProsjekom.get(n).getPrezime()
                            + " rodeni " + nagrada.getDatumRodjenja());
            }
        } catch (PostojiViseNajmladjihStudenataException e){
            log.info("Nema vise studenata koji su rodeni isti dan");
        }
        return nagrada;

    }

}
