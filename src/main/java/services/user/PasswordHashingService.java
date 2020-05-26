package services.user;

public interface PasswordHashingService {
    String hash(String password);
}
