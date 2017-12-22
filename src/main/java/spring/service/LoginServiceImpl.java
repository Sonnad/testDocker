package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.db.dao.UserRepository;
import spring.db.model.User;

/**
 * Created by Sonad on 05.10.17.
 */

@Service
public class LoginServiceImpl implements LoginService {

    private static BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User login = userRepository.findByLogin(string);
        if (login == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return new MyLogin(login);
    }

    @Override
    public boolean saveUser(String username, String password) {

        if (userRepository.findByLogin(username) != null) return false;

        userRepository.save(new User(username, bcryptEncoder.encode(password)));
        return true;
    }

}
