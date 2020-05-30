package services.impl.user;
import db.User;

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
    public void register(String username, String password, String rePassword, String firstName, String lastName) throws Exception {
        if (userDataValidationService.isUserDataValid(username, password, rePassword)) {

            User user=new User();
            user.setUsername(username);
            user.setPassword(passwordHashingService.hash(password));
            user.setFirstName(firstName);
            user.setLastName(lastName);

            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public UserServiceModel login(String username, String password) throws Exception {
        List<User> users=entityManager.createQuery(
                "select u from User u where u.username=:username",User.class)
                .setParameter("username",username)
                .getResultList();

        if(!users.isEmpty()){
            User user=users.get(0);

            if(!user.getPassword().equals(passwordHashingService.hash(password))){
                throw new Exception("Wrong password. Please try again.");
            }
            return modelMapper.map(user,UserServiceModel.class);
        }else{
            throw new Exception("User with such username doesn't exist. Please try again.");
        }
    }
}
