package ar.com.hexacta.tpl.model;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 604529088687479075L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "COUNTRY")
    private String country;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "BOOK_CATEGORY")
    private Set<BookCategory> bookCategories;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "BOOK_COPY")
    private Set<BookCopy> bookCopies;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "BOOK_COMMENT")
    private List<Comment> bookComments;

    // Hibernate needs
=======
import java.util.Set;

public class Book extends Entity {

    private static final long serialVersionUID = 604529088687479075L;

    private String name;

    private String description;

    private String publisher;

    private String country;

    private Set<BookCategory> bookCategories;

    private Set<BookCopy> bookCopies;

    private Set<Comment> bookComments;

    // Hibernate needs this
>>>>>>> 7f77aeac5692d449136e4c018972f188833f0bc8
    public Book() {
        super();
    }

    public Book(final String name) {
        super();
        this.name = name;
    }

    public Book(final String aName, final String aDescription, final String aPublisher,
<<<<<<< HEAD
            final Set<BookCategory> bookCategories, final Set<BookCopy> bookCopies, final List<Comment> bookComments) {
=======
            final Set<BookCategory> bookCategories, final Set<BookCopy> bookCopies, final Set<Comment> bookComments) {
>>>>>>> 7f77aeac5692d449136e4c018972f188833f0bc8
        super();
        name = aName;
        description = aDescription;
        publisher = aPublisher;
        this.bookCategories = bookCategories;
        this.bookCopies = bookCopies;
        this.bookComments = bookComments;

    }

    public Set<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public String getDescription() {
        return description;
    }

    public BookCopy getFreeBookCopy() {
        for (BookCopy bookCopy : bookCopies) {
            if (bookCopy.getState().equals(BookCopy.STATE_FREE))
                return bookCopy;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setBookCategories(final Set<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

<<<<<<< HEAD
    public List<Comment> getBookComments() {
        return bookComments;
    }

    public void setBookComments(final List<Comment> bookComments) {
        this.bookComments = bookComments;
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
=======
    public Set<Comment> getBookComments() {
        return bookComments;
    }

    public void setBookComments(final Set<Comment> bookComments) {
        this.bookComments = bookComments;
    }

    public void addBookComment(final Comment aComment) {
        aComment.setBook(this);
        bookComments.add(aComment);
    }

>>>>>>> 7f77aeac5692d449136e4c018972f188833f0bc8
}
