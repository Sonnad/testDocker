package spring.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.db.dao.AuthorRepository;
import spring.db.dao.AuthorsBooksRepository;
import spring.db.dao.BookRepository;
import spring.db.dao.PublisherRepository;
import spring.db.model.Author;
import spring.db.model.AuthorsBooks;
import spring.db.model.Book;
import spring.db.model.Publisher;

import java.util.Set;

@Service
public class AuthorsBooksServiceImpl implements AuthorsBooksService {

    private final static Logger log = Logger.getLogger(AuthorsBooksServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorsBooksRepository authorsBooksRepository;




    @Override
    public Iterable<AuthorsBooks> listAll() {
        return authorsBooksRepository.findAll();
    }

    @Override
    public boolean delete(String title) {
        Book book = bookRepository.findByTitle(title);
        if (book == null ) return false;
        Set<AuthorsBooks> bookSet = book.getBookSet();
        if (bookSet.size() == 0) return false;
        for (AuthorsBooks books: bookSet) {
            authorsBooksRepository.delete(books.getId());
        }
        return true;
    }

    @Override
    public AuthorsBooks add(String author, String name, String publisher) {
        Author bookAuthor = authorRepository.findByFullName(author);
        Book bookName = bookRepository.findByTitle(name);
        Publisher bookPublisher = publisherRepository.findByName(publisher);
        if (authorsBooksRepository.exists(new AuthorsBooks.UserPK(bookAuthor, bookName)))
            return null;
        AuthorsBooks newBook = new AuthorsBooks(new AuthorsBooks.UserPK(bookAuthor, bookName), bookPublisher);
        return authorsBooksRepository.save(newBook);
    }



}
