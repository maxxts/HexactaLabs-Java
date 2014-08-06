/**
 *
 */
package ar.com.hexacta.tpl.model;

public class Comment extends Entity {

    private static final long serialVersionUID = 537917183637872456L;

    private String user;

    private String body;

    private String book;

    // Hibernate needs
    public Comment() {
        super();
    }

    public Comment(final String book, final String user, final String body) {
        super();
        this.book = book;
        this.user = user;
        this.body = body;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getBook() {
        return book;
    }

    public void setBook(final String book) {
        this.book = book;
    }

}
