package services.impl;

import services.PasswordHashingService;

import javax.annotation.ManagedBean;
import javax.enterprise.inject.Default;

public class PasswordHashingServiceImpl implements PasswordHashingService {
    @Override
    public String hash(String password) {
        return "$"+password+"!"; //changeable
    }
}
