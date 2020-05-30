package services;

import enums.Gender;
import services.models.UserServiceModel;

public interface UserActionService {
    void register(String username, String password,String rePassword,String firstName, String lastName) throws Exception;
    UserServiceModel login(String username,String password) throws Exception;
}
