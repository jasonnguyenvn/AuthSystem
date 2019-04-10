package vn.jason.auth.role;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Role {
    abstract String name();
    abstract String description();
    
    static Builder builder() {
        return new AutoValue_Role.Builder();
    }
    
    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder name(String name);
        abstract Builder description(String description);
        abstract Role build();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name() == null) ? 0 : this.name().hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (this.name() == null) {
            if (other.name() != null)
                return false;
        } else if (!this.name().equalsIgnoreCase(other.name()))
            return false;
        return true;
    }
    
    

}
