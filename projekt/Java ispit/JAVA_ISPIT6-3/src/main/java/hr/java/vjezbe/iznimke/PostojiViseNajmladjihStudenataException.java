package hr.java.vjezbe.iznimke;

public class PostojiViseNajmladjihStudenataException extends RuntimeException {
// ne mora se obraditi

    /**
     * @author Denis
     *
     * Pokrece se kada korisnik upise vise studenata koji imaju najbolji prosjek
     * , te istu godinu rodenja. Ta izimka nasljeduje svojstva ne oznacenih metoda
     */

    private static final long serialVersionUID = 2711724378809456882L;

    /**
     * Kreira konstruktora bez ulaznih parametara
     */
    public PostojiViseNajmladjihStudenataException() {
    }

    /**
     * Kreira konstruktor koji inicijalizira poruku
     * @param message
     */
    public PostojiViseNajmladjihStudenataException(String message) {
        super(message);
    }

    /**
     * Kreira konstruktor koji inicijalizira poruku i uzrok
     * @param message
     * @param cause
     */
    public PostojiViseNajmladjihStudenataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Kreira konstruktor koji inicijalizira uzrok
     * @param cause
     */
    public PostojiViseNajmladjihStudenataException(Throwable cause) {
        super(cause);
    }
}
