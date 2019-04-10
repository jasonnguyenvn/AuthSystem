package vn.jason.auth.role;

import org.junit.Assert;
import org.junit.Test;

import vn.jason.auth.permission.Permission;


public class RoleTest {
    @Test
    public void testInstanceEqualsItself() {
        Role role = Role.builder()
                .name("A")
                .description("test")
                .build();
        Assert.assertTrue(role.equals(role));
    }
    

    @Test
    public void testTwoInstancesEqualSuccessfully() {
        Role role1 = Role.builder()
                .name("A")
                .description("test")
                .build();
        Role role2 = Role.builder()
                .name("A")
                .description("test222")
                .build();
        Assert.assertTrue(role1.equals(role2));
    }
    
    @Test
    public void testTwoInstancesEqualWithNameIgnoreCaseSuccessfully() {
        Role role1 = Role.builder()
                .name("Aa")
                .description("test")
                .build();
        Role role2 = Role.builder()
                .name("aa")
                .description("test222")
                .build();
        Assert.assertTrue(role1.equals(role2));
    }
    
    @Test
    public void testTwoInstancesNotEqualSuccessfully() {
        Role role1 = Role.builder()
                .name("A")
                .description("test")
                .build();
        Role role2 = Role.builder()
                .name("b")
                .description("test222")
                .build();
        Assert.assertFalse(role1.equals(role2));
    }
    
    @Test
    public void testInstanceNotEqualNull() {
        Role role = Role.builder()
                .name("A")
                .description("test")
                .build();
        Assert.assertFalse(role.equals(null));
    }
    
    @Test
    public void testInstanceNotEqualInstanceOfAnotherClass() {
        Role role = Role.builder()
                .name("A")
                .description("test")
                .build();
        Permission permission = Permission.builder()
                                            .name("A")
                                            .build();
        Assert.assertFalse(role.equals(permission));
    }

}
