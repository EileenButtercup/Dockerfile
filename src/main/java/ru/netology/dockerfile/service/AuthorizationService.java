package ru.netology.dockerfile.service;

import org.springframework.stereotype.Service;
import ru.netology.dockerfile.exception.InvalidCredentials;
import ru.netology.dockerfile.exception.UnauthorizedUser;
import ru.netology.dockerfile.model.Authorities;
import ru.netology.dockerfile.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {

    UserRepository userRepository;

    private AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("USER NAME OR PASSWORD IS EMPTY");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("UNKNOWN USER " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}