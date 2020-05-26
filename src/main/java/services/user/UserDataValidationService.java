package services.user;

public interface UserDataValidationService {
    Boolean isUserDataValid(String username,String password, String rePassword);
}
