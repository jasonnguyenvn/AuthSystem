package vn.jason.auth.user;

import java.util.Optional;
import java.util.Set;

import vn.jason.auth.permission.Permission;

public interface UserService {
    Optional<User> getCEO();
    Optional<User> createCEO();
    Optional<User> createUser(User manager);
    Set<User> list(long page, long recordsPerPage);
    long ceoID();
    

    Optional<User> get(long id);
    Optional<User> updatePermission(long id, Set<Permission> permissions);
    Set<Permission> getFullPermissions(long id);
}
