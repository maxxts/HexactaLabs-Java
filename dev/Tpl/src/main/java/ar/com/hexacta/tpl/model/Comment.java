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
public class Comment implements Serializable {

    private static final long serialVersionUID = 537917183637872456L;

<<<<<<< HEAD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(name = "USER")
=======
    private Book book;

>>>>>>> 7f77aeac5692d449136e4c018972f188833f0bc8
    private String user;

    @Column(name = "BODY")
    private String body;

<<<<<<< HEAD
    @Column(name = "BOOK")
    private String book;

=======
>>>>>>> 7f77aeac5692d449136e4c018972f188833f0bc8
    // Hibernate needs
    public Comment() {
        super();
    }

    public Comment(final Book book, final String user, final String body) {
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

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
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
