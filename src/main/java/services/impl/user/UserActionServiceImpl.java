package services.impl.user;

import db.User;

import exceptions.InvalidDataException;
import org.modelmapper.ModelMapper;
import services.DbService;
import services.PasswordHashingService;
import services.UserActionService;
import services.UserDataValidationService;
import services.impl.db.DbServiceImpl;
import services.models.UserServiceModel;

import javax.inject.Inject;
import javax.persistence.criteria.*;
import java.util.List;

public class UserActionServiceImpl implements UserActionService {
    private final ModelMapper modelMapper;
    private final DbService dbService;
    private final UserDataValidationService userDataValidationService;
    private final PasswordHashingService passwordHashingService;

    @Inject
    public UserActionServiceImpl(ModelMapper modelMapper, DbService dbService, UserDataValidationService userDataValidationService, PasswordHashingService passwordHashingService) {
        this.modelMapper = modelMapper;
        this.dbService = dbService;
        this.userDataValidationService = userDataValidationService;
        this.passwordHashingService = passwordHashingService;
    }

    @Override
    public void register(String username, String password, String firstName, String lastName) throws InvalidDataException {
        if (userDataValidationService.isUserDataValid(username)) {

            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordHashingService.hash(password));
            user.setFirstName(firstName);
            user.setLastName(lastName);

            dbService.persist(user);
        }
    }

    @Override
    public UserServiceModel login(final String username, String password) throws InvalidDataException {

        List<User> users = dbService.createQuery(new DbServiceImpl.Query<User, User>(User.class, User.class) {
            @Override
            protected Selection<? extends User> select() {
                return null;
            }

            @Override
            protected Predicate where() {
                return builder().equal(root().get("username"), username);
            }
        });

        if (!users.isEmpty()) {
            User user = users.get(0);

            if (!user.getPassword().equals(passwordHashingService.hash(password))) {
                throw new InvalidDataException("Паролата е грешна, моля опитайте отново!");
            }
            return modelMapper.map(user, UserServiceModel.class);
        } else {
            throw new InvalidDataException("Потребител с такова име не съществува, моля опитайте отново!");
        }
    }
}
