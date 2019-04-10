package vn.jason.auth.role;

class TestRoleMetaDataModule extends RoleMetaDataModule {
    @Override
    protected void configure() {
        this.bind(RoleMetaDataProvider.class)
            .to(TestRoleMetaDataProvider.class);
    }
}