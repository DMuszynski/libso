package pl.dmuszynski.aquashop.payload.user.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponseDTO {
    private Long id;
    private String type = "Bearer";
    private String accessToken;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponseDTO(String accessToken, Long id, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.id = id;
    }
}
