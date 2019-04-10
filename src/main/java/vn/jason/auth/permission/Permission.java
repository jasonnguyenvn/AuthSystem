package vn.jason.auth.permission;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Permission {
    abstract String name();
    
    public static Builder builder() {
        return new AutoValue_Permission.Builder();
    }
    
    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);
        public abstract Permission build();
    }
}
