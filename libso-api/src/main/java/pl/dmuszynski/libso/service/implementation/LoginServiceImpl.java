package pl.dmuszynski.libso.service.implementation;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import pl.dmuszynski.libso.payload.dto.response.JwtResponseDTO;
import pl.dmuszynski.libso.service.LoginService;
import pl.dmuszynski.libso.security.JwtUtils;
import pl.dmuszynski.libso.model.User;

import java.util.stream.Collectors;
import java.util.Set;

@RequiredArgsConstructor
@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponseDTO signIn(String username, String password) {
        final Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final User userPrincipal = (User) authentication.getPrincipal();
        final Set<String> authorities = userPrincipal.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet());

        return JwtResponseDTO.builder()
            .id(userPrincipal.getId())
            .email(userPrincipal.getEmail())
            .username(userPrincipal.getUsername())
            .accessToken(this.jwtUtils.generateJwtToken(authentication))
            .authorities(authorities)
            .build();
    }
}
