package vn.jason.auth.user;

import java.util.HashSet;
import java.util.Set;

public final class UserFixture {
    private UserFixture() {}
    
    public static Set<User> createtCEOAnd99999Users() {
        Set<User> datasource = new HashSet<>();
        // first init CEO
        User ceo = User.builder()
                .id(0L)
                .build();
        datasource.add(ceo);
        
        return create99999Users(datasource, ceo);
    }

    public static Set<User> create99999Users(Set<User> datasource, User ceo) {
        // now we have 99,999 users left.
        // 10 1st level managers (each one manages 90 staffs of next level)
        // 900 2nd level managers (each one manage 110 staffs of next level)
        // (the last 2nd level manager manages 110 + 89 staffs)
        // 99,000+9 normal staff
        long noOf1stLevel = 10;
        long noOf2ndLevel = 90;
        long noOf3rdLevel = 110;
        for (long i=1, currentId=1; i<=noOf1stLevel; i++) {
            User user = initUser(datasource, currentId++, ceo);
            
            for (long j=1; j<=noOf2ndLevel;j++) {
                User user2 = initUser(datasource, currentId++, user);
                currentId = initNextLineStaffs(datasource, currentId, 1, noOf3rdLevel, user2);
                if (i == noOf1stLevel && j == noOf2ndLevel) {
                    currentId = initNextLineStaffs(datasource, currentId, 1, 89, user2);
                }
            }
        }
        
        return datasource;
    }

    private static long initNextLineStaffs(Set<User> datasource, long currentId, long start, long end, User manager) {
        for (long m=start; m<=end; m++) {
            initUser(datasource, currentId++, manager);
        }
        return currentId;
    }

    private static User initUser(Set<User> datasource, long currentId, User manager) {
        User user = User.builder()
                .id(currentId)
                .manager(manager)
                .build();
        datasource.add(user);
        return user;
    }


}
