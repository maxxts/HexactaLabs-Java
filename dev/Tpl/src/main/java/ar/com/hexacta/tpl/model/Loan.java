/**
 * 
 */
package ar.com.hexacta.tpl.model;

import java.util.Date;

/**
 * @author clopez
 * 
 */
public class Loan extends Entity {

    private static final long serialVersionUID = 1L;

    private String user;

    private BookCopy book;

    private Date fromDate;

    private Date toDate;

    protected Loan() {
        super();
    }

    public Loan(final String user, final BookCopy book, final Date fromDate, final Date toDate) {
        super();
        this.user = user;
        this.book = book;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getUser() {
        return user;
    }

    public BookCopy getBook() {
        return book;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

}
