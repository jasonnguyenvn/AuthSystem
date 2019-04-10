package vn.jason.auth.role.metadata;

public enum SIMPLE_ROLES implements RoleMetaData {
    CEO("CEO", "Higest role in company."),
    MANAGER("manager", "The one who manages a group of staff."),
    STAFF("staff", "The one who works for the company."),
    USER("user", "Base role off the system.");
    
    private final String name;
    private final String description;
    
    SIMPLE_ROLES(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    @Override
    public String roleName() {
        return this.name;
    }

    @Override
    public String description() {
        return this.description;
    }

}
