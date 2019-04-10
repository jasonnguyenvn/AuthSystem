package vn.jason.auth.role;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import vn.jason.auth.role.masterdata.RoleMasterData;
import vn.jason.auth.role.masterdata.RoleMasterDataProvider;

@Singleton
public class RoleRepositoryDefaultImpl implements RoleRepository {
    private final Set<Role> dataSet;
    private final Set<RoleMasterData> masterData;
    
    @Inject
    public RoleRepositoryDefaultImpl(RoleMasterDataProvider metaDataProvider) {
        this.masterData = metaDataProvider.get();
        this.dataSet = this.masterData
                            .stream()
                            .map(data -> {
                                return Role.builder()
                                        .name(data.name())
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
