package services.user;

import enums.Gender;
import services.models.UserServiceModel;

public interface UserActionService {
    Boolean register(String username, String password,String rePassword,String firstName, String lastName, Gender gender,Integer age,String description);
    UserServiceModel login(String username,String password);
}
