package vn.jason.auth.role.metadata;

import com.google.inject.AbstractModule;

public class RoleMetaDataModule extends AbstractModule {
    @Override
    protected void configure() {
        this.bind(RoleMetaDataProvider.class)
            .to(SimpleRoleMetaDataProvider.class);
    }
}
