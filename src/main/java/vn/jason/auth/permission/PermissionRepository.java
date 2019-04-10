package vn.jason.auth.permission;

import java.util.Optional;
import java.util.Set;

public interface PermissionRepository {
    Set<Permission> getAll();
    Optional<Permission> getByName(String name);
    Optional<Permission> add(String name);
    Set<Permission> find(String name);
}
