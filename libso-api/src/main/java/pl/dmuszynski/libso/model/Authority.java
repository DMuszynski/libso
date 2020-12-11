package pl.dmuszynski.libso.model;

import org.springframework.security.core.GrantedAuthority;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Authority extends AbstractEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private AuthorityType authorityType;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return authorityType.name();
    }
}
