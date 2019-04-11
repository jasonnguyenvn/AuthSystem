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
    public synchronized Set<User> getAll(int page, int recordsPerPage) {
        if (page<1 || page>data.size()/recordsPerPage || recordsPerPage>data.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.data.entrySet().parallelStream()
                .skip(Integer.valueOf(recordsPerPage*(page-1)).longValue())
                .limit(recordsPerPage)
                .map(Entry::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public synchronized Optional<User> get(long id) {
        return Optional.ofNullable(this.data.get(id));
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
            this.get(user.manager().id())
                .ifPresent(manager -> {
                    manager.nextLineStaffs().add(user);
                });
        }
        return Optional.ofNullable(user);
    }

}
