package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.User;

import java.time.LocalDateTime;

@Projection(name = "userSettingsView", types = User.class)
public interface UserSettingsView extends EntityView {
    String getUsername();
    String getEmail();
    LocalDateTime getCreationDate();
}
