package services;

import enums.Gender;
import exceptions.InvalidDataException;
import services.models.UserServiceModel;

public interface UserActionService {
    void register(String username, String password,String firstName, String lastName) throws InvalidDataException;
    UserServiceModel login(String username,String password) throws InvalidDataException;
}
