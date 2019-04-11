package vn.jason.auth.user;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    Optional<User> getCEO();
    Optional<User> createCEO();
    Optional<User> createUser(User manager);
    Set<User> list(long page, long recordsPerPage);
    long ceoID();
}
