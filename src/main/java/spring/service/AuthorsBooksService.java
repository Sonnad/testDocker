package spring.service;


import spring.db.model.AuthorsBooks;

public interface AuthorsBooksService {

    Iterable<AuthorsBooks> listAll();

    boolean delete(String title);

    AuthorsBooks add(String author, String name, String publisher);

}
