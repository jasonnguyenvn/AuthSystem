package vn.jason.auth.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.inject.Singleton;

@Singleton
public class UserRepositoryDefaultImpl implements UserRepository {
    private Map<Long, User> data = new HashMap<>();
    
    @Override
    public synchronized Set<User> getAll(long page, long recordsPerPage) {
        if (page<1 || page>data.size()/recordsPerPage || recordsPerPage>data.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.data.entrySet().parallelStream()
                .skip(recordsPerPage*(page-1))
                .limit(recordsPerPage)
                .map(Entry::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public synchronized Optional<User> get(long id) {
        return Optional.ofNullable(this.data.get(id))
                .map(data -> User.clone(data));
        
    }

    @Override
    public synchronized Optional<User> add(User user) {
        if (this.data.containsKey(user.id())) {
            return this.get(user.id());
        }
        this.data.put(user.id(), user);
        if (Objects.nonNull(user.manager())) {
            if (!this.data.containsKey(user.manager().id())) {
                throw new IllegalArgumentException();
            }
            User manager = this.data.get(user.manager().id());
            manager.nextLineStaffs().add(user);
        }
        return this.get(user.id());
    }

    @Override
    public Optional<User> update(User data) {
        this.data.remove(data.id());
        return this.add(data);
    }

}
