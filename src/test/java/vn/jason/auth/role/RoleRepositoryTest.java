package vn.jason.auth.role;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;

import vn.jason.auth.role.masterdata.RoleMasterDataModule;

public class RoleRepositoryTest {
    private RoleRepository repository;
    
    @Before
    public void setUp() {
        this.repository = Guice.createInjector(new RoleModule(), new TestRoleMasterDataModule())
                            .getInstance(RoleRepository.class);
    }
    
    @Test
    public void testGetAllShouldProvideAllSystemRolesFromMetaData() {
        Assert.assertFalse(this.repository.getAll().isEmpty());
        Assert.assertTrue(this.repository.getAll().size()==4);
    }
    
    @Test
    public void testGetRoleByNameSuccessfully() {
        Assert.assertTrue(this.repository.get(TEST_ROLES.CEO.name()).isPresent());
        Assert.assertTrue(this.repository.get(TEST_ROLES.STAFF.name()).isPresent());
        Assert.assertTrue(this.repository.get(TEST_ROLES.MANAGER.name()).isPresent());
        Assert.assertTrue(this.repository.get(TEST_ROLES.USER.name()).isPresent());
    }
    
    @Test
    public void testGetRoleByNameReturnNoInstanceWhenPassWrongRoleName() {
        Assert.assertFalse(this.repository.get("INVALID_ROLE_NAME").isPresent());
    }
    
    @Test
    public void testInitMetaFromCurrentProductionRoleMetaDataModule() {
        RoleRepository repository = Guice.createInjector(new RoleModule(), new RoleMasterDataModule())
                .getInstance(RoleRepository.class);
        Assert.assertFalse(repository.getAll().isEmpty());
    }

}
