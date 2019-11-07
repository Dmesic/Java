package hr.java.vjezbe.iznimke;

import hr.java.vjezbe.entitet.Student;

public class NemoguceOdreditiProsjekStudenataException extends Exception {
    //mora se obraditi
    /**
     * @author Denis
     *
     * Pokrece se kada korisnik upise ocjene koje vracaju prosjek manji od 1
     * , te nasljeduje svojstva oznacenih metoda
     */
    private static final long serialVersionUID = 2711724378809456882L;

    /**
     * Kreira konstruktora bez ulaznih parametara
     */


    public NemoguceOdreditiProsjekStudenataException(Student student) {
        super("Student" + student.getIme() + " " + student.getPrezime() + " ima ocjenu ispita 'nedovoljan (1)'" );
    }

    /**
     * Kreira konstruktor koji inicijalizira poruku
     * @param message
     */
    public NemoguceOdreditiProsjekStudenataException(String message) {
        super(message);
    }

    /**
     * Kreira konstruktor koji inicijalizira poruku i uzrok
     * @param message
     * @param cause
     */
    public NemoguceOdreditiProsjekStudenataException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Kreira konstruktor koji inicijalizira uzrok
     * @param cause
     */
    public NemoguceOdreditiProsjekStudenataException(Throwable cause) {

        super(cause);
    }

}
