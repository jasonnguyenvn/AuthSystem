package vn.jason.auth.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;


public class UserServiceTest {
    UserService service;
    
    @Before
    public void setUp() {
        this.service = Guice.createInjector(new UserModule())
                            .getInstance(UserService.class);
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
    }

}
