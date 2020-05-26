package services.impl.user;

import services.PasswordHashingService;

public class PasswordHashingServiceImpl implements PasswordHashingService {
    @Override
    public String hash(String password) {
        return "$"+password+"!"; //changeable
    }
}
