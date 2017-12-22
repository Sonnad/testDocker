package spring.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "book")
public class Book {

    private String title;

    private static final long serialVersionUID = 1L;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToMany(targetEntity = AuthorsBooks.class, mappedBy = "id.book")
    private Set<AuthorsBooks> bookSet = new HashSet<>();

    @JsonIgnore
    public Set<AuthorsBooks> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<AuthorsBooks> bookSet) {
        this.bookSet = bookSet;
    }

    @Column(name = "book_title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
