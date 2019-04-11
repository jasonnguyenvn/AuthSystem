package vn.jason.auth.user;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    Set<User> getAll(long page, long recordsPerPage);
    Optional<User> get(long id);
    Optional<User> add(User user);
    Optional<User> update(User data);
}
