package spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.db.dao.AuthorRepository;

/**
 * Created by Sonad on 05.12.17.
 */
@Controller
public class AuthorRestService {

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/author/{name}", method = RequestMethod.GET)
    public ModelAndView books(@PathVariable("name") String name) {
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books", authorRepository.findByFullName(name).getBookSet());
        mav.addObject("author", name.toUpperCase().charAt(0) + name.substring(1));
        return mav;
    }

}
