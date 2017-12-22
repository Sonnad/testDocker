package spring.db.model;

import javax.persistence.EmbeddedId;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sonad on 29.11.17.
 */

@Entity
@Table(name = "book_author")
public class AuthorsBooks implements Serializable{

    public AuthorsBooks() {
    }

    public AuthorsBooks(UserPK id, Publisher publisher) {
        this.publisher = publisher;
        this.id = id;
    }

    @EmbeddedId
    private UserPK id;

    public UserPK getId() {
        return id;
    }

    @Embeddable
    public static class UserPK implements Serializable {

        public UserPK() {
        }

        public UserPK(Author author, Book book) {
            this.author = author;
            this.book = book;
        }

        @ManyToOne(optional = false)
        @JoinColumn
        private Author author;

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }


        @ManyToOne(optional = false)
        @JoinColumn
        private Book book;

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

    }

    @ManyToOne(optional = false)
    @JoinColumn
    private Publisher publisher;

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
