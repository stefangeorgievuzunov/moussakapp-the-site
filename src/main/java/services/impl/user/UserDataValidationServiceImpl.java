package services.impl.user;

import db.User;
import exceptions.InvalidDataException;
import services.DataManagementService;
import services.UserDataValidationService;
import services.impl.db.DataManagementServiceImpl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class UserDataValidationServiceImpl implements UserDataValidationService {
    private final DataManagementService dataManagementService;

    @Inject
    public UserDataValidationServiceImpl(DataManagementService dataManagementService) {
        this.dataManagementService = dataManagementService;
    }

    @Override
    public Boolean isUserDataValid(String username) throws InvalidDataException {
        return isUsernameFree(username);
    }

    private Boolean isUsernameFree(final String username) throws InvalidDataException{

        List<User> users=dataManagementService.select(new DataManagementServiceImpl.Specification<User, User>(User.class,User.class) {
            @Override
            protected Selection<? extends User> select(Root<User> root, CriteriaBuilder builder) {
                return root;
            }

            @Override
            protected Predicate where(Root<User> root, CriteriaBuilder builder) {
                return builder.equal(root.get("username"),username);
            }
        });

        if (!users.isEmpty()) {
            throw new InvalidDataException("Потребителското име е заето, моля посочете друго");
        }
        return true;
    }
}
