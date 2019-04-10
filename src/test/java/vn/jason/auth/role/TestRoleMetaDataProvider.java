package vn.jason.auth.role;

import java.util.HashSet;
import java.util.Set;

import vn.jason.auth.role.metadata.RoleMetaData;
import vn.jason.auth.role.metadata.RoleMetaDataProvider;

class TestRoleMetaDataProvider implements RoleMetaDataProvider {

    @Override
    public Set<RoleMetaData> get() {
        Set<RoleMetaData> result = new HashSet<>();
        
        result.add(TEST_ROLES.CEO);
        result.add(TEST_ROLES.MANAGER);
        result.add(TEST_ROLES.STAFF);
        result.add(TEST_ROLES.USER);
        
        return result;
    }

}
