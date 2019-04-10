package vn.jason.auth.role.masterdata;

import java.util.HashSet;
import java.util.Set;

public class SimpleRoleMasterDataProvider implements RoleMasterDataProvider {

    @Override
    public Set<RoleMasterData> get() {
        Set<RoleMasterData> result = new HashSet<>();
        
        result.add(SIMPLE_ROLES.CEO);
        result.add(SIMPLE_ROLES.MANAGER);
        result.add(SIMPLE_ROLES.STAFF);
        result.add(SIMPLE_ROLES.USER);
        
        return result;
    }

}
