package vn.jason.auth.user;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import vn.jason.auth.permission.Permission;

@Singleton
public class UserServiceDefaultImpl implements UserService {
    private final long CEO_ID = 0;
    
    private static long currentId = 1;
    
    private UserRepository repository;
    
    @Inject
    public UserServiceDefaultImpl(UserRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public synchronized Optional<User> getCEO() {
        return this.repository.get(CEO_ID);
    }

    @Override
    public synchronized Optional<User> createCEO() {
        User ceo = User.builder()
                        .id(CEO_ID)
                        .build();
        
        return this.repository.add(ceo);
    }

    @Override
    public synchronized Optional<User> createUser(User manager) {
        User user = User.builder()
                        .id(currentId++)
                        .manager(manager)
                        .build();
        return this.repository.add(user);
    }

    @Override
    public Set<User> list(long page, long recordsPerPage) {
        return this.repository.getAll(page, recordsPerPage);
    }

    @Override
    public final long ceoID() {
        return CEO_ID;
    }

    @Override
    public Optional<User> get(long id) {
        return this.repository.get(id);
    }

    @Override
    public Optional<User> updatePermission(long id, Set<Permission> permissions) {
        return this.repository.get(id)
                .map(user -> {
                    user.permissions().clear();
                    user.permissions().addAll(permissions);
                    return user;
                })
                .flatMap(this.repository::update);
    }

    @Override
    public Set<Permission> getFullPermissions(long id) {
        Optional<User> user = this.get(id);
        if (!user.isPresent()) {
            throw new IllegalArgumentException();
        }
        
        Set<Permission> result = new HashSet<>();
        
        User data = user.get();
        result.addAll(data.permissions());
        for (User nextLineUser : data.nextLineStaffs()) {
            result.addAll(this.getFullPermissions(nextLineUser.id()));
        }
        
        return result;
    }


}
