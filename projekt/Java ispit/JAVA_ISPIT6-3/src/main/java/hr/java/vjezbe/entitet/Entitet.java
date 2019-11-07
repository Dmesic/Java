package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entitet implements Serializable {

    private static final long serialVersionUID = 19L;

    private Long id;

    public Entitet(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
