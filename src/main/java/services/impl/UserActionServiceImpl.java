package services.impl;
import db.User;
import enums.Gender;
import org.modelmapper.ModelMapper;
import services.PasswordHashingService;
import services.UserActionService;
import services.UserDataValidationService;
import services.models.UserServiceModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserActionServiceImpl implements UserActionService {
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;
    private final UserDataValidationService userDataValidationService;
    private final PasswordHashingService passwordHashingService;

    @Inject
    public UserActionServiceImpl(ModelMapper modelMapper, EntityManager entityManager, UserDataValidationService userDataValidationService, PasswordHashingService passwordHashingService) {
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
        this.userDataValidationService = userDataValidationService;
        this.passwordHashingService = passwordHashingService;
    }

    @Override
    public Boolean register(String username, String password, String rePassword, String firstName, String lastName, Gender gender, Integer age, String description) {
        if (userDataValidationService.isUserDataValid(username, password, rePassword)) {

            User user=new User();
            user.setUsername(username);
            user.setPassword(passwordHashingService.hash(password));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            user.setGender(gender);
            user.setDescription(description);

            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();

            return true;
        }
        return false;
    }

    @Override
    public UserServiceModel login(String username, String password) {
        List<User> users=entityManager.createQuery(
                "select u from User u where u.username=:username",User.class)
                .setParameter("username",username)
                .getResultList();

        if(!users.isEmpty()){
            User user=users.get(0);

            if(user.getPassword().equals(passwordHashingService.hash(password))){
                return modelMapper.map(user,UserServiceModel.class);
            }
        }
        return null;
    }
}
