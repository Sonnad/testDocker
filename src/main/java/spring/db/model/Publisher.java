package spring.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Sonad on 29.11.17.
 */

@Entity
@Table(name = "publisher")
public class Publisher {

    private Integer id;
    private String name;

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher() {
    }

    private Set<AuthorsBooks> booksList;
    @OneToMany(targetEntity = AuthorsBooks.class, mappedBy = "publisher")

    @JsonIgnore
    public Set<AuthorsBooks> getBooks() {
        return booksList;
    }

    public void setBooks(Set<AuthorsBooks> booksList) {
        this.booksList = booksList;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "publisher_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
