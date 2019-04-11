package vn.jason.auth.user;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;

import vn.jason.auth.permission.Permission;
import vn.jason.auth.role.Role;

@AutoValue
public abstract class User {
    @Nullable abstract Long id();
    abstract Set<Permission> permissions();
    abstract Set<Role> roles();
    abstract Set<User> nextLineStaffs();
    @Nullable abstract User manager();

    public static Builder builder() {
        return new AutoValue_User.Builder()
                .permissions(new HashSet<>())
                .roles(new HashSet<>())
                .nextLineStaffs(new HashSet<>())
                .manager(null);
    }
    
    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(@Nullable Long id);
        public abstract Builder permissions(Set<Permission> permissions);
        public abstract Builder roles(Set<Role> roles);
        public abstract Builder nextLineStaffs(Set<User> nextLineStaffs);
        public abstract Builder manager(@Nullable User user);
        public abstract User build();
    }


    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id() == null) ? 0 : this.id().hashCode());
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
        return Objects.equals(this.id(), other.id());
    }
}

