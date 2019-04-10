package vn.jason.auth.role.masterdata;

public enum SIMPLE_ROLES implements RoleMasterData {
    CEO("Higest role in company."),
    MANAGER("The one who manages a group of staff."),
    STAFF("The one who works for the company."),
    USER("Base role off the system.");
    
    private final String description;
    
    SIMPLE_ROLES(String description) {
        this.description = description;
    }

    @Override
    public String description() {
        return this.description;
    }
}
