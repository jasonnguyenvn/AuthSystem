package vn.jason.auth.role;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Role {
    abstract Long id();
    abstract String name();
    
    static Builder builder() {
        return new AutoValue_Role.Builder();
    }
    
    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder id(Long id);
        abstract Builder name(String name);
        abstract Role build();
    }

}
