package vn.jason.auth.role;

import com.google.inject.AbstractModule;

class TestRoleMetaDataModule extends AbstractModule {
    @Override
    protected void configure() {
        this.bind(RoleMetaDataProvider.class)
            .to(TestRoleMetaDataProvider.class);
    }
}