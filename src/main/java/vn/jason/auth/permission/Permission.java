package vn.jason.auth.permission;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Permission {
    abstract Long id();
    abstract String name();
    
    static Builder builder() {
        return new AutoValue_Permission.Builder();
    }
    
    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder id(Long id);
        abstract Builder name(String name);
        abstract Permission build();
    }
}
