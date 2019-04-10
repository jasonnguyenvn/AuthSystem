package vn.jason.auth.permission;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Singleton;

@Singleton
public class PermissionRepositoryDefaultImpl implements PermissionRepository {
    private final Set<Permission> dataSet = new HashSet<>();

    @Override
    public synchronized Set<Permission> getAll() {
        return this.getDataSetStream()
                .collect(Collectors.toSet());
    }

    @Override
    public synchronized Optional<Permission> getByName(String name) {
        return this.getDataSetStream()
                .filter(permission -> permission.name().equalsIgnoreCase(name))
                .findAny();
    }

    @Override
    public synchronized Optional<Permission> add(String name) {
        if (this.getByName(name).isPresent()) {
            throw new IllegalArgumentException("Duplicated name!");
        }
        Permission permission = Permission.builder()
                                .name(name)
                                .build();
        this.dataSet.add(permission);
        return Optional.ofNullable(permission);
    }

    @Override
    public synchronized Set<Permission> find(String name) {
        return this.getDataSetStream()
                .filter(permission -> StringUtils.containsIgnoreCase(permission.name(), name))
                .collect(Collectors.toSet());
    }

    private Stream<Permission> getDataSetStream() {
        return this.dataSet
                .stream();
    }

}
