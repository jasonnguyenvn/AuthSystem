package vn.jason.auth.role;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class RoleModule extends AbstractModule {
    @Override 
    protected void configure() {
        this.bind(RoleRepository.class)
            .to(RoleRepositoryDefaultImpl.class)
            .in(Singleton.class);
    }

}
