package vn.jason.auth.role;

import vn.jason.auth.role.metadata.RoleMetaDataModule;
import vn.jason.auth.role.metadata.RoleMetaDataProvider;

class TestRoleMetaDataModule extends RoleMetaDataModule {
    @Override
    protected void configure() {
        this.bind(RoleMetaDataProvider.class)
            .to(TestRoleMetaDataProvider.class);
    }
}