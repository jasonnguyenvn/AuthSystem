package vn.jason.auth.user;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import vn.jason.auth.permission.Permission;
import vn.jason.auth.permission.PermissionModule;
import vn.jason.auth.permission.PermissionRepository;


public class UserServiceTest {
    private Injector injector;
    private UserService service;

    private PermissionRepository permissionRepository;
    
    @Before
    public void setUp() {
        this.injector = Guice.createInjector(new UserModule(), new PermissionModule());
        this.service = this.injector.getInstance(UserService.class);
        this.permissionRepository = this.injector.getInstance(PermissionRepository.class);
    }

    
    @Test
    public void shouldReturnCEOId() {
        Assert.assertTrue(this.service.ceoID()>-1);
    }
    
    @Test
    public void shouldCreateCEOSuccessfully() {
        Assert.assertFalse(this.service.getCEO().isPresent());
        User ceo = this.service.createCEO().get();
        Assert.assertTrue(this.service.ceoID() == ceo.id());
        Assert.assertNull(ceo.manager());
        Assert.assertTrue(this.service.list(1, 1).size()==1);
        Assert.assertEquals(ceo, this.service.getCEO().get());
    }
    
    @Test
    public void shouldCreateUserSuccessfully() {
        User ceo = this.service.createCEO().get();
        User user = this.service.createUser(ceo).get();
        Assert.assertTrue(this.service.list(1, 2).size()==2);
        Assert.assertEquals(ceo, user.manager());

        User user2 = this.service.createUser(ceo).get();
        Assert.assertEquals(ceo, user2.manager());
        Assert.assertNotEquals(user, user2);
        
        Assert.assertEquals(user2, this.service.get(user2.id()).get());
    }
    
    @Test
    public void shouldUpdatePermissionsSuccessfully() {
        this.preparePermisisons();
        
        User ceo = this.service.createCEO().get();
        Assert.assertTrue(ceo.permissions().isEmpty());
        
        Set<Permission> ceoPermissions = this.createPermissionGroupAB();
        
        this.service.updatePermission(ceo.id(), ceoPermissions).get();
        
        ceo = this.service.getCEO().get();
        Assert.assertTrue(ceo.permissions().size()==2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetFullPermssionsFailBecauseInputWrongUserId() {
        this.preparePermisisons();
        this.service.getFullPermissions(99);
    }
    
    @Test
    public void testGetFullPermissions() {
        this.preparePermisisons();
        
        User ceo = this.service.createCEO().get();
        Assert.assertTrue(ceo.permissions().isEmpty());
        
        Set<Permission> ceoPermissions = this.createPermissionGroupAB();
        
        this.service.updatePermission(ceo.id(), ceoPermissions).get();
        
        ceo = this.service.getCEO().get();
        Assert.assertTrue(ceo.permissions().size()==2);
        Assert.assertTrue(this.service.getFullPermissions(ceo.id()).size()==2);
        

        User user = this.service.createUser(ceo).get();
        Set<Permission> userPermissions = this.createPermissionGroupACD();
        
        this.service.updatePermission(user.id(), userPermissions);
        
        user = this.service.get(user.id()).get();
        Assert.assertTrue(user.permissions().size()==3);
        Assert.assertTrue(this.service.getFullPermissions(user.id()).size()==3);
        Assert.assertTrue(this.service.getFullPermissions(ceo.id()).size()==4);
    }
    
    private Set<Permission> createPermissionGroupAB() {
        Set<Permission> permissionGroup = new HashSet<>();
        permissionGroup.add(this.permissionRepository.getByName("A").get());
        permissionGroup.add(this.permissionRepository.getByName("b").get());
        
        return permissionGroup;
    }
    
    private Set<Permission> createPermissionGroupACD() {
        Set<Permission> permissionGroup = new HashSet<>();
        permissionGroup.add(this.permissionRepository.getByName("A").get());
        permissionGroup.add(this.permissionRepository.getByName("c").get());
        permissionGroup.add(this.permissionRepository.getByName("D").get());
        
        return permissionGroup;
    }
    
    private void preparePermisisons() {
        this.permissionRepository.add("A");
        this.permissionRepository.add("B");
        this.permissionRepository.add("C");
        this.permissionRepository.add("D");
        this.permissionRepository.add("E");
        this.permissionRepository.add("F");
    }

}
