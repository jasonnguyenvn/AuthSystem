package vn.jason.auth.permission;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;

public class PermissionRepositoryTest {
    private PermissionRepository repository;

    @Before
    public void setUp() {
        this.repository = Guice.createInjector(new PermissionModule()).getInstance(PermissionRepository.class);
    }

    @Test
    public void testShouldReturnEmptyWhenGetAllWithoutInitData() {
        Assert.assertTrue(this.repository.getAll().isEmpty());
    }

    @Test
    public void testGetAllSuccessfully() {
        this.repository.add("A");
        this.repository.add("B");
        Assert.assertTrue(this.repository.getAll().size() == 2);
    }

    @Test
    public void testGetByNameReturnNoPermissionInstance() {
        Assert.assertFalse(this.repository.getByName("A").isPresent());
    }

    @Test
    public void testGetByNameReturnPermissionSuccessfully() {
        this.repository.add("A");
        Assert.assertTrue(this.repository.getByName("A").isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowExceptionWhenAddExistedPermission() {
        this.repository.add("A");
        Assert.assertTrue(this.repository.getByName("A").isPresent());
        this.repository.add("A");
    }

    @Test
    public void testFindNoResult() {
        this.repository.add("A");
        this.repository.add("B");
        Assert.assertTrue(this.repository.find("C").isEmpty());
    }

    @Test
    public void testFindSucessfully() {
        this.repository.add("A");
        this.repository.add("AA");
        this.repository.add("B");
        Assert.assertTrue(this.repository.find("a").size() == 2);
        Assert.assertTrue(this.repository.find("aa").size() == 1);
        Assert.assertTrue(this.repository.find("A").size() == 2);
        Assert.assertTrue(this.repository.find("aA").size() == 1);
        Assert.assertTrue(this.repository.find("Aa").size() == 1);
        Assert.assertTrue(this.repository.find("AA").size() == 1);
    }

}
