package vn.jason.auth.role;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    Set<Role> getAll();
    Optional<Role> get(String name);

}
