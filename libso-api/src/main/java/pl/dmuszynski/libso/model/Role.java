package pl.dmuszynski.libso.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Role extends AbstractEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Override
    public String getAuthority() {
        return roleType.name();
    }
}
