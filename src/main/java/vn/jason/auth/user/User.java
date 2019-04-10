package vn.jason.auth.user;

import java.util.Set;

import com.google.auto.value.AutoValue;

import vn.jason.auth.permission.Permission;
import vn.jason.auth.role.Role;

@AutoValue
public abstract class User {
    abstract Long id();
    abstract String username();
    abstract Set<Permission> permissions();
    abstract Set<Role> roles();

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }
    
    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);
        public abstract Builder username(String username);
        public abstract Builder permissions(Set<Permission> permissions);
        public abstract Builder roles(Set<Role> roles);
        public abstract User build();
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id() == null) ? 0 : this.id().hashCode());
        result = prime * result + ((this.username() == null) ? 0 : this.username().hashCode());
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
        User other = (User) obj;
        if (this.id() == null) {
            if (other.id() != null)
                return false;
        } else if (!this.id().equals(other.id()))
            return false;
        if (this.username() == null) {
            if (other.username() != null)
                return false;
        } else if (!this.username().equals(other.username()))
            return false;
        return true;
    }
    
    

}

