package pl.dmuszynski.libso.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractEntity implements UserDetails {

    private String email;

    private String username;

    private String password;

    private boolean locked;

    private boolean enabled;

    @CreatedDate
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
