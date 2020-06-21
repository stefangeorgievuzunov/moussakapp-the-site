package services;

import exceptions.InvalidDataException;

public interface UserDataValidationService {
    Boolean isUserDataValid(String username) throws InvalidDataException;
}
