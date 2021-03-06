package vn.jason.auth.role;

import vn.jason.auth.role.masterdata.RoleMasterData;

public enum TEST_ROLES implements RoleMasterData {
    CEO("Higest role in company."),
    MANAGER("The one who manages a group of staff."),
    STAFF("The one who works for the company."),
    USER("Base role off the system.");
    
    private final String description;
    
    TEST_ROLES(String description) {
        this.description = description;
    }

    @Override
    public String description() {
        return this.description;
    }
}
