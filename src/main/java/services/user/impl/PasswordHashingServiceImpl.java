package services.user.impl;

import services.user.PasswordHashingService;

public class PasswordHashingServiceImpl implements PasswordHashingService {
    @Override
    public String hash(String password) {
        return "$"+password+"!"; //changeable
    }
}
