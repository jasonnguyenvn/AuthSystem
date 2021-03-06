package vn.jason.auth.user;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.google.inject.Guice;


public class UserRepositoryTest {
    private UserRepository repository;
    private Set<User> data;
    
    @Before
    public void setUp() {
        this.repository = Guice.createInjector(new UserModule())
                            .getInstance(UserRepository.class);
    }

    
    @Test
    public void testGetFirstUser() {
        this.prepareData();
        User ceo = User.builder()
                .id(0L)
                .build();
        Optional<User> firstUser= this.repository.get(0L);
        Assert.assertTrue(firstUser.isPresent());
        Assert.assertEquals(ceo, firstUser.get());
    }
    
    @Test
    public void testGetLastUser() {
        this.prepareData();
        User user = User.builder()
                .id(99999L)
                .build();
        Optional<User> lastUser= this.repository.get(99999L);
        Assert.assertTrue(lastUser.isPresent());
        Assert.assertEquals(user, lastUser.get());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddFailBecauseUserManagerNotExist() {
        User fakeManager = User.builder()
                .id(0L)
                .build();
        User user = User.builder()
                .id(1L)
                .manager(fakeManager)
                .build();
        this.repository.add(user);
    }
    
    @Test
    public void testAddNewUserSuccessfully() {
        User user = User.builder()
                .id(0L)
                .build();
        User addedUser = this.repository.add(user).get();
        Assert.assertEquals(user, addedUser);
    }
    
    @Test
    public void testAddExistedUser() {
        this.prepareData();
        User ceo = User.builder()
                .id(0L)
                .build();
        User addedUser = this.repository.add(ceo).get();
        Assert.assertEquals(ceo, addedUser);
        Assert.assertTrue(ceo != addedUser);
    }
    
    @Test
    public void testGetAllWithoutPaging() {
        this.prepareData();
        Set<User> result = this.repository.getAll(1, 100000);
        Assert.assertTrue(result.size()==100000);
    }
    
    @Test
    public void testGetAllWithPaging100Records() {
        this.prepareData();
        for (int i=0;i<100000/100;i++) {
            Set<User> page = this.repository.getAll(1, 100);
            Assert.assertTrue(page.size()==100);
        }
    }
    
    @Test
    public void testUpdateSuccessfully() {
        this.prepareData();
        User ceo = this.repository.get(0).get();
        User user = this.repository.get(2).get();
        Assert.assertNotEquals(ceo, user.manager());
        User oldManager = user.manager();
        
        User clonedUser = User.clone(user);
        Whitebox.setInternalState(clonedUser, "manager", ceo); 

        user = this.repository.get(2).get();
        Assert.assertNotEquals(ceo, user.manager());
        Assert.assertEquals(oldManager, user.manager());
        
        this.repository.update(clonedUser);
        
        user = this.repository.get(2).get();
        ceo = this.repository.get(0).get();
        Assert.assertNotEquals(oldManager, user.manager());
        Assert.assertEquals(ceo, user.manager());
        Assert.assertTrue(ceo.nextLineStaffs().contains(user));
    }
    
    @Test(expected =  IndexOutOfBoundsException.class)
    public void testGetAllThrowExceptionIfPageNumberEqualsZero() {
        this.prepareData();
        this.repository.getAll(0, 100000);
    } 
    
    @Test(expected =  IndexOutOfBoundsException.class)
    public void testGetAllThrowExceptionIfPageNumberOutOfBound() {
        this.prepareData();
        this.repository.getAll(2, 100000);
    }
    

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAllThrowExceptionWhenOutOfRange() {
        this.prepareData();
        this.repository.getAll(1, 100001);
    }
    
    @Test
    public void verifyStaffLevels() {
        this.prepareData();
        User ceo = this.repository.get(0L).get();
        // now we have 99,999 users left.
        // 10 1st level managers (each one manages 90 staffs of next level)
        // 900 2nd level managers (each one manage 110 staffs of next level)
        // (the last 2nd level manager manages 110 + 89 staffs)
        int noOf1stLevel = 10;
        int noOf2ndLevel = 90;
        int noOf3rdLevel = 110;
        for (long i=1, currentId=1; i<=noOf1stLevel; i++) {
            User user = this.repository.get(currentId++).get();
            this.verifyUserLevel(user, ceo);
            
            for (long j=1; j<=noOf2ndLevel;j++) {
                User user2 = this.repository.get(currentId++).get();
                this.verifyUserLevel(user2, user);
                currentId = this.verifyNextLineStaffs(currentId, 1, noOf3rdLevel, user2);

                if (i == noOf1stLevel && j == noOf2ndLevel) {
                    currentId = this.verifyNextLineStaffs(currentId, 1, 89, user2);
                }
            }
        }
    }

    private long verifyNextLineStaffs(long currentId, long start, long end, User manager) {
        for (long m=start; m<=end; m++) {
            User user3 = this.repository.get(currentId++).get();
            
            this.verifyUserLevel(user3, manager);
        }
        return currentId;
    }

    private void verifyUserLevel(User user, User nextLineManager) {
        nextLineManager = this.repository.get(nextLineManager.id()).get();
        Assert.assertEquals(nextLineManager, user.manager());
        Assert.assertTrue(nextLineManager.nextLineStaffs().contains(user));
    }
    

    private void prepareData() {
        this.data = UserFixture.createtCEOAnd99999Users();
        data.stream().forEach(user -> {
            if (Objects.nonNull(user.manager())) {
                if (!this.repository.get(user.manager().id()).isPresent()) {
                    this.repository.add(user.manager());
                }
            }
            this.repository.add(user);
        });
    }
}
