package vn.jason.auth.role.masterdata;

import com.google.inject.AbstractModule;

public class RoleMasterDataModule extends AbstractModule {
    @Override
    protected void configure() {
        this.bind(RoleMasterDataProvider.class)
            .to(SimpleRoleMasterDataProvider.class);
    }
}
