package vn.jason.auth.role;

import org.apache.commons.lang3.StringUtils;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Role {
    abstract String name();
    abstract String description();
    
    public static Builder builder() {
        return new AutoValue_Role.Builder();
    }
    
    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);
        public abstract Builder description(String description);
        public abstract Role build();
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name() == null) ? 0 : this.name().hashCode());
        return result;
    }
    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        return StringUtils.equalsIgnoreCase(this.name(), other.name());
    }
    
    

}
