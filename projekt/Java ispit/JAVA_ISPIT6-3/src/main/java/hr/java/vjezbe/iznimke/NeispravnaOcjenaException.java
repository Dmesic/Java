package hr.java.vjezbe.iznimke;

public class NeispravnaOcjenaException extends RuntimeException {

    /**
     * @author Denis
     *
     * Pokrece se kada korisnik upise ocjene manje od 1, i vece od 5
     */
    private static final long serialVersionUID = 2711724378809456882L;

    public NeispravnaOcjenaException() {
    }

    public NeispravnaOcjenaException(String message) {
        super(message);
    }

    public NeispravnaOcjenaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeispravnaOcjenaException(Throwable cause) {
        super(cause);
    }
}
