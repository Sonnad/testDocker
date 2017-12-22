package spring.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.db.model.AuthorsBooks;
import spring.service.AuthorsBooksService;
import spring.service.LoginService;

import java.security.Principal;

/**
 * Created by Sonad on 19.09.17.
 */
@RestController
@RequestMapping("/rest/sample")
public class SampleRestService {

    @Autowired
    LoginService loginService;

    @Autowired
    AuthorsBooksService authorsBooksService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> loadPage() {
        return ResponseEntity.ok(authorsBooksService.listAll());

    }

    @RequestMapping(value= "/add/{bookName}/{bookAuthor}/{bookPublisher}",method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(@PathVariable("bookName") String name,
                                          @PathVariable("bookAuthor") String author,
                                          @PathVariable("bookPublisher") String publisher,
                                          @AuthenticationPrincipal Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\" : \"Unauthorized\"}");
        }

        if (name.equals("undefined") || author.equals("undefined") || publisher.equals("undefined"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\" : \"Invalid name, author or publisher\"}");
        AuthorsBooks newBook = authorsBooksService.add(author, name, publisher);
        if (newBook == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\" : \"Duplicate pair Author-Book\"}");

        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @RequestMapping(value = "/delete/{bookName}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("bookName") String bookName, @AuthenticationPrincipal Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\" : \"Unauthorized\"}");
        }

        if (bookName.equals("undefined"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\" : \"Invalid name\"}");
        if (authorsBooksService.delete(bookName)) {
            return ResponseEntity.ok("");
        }
        else
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\" : \"Book not found\"}");
    }

    @RequestMapping(value = "/signup/{username}/{password}", method = RequestMethod.POST)
    public ResponseEntity<Object> signUp(@PathVariable("username") String username, @PathVariable("password") String password) {

        if (username.equals("undefined") || password.equals("undefined"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\" : \"Invalid username or password\"}");
        if (loginService.saveUser(username, password)) return ResponseEntity.ok("{\"message\" : \"Success\"}");
        else return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\" : \"Username already in use\"}");
    }

}
