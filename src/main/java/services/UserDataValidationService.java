package services;

public interface UserDataValidationService {
    Boolean isUserDataValid(String username,String password, String rePassword) throws Exception;
}
