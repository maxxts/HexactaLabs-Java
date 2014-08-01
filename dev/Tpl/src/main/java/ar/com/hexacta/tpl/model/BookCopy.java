/**
 *
 */
package ar.com.hexacta.tpl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class BookCopy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    public static final String BOOK_RATE_BAD = "Bad";

    public static final String BOOK_RATE_NORMAL = "Normal";

    public static final String BOOK_RATE_GOOD = "Good";

    public static final String BOOK_RATE_VERY_GOOD = "Very good";

    public static final String STATE_LOANED = "Loaned";

    public static final String STATE_FREE = "Free";

    private static final long serialVersionUID = 1L;

    @Column(name = "CODE")
    private String code = "";

    @Column(name = "BOOK_RATE")
    private String bookRate;

    @Column(name = "STATE")
    private String state;

    public BookCopy() {

    }

    public BookCopy(final String code, final String bookRate, final String state) {
        super();
        this.code = code;
        this.bookRate = bookRate;
        this.state = state;
    }

    public void changeToFree() {
        state = STATE_FREE;
    }

    public void changeToLoaned() {
        state = STATE_LOANED;
    }

    public String getBookRate() {
        return bookRate;
    }

    public String getCode() {
        return code;
    }

    public String getState() {
        return state;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }
}
