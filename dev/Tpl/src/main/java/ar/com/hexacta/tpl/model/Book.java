package ar.com.hexacta.tpl.model;

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
    public Book() {
        super();
    }

    public Book(final String name) {
        super();
        this.name = name;
    }

    public Book(final String aName, final String aDescription, final String aPublisher,
            final Set<BookCategory> bookCategories, final Set<BookCopy> bookCopies, final Set<Comment> bookComments) {
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

}
