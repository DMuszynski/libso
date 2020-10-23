package pl.dmuszynski.libso.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
public class User extends AbstractEntity implements UserDetails {

    private String email;
    private String username;
    private String password;
    private boolean isLocked;
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return isEnabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
