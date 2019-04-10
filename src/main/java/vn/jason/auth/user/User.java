package vn.jason.auth.user;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class User {
    abstract Long id();
    abstract String name();

    static Builder builder() {
        return new AutoValue_User.Builder();
    }
    
    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder id(Long id);
        abstract Builder name(String name);
        abstract User build();
    }

}

