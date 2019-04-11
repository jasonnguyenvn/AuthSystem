package vn.jason.auth.user;

import java.util.Optional;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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


}
