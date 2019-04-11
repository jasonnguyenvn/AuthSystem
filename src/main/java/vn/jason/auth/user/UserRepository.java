package vn.jason.auth.user;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    Set<User> getAll(int page, int recordsPerPage);
    Optional<User> get(long id);
    Optional<User> add(User user);
}
