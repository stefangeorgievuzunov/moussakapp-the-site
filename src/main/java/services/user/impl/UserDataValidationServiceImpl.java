package services.user.impl;

import db.User;
import services.user.UserDataValidationService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserDataValidationServiceImpl implements UserDataValidationService {
    private final EntityManager entityManager;

    @Inject
    public UserDataValidationServiceImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public Boolean isUserDataValid(String username, String password, String rePassword) {
        return validatePasswordFormat(password) && isPasswordConfirmed(password,rePassword) && isUsernameFree(username);
    }

    private Boolean validatePasswordFormat(String password){
        return  password !=null && !password.isEmpty() && password.length()>=8;
    }

    private Boolean isPasswordConfirmed(String password,String rePassword){
        return password.equals(rePassword);
    }

    private Boolean isUsernameFree(String username){
        List<User> users=entityManager.createQuery(
                "select u from User u where u.username=:username",User.class)
                .setParameter("username",username)
                .getResultList();

        return  users.isEmpty();
    }
}
