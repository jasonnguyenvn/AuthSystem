package vn.jason.auth.role;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import vn.jason.auth.role.metadata.RoleMetaData;
import vn.jason.auth.role.metadata.RoleMetaDataProvider;

@Singleton
public class RoleRepositoryDefaultImpl implements RoleRepository {
    private final Set<Role> dataSet;
    private final Set<RoleMetaData> metaData;
    
    @Inject
    public RoleRepositoryDefaultImpl(RoleMetaDataProvider metaDataProvider) {
        this.metaData = metaDataProvider.get();
        this.dataSet = this.metaData
                            .stream()
                            .map(data -> {
                                return Role.builder()
                                        .name(data.roleName())
                                        .description(data.description())
                                        .build();
                            })
                            .collect(Collectors.toSet());
    }

    @Override
    public Set<Role> getAll() {
        return this.dataSet.stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Role> get(String name) {
        return this.dataSet.stream()
                .filter(role -> StringUtils.equalsIgnoreCase(role.name(), name))
                .findAny();
    }

}
