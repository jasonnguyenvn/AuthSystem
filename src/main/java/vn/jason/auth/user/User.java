package vn.jason.auth.user;

import java.util.Objects;
import java.util.Set;

import com.google.auto.value.AutoValue;

import vn.jason.auth.permission.Permission;
import vn.jason.auth.role.Role;

@AutoValue
public abstract class User {
    abstract Long id();
    abstract Set<Permission> permissions();
    abstract Set<Role> roles();

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }
    
    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);
        public abstract Builder permissions(Set<Permission> permissions);
        public abstract Builder roles(Set<Role> roles);
        public abstract User build();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id() == null) ? 0 : this.id().hashCode());
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
        User other = (User) obj;
        return Objects.equals(this.id(), other.id());
    }
    
    
    

}

