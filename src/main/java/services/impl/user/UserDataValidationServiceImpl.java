package services.impl.user;

import db.User;
import services.UserDataValidationService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserDataValidationServiceImpl implements UserDataValidationService {
    private final EntityManager entityManager;

    @Inject
    public UserDataValidationServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Boolean isUserDataValid(String username, String password, String rePassword) throws Exception {
        return validatePasswordFormat(password) && isPasswordConfirmed(password, rePassword) && isUsernameFree(username);
    }

    private Boolean validatePasswordFormat(String password) throws Exception {
        if (password.length() < 8) {
            throw new Exception("Password is too short. Minimum size is 8 symbols.");
        }
        return true;
    }

    private Boolean isPasswordConfirmed(String password, String rePassword) throws Exception {
        if (!password.equals(rePassword)) {
            throw new Exception("Passwords mismatch.");
        }
        return true;
    }

    private Boolean isUsernameFree(String username) throws Exception {
        List<User> users = entityManager.createQuery(
                "select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getResultList();

        if (!users.isEmpty()) {
            throw new Exception("Username is busy ! Please pick another one.");
        }
        return true;
    }
}
