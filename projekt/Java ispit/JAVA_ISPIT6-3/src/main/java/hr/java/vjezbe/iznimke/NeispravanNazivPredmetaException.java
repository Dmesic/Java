package hr.java.vjezbe.iznimke;

public class NeispravanNazivPredmetaException extends Exception {

    /**
     * @author Denis
     *
     * Pokrece se kada korisnik upise znakove koji nisu slova i razmak
     */

    private static final long serialVersionUID = 2711724378809456882L;

    public NeispravanNazivPredmetaException() {
    }

    public NeispravanNazivPredmetaException(String message) {
        super(message);
    }

    public NeispravanNazivPredmetaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeispravanNazivPredmetaException(Throwable cause) {
        super(cause);
    }
}
