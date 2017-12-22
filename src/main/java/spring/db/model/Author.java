package spring.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;



@Entity
@Table(name = "author")
public class Author {

    private String fullName;

    public Author() {
    }

    public Author(String fullName) {
        this.fullName = fullName;

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

    @OneToMany(targetEntity = AuthorsBooks.class, mappedBy = "id.author")
    private Set<AuthorsBooks> authorSet = new HashSet<>();

    @JsonIgnore
    public Set<AuthorsBooks> getBookSet() {
        return authorSet;
    }

    public void setBookSet(Set<AuthorsBooks> authorSet) {
        this.authorSet = authorSet;
    }

    @Column(name = "author_full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }




}
