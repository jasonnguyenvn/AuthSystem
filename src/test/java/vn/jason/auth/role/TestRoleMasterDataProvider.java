package vn.jason.auth.role;

import java.util.HashSet;
import java.util.Set;

import vn.jason.auth.role.masterdata.RoleMasterData;
import vn.jason.auth.role.masterdata.RoleMasterDataProvider;

class TestRoleMasterDataProvider implements RoleMasterDataProvider {

    @Override
    public Set<RoleMasterData> get() {
        Set<RoleMasterData> result = new HashSet<>();
        
        result.add(TEST_ROLES.CEO);
        result.add(TEST_ROLES.MANAGER);
        result.add(TEST_ROLES.STAFF);
        result.add(TEST_ROLES.USER);
        
        return result;
    }

}
