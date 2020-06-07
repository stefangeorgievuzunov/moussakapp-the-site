package services;

import exceptions.InvalidDataException;

public interface UserDataValidationService {
    Boolean isUserDataValid(String username,String password, String rePassword) throws InvalidDataException;
}
