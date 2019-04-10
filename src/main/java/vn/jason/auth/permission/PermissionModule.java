package vn.jason.auth.permission;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;;

public class PermissionModule extends AbstractModule {
    @Override 
    protected void configure() {
      this.bind(PermissionRepository.class)
          .to(PermissionRepositoryDefaultImpl.class)
          .in(Singleton.class);
    }
    
}
