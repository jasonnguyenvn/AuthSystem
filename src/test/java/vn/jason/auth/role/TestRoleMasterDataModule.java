package vn.jason.auth.role;

import vn.jason.auth.role.masterdata.RoleMasterDataModule;
import vn.jason.auth.role.masterdata.RoleMasterDataProvider;

public class TestRoleMasterDataModule extends RoleMasterDataModule {
    @Override
    protected void configure() {
        this.bind(RoleMasterDataProvider.class)
            .to(TestRoleMasterDataProvider.class);
    }
}