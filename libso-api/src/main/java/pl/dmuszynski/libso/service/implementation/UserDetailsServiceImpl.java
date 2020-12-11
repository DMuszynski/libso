package pl.dmuszynski.libso.service.implementation;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import pl.dmuszynski.libso.repository.UserRepository;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
            .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
