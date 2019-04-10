package vn.jason.auth.role;

import java.util.HashSet;
import java.util.Set;

public class SimpleRoleMetaDataProvider implements RoleMetaDataProvider {

    @Override
    public Set<RoleMetaData> get() {
        Set<RoleMetaData> result = new HashSet<>();
        
        result.add(SIMPLE_ROLES.CEO);
        result.add(SIMPLE_ROLES.MANAGER);
        result.add(SIMPLE_ROLES.STAFF);
        result.add(SIMPLE_ROLES.USER);
        
        return result;
    }

}
