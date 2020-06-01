package services.impl.user;
import db.User;

import org.modelmapper.ModelMapper;
import services.DataManagementService;
import services.PasswordHashingService;
import services.UserActionService;
import services.UserDataValidationService;
import services.impl.db.DataManagementServiceImpl;
import services.models.UserServiceModel;

import javax.inject.Inject;
import javax.persistence.criteria.*;
import java.util.List;

public class UserActionServiceImpl implements UserActionService {
    private final ModelMapper modelMapper;
    private final DataManagementService dataManagementService;
    private final UserDataValidationService userDataValidationService;
    private final PasswordHashingService passwordHashingService;

    @Inject
    public UserActionServiceImpl(ModelMapper modelMapper,DataManagementService dataManagementService, UserDataValidationService userDataValidationService, PasswordHashingService passwordHashingService) {
        this.modelMapper = modelMapper;
        this.dataManagementService = dataManagementService;
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

            dataManagementService.persist(user);
        }
    }

    @Override
    public UserServiceModel login(final String username, String password) throws Exception {

        List<User> users=dataManagementService.select(new DataManagementServiceImpl.Specification<User,User>(User.class,User.class) {
            @Override
            protected Selection<? extends User> select(Root<User> root, CriteriaBuilder builder) {
                return null;
            }

            @Override
            protected Predicate where(Root<User> root, CriteriaBuilder builder) {
                return builder.equal(root.get("username"),username);
            }
        });

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
