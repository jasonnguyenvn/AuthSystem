package vn.jason.auth.user;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class UserModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(UserRepository.class)
            .to(UserRepositoryDefaultImpl.class)
            .in(Singleton.class);
    }

}
